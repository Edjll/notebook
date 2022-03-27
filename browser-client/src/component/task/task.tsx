import {ButtonGroup, Card, Dropdown, DropdownButton, Form, ProgressBar} from "react-bootstrap";
import {useEffect, useState} from "react";
import './task.scss';
import {ApiConstants, ApiService} from "../../service/api-service";
import {useNavigate} from "react-router-dom";

export interface TaskProps {
    body: TaskBody,
    deleteHandler: Function
}

export interface TaskBody {
    id: number,
    completed: boolean,
    title: string,
    description: string,
    startDate: Date,
    endDate: Date
}

const Task = ({body, deleteHandler}: TaskProps) => {
    const [progress, setProgress] = useState<number>(Math.floor((new Date().getTime() - body.startDate.getTime()) / (body.endDate.getTime() - body.startDate.getTime()) * 100));
    const [completed, setCompleted] = useState<boolean>(body.completed);
    const [progressEnabled, setProgressEnabled] = useState<boolean>(new Date().getTime() >= body.startDate.getTime());
    const navigate = useNavigate();

    useEffect(() => {
        setCompleted(body.completed);
        setProgress(Math.min(Math.floor((new Date().getTime() - body.startDate.getTime()) / (body.endDate.getTime() - body.startDate.getTime()) * 100), 100));

        let interval: NodeJS.Timer;
        const timeout = setTimeout(() => {
            if (new Date().getTime() >= body.endDate.getTime()) {
                clearTimeout(timeout);
                clearInterval(interval);
                setProgressEnabled(false);
            } else {
                setProgressEnabled(true);
                interval = setInterval(() => {
                    const prog = (new Date().getTime() - body.startDate.getTime()) / (body.endDate.getTime() - body.startDate.getTime()) * 100;
                    if (prog >= 100) {
                        clearInterval(interval);
                        setProgress(100);
                    } else {
                        setProgress(Math.max(Math.floor((prog)), 0));
                    }
                }, 1000);
            }
        }, progressEnabled ? 0 : body.startDate.getTime() - new Date().getTime());
        return () => {
            clearTimeout(timeout);
            clearInterval(interval);
        }
    }, [body]);

    const updateStatus = () => {
        ApiService
            .getInstance()
            .put(ApiConstants.ENDPOINT_TASKS_UPDATE_STATUS.replace("%id", body.id.toString()), {
                completed: !completed
            })
            .then(() => {
                setCompleted(!completed)
            })
    }

    const deleteTask = () => {
        ApiService
            .getInstance()
            .delete(ApiConstants.ENDPOINT_TASKS_DELETE.replace("%id", body.id.toString()))
            .then(() => {
                deleteHandler(body.id);
            })
    }

    return (
        <Card className={'m-3 task' + (completed ? ' task_completed' : '')}>
            <Card.Header className={'d-flex align-items-center justify-content-between'}>
                <div className={'d-flex align-items-center'}>
                    <Form.Check type="switch" checked={completed} onChange={updateStatus}/>
                    <span>{body.title}</span>
                </div>
                <DropdownButton id="dropdown-item-button" title="•••" as={ButtonGroup} size={'sm'} align={'end'}
                                className={'ml-auto'}>
                    <Dropdown.Item as="button" onClick={() => navigate('/tasks/update/' + body.id)}>Изменить</Dropdown.Item>
                    <Dropdown.Item as="button" onClick={deleteTask}>Удалить</Dropdown.Item>
                </DropdownButton>
            </Card.Header>
            <Card.Body>
                <Card.Text>
                    {body.description}
                </Card.Text>
            </Card.Body>
            <Card.Footer className={'d-flex justify-content-between align-items-center'}>
                <small className={'flex-shrink-0'}>С {body.startDate.toLocaleString()}</small>
                {
                    progressEnabled
                        ? <ProgressBar animated
                                       now={progress}
                                       label={`${progress}%`}
                                       className={'w-100 mx-3'}
                        />
                        : null
                }
                <small className={'flex-shrink-0'}>До {body.endDate.toLocaleString()}</small>
            </Card.Footer>
        </Card>
    );
}

export default Task;