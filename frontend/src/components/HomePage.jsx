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
                    country:  country,
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
        setPosition(event.target.value);
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
            {role === "USER" &&
                <UserVacancyList vacancies={vacancies}/>
            }
            {role === "COMPANY" &&
                <ResumeList resumes={resumes}/>
            }
        </main>
    );
}

export {HomePage};