import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

import styles from '../../styles/Vacancy_details.module.css'


const initialState = {
    id: 0,
    title: "",
    description: "",
    additionalInfo: ""
};

function VacancyDetails() {
    const navigate = useNavigate();

    const path = window.location.pathname;
    const pathSegments = path.split('/');
    const vacancyId = pathSegments[pathSegments.length - 1];

    const [vacancyData, setVacancyData] = useState(initialState);

    useEffect(() => {
        axios.get("/api/vacancy/" + vacancyId)
            .then(response => {
                setVacancyData((prevVacancyData) => ({
                    ...prevVacancyData,
                    ...response.data
                }));
                console.log(response.data);
            })
            .catch((error) => {
                if ((error.response.status === 401 || error.response.status === 403) && localStorage.getItem('token')) {
                    localStorage.removeItem('token');
                    navigate('/login')
                }
                console.log(error.response.data.description);
            });
    }, [])

    const responseVacancy = (event) => {
        event.preventDefault();
        const resumeId = localStorage.getItem("resumeId");
        if (resumeId) {
            axios.post('/api/resume/' + resumeId, {vacancyId: vacancyId})
                .then((response) => {
                    console.log("OK")
                })
                .catch(error => {
                    console.log("Error")
                })
        }
    }

    return (
        <div className={styles.vacancy_details}>
            <div>
                <h1> {vacancyData.title}</h1>
            </div>
            <div>
                <p className={styles.vacancy_description}>Опис</p>
                {vacancyData.description}
            </div>
            <div>
                <p className={styles.vacancy_description}>Додаткова інформація</p>
                {vacancyData.additionalInfo}
            </div>
            <div >
                <button className={styles.send_button} type="submit" onClick={responseVacancy}>Відгукнутися</button>
            </div>
        </div>
    );
}

export {VacancyDetails};