package pl.javorus.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javorus.habittracker.model.Tag;
import pl.javorus.habittracker.repository.TagRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag getTag(String tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    public List<Tag> getTagsByUserId(String userId) {
        return tagRepository.findAllByUserId(userId);
    }

    public Tag updateTag(String tagId, Tag updatedTag) {
        Tag existingTag = tagRepository.findById(tagId).orElse(null);
        if (existingTag != null) {
            existingTag.setName(updatedTag.getName());
            return tagRepository.save(existingTag);
        }
        return null;
    }

    public void deleteTag(String tagId) {
        tagRepository.deleteById(tagId);
    }
}