import styles from '../styles/HomePage.module.css'
import axios from 'axios';
import {useEffect, useState} from "react";
import {UserVacancyList} from "./user/UserVacancyList";
import useRoles from "../hooks/useRoles";
import {ResumeList} from "./user/ResumeList";

function HomePage() {
    const [position, setPosition] = useState('');
    const [country, setCountry] = useState('');
    const [experience, setExperience] = useState(0);
    const [title, setTitle] = useState('');
    const [city, setCity] = useState('');
    const [vacancies, setVacancies] = useState([]);
    const [resumes, setResumes] = useState([]);
    const role = useRoles();

    useEffect(() => {
        if (role === "USER") {
            axios.get('/api/vacancy', {
                params: {
                    title: title
                }
            })
                .then(response => {
                    setVacancies(response.data)
                })
                .catch(error => {
                    console.log(error);
                })
        } else {
            axios.get('/api/resume', {
                params: {
                    position: position,
                    country: country,
                    experience: experience
                }
            })
                .then(response => {
                    setResumes(response.data)
                })
                .catch(error => {
                    console.log(error);
                })
        }
    }, [role])

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handlePositionChange = (event) => {
        setPosition(event.target.value);
    };

    const findVacancies = async (event) => {
        event.preventDefault();
        axios.get('/api/vacancy', {
            params: {
                title: title,
                country: country,
                city: city
            }
        })
            .then(response => {
                setVacancies(response.data)
            })
            .catch(error => {
                console.log(error);
            })
    }

    const findResumes = async (event) => {
        event.preventDefault();
        axios.get('/api/resume', {
            params: {
                position: position,
                country: country,
                experience: experience
            }
        })
            .then(response => {
                setResumes(response.data)
            })
            .catch(error => {
                console.log(error);
            })
    }

    return (
        <main>
            <h1 className={styles.title}>Найдите свою работу мечты</h1>
            {role === "USER" &&
                <>
                    <form className={styles.form_home} onSubmit={findVacancies}>
                        <input className={styles.input_home}
                               type="text"
                               id="title"
                               name="title"
                               placeholder="Пошук по професії"
                               onChange={handleTitleChange}
                        />
                        <input className={styles.input_home} type="text" placeholder="Місто"/>
                        <button className={styles.button_home} type="submit">Знайти</button>
                    </form>
                    <UserVacancyList vacancies={vacancies}/>
                </>
            }
            {role === "COMPANY" &&
                <>
                    <form className={styles.form_home} onSubmit={findResumes}>
                        <input className={styles.input_home}
                               type="text"
                               id="position"
                               name="position"
                               placeholder="Пошук працівника"
                               onChange={handlePositionChange}
                        />
                        <input className={styles.input_home} type="text" placeholder="Місто"/>
                        <button className={styles.button_home} type="submit">Знайти</button>
                    </form>
                    <ResumeList resumes={resumes}/>
                </>
            }
        </main>
    );
}

export {HomePage};