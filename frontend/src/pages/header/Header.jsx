import {Link} from 'react-router-dom';
import {Profile} from "./Profile";
import {useUser} from "../../hooks/useCurrentUser";
import {Button} from "react-bootstrap";
import React from "react";

export default function Header() {
    const {user} = useUser(); // получаем текущего пользователя из контекста

    return (<header className="d-flex justify-content-between align-items-center p-3 has-background-fourthly">
        <div className="d-flex gap-3 align-items-center">
            <Link to="/home" className="text-decoration-none text-primary">
                Отзывы
            </Link>
        </div>

        <div className="is-flex" style={{gap: '10px'}}>
            {user && (<Button variant="primary" href="/create-review">
                Создать отзыв
            </Button>)}
            <Profile/>
        </div>
    </header>);
}
