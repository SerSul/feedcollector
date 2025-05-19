import axios from 'axios';

const api = axios.create({
    baseURL: '/api', // Относительный путь для проксирования через Nginx
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json'
    }
});

export default api;