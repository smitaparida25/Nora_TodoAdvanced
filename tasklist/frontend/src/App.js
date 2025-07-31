import TodoList from './components/TodoList';

function App() {
  return (
    <div className="App">
    <header className="top-header">
      <h1>Hello Smita!</h1>
      <div className="top-right">
        <span className="pomodoro">POMODORO</span>
        <span className="theme-toggle">🌙☆</span>
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
