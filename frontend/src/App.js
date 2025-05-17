import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          {/* Другие страницы — например, главная */}
          <Route path="/" element={<h1>Главная</h1>} />
        </Routes>
      </Router>
  );
}

export default App;
