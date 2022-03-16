package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sstu.notepad.model.TagBody;
import ru.sstu.notepad.service.TagService;

@RestController
@RequestMapping(ApiConstants.TAGS)
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public void create(@RequestBody TagBody body) {
        tagService.create(body);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody TagBody body) {
        tagService.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }
}
