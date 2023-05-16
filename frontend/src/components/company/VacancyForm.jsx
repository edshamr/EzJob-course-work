import React, {useState} from "react";
import styles from "../../styles/ResumeForm.module.css";
import axios from "axios";


const initialState = {
    title: "",
    description: "",
    additionalInfo: ""
};
function VacancyForm() {
    const [formData, setFormData] = useState(initialState);

    const handleSubmit = (event) => {
        event.preventDefault();
        const companyId = localStorage.getItem("companyId");
        const requestDto = {
            title: formData.title,
            description: formData.description,
            additionalInfo: formData.additionalInfo
        };
        if (companyId) {
            axios.post('/api/vacancy', requestDto, {
                params: {
                    companyId: companyId
                }
            })
                .then((response) => {
                    console.log("OK");
                })
                .catch(error => {
                    console.log("Error");
                })
        } else {
            console.log("Company did not found.");
        }
    }

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
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
                <input className={styles.inpt_submit} type="submit" value="Надіслати"/>
            </div>
        </form>
    );
}

export {VacancyForm};