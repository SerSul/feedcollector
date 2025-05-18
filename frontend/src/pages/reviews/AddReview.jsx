import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import api from "../../api";

const AddReview = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [url, setUrl] = useState('');
    const [rating, setRating] = useState('5');
    const [content, setContent] = useState('');
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        const user = localStorage.getItem('user');
        if (!user) {
            navigate('/login');
        }
    }, [navigate]);

    const validate = () => {
        const errs = {};
        if (!title.trim()) errs.title = 'Название обязательно';
        if (!url.trim()) errs.url = 'Ссылка обязательна';
        else if (!/^https?:\/\/.+/.test(url)) errs.url = 'Введите корректный URL';
        if (!content.trim()) errs.content = 'Текст отзыва обязателен';
        if (![1,2,3,4,5].includes(Number(rating))) errs.rating = 'Выберите оценку';
        setErrors(errs);
        return Object.keys(errs).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!validate()) return;

        try {
            await api.post('/reviews/create-review', {
                title: title,
                description: description,
                url: url,
                rating: rating,
                content: content
            });
            navigate('/');
        } catch (error) {
            console.error('Ошибка при отправке отзыва', error);
        }
    };

    return (
        <section className="section">
            <div className="container">
                <div className="columns is-centered">
                    <div className="column is-half">
                        <div className="card">
                            <div className="card-header">
                                <p className="card-header-title">Новый отзыв</p>
                            </div>
                            <div className="card-content">
                                <form onSubmit={handleSubmit} noValidate>
                                    {/* Название */}
                                    <div className="field">
                                        <label className="label">Название*</label>
                                        <div className="control">
                                            <input
                                                className={`input ${errors.title ? 'is-danger' : ''}`}
                                                type="text"
                                                placeholder="Краткое название"
                                                value={title}
                                                onChange={e => setTitle(e.target.value)}
                                            />
                                        </div>
                                        {errors.title && <p className="help is-danger">{errors.title}</p>}
                                    </div>

                                    {/* Описание */}
                                    <div className="field">
                                        <label className="label">Краткое описание</label>
                                        <div className="control">
                                            <input
                                                className="input"
                                                type="text"
                                                placeholder="Необязательное краткое описание"
                                                value={description}
                                                onChange={e => setDescription(e.target.value)}
                                            />
                                        </div>
                                    </div>

                                    {/* URL */}
                                    <div className="field">
                                        <label className="label">Ссылка на ресурс*</label>
                                        <div className="control">
                                            <input
                                                className={`input ${errors.url ? 'is-danger' : ''}`}
                                                type="url"
                                                placeholder="https://example.com"
                                                value={url}
                                                onChange={e => setUrl(e.target.value)}
                                            />
                                        </div>
                                        {errors.url && <p className="help is-danger">{errors.url}</p>}
                                    </div>

                                    {/* Оценка */}
                                    <div className="field">
                                        <label className="label">Ваша оценка*</label>
                                        <div className="control">
                                            <div className="select">
                                                <select
                                                    value={rating}
                                                    onChange={e => setRating(e.target.value)}
                                                >
                                                    {[1, 2, 3, 4, 5].map(num => (
                                                        <option key={num} value={num}>{num} ★</option>
                                                    ))}
                                                </select>
                                            </div>
                                        </div>
                                        {errors.rating && <p className="help is-danger">{errors.rating}</p>}
                                    </div>

                                    {/* Текст отзыва */}
                                    <div className="field">
                                        <label className="label">Текст отзыва*</label>
                                        <div className="control">
                      <textarea
                          className={`textarea ${errors.content ? 'is-danger' : ''}`}
                          placeholder="Подробно опишите ваше мнение"
                          value={content}
                          onChange={e => setContent(e.target.value)}
                      />
                                        </div>
                                        {errors.content && <p className="help is-danger">{errors.content}</p>}
                                    </div>

                                    {/* Кнопки */}
                                    <div className="field is-grouped">
                                        <div className="control">
                                            <button type="submit" className="button is-primary">Опубликовать</button>
                                        </div>
                                        <div className="control">
                                            <button
                                                type="button"
                                                className="button is-light"
                                                onClick={() => navigate('/')}
                                            >
                                                Отмена
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default AddReview;
