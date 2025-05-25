import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/auth/Login';
import Header from "./pages/header/Header";
import React from "react";
import AddReview from "./pages/reviews/AddReview";
import Home from "./pages/Home";

import 'bulma/css/bulma.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import './colors.css';
import Register from "./pages/auth/Register";
import ReviewPage from "./pages/reviews/Review";
import PrivateRoute from "./hooks/PrivateRoute";

function App() {
    return (
        <Router>
            <div className="has-background-grey-lighter" style={{ minHeight: '100vh' }}>
                <Header />
                <Routes>
                    <Route path="/" element={<Navigate to="/home" replace />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route
                        path="/create-review"
                        element={
                            <PrivateRoute>
                                <AddReview />
                            </PrivateRoute>
                        }
                    />
                    <Route path="/home" element={<Home />} />
                    <Route path="/reviews/:id" element={<ReviewPage />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;