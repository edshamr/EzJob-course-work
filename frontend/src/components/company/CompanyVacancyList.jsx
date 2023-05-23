import styles from '../../styles/vacancy_list.module.css'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";

const CompanyVacancyList = ({ vacancies }) => {
    const [currentPage, setCurrentPage] = useState(1);
    const vacanciesPerPage = 5;

    const indexOfLastVacancy = currentPage * vacanciesPerPage;
    const indexOfFirstVacancy = indexOfLastVacancy - vacanciesPerPage;
    const currentVacancies = vacancies.slice(indexOfFirstVacancy, indexOfLastVacancy);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    }

    useEffect(() => {
        console.log(vacancies)
    })

    return (
        <>
        <div className={styles.layout_a}>
            <Link to="/company/vacancy/form" className={styles.apply_button}>Add vacancy</Link>
        </div>
        <div className={styles.vacancy_list}>
                {currentVacancies.map((vacancy) => (
                <div className={styles.vacancy_item} key={vacancy.id}>
                    <div className={styles.vacancy_info}>
                        <div className={styles.vacancy_title}>{vacancy.title}</div>
                    </div>
                    <Link to={`/company/vacancy/${vacancy.id}`} className={styles.apply_button}>View Details</Link>
                </div>
                ))}
        </div>
            <div className={styles.pageButtons}>
                <ul className={styles.div_center}>
                {Array.from({ length: Math.ceil(vacancies.length / vacanciesPerPage) }, (_, index) => {
                    const pageNumber = index + 1;

                    return (
                        <li className={styles.active_li}>
                        <button
                            key={index}
                            className={styles.page_button}
                            onClick={() => handlePageChange(pageNumber)}
                        >
                            {pageNumber}
                        </button>
                        </li>
                    );
                })}
                </ul>
                </div>
        </>
    );
};

export default CompanyVacancyList;
