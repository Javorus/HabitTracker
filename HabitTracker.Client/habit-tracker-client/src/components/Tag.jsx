import React from "react";

export default function TagComponent({ tags, handleTagsChange }) {
  return (
    <div>
      <label htmlFor="taskTags">Tags:</label>
      <input
        type="text"
        id="taskTags"
        value={tags}
        onChange={handleTagsChange}
      />
    </div>
  );
}
