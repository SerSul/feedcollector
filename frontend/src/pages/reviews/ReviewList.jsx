import React from 'react';
import ReviewListElem from './ReviewListElem';
import { Container } from 'react-bootstrap';

const ReviewList = ({ reviews }) => {
    return (
        <Container>
            {reviews.length === 0 ? (
                <p>Обзоры отсутствуют.</p>
            ) : (
                reviews.map((review, index) => (
                    <ReviewListElem key={index} review={review} />
                ))
            )}
        </Container>
    );
};

export default ReviewList;
