import './sidebar.scss';
import React from "react";
import SidebarRow from "./sidebar-row";
import {Accordion, Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import AuthService from "../../service/auth-service";
import ExportService from "../../service/export-service";

const Sidebar = () => {

    return (
        <div className={'sidebar'}>
            <Accordion alwaysOpen={true} defaultActiveKey={['0', '1', '2']}>
                <Accordion.Item eventKey="0">
                    <Accordion.Header>Задачи</Accordion.Header>
                    <Accordion.Body className={'show'}>
                        <SidebarRow>
                            <Link to={'/tasks/create'}>Создать задачу</Link>
                        </SidebarRow>
                        <SidebarRow>
                            <Link to={'/tasks/actual'}>Актуальные задачи</Link>
                        </SidebarRow>
                        <SidebarRow>
                            <Link to={'/tasks/completed'}>Выполненные задачи</Link>
                        </SidebarRow>
                        <SidebarRow>
                            <Link to={'/tasks/expired'}>Просроченные задачи</Link>
                        </SidebarRow>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header>Заметки</Accordion.Header>
                    <Accordion.Body>
                        <SidebarRow>
                            <Link to={'/notes/create'}>Создать заметку</Link>
                        </SidebarRow>
                        <SidebarRow>
                            <Link to={'/notes'}>Все заметки</Link>
                        </SidebarRow>
                        <SidebarRow>
                            <Link to={'/notes?sectionId=1'}>Важные заметки</Link>
                        </SidebarRow>
                        <SidebarRow>
                            <Link to={'/notes?sectionId=2'}>Семейные заметки</Link>
                        </SidebarRow>
                        <SidebarRow>
                            <Link to={'/notes?sectionId=3'}>Скрытые заметки</Link>
                        </SidebarRow>
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="2">
                    <Accordion.Header>Экспорт</Accordion.Header>
                    <Accordion.Body>
                        <SidebarRow>
                            <span onClick={() => ExportService.exportTasks()}>Вырузить задачи</span>
                        </SidebarRow>
                        <SidebarRow>
                            <span onClick={() => ExportService.exportNotes()}>Вырузить заметки</span>
                        </SidebarRow>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
            <Button variant="danger" className={'m-3 mt-auto'} onClick={() => AuthService.logout()}>Выйти</Button>
        </div>
    );
}

export default Sidebar;