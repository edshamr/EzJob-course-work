import styles from '../../styles/vacancy_list.module.css'
import {Link} from "react-router-dom";

const VacancyList = ({ vacancies }) => {
    const handleVacancyClick = (vacancyId) => {
        // Redirect to the detail page for the selected vacancy
        console.log("IN handler");
    };

    return (
        <>
        <div className={styles.layout_a}>
            <Link to="/vacancy/form" className={styles.apply_button}>Add vacancy</Link>
        </div>
        <div className={styles.vacancy_list}>
                {vacancies.map((vacancy) => (
                <div className={styles.vacancy_item} key={vacancy.id}>
                    <div className={styles.vacancy_info}>
                        <div className={styles.vacancy_title}>{vacancy.title}</div>
                        <div className={styles.vacancy_description}>{vacancy.description}</div>
                    </div>
                    <button className={styles.apply_button} onClick={() => handleVacancyClick(vacancy.id)}>View Details</button>
                </div>
                ))}
        </div>
        </>
    );
};

export default VacancyList;
