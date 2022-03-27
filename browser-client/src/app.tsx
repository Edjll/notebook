import React from 'react';
import './app.scss';
import Header from "./component/header/header";
import Sidebar from "./component/sidebar/sidebar";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Tasks from "./component/task/tasks";
import AuthService from "./service/auth-service";
import Login from "./component/login/login";
import TaskForm from "./component/task/task-form";
import {ApiConstants} from "./service/api-service";
import backgroundSrc from './image/background.jpg';
import NoteForm from "./component/note/note-form";
import Notes from "./component/note/notes";

const App = () => {

    if (!AuthService.isAuthenticated() && window.location.pathname !== '/') {
        window.location.pathname = '/';
    }

    return (
        <BrowserRouter>
            <div className={'app'}>
                <Header/>
                <main className={'app__main'}>
                    <div className={'app__background'}>
                        <img src={backgroundSrc} alt="background" className={'app__background__image'}/>
                    </div>
                    {
                        AuthService.isAuthenticated()
                            ? <Sidebar/>
                            : null
                    }
                    <div className={'app__main__container'}>
                        <Routes>
                            <Route path={'/notes/create'} element={<NoteForm/>}/>
                            <Route path={'/notes/update/:id'} element={<NoteForm/>}/>
                            <Route path={'/notes'} element={<Notes/>}/>
                            <Route path={'/tasks/actual'} element={<Tasks endpoint={ApiConstants.ENDPOINT_TASKS_ACTUAL}/>}/>
                            <Route path={'/tasks/completed'} element={<Tasks endpoint={ApiConstants.ENDPOINT_TASKS_COMPLETED}/>}/>
                            <Route path={'/tasks/expired'} element={<Tasks endpoint={ApiConstants.ENDPOINT_TASKS_EXPIRED}/>}/>
                            <Route path={'/tasks/create'} element={<TaskForm/>}/>
                            <Route path={'/tasks/update/:id'} element={<TaskForm/>}/>
                            <Route path={'/'} element={
                                AuthService.isAuthenticated()
                                    ?   <Tasks endpoint={ApiConstants.ENDPOINT_TASKS_ACTUAL}/>
                                    :   <Login/>
                            }/>
                        </Routes>
                    </div>
                </main>
            </div>
        </BrowserRouter>
    );
}

export default App;
