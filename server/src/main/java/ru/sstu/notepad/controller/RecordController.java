package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sstu.notepad.model.record.RecordBody;
import ru.sstu.notepad.model.record.RecordBodyToSave;
import ru.sstu.notepad.service.RecordService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.RECORDS)
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public List<RecordBody> getAllActiveRecords() {
        return recordService.getAllActiveRecords();
    }

    @GetMapping("/all")
    public List<RecordBody> getAll() {
        return recordService.getAll();
    }

    @PostMapping
    public void save(@RequestBody @Valid RecordBodyToSave body) {
        recordService.save(body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recordService.delete(id);
    }
}
