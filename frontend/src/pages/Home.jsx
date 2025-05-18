import React, {useEffect, useState} from 'react';
import ReviewList from "./reviews/ReviewList";
import api from "../api";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSearch} from "@fortawesome/free-solid-svg-icons";

const Home = () => {
    const [reviews, setReviews] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const [searchQuery, setSearchQuery] = useState('');
    const pageSize = 5;

    useEffect(() => {
        const fetchReviews = async () => {
            try {
                let url = `/reviews?page=${page}&size=${pageSize}`;
                if (searchQuery.length >= 3) {
                    url = `/reviews/search?title=${encodeURIComponent(searchQuery)}&page=${page}&size=${pageSize}`;
                }
                const response = await api.get(url);
                const data = response.data;
                setReviews(data.content);
                setTotalPages(data.totalPages);
            } catch (err) {

            }
        };

        // дебаунс для поиска
        const delayDebounceFn = setTimeout(() => {
            fetchReviews();
        }, 500);

        return () => clearTimeout(delayDebounceFn);
    }, [page, searchQuery]);

    const handlePrev = () => {
        if (page > 0) setPage(page - 1);
    };

    const handleNext = () => {
        if (page < totalPages - 1) setPage(page + 1);
    };

    const handleSearchChange = (e) => {
        setSearchQuery(e.target.value);
        setPage(0); // при новом поиске возвращаемся на первую страницу
    };

    return (
        <div className="has-background-grey-lighter" style={{height: '100%'}}>
        <section className="section container">
            <div className="box is-small">
                <div className="container">
                    {/* Поиск */}
                    <div className="control mb-3 has-icons-left">
                        <input
                            className="input is-medium border-0 has-background-secondary"
                            style={{}}
                            type="text"
                            value={searchQuery}
                            onChange={handleSearchChange}
                        />
                        <span className="icon is-left">
                                <FontAwesomeIcon icon={faSearch}/>
                            </span>
                    </div>

                    {/* Список отзывов */}
                    <ReviewList reviews={reviews}/>

                    {/* Пагинация */}
                    {totalPages > 1 && (<nav
                        className="pagination is-centered"
                        role="navigation"
                        aria-label="pagination"
                        style={{marginTop: '20px'}}
                    >
                        <button
                            className="pagination-previous button"
                            onClick={handlePrev}
                            disabled={page === 0}
                        >
                            Назад
                        </button>
                        <button
                            className="pagination-next button"
                            onClick={handleNext}
                            disabled={page === totalPages - 1}
                        >
                            Вперед
                        </button>
                        <ul className="pagination-list">
                            {Array.from({length: totalPages}, (_, i) => (<li key={i}>
                                <button
                                    className={`pagination-link button ${i === page ? 'is-current' : ''}`}
                                    onClick={() => setPage(i)}
                                >
                                    {i + 1}
                                </button>
                            </li>))}
                        </ul>
                    </nav>)}
                </div>
            </div>
        </section>
    </div>);

};

export default Home;
