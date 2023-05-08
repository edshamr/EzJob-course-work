import styles from '../styles/HomePage.module.css'

function HomePage() {
    return (
      <main>
        <h1 className={styles.title}>Найдите свою работу мечты</h1>
        <form className={styles.form_home}>
          <input className={styles.input_home} type="text" placeholder="Введите ключевые слова" />
          <input className={styles.input_home} type="text" placeholder="Город" />
          <button className={styles.button_home} type="submit">Найти вакансии</button>
        </form>
      </main>
    );
  }
  
  export {HomePage};