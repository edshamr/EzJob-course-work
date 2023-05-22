import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

import styles from '../../styles/Vacancy_details.module.css'


const initialState = {
    email: "",
    firstname: "",
    lastname: "",
    patronymic: "",
    city: "",
    country: "",
    phone: "",
    position: "",
    university: "",
    experience: 0,
    skills: "",
    additionalInfo: ""
};

function ResumeDetails() {
    const navigate = useNavigate();

    const path = window.location.pathname;
    const pathSegments = path.split('/');
    const resumeId = pathSegments[pathSegments.length - 1];

    const [resumeData, setResumeData] = useState(initialState);

    useEffect(() => {
        axios.get("/api/resume/" + resumeId)
            .then(response => {
                setResumeData((prevVacancyData) => ({
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

    return (
        <div className={styles.vacancy_details}>
            <div>
                <h1> {resumeData.firstname}</h1>
            </div>
            <div>
                <p className={styles.vacancy_description}>Опис</p>
                {resumeData.lastname}
            </div>
            <div>
                <p className={styles.vacancy_description}>Додаткова інформація</p>
                {resumeData.additionalInfo}
            </div>
        </div>
    );
}

export {ResumeDetails};