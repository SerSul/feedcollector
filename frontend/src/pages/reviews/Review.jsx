import React, { useEffect, useState } from "react";
import api from "../../api";
import { useParams } from "react-router-dom";
import { useUser } from "../../hooks/useCurrentUser";

const ReviewPage = () => {
    const { id } = useParams();

    const [review, setReview] = useState(null);
    const [userComment, setUserComment] = useState("");
    const [parentCommentId, setParentCommentId] = useState("");
    const [showReset, setShowReset] = useState(false);
    const { user } = useUser();

    useEffect(() => {
        setShowReset(userComment.trim() !== "");
    }, [userComment]);

    useEffect(() => {
        const fetchReview = async () => {
            try {
                const response = await api.get(`/reviews/${id}`);
                setReview(response.data);
            } catch (err) {
                console.error("Ошибка при запросе:", err);
            }
        };

        fetchReview();
    }, [id]);

    const handleReplyClick = (username, commentId) => {
        setParentCommentId(commentId);
        setUserComment((prev) =>
            prev.trim() ? `${prev} ${username}, ` : `${username}, `
        );
    };

    const handleResetReply = () => {
        setUserComment("");
        setParentCommentId("");
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await api.post('/reviews/add-comment', {
                content: userComment,
                parent_comment_id: parentCommentId || null,
                review_id: review.id
            });

            setUserComment('');
            setParentCommentId('');
            setShowReset(false);

            const updatedReview = await api.get(`/reviews/${id}`);
            setReview(updatedReview.data);

        } catch (error) {
            console.error('Ошибка при отправке комментария:', error);
            alert('Не удалось отправить комментарий.');
        }
    };


    if (!review) {
        return <div>Загрузка...</div>;
    }

    return (
        <section
            className="section has-background-grey-lighter"
            style={{ height: "100%" }}
        >
            <div className="container">
                <div className="card">
                    <div className="card-content">
                        <div className="box">
                            <article className="media">
                                <div className="media-content">
                                    <div className="content">
                                        <p className="title is-4">{review.title}</p>
                                        <p className="subtitle is-6">
                                            {review.author_user_name}
                                            <span className="tag is-info ml-2">
                        {review.rating} ★
                      </span>
                                            <span className="is-size-7 has-text-grey ml-2">
                        {new Date(review.created_at).toLocaleString("ru-RU")}
                      </span>
                                            {review.url && (
                                                <a
                                                    href={review.url}
                                                    target="_blank"
                                                    rel="noreferrer"
                                                    className="tag is-link is-light ml-2"
                                                >
                          <span className="icon is-small">
                            <i className="fas fa-external-link-alt" />
                          </span>
                                                    <span>Ссылка на ресурс</span>
                                                </a>
                                            )}
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
                                                <strong>{comment.author_user_name}</strong>
                                                <small className="is-size-7 has-text-grey">
                                                    {new Date(comment.created_at).toLocaleString("ru-RU")}
                                                </small>
                                                <br />
                                                <span>{comment.content}</span>
                                            </p>
                                            <button
                                                className="button is-small is-light mt-2 reply-button"
                                                onClick={() =>
                                                    handleReplyClick(comment.author_user_name, comment.id)
                                                }
                                            >
                        <span className="icon">
                          <i className="fas fa-reply" />
                        </span>
                                                <span>Ответить</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            ))}

                            {user ? (
                                <form onSubmit={handleSubmit}>
                                    <input
                                        type="hidden"
                                        value={parentCommentId}
                                        name="parentCommentId"
                                    />
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
                                                <span
                                                    className="reset-reply"
                                                    onClick={handleResetReply}
                                                >
                          <i className="fas fa-times" />
                        </span>
                                            )}
                                        </div>
                                    </div>
                                    <div className="field">
                                        <div className="control">
                                            <button type="submit" className="button is-primary">
                                                Отправить
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            ) : (
                                <div className="notification has-text-centered">
                                    <p>
                                        Чтобы оставить комментарий,{" "}
                                        <a href="/login">войдите в аккаунт</a>.
                                    </p>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ReviewPage;
