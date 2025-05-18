import React, {useState} from 'react';
import {useUser} from "../../hooks/useCurrentUser";
import {Modal, Button} from "react-bootstrap";
import api from "../../api";

export const Profile = () => {
    const {user} = useUser();
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleLogout = async () => {
        try {
            await api.post("/auth/logout");
            localStorage.removeItem('user'); // очистить кеш
            window.location.reload();
        } catch (error) {
            console.error("Ошибка выхода:", error);
        }
    };


    if (!user) {
        return (
            <Button variant="primary" onClick={() => window.location.href = '/login'}>
                Войти
            </Button>
        );
    }

    return (
        <>
            <Button variant="primary" onClick={handleShow}>
                Профиль
            </Button>

            <Modal show={show} onHide={handleClose} centered>
                <Modal.Header closeButton>
                    <Modal.Title>Профиль</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>Имя: {user.name}</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="outline-danger" onClick={handleLogout}>
                        Выйти
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};
