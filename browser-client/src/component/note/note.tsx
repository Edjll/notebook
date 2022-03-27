import {ButtonGroup, Card, Dropdown, DropdownButton} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import './note.scss';
import {ApiConstants, ApiService} from "../../service/api-service";

export interface NoteProps {
    body: NoteBody,
    deleteHandler: Function
}

export interface NoteBody {
    id: number;
    title: string;
    description: string;
    createdDate: Date;
    modifiedDate: Date | null;
    priority: number;
    priorityBody: PriorityBody;
    section: number;
    sectionBody: SectionBody;
}

export interface PriorityBody {
    id: number,
    name: string,
    className: string
}

export interface SectionBody {
    id: number,
    name: string
}

const Note = ({body, deleteHandler}: NoteProps) => {
    const navigate = useNavigate();

    const deleteTask = () => {
        ApiService
            .getInstance()
            .delete(ApiConstants.ENDPOINT_NOTE.replace("%id", body.id.toString()))
            .then(() => {
                deleteHandler(body.id);
            })
    }

    return (
        <Card className={`m-3 note note_${body.priorityBody.className}`}>
            <Card.Header className={'d-flex align-items-center justify-content-between'}>
                <span>{body.title}</span>
                <DropdownButton id="dropdown-item-button" title="•••" as={ButtonGroup} size={'sm'} align={'end'}
                                className={'ml-auto'}>
                    <Dropdown.Item as="button"
                                   onClick={() => navigate('/notes/update/' + body.id)}>Изменить</Dropdown.Item>
                    <Dropdown.Item as="button" onClick={deleteTask}>Удалить</Dropdown.Item>
                </DropdownButton>
            </Card.Header>
            <Card.Body>
                <Card.Text>
                    {body.description}
                </Card.Text>
            </Card.Body>
            <Card.Footer className={'d-flex justify-content-between align-items-center'}>
                <small className={'flex-shrink-0'}>Создана {body.createdDate.toLocaleString()}</small>
                {
                    !!body.modifiedDate
                        ? <small className={'flex-shrink-0'}>Изменена {body.modifiedDate.toLocaleString()}</small>
                        : null
                }
            </Card.Footer>
        </Card>
    );
}

export default Note;