import React from 'react';
import './App.css';
import Home from './Home';
import NewStudent from './NewStudent';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LectureList from './LectureList';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/lectures' exact={true} element={<LectureList/>}/>
        <Route path='/students/new' exact={true} element={<NewStudent/>}/>
      </Routes>
    </Router>
  )
}

export default App;