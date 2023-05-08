import React, {useEffect, useState} from 'react';
import styles from '../styles/Login.module.css'
import axios from 'axios'

function RegistrationForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.post('/auth/registration', { username, password, email })
      .then(response => {
        // Handle successful login
      })
      .catch(error => {
        // Handle registration error
        if (error.response && error.response.data) {
          setError(error.response.data.description);
        } else {
          setError('An error occurred. Please try again.');
        }
      });

    // Reset the form
    setUsername('');
    setPassword('');
    setEmail('');
    setError('');
  };

  return (
    <>
    <div className={styles.main_login_container}>
     <div className={styles.login_form}>
      <form onSubmit={handleSubmit} className={styles.login_form}>
      <h2 className={styles.title}>Registration</h2>
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
        <div className={styles.form_group}>
        <label className={styles.form_group_label}>
          Email:
          <input className={styles.form_group_input}
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </label>
          {error && <p className={styles.error_message}>{error}</p>}
        </div>
        <button type="submit" className={styles.button_sub}>Sign up</button>
        <p className={styles.signup_link}>Уже есть аккаунт?<a href="./login" className={styles.signup_link_a}>Войти</a></p>
      </form>
    </div>
    </div>
    </>
  );
}

export {RegistrationForm};