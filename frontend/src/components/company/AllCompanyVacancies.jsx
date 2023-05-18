import {useEffect, useState} from "react";
import axios from "axios";
import CompanyVacancyList from "./CompanyVacancyList";

function AllCompanyVacancies() {
    const [vacancies, setVacancies] = useState([]);

    useEffect(() => {
        const companyId = localStorage.getItem("companyId");
        if (companyId) {
            axios.get("/api/vacancy", {
                params: {
                    companyId: companyId
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
                <CompanyVacancyList vacancies={vacancies}/>
            </div>
        </div>
    );
}

export {AllCompanyVacancies};