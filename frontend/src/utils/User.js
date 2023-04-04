import React, { useEffect, useState } from 'react';
import styles from '../styles/User.module.css';

function User() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            const response = await fetch('/auth/test');
            const data = await response.json();
            setUser(data);
        };

        fetchUser();
    });

    if (!user) {
        return <div>Loading...</div>;
    }

    const { username, email } = user;

    return (
        <div className={styles.myStyles}>
            <h2>{username}</h2>
            <p>{email}</p>
        </div>
    );
}

export default User;
