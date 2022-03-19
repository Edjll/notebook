package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.sstu.notepad.model.record.RecordBody;
import ru.sstu.notepad.service.RecordService;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    @GetMapping(ApiConstants.RECORDS_GET_ALL)
    public List<RecordBody> getAll() {
        return recordService.getAll();
    }

    @PostMapping
    public void save(@RequestBody @Valid RecordBody body) {
        recordService.save(body);
    }

    @GetMapping(ApiConstants.RECORDS_FIND)
    public List<RecordBody> find(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime startDate,
                                 @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
       return recordService.findByDate(startDate, endDate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recordService.delete(id);
    }
}
