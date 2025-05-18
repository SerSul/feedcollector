import { Link } from 'react-router-dom';
import api from '../../api';
import { Profile } from "./Profile";

export default function Header() {
    return (
        <header className="d-flex justify-content-between align-items-center p-3 has-background-fourthly">
            <div>
                <Link to="/home" className="text-decoration-none text-primary">Отзывы</Link>
            </div>
            <div>
                <Profile />
            </div>
        </header>
    );
}
