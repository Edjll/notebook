import {Alert, Button, Card, Container, Form, InputGroup} from "react-bootstrap";
import {ApiConstants, ApiService} from "../../service/api-service";
import {FormEvent, useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

const TaskForm = () => {
    const params = useParams();

    const [title, setTitle] = useState<string>('');
    const [description, setDescription] = useState<string>('');
    const [startDate, setStartDate] = useState<string>('');
    const [endDate, setEndDate] = useState<string>('');
    const [validated, setValidated] = useState<boolean>(false);
    const [errors, setErrors] = useState<{ errorMessages: string }[]>([]);

    const navigate = useNavigate();

    const submit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setValidated(true);

        if (event.currentTarget.checkValidity()) {
            ApiService
                .getInstance()
                .post(ApiConstants.ENDPOINT_TASKS, {
                    id: params.id !== undefined ? params.id : null,
                    title: title,
                    description: description,
                    startDate: startDate,
                    endDate: endDate,
                    statusActive: true
                })
                .then(() => {
                    navigate('/tasks/actual');
                })
                .catch(error => {
                    setErrors(error.response.data);
                });
        }
    }


    useEffect(() => {
        if (params.id !== undefined) {
            ApiService
                .getInstance()
                .get(ApiConstants.ENDPOINT_TASK.replace("%id", params.id as string))
                .then(response => {
                    setTitle(response.data.title);
                    setDescription(response.data.description);
                    setStartDate(response.data.startDate);
                    setEndDate(response.data.endDate);
                });
        } else {
            setTitle('');
            setDescription('');
            setStartDate('');
            setEndDate('');
        }
    }, [params]);

    const getInputValue = (e: FormEvent<HTMLInputElement>) => {
        const target = e.target as HTMLInputElement;
        return target.value;
    }

    return (
        <Container className={'d-flex justify-content-center align-items-center h-100'}>
            <Card className={'m-3 form-card'}>
                <Card.Header className={'d-flex align-items-center justify-content-between'}>
                    {
                        params.id !== undefined
                            ? 'Изменение задачи'
                            : 'Создание задачи'
                    }
                </Card.Header>
                <Card.Body>
                    <Form noValidate validated={validated} onSubmit={submit}>
                        {
                            errors.length
                                ? <Alert variant="danger" onClose={() => setErrors([])} dismissible>
                                    { errors.map(error => <p>{error.errorMessages}</p>) }
                                </Alert>
                                : null
                        }
                        <Form.Group>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Заголовок</InputGroup.Text>
                                <Form.Control
                                    required
                                    onInput={(e) => setTitle(getInputValue(e as FormEvent<HTMLInputElement>))}
                                    value={title}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Заголовок нужно заполнить
                                </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                        <Form.Group>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Описание</InputGroup.Text>
                                <Form.Control
                                    required
                                    onInput={(e) => setDescription(getInputValue(e as FormEvent<HTMLInputElement>))}
                                    value={description}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Описание нужно заполнить
                                </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                        <Form.Group>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Дата начала</InputGroup.Text>
                                <Form.Control
                                    required
                                    type={'datetime-local'}
                                    onInput={(e) => setStartDate(getInputValue(e as FormEvent<HTMLInputElement>))}
                                    value={startDate}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Дату начала нужно заполнить
                                </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                        <Form.Group>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Дата окончания</InputGroup.Text>
                                <Form.Control
                                    required
                                    type={'datetime-local'}
                                    onInput={(e) => setEndDate(getInputValue(e as FormEvent<HTMLInputElement>))}
                                    value={endDate}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Дату окончания нужно заполнить
                                </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                        <Button className={'w-100'} type={'submit'}>
                            {
                                params.id !== undefined
                                    ? 'Изменить'
                                    : 'Создать'
                            }
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </Container>
    );
}

export default TaskForm;