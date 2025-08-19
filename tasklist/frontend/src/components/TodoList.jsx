import React, { useState, useEffect } from 'react';
import './TodoList.css';

function TodoList({ title }) {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');
  const backendUrl = title.toLowerCase() === 'habit'
    ? 'http://localhost:8081/api/habits'
    : 'http://localhost:8081/api/tasks';

const apiPrefix = title.toLowerCase() === 'habit' ? 'habits' : 'tasks';

useEffect(() => {
  const listType = title.toLowerCase();
  const url = listType === 'habit'
    ? `http://localhost:8081/api/habits`
    : `http://localhost:8081/api/tasks/by-list?listType=${listType}`;

  fetch(url)
    .then(res => res.json())
    .then(data => {
      if (Array.isArray(data)) {
        setTasks(data);
      } else if (data.tasks) {
        setTasks(data.tasks);   // backend gave { tasks: [...] }
      } else if (data.habit) {
        setTasks(data.habit);  // backend gave { habits: [...] }
      } else {
        setTasks([]);           // fallback, avoid crash
      }
    })
    .catch(err => console.error('Failed to load tasks:', err));
}, [title]);




  const handleAddTask = () => {
      if (newTask.trim() === '') return;

      const taskToAdd = {
        title: newTask,
        isCompleted: false,
        ...(title.toLowerCase() === 'habit' ? {} : { date: new Date().toISOString().split('T')[0] }),
        listType: title.toLowerCase()
      };


      const listType = title.toLowerCase();
      fetch(backendUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(taskToAdd)
      })
        .then(res => {
          if (!res.ok) throw new Error('Failed to create task');
          return res.json();
        })
        .then(createdTask => {
          setTasks(prev => [...prev, createdTask]); // Add backend task
          setNewTask('');
        })
        .catch(err => {
          console.error(err);
          alert('Error adding task');
        });
    };


  const handleToggle = (id) => {
    const task = tasks.find(t => t.id === id);
    const updatedTask = { ...task, isCompleted: !task.isCompleted };

    fetch(`${backendUrl}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedTask),
    })
      .then(res => {
        if (!res.ok) throw new Error('Failed to update task');
        return res.json();
      })
      .then(() => {
        setTasks(prev =>
          prev.map(t => (t.id === id ? updatedTask : t))
        );
      })
      .catch(err => {
        console.error(err);
        alert('Error updating task');
      });
  };


  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleAddTask();
    }
  };

  const handleDelete = (id) =>{
      fetch(`${backendUrl}/${id}`, {method : 'DELETE'})
      .then(res => {
          if(!res.ok) throw new Error('Failed to delete task');
          setTasks(prev => prev.filter(t => t.id !== id));
          })
      .catch(err => console.error(err));
  };

  return (
    <div className="todo-column">
      <h2>{title}</h2>
      <ul className="task-list">
        {tasks.map((task) => (
          <li key={task.id} className="task-item">
            <input
              type="checkbox"
              checked={task.isCompleted}
              onChange={() => handleToggle(task.id)}
            />
            <span style={{ textDecoration: task.isCompleted ? 'line-through' : 'none' }}>
              {task.title}
            </span>
            <button
              onClick={() => handleDelete(task.id)}
              style={{
                border: 'none',
                background: 'transparent',
                padding: 0,
                cursor: 'pointer',
                fontSize: '24px'
              }}
            >
            🚮
            </button>

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
