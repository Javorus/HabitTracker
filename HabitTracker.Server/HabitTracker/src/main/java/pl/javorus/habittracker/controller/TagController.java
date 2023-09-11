package pl.javorus.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javorus.habittracker.dto.TagRequest;
import pl.javorus.habittracker.model.Tag;
import pl.javorus.habittracker.model.User;
import pl.javorus.habittracker.service.TagService;
import pl.javorus.habittracker.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;
    private final UserService userService;

    @Autowired
    public TagController(TagService tagService, UserService userService) {
        this.tagService = tagService;
        this.userService = userService;
    }

    @PostMapping("/user/{userId}")
    public Tag createTag(@RequestBody TagRequest tagRequest, @PathVariable String userId) {
        User user = userService.getUser(userId);
        Tag tag = new Tag(
                UUID.randomUUID().toString(),
                user,
                tagRequest.name()

        );

        return tagService.createTag(tag);
    }

    @GetMapping("/{tagId}")
    public Tag getTag(@PathVariable String tagId) {
        return tagService.getTag(tagId);
    }

    @GetMapping("/user/{userId}")
    public List<Tag> getTagsByUserId(@PathVariable String userId) {
        return tagService.getTagsByUserId(userId);
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