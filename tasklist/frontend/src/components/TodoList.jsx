import './TodoList.css';

function TodoList({ title }) {
  return (
    <div className="todo-column">
      <h2>{title}</h2>
      <ul className="task-list">
        <li className="task-item">
          <input type="checkbox" />
          <span>Journal</span>
        </li>
        <li className="task-item">
          <input type="checkbox" />
          <span>Journal</span>
        </li>
        <li className="task-item">
          <input type="checkbox" />
          <span>Journal</span>
        </li>
        <li className="task-item">
          <input type="checkbox" />
          <span>Journal</span>
        </li>
        <li className="task-item">
          <input type="checkbox" />
          <span>Journal</span>
        </li>
        <li className="add-task">＋ Add Task</li>
      </ul>
    </div>
  );
}

export default TodoList;