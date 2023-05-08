import React, {useState} from 'react';
import styles from '../styles/Login.module.css'
import axios from "axios";

function LoginForm() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        // Perform the login request to your RESTful API using username and password
        // You can use a library like Axios or fetch to make the HTTP request

        axios.post('/auth/login', {username, password})
            .then(response => {
                // Handle successful login
            })
            .catch(error => {
                // Handle login error
            });

        // Reset the form
        setUsername('');
        setPassword('');
    };

    return (
        <>
            <div className={styles.main_login_container}>
                <div className={styles.login_form}>
                    <form onSubmit={handleSubmit} className={styles.login_form}>
                        <h2 className={styles.title}>Вход</h2>
                        <div className={styles.form_group}>
                            <label className={styles.form_group_label}>
                                Username:
                                <input className={styles.form_group_input}
                                       type="text"
                                       value={username}
                                       onChange={(e) => setUsername(e.target.value)}
                                />
                            </label>
                        </div>
                        <div className={styles.form_group}>
                            <label className={styles.form_group_label}>
                                Password:
                                <input className={styles.form_group_input}
                                       type="password"
                                       value={password}
                                       onChange={(e) => setPassword(e.target.value)}
                                />
                            </label>
                        </div>
                        <button type="submit" className={styles.button_sub}>Login</button>
                        <p className={styles.signup_link}>Нет аккаунта? <a href="#"
                                                                           className={styles.signup_link_a}>Зарегистрироваться</a>
                        </p>
                    </form>
                </div>
            </div>
        </>
    );
}

export {LoginForm};