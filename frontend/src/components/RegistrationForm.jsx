import React, {useEffect, useState} from 'react';
import styles from '../styles/Login.module.css';
import axios from 'axios';


const initialState = {
    name: "",
    password: "",
    email: ""
};

const RegistrationForm = () => {
    const [formData, setFormData] = useState(initialState);
    const [errors, setErrors] = useState("");
    const [error, setError] = useState("");
    const [roles, setRoles] = useState([]);
    const [selectedRole, setSelectedRole] = useState("");

    useEffect(() => {
        axios.get("/api/roles/allRoles",{})
            .then(response => {
                setRoles(response.data)
            })
    },[])

    const validateForm = () => {
        let valid = true;
        const newErrors = {};

        if (!formData.name.trim()) {
            newErrors.name = <span style={{color: "red"}}>{"Name is required"}</span>;
            valid = false;
        }

        if (!formData.password.trim()) {
            newErrors.password = <span style={{color: "red"}}>{"Password is required"}</span>;
            valid = false;
        } else if (formData.password.trim().length < 6) {
            newErrors.password = <span style={{color: "red"}}>{"Password must be at least 8 characters"}</span>;
            valid = false;
        }

        if (!formData.email.trim()) {
            newErrors.email = <span style={{color: "red"}}>"Email is required"</span>;
            valid = false;
        } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
            newErrors.email = <span style={{color: "red"}}>"Email is invalid"</span>;
            valid = false;
        }

        setErrors(newErrors);
        return valid;
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(selectedRole)

        if (validateForm()) {
            console.log("Form is valid");

            axios.post('/api/auth/registration', {
                username: formData.name,
                password: formData.password,
                email: formData.email,
                role: selectedRole
            })
                .then(response => {
                    // Handle successful login
                    const token = response.data.token;

                    // Store the token in local storage
                    localStorage.setItem('token', token);

                })
                .catch(error => {
                    // Handle login error
                    console.log(error)
                    if (error.response && error.response.data) {
                        setError(<span style={{color: "red"}}>{error.response.data.description}</span>);
                    } else {
                        setError(<span style={{color: "red"}}>{'An error occurred. Please try again.'}</span>);
                    }
                });

            setFormData(initialState);
        } else {
            console.log("Form is invalid");
        }
    };

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
        setErrors({...errors, [name]: ""});
        setError("");
    };
    return (
        <>
            <div className={styles.main_login_container}>
                <div className={styles.login_form}>
                    <form onSubmit={handleSubmit} className={styles.login_form}>
                        <h2 className={styles.title}>Регистрация</h2>
                        <div className={styles.form_group}>
                            <label htmlFor="name" className={styles.form_group_label}>
                                Username:
                                <input
                                    className={styles.form_group_input}
                                    type="text"
                                    id="name"
                                    name='name'
                                    value={formData.name}
                                    onChange={handleChange}
                                />
                            </label>
                            {errors.name && <span className={styles.error_message}>{errors.name}</span>}
                        </div>
                        <div className={styles.form_group}>
                            <label htmlFor="password" className={styles.form_group_label}>
                                Password:
                                <input
                                    className={styles.form_group_input}
                                    type="password"
                                    id="password"
                                    name="password"
                                    value={formData.password}
                                    onChange={handleChange}
                                />
                            </label>
                            {errors.password && <span className={styles.error_message}>{errors.password}</span>}
                        </div>
                        <div className={styles.form_group}>
                            <label htmlFor="password" className={styles.form_group_label}>
                                Email:
                                <input
                                    className={styles.form_group_input}
                                    type="text"
                                    id="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                />
                            </label>
                            {errors.email && <span className={styles.error_message}>{errors.email}</span>}
                            {error && <p className={styles.error_message}>{error}</p>}
                        </div>
                        <div>
                            {roles.map(role => (
                                <div key={role}>
                                    <input
                                        type="radio"
                                        name="role"
                                        id = "role"
                                        value={role}
                                        checked={selectedRole === role}
                                        onChange={event => {
                                            setSelectedRole(event.target.value)
                                        }}
                                    />
                                    <label>{role}</label>
                                </div>
                            ))}
                        </div>
                        <button type="submit" className={styles.button_sub}>
                            Sing up
                        </button>
                        <p className={styles.signup_link}>
                            Есть аккаунт?{' '}
                            <a href="./login" className={styles.signup_link_a}>
                                Log in
                            </a>
                        </p>
                    </form>
                </div>
            </div>
        </>
    );
}

export {RegistrationForm};
