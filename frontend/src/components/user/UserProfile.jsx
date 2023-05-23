import styles from '../../styles/UserProfile.module.css'
import {useEffect, useState} from "react";
import axios from "axios";
import useRoles from "../../hooks/useRoles";
import {NotFound} from "../error/NotFound";

const initialState = {
    username: '',
    email: '',
    phone: '',
    position: ''
};

function UserProfile() {
    const [profileData, setProfileData] = useState(initialState);
    const [error, setError] = useState(false);
    const role = useRoles();

    useEffect(() => {
        if (role !== "USER") {
            setError(true)
        } else {
            setError(false)
        }
        const resumeId = localStorage.getItem("resumeId")
        if (resumeId) {
            axios.get("/api/user/profile", {
                params: {
                    resume: resumeId
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
                <div className={styles.container}>
                    <div className={styles.card}>
                        <div className={styles.card_header}>
                            <div className={styles.card_title}>
                                <h1 className={styles.user_name}>{profileData.username}</h1>
                            </div>
                        </div>
                        <div className={styles.card_body}>
                            <ul className={styles.ul_body}>
                                <li className={styles.user_info}>
                                    <span className={styles.user_span}>Email:</span>
                                    {profileData.email}
                                </li>
                                <li className={styles.user_info}>
                                    <span className={styles.user_span}>Phone:</span>
                                    {profileData.phone}
                                </li>
                                <li className={styles.user_info}>
                                    <span className={styles.user_span}>Education:</span>
                                    {profileData.position}
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

export {UserProfile};