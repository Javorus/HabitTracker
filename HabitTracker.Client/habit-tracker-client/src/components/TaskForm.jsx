import React, { useState } from "react";
import { useAuth } from "./authContext";
import { createTaskApi } from "./api/TaskApi";

export default function TaskForm({ onTaskCreated, tags }) {
  const [taskTitle, setTaskTitle] = useState("");
  const [taskDescription, setTaskDescription] = useState("");
  const [taskDate, setTaskDate] = useState("");
  const [taskDuration, setTaskDuration] = useState("");
  const [selectedTags, setSelectedTags] = useState([]);
  const authContext = useAuth();

  const handleTitleChange = (e) => {
    setTaskTitle(e.target.value);
  };

  const handleDescriptionChange = (e) => {
    setTaskDescription(e.target.value);
  };

  const handleDateChange = (e) => {
    setTaskDate(e.target.value);
  };

  const handleDurationChange = (e) => {
    setTaskDuration(e.target.value);
  };

  const handleTagSelection = (tagId) => {
    if (selectedTags.includes(tagId)) {
      setSelectedTags(selectedTags.filter((id) => id !== tagId));
    } else {
      setSelectedTags([...selectedTags, tagId]);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const task = {
      title: taskTitle,
      description: taskDescription,
      startDate: taskDate,
      duration: taskDuration,
      tagIds: selectedTags,
    };

      if (authContext.isAuthenticated) {
          console.log(task);
      createTaskApi(authContext.user, task)
        .then((res) => {
          onTaskCreated(res.data);
        })
        .catch((e) => console.log(e));
    }

    setTaskTitle("");
    setTaskDescription("");
    setTaskDate("");
    setTaskDuration("");
    setSelectedTags([]);
  };

  return (
    <div>
      <h2>Add Task</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="taskTitle">Title:</label>
          <input
            type="text"
            id="taskTitle"
            value={taskTitle}
            onChange={handleTitleChange}
          />
        </div>
        <div>
          <label htmlFor="taskDescription">Description:</label>
          <input
            type="text"
            id="taskDescription"
            value={taskDescription}
            onChange={handleDescriptionChange}
          />
        </div>
        <div>
          <label htmlFor="taskDate">Date:</label>
          <input
            type="date"
            id="taskDate"
            value={taskDate}
            onChange={handleDateChange}
          />
        </div>
        <div>
          <label htmlFor="taskDuration">Duration:</label>
          <input
            type="time"
            id="taskDuration"
            value={taskDuration}
            onChange={handleDurationChange}
          />
        </div>
        <div>
          <h4>Tags:</h4>
          {tags.map((tag) => (
            <label key={tag.tagId}>
              <input
                type="checkbox"
                checked={selectedTags.includes(tag.tagId)}
                onChange={() => handleTagSelection(tag.tagId)}
              />
              {tag.name}
            </label>
          ))}
        </div>
        <button type="submit">Add Task</button>
      </form>
    </div>
  );
}
