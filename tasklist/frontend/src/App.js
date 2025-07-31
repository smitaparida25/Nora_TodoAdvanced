import { useState, useEffect } from 'react';
import TodoList from './components/TodoList';
import './App.css';

function App() {
  const [theme, setTheme] = useState('light');

  const toggleTheme = () => {
    setTheme(prev => (prev === 'light' ? 'dark' : 'light'));
  };

  useEffect(() => {
    document.body.className = ''; // Clear old class
    document.body.classList.add(theme);
  }, [theme]);

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
        <TodoList title="Today Todo" />
        <TodoList title="Todo" />
        <TodoList title="Habit" />
      </div>
    </div>
  );
}

export default App;
