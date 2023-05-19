import styles from '../../styles/vacancy_list.module.css'
import {Link} from "react-router-dom";

const CompanyVacancyList = ({ vacancies }) => {
    return (
        <>
        <div className={styles.layout_a}>
            <Link to="/company/vacancy/form" className={styles.apply_button}>Add vacancy</Link>
        </div>
        <div className={styles.vacancy_list}>
                {vacancies.map((vacancy) => (
                <div className={styles.vacancy_item} key={vacancy.id}>
                    <div className={styles.vacancy_info}>
                        <div className={styles.vacancy_title}>{vacancy.title}</div>
                    </div>
                    <Link to={`/company/vacancy/${vacancy.id}`} className={styles.apply_button}>View Details</Link>
                </div>
                ))}
        </div>
        </>
    );
};

export default CompanyVacancyList;
