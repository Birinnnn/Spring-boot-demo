import React, { useEffect, useState } from 'react';
import {Link, useNavigate} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';

const NewStudent = () => {
    const initialForm = {
        firstName: '',
        lastName: '',
        email: '',
        gpa: '',
        hobby: ''
    };

    const [student, setStudent] = useState(initialForm);
    const navigate = useNavigate();

    useEffect(() => {

    }, []);

    const handleChange = (event) => {
        const {name, value} = event.target;
        setStudent({...student, [name]: value});
    }

    const handleSubmit = async (event) => {
      event.preventDefault();
      const {firstName, lastName, email, gpa, hobby } = student;

      const response = await fetch('/v1/student', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(student),
      });

      if (response.ok) {
        navigate('/students');
      } else {
        console.log('Error', response.statusText);
      }
    };



    const title = <h2>Add Student</h2>;
    return (
        <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                    <Label for="firstName">First Name</Label>
                    <Input type="text" name="firstName" id="firstName" value={student.firstName || ''}
                        onChange={handleChange} autoComplete="firstName"/>
                    </FormGroup>
                    <FormGroup>
                    <Label for="lastName">Last Name</Label>
                    <Input type="text" name="lastName" id="lastName" value={student.lastName || ''}
                        onChange={handleChange} autoComplete="lastName"/>
                    </FormGroup>
                    <FormGroup>
                    <Label for="email">Email</Label>
                    <Input type="text" name="email" id="email" value={student.email || ''}
                        onChange={handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                    <Label for="gpa">GPA</Label>
                    <Input type="text" name="gpa" id="gpa" value={student.gpa || ''}
                        onChange={handleChange} autoComplete="gpa"/>
                    </FormGroup>
                    <FormGroup>
                    <Label for="hobby">Hobby</Label>
                    <Input type="text" name="hobby" id="hobby" value={student.hobby || ''}
                        onChange={handleChange} autoComplete="hobby"/>
                    </FormGroup>
                    <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link} to="/students">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    )
};

export default NewStudent;