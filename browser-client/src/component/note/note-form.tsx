import {Alert, Button, Card, Container, Form, InputGroup} from "react-bootstrap";
import {ApiConstants, ApiService} from "../../service/api-service";
import {FormEvent, useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {PriorityService} from "../../service/priority-service";
import {SectionService} from "../../service/section-service";

export interface Section {
    id: number,
    name: string
}

const NoteForm = () => {
    const params = useParams();

    const [title, setTitle] = useState<string>('');
    const [description, setDescription] = useState<string>('');
    const [priority, setPriority] = useState<number>(PriorityService.getPriorities()[0].id);
    const [section, setSection] = useState<number>(SectionService.getSections()[0].id);
    const [validated, setValidated] = useState<boolean>(false);
    const [errors, setErrors] = useState<{ errorMessages: string }[]>([]);

    const navigate = useNavigate();

    const submit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setValidated(true);

        if (event.currentTarget.checkValidity()) {
            if (params.id !== undefined) {
                updateNote();
            } else {
                createNote();
            }
        }
    }

    const createNote = () => {
        ApiService
            .getInstance()
            .post(ApiConstants.ENDPOINT_NOTES, {
                id: null,
                title: title,
                description: description,
                priority: priority,
                section: section
            })
            .then(() => {
                navigate('/notes');
            })
            .catch(error => {
                setErrors(error.response.data)
            });
    }

    const updateNote = () => {
        ApiService
            .getInstance()
            .put(ApiConstants.ENDPOINT_NOTE.replace("%id", params.id as string), {
                id: params.id,
                title: title,
                description: description,
                priority: priority,
                section: section
            })
            .then(() => {
                navigate('/notes');
            })
            .catch(error => {
                setErrors(error.response.data)
            });
    }

    useEffect(() => {
        if (params.id !== undefined) {
            ApiService
                .getInstance()
                .get(ApiConstants.ENDPOINT_NOTE.replace("%id", params.id as string))
                .then(response => {
                    setTitle(response.data.title);
                    setDescription(response.data.description);
                    setPriority(response.data.priority);
                    setSection(response.data.section);
                });
        } else {
            setTitle('');
            setDescription('');
            setPriority(PriorityService.getPriorities()[0].id);
            setSection(SectionService.getSections()[0].id);
        }
    }, [params]);

    const getInputValue = (e: FormEvent<HTMLInputElement>) => {
        const target = e.target as HTMLInputElement;
        return target.value;
    }

    const getSelectValue = (e: FormEvent<HTMLSelectElement>) => {
        const target = e.target as HTMLSelectElement;
        return parseInt(target.value);
    }

    return (
        <Container className={'d-flex justify-content-center align-items-center h-100'}>
            <Card className={'m-3 form-card'}>
                <Card.Header className={'d-flex align-items-center justify-content-between'}>
                    {
                        params.id !== undefined
                            ? 'Изменение заметки'
                            : 'Создание заметки'
                    }
                </Card.Header>
                <Card.Body>
                    <Form noValidate validated={validated} onSubmit={submit}>
                        {
                            errors.length
                                ? <Alert variant="danger" onClose={() => setErrors([])} dismissible>
                                    {errors.map((error: { errorMessages: string }, index) => <p
                                        key={'error-' + index}>{error.errorMessages}</p>)}
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
                                <InputGroup.Text id="basic-addon1">Приоритет</InputGroup.Text>
                                <Form.Select
                                    required
                                    aria-label="Default select example"
                                    value={priority}
                                    onChange={e => setPriority(getSelectValue(e as FormEvent<HTMLSelectElement>))}
                                >
                                    {
                                        PriorityService
                                            .getPriorities()
                                            .map(prior => <option key={'priority-' + prior.id} value={prior.id}>{prior.name}</option>)
                                    }
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    Приоритет нужно выбрать
                                </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                        <Form.Group>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Раздел</InputGroup.Text>
                                <Form.Select
                                    required
                                    value={section}
                                    aria-label="Default select example"
                                    onChange={e => setSection(getSelectValue(e as FormEvent<HTMLSelectElement>))}
                                >
                                    {
                                        SectionService
                                            .getSections()
                                            .map(sect => <option key={'section-' + sect.id} value={sect.id}>{sect.name}</option>)
                                    }
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    Раздел нужно выбрать
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

export default NoteForm;