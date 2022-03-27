package ru.sstu.notepad.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sstu.notepad.model.priority.PriorityBody;
import ru.sstu.notepad.service.PriorityService;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.PRIORITIES)
@AllArgsConstructor
public class PriorityController {

    private final PriorityService service;

    @GetMapping
    public List<PriorityBody> getAll() {
        return service.getAll();
    }
}
