package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sstu.notepad.model.RecordBody;
import ru.sstu.notepad.service.RecordService;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiConstants.RECORDS)
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    public void create(@RequestBody @Valid RecordBody body) {
        recordService.create(body);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RecordBody body) {
        recordService.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recordService.delete(id);
    }
}
