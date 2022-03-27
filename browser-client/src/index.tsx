import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.scss';
import App from './app';
import AuthService from "./service/auth-service";
import {ApiService} from "./service/api-service";
import {PriorityService} from "./service/priority-service";
import {SectionService} from "./service/section-service";

const app = () => {
    ReactDOM.render(
        <React.StrictMode>
            <App/>
        </React.StrictMode>,
        document.getElementById("root")
    );
}

AuthService.init(() => {
    ApiService.init();
    if (AuthService.isAuthenticated()) {
        PriorityService.init()
            .then(() => SectionService.init())
            .then(() => app());
    } else {
        app();
    }
});