import { useState, useEffect } from 'react';
import TodoList from './components/TodoList';
import './App.css';

function App() {
  const [theme, setTheme] = useState('light');
  const [tasks, setTasks] = useState([]);

  const toggleTheme = () => {
    setTheme(prev => (prev === 'light' ? 'dark' : 'light'));
  };

  useEffect(() => {
    document.body.className = '';
    document.body.classList.add(theme);
  }, [theme]);

  useEffect(() => {
  fetch('http://localhost:8081/api/tasks')
  .then(res => res.json())
  .then(data => setTasks(Array.isArray(data) ? data : data.tasks || []))
  .catch(err => console.error('Failed to fetch tasks:', err));
  }, []);

  return (
    <div className="App">
      <header className="top-header">
        <h1>Hello Smita!</h1>
        <div className="top-right">
          <span className="pomodoro">POMODORO</span>
          <span className="theme-toggle" onClick={toggleTheme}>
            {theme === 'light' ? '🌙' : '☀️'}
          </span>
        </div>
      </header>
      <div className="todo-container">
        <TodoList title="Today Todo" tasks={tasks} setTasks={setTasks} />
        <TodoList title="Todo" tasks={tasks} setTasks={setTasks} />
        <TodoList title="Habit" tasks={tasks} setTasks={setTasks} />
      </div>
    </div>
  );
}

export default App;
