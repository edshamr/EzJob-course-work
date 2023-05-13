import styles from '../styles/ResumeForm.module.css'
import React, {useState, useEffect} from 'react';
import axios from 'axios';


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
    additionalInfo: ""
};

function ResumeForm() {
    const [formData, setFormData] = useState(initialState);

    useEffect(() => {
        const resumeId = localStorage.getItem("resumeId");

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

    const handleSubmit = (event) => {
        event.preventDefault();
        const resumeId = localStorage.getItem("resumeId");
        const requestDto = {
            email: formData.email,
            firstname: formData.firstname,
            lastname: formData.lastname,
            patronymic: formData.patronymic,
            city: formData.city,
            country: formData.country,
            phone: formData.phone,
            position: formData.position,
            university: formData.university,
            experience: formData.experience,
            additionalInfo: formData.additionalInfo
        }

        axios.put('api/resume/' + resumeId, requestDto)
            .then((response) => {
                console.log("OK")
            })
            .catch(error => {
                console.log("Error")
            })
    }

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };


    return (
        <form onSubmit={handleSubmit} className={styles.form_resume}>
            <h2 className={styles.title_resume}>Створення резюме</h2>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="firstname">Ім'я</label>
                <input className={styles.input_form}
                       type="text"
                       id="firstname"
                       name="firstname"
                       defaultValue={formData.firstname}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="lastname">Прізвище</label>
                <input className={styles.input_form}
                       type="text" id="lastname"
                       name="lastname"
                       defaultValue={formData.lastname}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="patronymic">По-батькові</label>
                <input className={styles.input_form}
                       type="text" id="patronymic"
                       name="patronymic"
                       defaultValue={formData.patronymic}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="email">Email</label>
                <input className={styles.input_form}
                       type="text" id="email"
                       name="email"
                       defaultValue={formData.email}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="city">Місто</label>
                <input className={styles.input_form}
                       type="text" id="city"
                       name="city"
                       defaultValue={formData.city}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="country">Країна</label>
                <input className={styles.input_form}
                       type="text"
                       id="country"
                       name="country"
                       defaultValue={formData.country}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="phone">Телефон</label>
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
                <label className={styles.label_form} for="position">Посада</label>
                <input className={styles.input_form}
                       type="text"
                       id="position"
                       name="position"
                       defaultValue={formData.position}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="university">Освіта</label>
                <input className={styles.input_form}
                       type="text"
                       id="university"
                       name="university"
                       defaultValue={formData.university}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="experience">Досвід роботи(рік)</label>
                <input className={styles.input_form}
                       type="number"
                       id="experience"
                       name="experience"
                       defaultValue={formData.experience}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} for="additionalInfo">Додаткова інформація</label>
                <textarea className={styles.input_form}
                          id="additionalInfo"
                          name="additionalInfo"
                          defaultValue={formData.additionalInfo}
                          onChange={handleChange}
                >
                </textarea>
            </div>
            <div className={styles.form_group}>
                <input className={styles.inpt_submit} type="submit" value="Надіслати"/>
            </div>
        </form>
    );
}

export {ResumeForm};