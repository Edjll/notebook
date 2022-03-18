package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sstu.notepad.model.TagBody;
import ru.sstu.notepad.service.TagService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.TAGS)
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public void create(@RequestBody @Valid TagBody body) {
        tagService.create(body);
    }

    @GetMapping("")
    public List<TagBody> getAll() {
        return tagService.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid TagBody body) {
        tagService.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }
}
