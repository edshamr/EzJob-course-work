import {useState} from "react";
import styles from "../../styles/vacancy_list.module.css";
import {Link} from "react-router-dom";

const UserVacancyList = ({vacancies}) => {
    const [currentPage, setCurrentPage] = useState(1);
    const vacanciesPerPage = 5;

    const indexOfLastVacancy = currentPage * vacanciesPerPage;
    const indexOfFirstVacancy = indexOfLastVacancy - vacanciesPerPage;
    const currentVacancies = vacancies.slice(indexOfFirstVacancy, indexOfLastVacancy);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    }

    return (
        <>
            <div className={styles.vacancy_list}>
                {currentVacancies.map((vacancy) => (
                    <div className={styles.vacancy_item} key={vacancy.id}>
                        <div className={styles.vacancy_info}>
                            <div className={styles.vacancy_title}>{vacancy.title}</div>
                        </div>
                        <Link to={`/user/vacancy/${vacancy.id}`} className={styles.apply_button}>
                            View Details
                        </Link>
                    </div>
                ))}
            </div>
            <div>
                {Array.from({length: Math.ceil(vacancies.length / vacanciesPerPage)}, (_, index) => {
                    return (
                        <button key={index} onClick={() => handlePageChange(index + 1)}>
                            {index + 1}
                        </button>
                    )
                })}
            </div>
        </>
    );
};
export {UserVacancyList};