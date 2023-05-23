import styles from '../styles/HomePage.module.css'
import axios from 'axios';
import {useEffect, useState} from "react";
import {UserVacancyList} from "./user/UserVacancyList";
import useRoles from "../hooks/useRoles";
import {ResumeList} from "./user/ResumeList";
import { Search } from './Search';

function HomePage() {
    const [position, setPosition] = useState('');
    const [title, setTitle] = useState('');
    const [vacancies, setVacancies] = useState([]);
    const [resumes, setResumes] = useState([]);
    const role = useRoles();
    const [selectedCity, setSelectedCity] = useState('');

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
                    city: selectedCity.label,
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

    const handleCitySelect = (selectedCity) => {
        setSelectedCity(selectedCity);
    };

    const handlePositionChange = (event) => {
        setPosition(event.target.value);
    };

    const findVacancies = async (event) => {
        event.preventDefault();
        if (!selectedCity.label) {
            selectedCity.label = '';
        }
        axios.get('/api/vacancy', {
            params: {
                title: title,
                city: selectedCity.label
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
        if (!selectedCity.label) {
            selectedCity.label = '';
        }
        axios.get('/api/resume', {
            params: {
                position: position,
                city: selectedCity.label,
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
            <h1 className={styles.title}>Знайдіть роботу своєї мрії</h1>
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
                        <Search onSelectCity={handleCitySelect} />
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
                        <Search onSelectCity={handleCitySelect} />
                        <button className={styles.button_home} type="submit">Знайти</button>
                    </form>
                    <ResumeList resumes={resumes}/>
                </>
            }
        </main>
    );
}

export {HomePage};