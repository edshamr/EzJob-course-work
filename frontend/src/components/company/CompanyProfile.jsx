import styles from '../../styles/UserProfile.module.css'
import {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import useRoles from "../../hooks/useRoles";
import {NotFound} from "../error/NotFound";

const initialState = {
    name: '',
    email: '',
    stuffCount: 0,
    country: '',
    description: '',
    additionalInfo: ''
};

function CompanyProfile() {
    const [profileData, setProfileData] = useState(initialState);
    const [error, setError] = useState(false)
    const role = useRoles();

    useEffect(() => {
        if (role !== "COMPANY") {
            setError(true);
        } else {
            setError(false)
        }
        const companyId = localStorage.getItem("companyId")
        if (companyId) {
            axios.get("/api/company/profile", {
                params: {
                    company: companyId
                }
            })
                .then(response => {
                    setProfileData((prevProfileData) => ({
                        ...prevProfileData,
                        ...response.data
                    }));
                })
                .catch(error => {
                    console.log(error.data.description)
                });
        }
    }, [role])

    if (error) {
        return <NotFound/>
    } else {
        return (
            <>
                <div className={styles.layout_a}>
                    <Link to="/company" className={styles.apply_button}>Редагувати</Link>
                </div>
                <div className={styles.container}>
                    <div className={styles.card}>
                        <div className={styles.card_header}>
                            <div className={styles.card_title}>
                                <h1 className={styles.user_name}>{profileData.name}</h1>
                            </div>
                        </div>
                        <div className={styles.card_body}>
                            <ul className={styles.ul_body}>
                                <li className={styles.user_info}>
                                    <span className={styles.user_span}>Country:</span>
                                    {profileData.country}
                                </li>
                                <li className={styles.user_info}>
                                    <span className={styles.user_span}>Stuff count:</span>
                                    {profileData.stuffCount}
                                </li>
                                <li className={styles.user_info}>
                                    <div>
                                        <span className={styles.user_span}>Description:</span>
                                    </div>
                                    {profileData.description}
                                </li>
                                <li className={styles.user_info}>
                                    <div>
                                        <span className={styles.user_span}>Additional information:</span>
                                    </div>
                                    {profileData.additionalInfo}
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

export {CompanyProfile};