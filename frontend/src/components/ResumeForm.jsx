import styles from '../styles/ResumeForm.module.css'

function ResumeForm() {
    return (
        <form className={styles.form_resume}>
        <h2 className={styles.title_resume}>Створення резюме</h2>
        <div className={styles.form_group}>
          <label className={styles.label_form} for="name">Ім'я:</label>
          <input type="text" id="name" name="name" required />
        </div>
        <div className={styles.form_group}>
          <label className={styles.label_form} for="email">Електронна пошта:</label>
          <input className={styles.input_form} type="email" id="email" name="email" required />
        </div>
        <div className={styles.form_group}>
          <label className={styles.label_form} for="phone">Номер телефону:</label>
          <input className={styles.input_form} type="tel" id="phone" name="phone" required />
        </div>
        <div className={styles.form_group}>
          <label className={styles.label_form} for="education">Освіта:</label>
          <input className={styles.input_form} type="text" id="education" name="education" required />
        </div>
        <div className={styles.form_group}>
          <label className={styles.label_form} for="experience">Досвід роботи:</label>
          <textarea className={styles.input_form} id="experience" name="experience" required></textarea>
        </div>
        <div className={styles.form_group}>
          <input className={styles.inpt_submit} type="submit" value="Надіслати" />
        </div>
      </form>
    );
  }
  
  export {ResumeForm};