package ru.sstu.notepad.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.sstu.notepad.model.note.NoteBody;
import ru.sstu.notepad.service.NoteService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.NOTES)
@AllArgsConstructor
public class NoteController {

    private final NoteService service;

    @PostMapping
    public void create(@RequestBody NoteBody body) {
        service.create(body);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody NoteBody body) {
        service.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<NoteBody> getAll(@RequestParam(required = false) Long sectionId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date searchDate) {
        return service.getAll(sectionId, searchDate);
    }

    @GetMapping("/{id}")
    public NoteBody getOne(@PathVariable Long id) {
        return service.getById(id);
    }
}
