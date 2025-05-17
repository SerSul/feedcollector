// src/hooks/useCurrentUser.ts
import { useEffect, useState } from 'react';
import api from '../api';

export function useCurrentUser() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        api.get('/me')
            .then(res => setUser(res.data))
            .catch(() => setUser(null));
    }, []);

    return user;
}
