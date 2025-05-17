import { useState } from 'react';
import api from '../api';

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await api.post('/auth/login', { user_name: username, password: password });
            alert('Успешный вход');
        } catch (error) {
            alert('Ошибка входа');
        }
    };

    return (
        <form onSubmit={handleLogin}>
            <h2>Вход</h2>
            <input value={username} onChange={e => setUsername(e.target.value)} placeholder="Логин" />
            <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Пароль" />
            <button type="submit">Войти</button>
        </form>
    );
}
