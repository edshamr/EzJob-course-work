import {useState} from "react";
import styles from "../../styles/vacancy_list.module.css";
import {Link} from "react-router-dom";

const ResumeList = ({resumes}) => {
    const [currentPage, setCurrentPage] = useState(1);

    const resumesPerPage = 5;
    const indexOfLastResume = currentPage * resumesPerPage;
    const indexOfFirstResume = indexOfLastResume - resumesPerPage;
    const currentResumes = resumes.slice(indexOfFirstResume, indexOfLastResume);

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    }

    return (
        <>
            <div className={styles.vacancy_list}>
                {currentResumes.map((resume) => (
                    <div className={styles.vacancy_item} key={resume.resumeId}>
                        <div className={styles.vacancy_info}>
                            <div className={styles.vacancy_title}>{resume.position}</div>
                        </div>
                        <Link to={`/company/resume/${resume.resumeId}`} className={styles.apply_button}>
                            View Details
                        </Link>
                    </div>
                ))}
            </div>
            <div>
            <ul className={styles.div_center}>
                {Array.from({ length: Math.ceil(resumes.length / resumesPerPage) }, (_, index) => {
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
export {ResumeList};