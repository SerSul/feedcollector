import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
    MDBContainer, MDBInput, MDBBtn
} from 'mdb-react-ui-kit';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import api from "../../api";
import {Button} from "react-bootstrap";

export default function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            await api.post('/auth/login', {
                user_name: email,
                password: password
            });

            navigate("/home");
            window.location.reload();
        } catch (error) {
            console.error('Ошибка входа:', error);

            // Показываем уведомление об ошибке
            toast.error('Неверный логин или пароль', {
                position: "top-right",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
        }
    };

    return (
        <div className="has-background-grey-lighter d-flex justify-content-center align-items-center" style={{height: '100%'}}>
            <MDBContainer className="mb-5 d-flex flex-column box w-50">
                <MDBInput
                    wrapperClass='mb-4'
                    id='form1'
                    placeholder='Login'
                    type='email'
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />

                <MDBInput
                    wrapperClass='mb-4'
                    id='form2'
                    placeholder='Password'
                    type='password'
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />

                <Button variant="outline-info" onClick={handleLogin}>
                    Sign in
                </Button>

                <div className="text-center">
                    <p>Not a member? <a href="/register">Register</a></p>
                </div>

                {/* Контейнер для уведомлений */}
                <ToastContainer />
            </MDBContainer>
        </div>
    );
}