import {Container} from "react-bootstrap";
import Task, {TaskBody} from "./task";
import {ApiService} from "../../service/api-service";
import {useEffect, useState} from "react";

export interface TasksProps {
    endpoint: string
}

const Tasks = ({endpoint}: TasksProps) => {
    const [tasks, setTasks] = useState<TaskBody[]>([]);

    const convertToTaskBody = (data: TaskBody[]) => {
        return data.map((task) => {
            return {
                ...task,
                startDate: new Date(task.startDate),
                endDate: new Date(task.endDate)
            }
        });
    }

    useEffect(() => {
        ApiService
            .getInstance()
            .get(endpoint)
            .then(response => {
                setTasks(convertToTaskBody(response.data));
            });
    }, [endpoint]);

    const deleteHandler = (id: number) => {
        const newTasks = [...tasks].filter(task => task.id !== id);
        setTasks(newTasks);
    }

    return (
        <Container>
            {
                tasks.map((task, index) => <Task body={task} key={'task-' + index} deleteHandler={deleteHandler}/>)
            }
        </Container>
    );
}

export default Tasks;