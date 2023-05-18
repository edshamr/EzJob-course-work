import styles from '../styles/HomePage.module.css'
import axios from 'axios';
import {useEffect, useState} from "react";
import {UserVacancyList} from "./user/UserVacancyList";
import useRoles from "../hooks/useRoles";

function HomePage() {
    const [title, setTitle] = useState('');
    const [city, setCity] = useState('');
    const [vacancies, setVacancies] = useState([]);
    const role = useRoles();

    useEffect(() => {
        axios.get('/api/vacancy', {
            params: {
                title: title
            }
        })
            .then(response => {
                console.log(response.data);
                setVacancies(response.data)
            })
            .catch(error => {
                console.log(error);
            })
    }, [])

    function logout() {
        axios.post('/api/logout/expire', {})
            .then(() => {
                localStorage.removeItem('token');
            })
            .catch(error => {
                if (error.status === 403) {
                    localStorage.removeItem('token');
                }
            });
    }

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleCityChange = (event) => {
        setCity(event.target.value);
    };

    const findVacancies = async (event) => {
        event.preventDefault();
        axios.get('/api/vacancy', {
            params: {
                title: title
            }
        })
            .then(response => {
                console.log(response.data);
                setVacancies(response.data)
            })
            .catch(error => {
                console.log(error);
            })

    }

    return (
        <main>
            <h1 className={styles.title}>Найдите свою работу мечты</h1>
            <form className={styles.form_home} onSubmit={findVacancies}>
                <input className={styles.input_home}
                       type="text"
                       id="name"
                       name="name"
                       placeholder="Пошук по професії"
                       onChange={handleTitleChange}
                />
                <input className={styles.input_home} type="text" placeholder="Місто"/>
                <button className={styles.button_home} type="submit">Знайти</button>
            </form>
            {localStorage.getItem('token') && (
                <form onSubmit={logout} className={styles.form_home}>
                    <button className={styles.button_home} type="submit">Вийти</button>
                </form>
            )}
            {role === "USER" &&
                <UserVacancyList vacancies={vacancies}/>
            }
        </main>
    );
}

export {HomePage};