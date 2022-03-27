package ru.sstu.notepad.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sstu.notepad.model.section.SectionBody;
import ru.sstu.notepad.service.SectionService;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SECTIONS)
@AllArgsConstructor
public class SectionController {

    private final SectionService service;

    @GetMapping
    public List<SectionBody> getAll() {
        return service.getAll();
    }
}
