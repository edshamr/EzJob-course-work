import {useEffect, useState} from "react";
import axios from "axios";
import useRoles from "../../hooks/useRoles";
import CompanyVacancyList from "../company/CompanyVacancyList";
import styles from "../../styles/vacancy_list.module.css";
import {Link} from "react-router-dom";

const UserVacancyList = ({ vacancies }) => {
    return (
        <>
            <div className={styles.vacancy_list}>
                {vacancies.map((vacancy) => (
                    <div className={styles.vacancy_item} key={vacancy.id}>
                        <div className={styles.vacancy_info}>
                            <div className={styles.vacancy_title}>{vacancy.title}</div>
                        </div>
                        <Link to={`/user/vacancy/${vacancy.id}`} className={styles.apply_button}>View Details</Link>
                    </div>
                ))}
            </div>
        </>
    );
};
export {UserVacancyList};