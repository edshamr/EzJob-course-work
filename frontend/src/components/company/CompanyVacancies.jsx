import {useEffect, useState} from "react";
import axios from "axios";
import VacancyList from "./VacancyList";

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
                <VacancyList vacancies={vacancies}/>
            </div>
        </div>
    );
}

export {CompanyVacancies};