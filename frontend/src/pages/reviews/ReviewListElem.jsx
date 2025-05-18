import React from 'react';
import {Container, Card, Badge} from 'react-bootstrap';

const ReviewListElem = ({review}) => {
    return (<Container className="mb-4">
        <Card className="has-background-secondary border-0">
            <Card.Body>
                <Card.Title>
                    <a href={`/reviews/${review.id}`} style={{textDecoration: 'none', color: 'inherit'}}>
                        {review.title}
                    </a>
                </Card.Title>
                <Card.Subtitle className="mb-2 text-muted">{review.author_user_name}</Card.Subtitle>
                <Card.Text>{review.description}</Card.Text>
                <div>
                    <Badge className="me-2 has-background-thirdly">
                        {review.rating} ★
                    </Badge>
                    <Badge text="dark" className="me-2 has-background-thirdly">
                        {new Date(review.created_at).toLocaleString('ru-RU')}
                    </Badge>
                    <Badge text="dark" className="me-2 has-background-thirdly">
                        {review.author_user_name}
                    </Badge>

                </div>
            </Card.Body>
        </Card>
    </Container>);
};

export default ReviewListElem;
