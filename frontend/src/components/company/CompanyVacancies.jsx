import {useEffect, useState} from "react";
import axios from "axios";
import VacancyList from "./VacancyList";
import {Link} from "react-router-dom";

function CompanyVacancies() {
    const [vacancies, setVacancies] = useState([]);

    useEffect(() => {
        const companyId = localStorage.getItem("companyId");
        if (companyId) {
            axios.get("/api/vacancy", {
                params: {
                    id: companyId
                }
            })
                .then(response => {
                    console.log(response.data);
                    setVacancies(response.data);
                })
        }
    }, [])

    return (
        <div>
            <div>
              <Link to="/vacancy/form">Add vacancy</Link>
            </div>
            <div>
                <h1>Vacancies</h1>
                <VacancyList vacancies={vacancies}/>
            </div>
        </div>
    );
}

export {CompanyVacancies};