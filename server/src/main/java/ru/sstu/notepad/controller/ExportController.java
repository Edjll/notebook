package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sstu.notepad.service.ExportService;

import java.io.IOException;

@RestController
@RequestMapping(ApiConstants.EXPORT)
@RequiredArgsConstructor
public class ExportController {
    private final ExportService exportService;
    @GetMapping
    public String export() throws IOException {
        return exportService.export();
    }
}
