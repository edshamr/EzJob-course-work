import React, {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import styles from "../../styles/ResumeForm.module.css";
import useRoles from "../../hooks/useRoles";


const initialState = {
    companyId: 0,
    title: "",
    description: "",
    additionalInfo: ""
};

function CompanyVacancy() {
    const role = useRoles();
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
            })
            .catch((error) => {
                if ((error.response.status === 401 || error.response.status === 403) && localStorage.getItem('token')) {
                    localStorage.removeItem('token');
                    navigate('/login')
                }
                console.log(error.response.data.description);
            });
    }, [])

    const handleSubmit = (event) => {
        event.preventDefault();
        const companyId = localStorage.getItem("companyId");
        const requestDto = {
            companyId: companyId,
            title: vacancyData.title,
            description: vacancyData.description,
            additionalInfo: vacancyData.additionalInfo
        };
        if (companyId) {
            axios.put('/api/vacancy/' + vacancyId, requestDto)
                .then((response) => {
                    navigate('/vacancy')
                })
                .catch(error => {
                    console.log("Error");
                })
        } else {
            console.log("Company did not found.");
        }
    }

    const handleChange = (event) => {
        const {name, value} = event.target;
        setVacancyData({...vacancyData, [name]: value});
    };

    return (
        <form onSubmit={handleSubmit} className={styles.form_resume}>
            <h2 className={styles.title_resume}>Створення вакансії</h2>
            <div className={styles.form_group}>
                <label className={styles.label_form} htmlFor="title">Заголовок</label>
                <input className={styles.input_form}
                       type="text"
                       id="title"
                       name="title"
                       defaultValue={vacancyData.title}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} htmlFor="description">Опис вакансії</label>
                <textarea className={styles.input_form}
                          id="description"
                          name="description"
                          defaultValue={vacancyData.description}
                          onChange={handleChange}
                >
                </textarea>
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} htmlFor="additionalInfo">Додаткова інформація</label>
                <textarea className={styles.input_form}
                          id="additionalInfo"
                          name="additionalInfo"
                          defaultValue={vacancyData.additionalInfo}
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

export {CompanyVacancy};