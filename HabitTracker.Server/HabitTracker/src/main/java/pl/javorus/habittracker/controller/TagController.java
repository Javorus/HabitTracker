package pl.javorus.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javorus.habittracker.model.Tag;
import pl.javorus.habittracker.service.TagService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @GetMapping("/{tagId}")
    public Tag getTag(@PathVariable String tagId) {
        return tagService.getTag(tagId);
    }

    @GetMapping("/user/{userId}")
    public List<Tag> getTagsByUser(@PathVariable String userId) {
        return tagService.getTagsByUser(userId);
    }

    @PutMapping("/{tagId}")
    public Tag updateTag(@PathVariable String tagId, @RequestBody Tag tag) {
        return tagService.updateTag(tagId, tag);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable String tagId) {
        tagService.deleteTag(tagId);
    }
}