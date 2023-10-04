import React, { useState, useEffect } from "react";
import { useAuth } from "./authContext";
import { getAllTasksForUserIdApi } from "./api/TaskApi";
import Card from 'react-bootstrap/Card';
import TaskForm from "./TaskForm";
import { getAllTagsForUserIdApi } from "./api/TagApi";

export default function TaskComponent() {
  const [tasks, setTasks] = useState([]);
  const [tags, setTags] = useState([]);
  const authContext = useAuth();

  useEffect(() => {
    retrieveTasks();
    retrieveTags();
  }, []);

  const retrieveTasks = () => {
    getAllTasksForUserIdApi(authContext.user)
      .then((response) => {
        setTasks(response.data);
      })
      .catch((error) => console.log(error));
  };

  const retrieveTags = () => {
    getAllTagsForUserIdApi(authContext.user)
      .then((response) => {
        setTags(response.data);
      })
      .catch((error) => console.log(error));
  };

  const handleTaskCreated = (newTask) => {
    setTasks([...tasks, newTask]);
  };

  return (
    <div>
      <h2>Add Task</h2>
      <TaskForm onTaskCreated={handleTaskCreated} tags={tags} />

      <h2>Tasks</h2>
      <ul>
        {tasks.map((task) => (
          <li key={task.taskId}>
            <Card style={{ width: '18rem' }}>
              <Card.Body>
                <Card.Title>{task.title}</Card.Title>
                <Card.Text>{task.description}</Card.Text>
                <Card.Text>
                  Tags: {task.tags.map((tag) => tag.name).join(", ")}
                </Card.Text>
              </Card.Body>
            </Card>
          </li>
        ))}
      </ul>
    </div>
  );
}
