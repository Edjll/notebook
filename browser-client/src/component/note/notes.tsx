import {Button, Container, FormControl, InputGroup} from "react-bootstrap";
import React, {FormEvent, useEffect, useState} from "react";
import Note, {NoteBody} from "./note";
import {useLocation} from "react-router-dom";
import {ApiConstants, ApiService} from "../../service/api-service";
import searchIcon from '../../image/search.png';
import './notes.scss';

function useQuery() {
    const {search} = useLocation();

    return React.useMemo(() => new URLSearchParams(search), [search]);
}

const Notes = () => {
    const [notes, setNotes] = useState<NoteBody[]>([]);
    const [searchDate, setSearchDate] = useState<string>('');
    const query = useQuery();

    const convertToNoteBody = (data: NoteBody[]) => {
        return data.map((task) => {
            return {
                ...task,
                createdDate: new Date(task.createdDate),
                modifiedDate: !!task.modifiedDate ? new Date(task.modifiedDate) : null
            }
        });
    }

    useEffect(() => {
        search();
    }, [query]);

    const deleteHandler = (id: number) => {
        const newNotes = [...notes].filter(task => task.id !== id);
        setNotes(newNotes);
    }

    const getInputValue = (e: FormEvent<HTMLInputElement>) => {
        const target = e.target as HTMLInputElement;
        return target.value;
    }

    const search = () => {
        ApiService
            .getInstance()
            .get(ApiConstants.ENDPOINT_NOTES, {
                params: {
                    sectionId: query.get("sectionId"),
                    searchDate: searchDate
                }
            })
            .then(response => {
                setNotes(convertToNoteBody(response.data));
            });
    }

    return (
        <Container className={'notes'}>
            <InputGroup className="m-3 search-form">
                <InputGroup.Text><img src={searchIcon} alt={'seach'} className={'search-icon'}/></InputGroup.Text>
                <FormControl
                    type={'date'}
                    onInput={(e) => setSearchDate(getInputValue(e as FormEvent<HTMLInputElement>))}
                    value={searchDate}
                />
                <Button onClick={search}>Найти</Button>
            </InputGroup>
            {
                notes.map(note => <Note body={note} key={'note-' + note.id} deleteHandler={deleteHandler}/>)
            }
        </Container>
    );
}

export default Notes;