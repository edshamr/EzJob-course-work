import styles from '../styles/ResumeForm.module.css'
import React, { useState, useEffect } from 'react';
import axios from 'axios';


const initialState = {
    email: "",
    firstname: "",
    lastname: "",
    patronimyc: "",
    city: "",
    country: "",
    phone: "",
    position: "",
    university: "",
    experience: 0,
    additionalInfo: ""
};

function ResumeForm() {
    const [formData, setFormData] = useState(initialState);

    useEffect(() => {
        const resumeId = localStorage.getItem("resumeId");
        console.log(resumeId);

        axios.get('api/resume/' + resumeId, {})
            .then((response) => {
                console.log(response.data);
                setFormData((prevFormData) => ({
                    ...prevFormData,
                    ...response.data
                }));
            })
            .catch((error) => {
                console.log(error.response.data.description);
            });
    }, []);


    return (
        <form className={styles.form_resume}>
            <h2 className={styles.title_resume}>Створення резюме</h2>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="firstname">Ім'я</label>
                <input className={styles.input_form} type="text" id="firstname" name="firstname" value={formData.firstname} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="lastname">Прізвище</label>
                <input className={styles.input_form} type="text" id="lastname" name="lastname" value={formData.lastname} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="patronymic">По-батькові</label>
                <input className={styles.input_form} type="text" id="patronymic" name="patronymic" value={formData.patronimyc} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="email">Email</label>
                <input className={styles.input_form} type="text" id="email" name="email" value={formData.email} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="city">Місто</label>
                <input className={styles.input_form} type="text" id="city" name="city" value={formData.city} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="country">Країна</label>
                <input className={styles.input_form} type="text" id="country" name="country" value={formData.country} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="phone">Телефон</label>
                <input className={styles.input_form} type="tel" id="phone" name="phone" pattern="^\+380\d{9}$" value={formData.phone} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="position">Посада</label>
                <input className={styles.input_form} type="text" id="position" name="position" value={formData.position} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="university">Освіта</label>
                <input className={styles.input_form} type="text" id="university" name="university" value={formData.university} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="experience">Досвід роботи(років)</label>
                <input className={styles.input_form} type="number" id="experience" name="experience" value={formData.experience} required />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="additionalInfo">Додаткова інформація</label>
                <textarea className={styles.input_form} id="additionalInfo" name="additionalInfo" value={formData.additionalInfo} required></textarea>
            </div>
            <div className={styles.form_group}>
                <input className={styles.inpt_submit} type="submit" value="Надіслати" />
            </div>
        </form>
    );
}

export {ResumeForm};