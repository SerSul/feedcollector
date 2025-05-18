import React from 'react';
import {Navigate} from 'react-router-dom';
import {useUser} from "./useCurrentUser";

export default function PrivateRoute({children}) {
    const {user} = useUser();

    if (!user) {
        return <Navigate to="/login" replace />;
    }

    return children;
}
