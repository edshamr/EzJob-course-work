import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";


const initialState = {
    id: 0,
    title: "",
    description: "",
    additionalInfo: ""
};

function VacancyDetails(vacancies) {
    const navigate = useNavigate();

    const path = window.location.pathname;
    const pathSegments = path.split('/');
    const vacancyId = pathSegments[pathSegments.length - 1];

    const [vacancyData, setVacancyData] = useState(initialState);

    useEffect(() => {
        axios.get("/api/vacancy/" + vacancyId)
            .then(response => {
                setVacancyData((prevVacancyData) => ({
                    ...prevVacancyData,
                    ...response.data
                }));
                console.log(response.data);
            })
            .catch((error) => {
                if ((error.response.status === 401 || error.response.status === 403) && localStorage.getItem('token')) {
                    localStorage.removeItem('token');
                    navigate('/login')
                }
                console.log(error.response.data.description);
            });
    }, [])

    return (
        <div>
            <div>
                {vacancyData.title}
            </div>
            <div>
                {vacancyData.description}
            </div>
            <div>
                {vacancyData.additionalInfo}
            </div>
        </div>
    );
}

export {VacancyDetails};