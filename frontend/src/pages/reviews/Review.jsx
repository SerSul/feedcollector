import React, {useEffect, useState} from 'react';
import api from "../../api";
import {useParams} from "react-router-dom";

const ReviewPage = () => {
    const {id} = useParams();

    const [review, setReview] = useState(null);
    const [userComment, setUserComment] = useState('');
    const [parentCommentId, setParentCommentId] = useState('');
    const [showReset, setShowReset] = useState(false);


    useEffect(() => {
        setShowReset(userComment.trim() !== '');
    }, [userComment]);

    useEffect(() => {
        const fetchReview = async () => {
            try {
                console.log('Отправляю запрос на /reviews/', id);
                const response = await api.get(`/reviews/${id}`);
                console.log('Ответ получен:', response);
                setReview(response.data);
            } catch (err) {
                console.error('Ошибка при запросе:', err);
            }
        };

        fetchReview();
    }, [id]);

    const handleReplyClick = (username, commentId) => {
        setParentCommentId(commentId);
        setUserComment(prev => prev.trim() ? `${prev} ${username}, ` : `${username}, `);
    };

    const handleResetReply = () => {
        setUserComment('');
        setParentCommentId('');
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log({parentCommentId, userComment});
    };

    if (!review) {
        return <div>Загрузка...</div>;
    }

    return (
        <div className="has-background-grey-lighter" style={{height: '100%'}}>
            <section className="section">
                <div className="container">
                    <div className="card">
                        <div className="card-content">
                            <div className="box">
                                <article className="media">
                                    <div className="media-content">
                                        <div className="content">
                                            <p className="title is-4">{review.title}</p>
                                            <p className="subtitle is-6">
                                                Автор: {review.userName}
                                                <span className="tag is-info ml-2">{review.rating} ★</span>
                                                <span className="is-size-7 has-text-grey ml-2">
                          {new Date(review.createdAt).toLocaleString('ru-RU')}
                        </span>
                                                {review.url &&
                                                    <a href={review.url} target="_blank" rel="noreferrer"
                                                       className="tag is-link is-light ml-2">
                            <span className="icon is-small">
                              <i className="fas fa-external-link-alt"/>
                            </span>
                                                        <span>Ссылка на ресурс</span>
                                                    </a>
                                                }
                                            </p>
                                            <p>{review.content}</p>
                                        </div>
                                    </div>
                                </article>
                            </div>

                            <div className="box mt-5">
                                {(!review.comments || review.comments.length === 0) && (
                                    <div className="notification is-light">
                                        Пока нет комментариев. Будьте первым!
                                    </div>
                                )}

                                {review.comments?.map((comment) => (
                                    <div key={comment.id} className="media mb-5">
                                        <div className="media-content">
                                            <div className="content">
                                                <p>
                                                    <strong>{comment.userName}</strong>
                                                    <small className="is-size-7 has-text-grey">
                                                        {new Date(comment.createdAt).toLocaleString('ru-RU')}
                                                    </small>
                                                    <br/>
                                                    <span>{comment.content}</span>
                                                </p>
                                                <button
                                                    className="button is-small is-light mt-2 reply-button"
                                                    onClick={() => handleReplyClick(comment.userName, comment.id)}
                                                >
                          <span className="icon">
                            <i className="fas fa-reply"/>
                          </span>
                                                    <span>Ответить</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                ))}

                                <form onSubmit={handleSubmit}>
                                    <input type="hidden" value={parentCommentId} name="parentCommentId"/>
                                    <div className="field">
                                        <div className="control has-icons-right">
                      <textarea
                          className="textarea is-small"
                          placeholder="Ваш комментарий..."
                          value={userComment}
                          onChange={(e) => setUserComment(e.target.value)}
                          required
                      />
                                            {showReset && (
                                                <span className="reset-reply" onClick={handleResetReply}>
                          <i className="fas fa-times"/>
                        </span>
                                            )}
                                        </div>
                                    </div>
                                    <div className="field">
                                        <div className="control">
                                            <button type="submit" className="button is-primary">Отправить</button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default ReviewPage;
