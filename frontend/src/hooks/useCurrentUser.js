import React, { createContext, useContext, useState, useEffect } from 'react';
import api from '../api';

const UserContext = createContext({
    user: null,
    refreshUser: () => Promise.resolve(),
});

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(() => {
        const savedUser = localStorage.getItem('user');
        return savedUser ? JSON.parse(savedUser) : null;
    });

    const refreshUser = async () => {
        try {
            const res = await api.get('/auth/me');
            setUser(res.data.data);
            localStorage.setItem('user', JSON.stringify(res.data.data));
            return res.data.data;
        } catch {
            setUser(null);
            localStorage.removeItem('user');
            return null;
        }
    };

    // Если пользователя нет, то пытаемся получить с сервера
    useEffect(() => {
        if (!user) {
            refreshUser();
        }
    }, [user]);

    return (
        <UserContext.Provider value={{ user, refreshUser }}>
            {children}
        </UserContext.Provider>
    );
};

export const useUser = () => useContext(UserContext);
