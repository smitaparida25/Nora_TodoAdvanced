import React, { useState } from 'react';
import './TodoList.css';

function TodoList({ title }) {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');

  const handleAddTask = () => {
    if (newTask.trim() === '') return;

    const taskToAdd = {
      id: Date.now(),
      title: newTask,
      completed: false
    };

    setTasks((prev) => [...prev, taskToAdd]);
    setNewTask('');
  };

  const handleToggle = (id) => {
    setTasks((prev) =>
      prev.map((task) =>
        task.id === id ? { ...task, completed: !task.completed } : task
      )
    );
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleAddTask();
    }
  };

  return (
    <div className="todo-column">
      <h2>{title}</h2>
      <ul className="task-list">
        {tasks.map((task) => (
          <li key={task.id} className="task-item">
            <input
              type="checkbox"
              checked={task.completed}
              onChange={() => handleToggle(task.id)}
            />
            <span style={{ textDecoration: task.completed ? 'line-through' : 'none' }}>
              {task.title}
            </span>
          </li>
        ))}
        <li className="task-item">
          <input
            type="text"
            placeholder="Add task..."
            value={newTask}
            onChange={(e) => setNewTask(e.target.value)}
            onKeyDown={handleKeyDown}
            style={{
                border: 'none',
                borderBottom: '1px solid #ccc',
                outline: 'none',
                padding: '4px',
                fontSize: '16px',
                background: 'transparent',
                width: '110px'
              }}
          />
          <span
            style={{
              color: 'black',
              cursor: 'pointer',
              userSelect: 'none'
            }}
            onClick={handleAddTask}
          >
            + Add task
          </span>

        </li>
      </ul>
    </div>
  );
}

export default TodoList;
