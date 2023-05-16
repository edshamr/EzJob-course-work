import React, { useState } from 'react';
import styles from '../styles/Login.module.css';
import axios from 'axios';

// Add an Axios interceptor to include the "Authorization" header
axios.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers['Authorization'] = `${token}`;
  }
  return config;
});

const initialState = {
  name: "",
  password: ""
};

const LoginForm = () => {
  const [formData, setFormData] = useState(initialState);
  const [errors, setErrors] = useState("");
  const [error, setError] = useState('');

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

    setErrors(newErrors);
    return valid;
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (validateForm()) {
      console.log("Form is valid");

      axios.post('api/auth/login', { username: formData.name, password: formData.password })
          .then(response => {
            // Handle successful login
            const token = response.data.token;
            const resumeId = response.data.resumeId;
            const companyId = response.data.companyId;
            // Store the token in local storage
            localStorage.setItem('token', token);
            if (response.data.resumeId) { 
              localStorage.setItem('resumeId', resumeId)
            }
            if (response.data.companyId) {
              localStorage.setItem('resumeId', companyId)
            }
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
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
    setErrors({ ...errors, [name]: "" });
    setError("");
  };
  return (
      <>
        <div className={styles.main_login_container}>
          <div className={styles.login_form}>
            <form onSubmit={handleSubmit} className={styles.login_form}>
              <h2 className={styles.title}>Вход</h2>
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