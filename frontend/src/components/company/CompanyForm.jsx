import styles from '../../styles/ResumeForm.module.css'
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";


const initialState = {
    name: '',
    stuffCount: 0,
    country: '',
    description: '',
    additionalInfo: ""
};

function CompanyForm() {
    const [formData, setFormData] = useState(initialState);
    const navigate = useNavigate();

    useEffect(() => {
        const companyId = localStorage.getItem("companyId");
        if (companyId) {
            axios.get('api/company/' + companyId, {})
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
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        const companyId = localStorage.getItem("companyId");
        const requestDto = {
            name: formData.name,
            stuffCount: formData.stuffCount,
            country: formData.country,
            description: formData.description,
            additionalInfo: formData.additionalInfo
        };
        if (companyId) {
            axios.put('api/company/' + companyId, requestDto)
                .then((response) => {
                    console.log("OK")
                })
                .catch(error => {
                    console.log("Error")
                })
        } else {
            axios.post('api/company', requestDto)
                .then((response) => {
                    localStorage.setItem("companyId", response.data.companyId);
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

    return (
        <form onSubmit={handleSubmit} className={styles.form_resume}>
            <h2 className={styles.title_resume}>Створення компанії</h2>
            <div className={styles.form_group}>
                <label className={styles.label_form} htmlFor="name">Ім'я</label>
                <input className={styles.input_form}
                       type="text"
                       id="name"
                       name="name"
                       defaultValue={formData.name}
                       onChange={handleChange}
                />
            </div>
            <div className={styles.form_group}>
                <label className={styles.label_form} htmlFor="country">Країна</label>
                <input className={styles.input_form}
                       type="text"
                       id="country"
                       name="country"
                       defaultValue={formData.country}
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

export {CompanyForm};