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
        <div className={styles.resume_container}>
            <div className={styles.tittle_resume}>
                <h1 className={styles.h1_resum}>Resume</h1>
            </div>
            <div className={styles.container}>
                <div className={styles.field}>
                    <label className={styles.field_label}>Email:</label>
                    <label className={styles.field_label}>First Name:</label>
                    <label className={styles.field_label}>Last Name:</label>
                    <label className={styles.field_label}>Patronymic:</label>
                    <label className={styles.field_label}>City:</label>
                    <label className={styles.field_label}>Position:</label>
                    <label className={styles.field_label}>Phone:</label>
                    <label className={styles.field_label}>University:</label>
                    <label className={styles.field_label}>Experience:</label>
                    <label className={styles.field_label}>Skills:</label>
                    <label className={styles.field_label}>Additional Information:</label>
                </div>
            
                <div className={styles.field}>
                    <span id="email" className={styles.field_span}>{resumeData.email}</span>
                    <span id="firstname" className={styles.field_span}>{resumeData.firstname}</span>
                    <span id="lastname" className={styles.field_span}>{resumeData.lastname}</span>
                    <span id="patronymic" className={styles.field_span}>{resumeData.patronymic}</span>
                    <span id="city" className={styles.field_span}>{resumeData.city}</span>
                    <span id="position" className={styles.field_span}>{resumeData.position}</span>
                    <span id="phone" className={styles.field_span}>{resumeData.phone}</span>
                    <span id="university" className={styles.field_span}>{resumeData.university}</span>
                    <span id="experience" className={styles.field_span}>{resumeData.experience}</span>
                    <span id="skills" className={styles.field_span}>{resumeData.skills}</span>
                    <span id="additionalInfo" className={styles.field_span}>{resumeData.additionalInfo}</span>
                </div>
            </div>
        </div>
    );
}

export {ResumeDetails};