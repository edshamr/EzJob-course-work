import styles from '../styles/HomePage.module.css'
import axios from 'axios';

function HomePage() {
    console.log(localStorage.getItem('token'))
    function logout() {
        axios.post('/api/logout/expire', {})
            .then(() => {
                localStorage.removeItem('token');
            })
            .catch(error => {
                if (error.status === 403) {
                    localStorage.removeItem('token');
                }
            });
    }

    return (
        <main>
            <h1 className={styles.title}>Найдите свою работу мечты</h1>
            <form className={styles.form_home}>
                <input className={styles.input_home} type="text" placeholder="Введите ключевые слова" />
                <input className={styles.input_home} type="text" placeholder="Город" />
                <button className={styles.button_home} type="submit">Найти вакансии</button>
            </form>

            {localStorage.getItem('token') && (
            <form onSubmit={logout} className={styles.form_home}>
                <button className={styles.button_home} type="submit">Выйти</button>
            </form>
            )}
        </main>
    );
}

export {HomePage};