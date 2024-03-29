import styles from '../../styles/ResumeForm.module.css'
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import useRoles from "../../hooks/useRoles";
import {NotFound} from "../error/NotFound";
import {Search} from "../Search";


const initialState = {
    email: "",
    firstname: "",
    lastname: "",
    patronymic: "",
    city: "",
    phone: "",
    position: "",
    university: "",
    experience: "",
    skills: "",
    additionalInfo: ""
};

function ResumeForm() {
    const [formData, setFormData] = useState(initialState);
    const [error, setError] = useState(false)
    const navigate = useNavigate();
    const role = useRoles();
    const [selectedCity, setSelectedCity] = useState('');

    useEffect(() => {
        if (role !== "USER") {
            setError(true)
        } else {
            setError(false)
        }
        const resumeId = localStorage.getItem("resumeId");
        if (resumeId) {
            axios.get('api/resume/' + resumeId, {})
                .then((response) => {
                    setFormData((prevFormData) => ({
                        ...prevFormData,
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
        }
    }, [role]);

    const handleSubmit = (event) => {
        event.preventDefault();
        const resumeId = localStorage.getItem("resumeId");
        if (!selectedCity.label) {
            setSelectedCity('');
        }
        const requestDto = {
            email: formData.email,
            firstname: formData.firstname,
            lastname: formData.lastname,
            patronymic: formData.patronymic,
            city: selectedCity.label,
            phone: formData.phone,
            position: formData.position,
            university: formData.university,
            experience: formData.experience,
            skills: formData.skills,
            additionalInfo: formData.additionalInfo
        }
        if (resumeId) {
            axios.put('api/resume/' + resumeId, requestDto)
                .then((response) => {
                    console.log("OK")
                })
                .catch(error => {
                    console.log("Error")
                })
        } else {
            axios.post('api/resume', requestDto)
                .then((response) => {
                    localStorage.setItem("resumeId", response.data.resumeId);
                })
                .catch(error => {
                    console.log(error);
                })
        }
    }

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleCitySelect = (selectedCity) => {
        setSelectedCity(selectedCity);
    };

    if (error) {
        return <NotFound/>
    } else {
        return (
            <form onSubmit={handleSubmit} className={styles.form_resume}>
                <h2 className={styles.title_resume}>Створення резюме</h2>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="firstname">Ім'я</label>
                    <input className={styles.input_form}
                           type="text"
                           id="firstname"
                           name="firstname"
                           defaultValue={formData.firstname}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="lastname">Прізвище</label>
                    <input className={styles.input_form}
                           type="text" id="lastname"
                           name="lastname"
                           defaultValue={formData.lastname}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="patronymic">По-батькові</label>
                    <input className={styles.input_form}
                           type="text" id="patronymic"
                           name="patronymic"
                           defaultValue={formData.patronymic}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="email">Email</label>
                    <input className={styles.input_form}
                           type="text" id="email"
                           name="email"
                           defaultValue={formData.email}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="phone">Телефон</label>
                    <input className={styles.input_form}
                           type="tel"
                           id="phone"
                           name="phone"
                           pattern="^\+380\d{9}$"
                           defaultValue={formData.phone}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="position">Посада</label>
                    <input className={styles.input_form}
                           type="text"
                           id="position"
                           name="position"
                           defaultValue={formData.position}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="university">Освіта</label>
                    <input className={styles.input_form}
                           type="text"
                           id="university"
                           name="university"
                           defaultValue={formData.university}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="experience">Досвід роботи(рік)</label>
                    <input className={styles.input_form}
                           type="number"
                           id="experience"
                           name="experience"
                           defaultValue={formData.experience}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="skills">Здібності і досвід</label>
                    <textarea className={styles.input_form}
                              id="skills"
                              name="skills"
                              defaultValue={formData.skills}
                              onChange={handleChange}
                    >
                </textarea>
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="additionalInfo">Додаткова інформація</label>
                    <textarea className={styles.input_form}
                              id="additionalInfo"
                              name="additionalInfo"
                              defaultValue={formData.additionalInfo}
                              onChange={handleChange}
                    >
                </textarea>
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="city">Поточне місто</label>
                    <input className={styles.input_form}
                           type="text" id="city"
                           name="city"
                           readOnly={true}
                           defaultValue={formData.city}
                           onChange={handleChange}
                    />
                    <Search onSelectCity={handleCitySelect} />
                </div>
                <div className={styles.form_group}>
                    <input className={styles.inpt_submit} type="submit" value="Надіслати"/>
                </div>
            </form>
        );
    }
}

export {ResumeForm};