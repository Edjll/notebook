import {Alert, Button, Card, Container, FormControl, InputGroup} from "react-bootstrap";
import './login.scss';
import AuthService from "../../service/auth-service";
import {FormEvent, useState} from "react";

const Login = () => {
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [error, setError] = useState<string>('');

    const submit = () => {
        AuthService
            .login(username, password)
            .then(() => {
                window.location.pathname = '/';
            })
            .catch(() => {
                setError('Неверный логин или пароль')
            });
    }

    const getInputValue = (e: FormEvent<HTMLInputElement>) => {
        const target = e.target as HTMLInputElement;
        return target.value;
    }

    return (
        <Container className={'d-flex justify-content-center align-items-center h-100'}>
            <Card className={'login'}>
                <Card.Header as={'h5'} className={'text-center'}>Вход в систему</Card.Header>
                <Card.Body>
                    {
                        error.length
                            ?   <Alert variant="danger" onClose={() => setError('')} dismissible>
                                    {error}
                                </Alert>
                            :   null
                    }
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Логин</InputGroup.Text>
                        <FormControl onInput={(e) => setUsername(getInputValue(e as FormEvent<HTMLInputElement>))}/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Пароль</InputGroup.Text>
                        <FormControl type={'password'} onInput={(e) => setPassword(getInputValue(e as FormEvent<HTMLInputElement>))}/>
                    </InputGroup>
                    <Button className={'w-100'} onClick={() => submit()}>Войти</Button>
                </Card.Body>
            </Card>
        </Container>
    );
}

export default Login;