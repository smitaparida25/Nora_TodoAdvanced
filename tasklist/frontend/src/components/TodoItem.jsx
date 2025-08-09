import React from 'react';
import './TodoList.css'; // or its own CSS if you prefer

function TodoItem({ task }) {
  return (
    <li className="task-item">
      <input type="checkbox" checked={task.completed} readOnly />
      <span>{task.title}</span>
    </li>
  );
}

export default TodoItem;
