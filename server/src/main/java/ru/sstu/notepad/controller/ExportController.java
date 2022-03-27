package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sstu.notepad.service.ExportService;

@RestController
@RequestMapping(ApiConstants.EXPORT)
@RequiredArgsConstructor
public class ExportController {

    private final ExportService exportService;

    @GetMapping(ApiConstants.EXPORT_NOTES)
    public ResponseEntity<?> exportNotes() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(exportService.exportNotes());
    }

    @GetMapping(ApiConstants.EXPORT_TASKS)
    public ResponseEntity<?> exportTasks() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(exportService.exportTasks());
    }
}
