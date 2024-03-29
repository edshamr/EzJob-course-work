import React, {useEffect, useState} from "react";
import styles from "../../styles/ResumeForm.module.css";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import useRoles from "../../hooks/useRoles";
import {NotFound} from "../error/NotFound";
import {Search} from "../Search";


const initialState = {
    id: 0,
    title: "",
    city: "",
    description: "",
    additionalInfo: ""
};

function VacancyForm() {
    const [formData, setFormData] = useState(initialState);
    const [error, setError] = useState(false);
    const navigate = useNavigate();
    const role = useRoles();
    const [selectedCity, setSelectedCity] = useState(null);

    useEffect(() => {
        if (role !== "COMPANY") {
            setError(true)
        } else {
            setError(false)
        }
    }, [role])

    const handleSubmit = (event) => {
        event.preventDefault();
        const companyId = localStorage.getItem("companyId");
        if (companyId) {
            const requestDto = {
                companyId: companyId,
                title: formData.title,
                city: selectedCity.label,
                description: formData.description,
                additionalInfo: formData.additionalInfo
            };
            axios.post('/api/vacancy', requestDto, {
                params: {
                    companyId: companyId
                }
            })
                .then((response) => {
                    navigate('/company/vacancy')
                })
                .catch(error => {
                    console.log("Error");
                })
        } else {
            console.log("Company did not found.");
        }
    }

    const handleCitySelect = (selectedCity) => {
        setSelectedCity(selectedCity);
    };

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    if (error) {
        return <NotFound/>
    } else {
        return (
            <form onSubmit={handleSubmit} className={styles.form_resume}>
                <h2 className={styles.title_resume}>Створення вакансії</h2>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="title">Заголовок</label>
                    <input className={styles.input_form}
                           type="text"
                           id="title"
                           name="title"
                           defaultValue={formData.title}
                           onChange={handleChange}
                    />
                </div>
                <div className={styles.form_group}>
                    <label className={styles.label_form} htmlFor="description">Опис вакансії</label>
                    <textarea className={styles.input_form}
                              id="description"
                              name="description"
                              defaultValue={formData.description}
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
                    <Search onSelectCity={handleCitySelect}/>
                </div>
                <div className={styles.form_group}>
                    <input className={styles.inpt_submit} type="submit" value="Надіслати"/>
                </div>
            </form>
        );
    }
}

export {VacancyForm};