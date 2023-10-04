import React, { useState, useEffect } from "react";
import { useAuth } from "./authContext";
import { createTaskApi, getAllTasksForUserIdApi } from "./api/TaskApi";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import TagComponent from "./TagComponent";

export default function TaskComponent() {
  const [taskTitle, setTaskTitle] = useState("");
  const [taskDescription, setTaskDescription] = useState("");
  const [taskDate, setTaskDate] = useState("");
  const [taskDuration, setTaskDuration] = useState("");
  const [taskTags, setTaskTags] = useState("");
  const [tasks, setTasks] = useState([]);

  const authContext = useAuth();

  useEffect(() => {
    retrieveTasks();
  }, []);

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

  const handleTagsChange = (e) => {
    setTaskTags(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const task = {
      title: taskTitle,
      description: taskDescription,
      startDate: taskDate,
      duration: taskDuration,
      tags: taskTags.split(","),
    };

    if (authContext.isAuthenticated) {
      createTaskApi(authContext.user, task)
        .then((res) => {
          retrieveTasks();
        })
        .catch((e) => console.log(e));
    }

    setTaskTitle("");
    setTaskDescription("");
    setTaskDate("");
    setTaskDuration("");
    setTaskTags("");
  };

  function retrieveTasks() {
    getAllTasksForUserIdApi(authContext.user)
      .then((response) => {
        setTasks(response.data);
      })
      .catch((error) => console.log(error));
  }

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
        <TagComponent tags={taskTags} handleTagsChange={handleTagsChange} />
        <Button type="submit">Add Task</Button>
      </form>

      <h2>Tasks</h2>
      <ul>
        {tasks.map((task) => (
          <li key={task.taskId}>
            <Card style={{ width: '18rem' }}>
              <Card.Body>
                <Card.Title>{task.title}</Card.Title>
                <Card.Text>{task.description}</Card.Text>
              </Card.Body>
            </Card>
          </li>
        ))}
      </ul>
    </div>
  );
}