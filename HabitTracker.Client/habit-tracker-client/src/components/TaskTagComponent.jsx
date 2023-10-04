import React, { useState } from 'react';
import TagComponent from './TagComponent';
import TaskComponent from './TaskComponent';

function ParentComponent() {
  const [tags, setTags] = useState([]);

  const handleTagsUpdate = (updatedTags) => {
    setTags(updatedTags);
  };

  return (
    <div>
      <TagComponent onTagsUpdate={handleTagsUpdate} />
      <TaskComponent tags={tags} />
    </div>
  );
}

export default ParentComponent;
