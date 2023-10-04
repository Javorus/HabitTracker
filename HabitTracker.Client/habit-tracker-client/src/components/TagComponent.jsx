import React, { useState, useEffect } from "react";
import { useAuth } from "./authContext";
import { getAllTagsForUserIdApi } from "./api/TagApi";

export default function TagComponent({ onTagsUpdate }) {
  const [tags, setTags] = useState([]);
  const [selectedTags, setSelectedTags] = useState([]);
  const authContext = useAuth();

  useEffect(() => {
    retrieveTags();
  }, []);

  function retrieveTags() {
    getAllTagsForUserIdApi(authContext.user)
      .then((response) => {
        setTags(response.data);
      })
      .catch((error) => console.log(error));
  }

  const handleTagSelection = (tagId) => {
    if (selectedTags.includes(tagId)) {
      setSelectedTags(selectedTags.filter((id) => id !== tagId));
    } else {
      setSelectedTags([...selectedTags, tagId]);
    }
  };

  const handleSaveTags = () => {
    const selectedTagObjects = tags.filter((tag) =>
      selectedTags.includes(tag.tagId)
    );
    onTagsUpdate(selectedTagObjects);
  };

  return (
    <div>
      <h2>Tags</h2>
      <ul>
        {tags.map((tag) => (
          <li key={tag.tagId}>
            <label>
              <input
                type="checkbox"
                checked={selectedTags.includes(tag.tagId)}
                onChange={() => handleTagSelection(tag.tagId)}
              />
              {tag.name}
            </label>
          </li>
        ))}
      </ul>
      <button onClick={handleSaveTags}>Save Tags</button>
    </div>
  );
}
