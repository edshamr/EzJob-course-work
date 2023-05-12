import {useEffect, useState} from 'react';
import axios from 'axios';

const useRoles = () => {
    const [role, setRole] = useState('');

    useEffect(() => {
        const token = localStorage.getItem('token');

        if (token) {
            axios.get('roles/resolve', {})
                .then((response) => {
                    const roleData = response.data;
                    setRole(roleData);
                })
                .catch((error) => {
                    if (error.response.status === 401) {
                        localStorage.removeItem('token');
                    }
                    console.log(error.response.data.description);
                });
        } else {
            console.log('Token does not exist');
        }
    }, []);

    return role;
};

export default useRoles;
