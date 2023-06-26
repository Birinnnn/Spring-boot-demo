import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const LectureList = () => {
  const [lectures, setLectures] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('/api/lectures')
      .then(response => response.json())
      .then(data => {
        const lecturesArray = data._embedded.lectures;

        Promise.all(
          lecturesArray.map(lecture =>
            Promise.all([
              fetch(lecture._links.instructor.href)
                .then(response => response.json()),
              fetch(lecture._links.students.href)
                .then(response => response.json())
            ])
            .then(([instructorData, studentsData]) => {
              lecture.instructor = `${instructorData.firstName} ${instructorData.lastName}`;
              lecture.students = studentsData._embedded.students;
              lecture.id = lecture._links.self.href.split('/').pop();
              return lecture;
            })
          )
        )
        .then(updatedLectures => {
          setLectures(updatedLectures);
          setLoading(false);
        });
      })
      .catch(error => {
        console.error('Error:', error);
        setLoading(false);
      });
  }, []);

  const remove = async (id) => {
    console.log('remove', id);
    await fetch(`/api/lectures/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedLectures = [...lectures].filter(i => i.id !== id);
      setLectures(updatedLectures);
    });
  };

  if (loading) {
    return <p>Loading...</p>;
  }

  const lectureList = lectures.map(lecture => {
    const instructorName = lecture.instructor ? `${lecture.instructor}` : 'No Instructor found';
    const studentNumber = lecture.students ? lecture.students.length : 0;

    return (
      <tr key={lecture.id}>
        <td style={{ whiteSpace: 'nowrap' }}>{lecture.title}</td>
        <td>{studentNumber}</td>
        <td>{instructorName}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/lectures/" + lecture.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => remove(lecture.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    );
  });

  return (
    <div>
      <AppNavbar />
      <Container fluid>
        <div className="float-end">
          <Button color="primary" tag={Link} to="/students/new">Add Student</Button>
          <Button color="primary" tag={Link} to="/instructors/new">Add Instructor</Button>
          <Button color="success" tag={Link} to="/lectures/new">Add Lecture</Button>

        </div>
        <h3>My Lecture</h3>
        <Table className="mt-4">
          <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="20%">Students</th>
              <th>Instructor</th>
              <th width="10%">Actions</th>
            </tr>
          </thead>
          <tbody>
            {lectureList}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default LectureList
