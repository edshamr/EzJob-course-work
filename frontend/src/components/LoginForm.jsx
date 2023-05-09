import React, { useState} from 'react';
import styles from '../styles/Login.module.css';
import axios from 'axios';

axios.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers['Authorization'] = `${token}`;
  }
  return config;
});
function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.post('/auth/login', { username, password })
        .then(response => {
          // Handle successful login
          const token = response.data.token;

          // Store the token in local storage
          localStorage.setItem('token', token);

          // Reset the form
          setUsername('');
          setPassword('');
          setError('');
        })
        .catch(error => {
          // Handle login error
          if (error.response && error.response.data) {
            setError(error.response.data.description);
          } else {
            setError('An error occurred. Please try again.');
          }
        });
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
                  <input
                      className={styles.form_group_input}
                      type="text"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                  />
                </label>
              </div>
              <div className={styles.form_group}>
                <label className={styles.form_group_label}>
                  Password:
                  <input
                      className={styles.form_group_input}
                      type="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                  />
                </label>
                {error && <p className={styles.error_message}>{error}</p>}
              </div>
              <button type="submit" className={styles.button_sub}>
                Log in
              </button>
              <p className={styles.signup_link}>
                Нет аккаунта?{' '}
                <a href="./registration" className={styles.signup_link_a}>
                  Зарегистрироваться
                </a>
              </p>
            </form>
          </div>
        </div>
      </>
  );
}

export { LoginForm };
