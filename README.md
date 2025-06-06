# FeedCollector — система сбора и управления отзывами

[![Java](https://img.shields.io/badge/Java-21-blue)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-61DAFB)](https://react.dev/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1)](https://www.postgresql.org/)

**Фуллстек-приложение** для сбора, модерации и анализа пользовательских отзывов с возможностью комментирования и ролевой моделью доступа.

## 📌 Основные возможности
- **CRUD-операции** с отзывами и комментариями
- **Аутентификация/авторизация** (JWT + Spring Security)
- **Ролевая модель**: Гость, Пользователь, Администратор
- **Поиск и фильтрация** отзывов по названию
- **Docker-развертывание** (бекенд + фронтенд + PostgreSQL)
- **REST API** с валидацией данных

## 🛠 Технологический стек
| Компонент       | Технологии                                                                 |
|-----------------|----------------------------------------------------------------------------|
| **Бекенд**      | Java 21, Spring Boot 3.2, Spring Security, Hibernate, Lombok, Maven        |
| **Фронтенд**    | React 18, Bulma CSS, Axios                                                 |
| **База данных** | PostgreSQL 16                                                              |
| **Инфраструктура** | Docker, Docker Compose, Nginx                                           |

## 🚀 Запуск проекта
### Требования
- Docker 24+ и Docker Compose
- JDK 21 (для локальной разработки)
- Node.js 18+ (для фронтенда)
