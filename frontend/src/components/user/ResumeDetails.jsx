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
        <div className={styles.resume_container}>
            <h1 className={styles.h1_resum}>Resume</h1>

            <div className={styles.field}>
                <label className={styles.field_label}>Email:</label>
                <span id="email" className={styles.field_span}>{resumeData.email}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>First Name:</label>
                <span id="firstname" className={styles.field_span}>{resumeData.firstname}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Last Name:</label>
                <span id="lastname" className={styles.field_span}>{resumeData.lastname}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Patronymic:</label>
                <span id="patronymic" className={styles.field_span}>{resumeData.patronymic}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>City:</label>
                <span id="city" className={styles.field_span}>{resumeData.city}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Country:</label>
                <span id="country" className={styles.field_span}>{resumeData.country}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Position:</label>
                <span id="position" className={styles.field_span}>{resumeData.position}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Phone:</label>
                <span id="phone" className={styles.field_span}>{resumeData.phone}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>University:</label>
                <span id="university" className={styles.field_span}>{resumeData.university}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Skills:</label>
                <span id="skills" className={styles.field_span}>{resumeData.skills}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Experience:</label>
                <span id="experience" className={styles.field_span}>{resumeData.experience}</span>
            </div>
            <div className={styles.field}>
                <label className={styles.field_label}>Additional Information:</label>
                <span id="additionalInfo" className={styles.field_span}>{resumeData.additionalInfo}</span>
            </div>
        </div>
    );
}

export {ResumeDetails};
