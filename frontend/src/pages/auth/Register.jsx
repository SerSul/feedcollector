import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {
    MDBContainer, MDBInput, MDBBtn,
} from 'mdb-react-ui-kit';
import api from '../../api';

export default function Register() {
    const [email, setEmail] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            await api.post('/auth/register', {
                email, user_name: userName, password
            });

            navigate('/login');
        } catch (error) {
            console.error('Ошибка регистрации:', error);
        }
    };

    return (
        <div className="has-background-grey-lighter d-flex justify-content-center align-items-center" style={{height: '100vh'}}>
            <MDBContainer className="mb-5 d-flex flex-column w-50 box">
                <MDBInput
                    wrapperClass='mb-4'
                    id='registerEmail'
                    placeholder='Email address'
                    type='email'
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />

                <MDBInput
                    wrapperClass='mb-4'
                    id='registerUserName'
                    placeholder='Username'
                    type='text'
                    value={userName}
                    onChange={e => setUserName(e.target.value)}
                />

                <MDBInput
                    wrapperClass='mb-4'
                    placeholder='Password'
                    id='registerPassword'
                    type='password'
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />

                <MDBBtn className="mb-4" onClick={handleRegister}>
                    Register
                </MDBBtn>

                <div className="text-center">
                    <p>Already have an account? <a href="/login">Sign in</a></p>
                </div>
            </MDBContainer>
        </div>);
}
