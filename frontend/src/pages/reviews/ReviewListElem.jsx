import React from 'react';
import { Container, Card, Badge } from 'react-bootstrap';

const ReviewListElem = ({ review }) => {
    return (
        <Container className="mb-4">
            <Card className="has-background-thirdly">
                <Card.Body>
                    <Card.Title>
                        <a href={`/reviews/${review.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                            {review.title}
                        </a>
                    </Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">{review.author}</Card.Subtitle>
                    <Card.Text>{review.text}</Card.Text>
                    <div>
                        <Badge bg="secondary" className="me-2">
                            {review.rating} ★
                        </Badge>
                        <Badge bg="light" text="dark" className="me-2">
                            {review.date}
                        </Badge>
                    </div>
                </Card.Body>
            </Card>
        </Container>
    );
};

export default ReviewListElem;
