import {useEffect, useState} from "react";
import axios from "axios";
import CompanyVacancyList from "./CompanyVacancyList";
import useRoles from "../../hooks/useRoles";
import {NotFound} from "../error/NotFound";


function AllCompanyVacancies() {
    const [vacancies, setVacancies] = useState([]);
    const [error, setError] = useState(false);
    const role = useRoles();

    useEffect(() => {
        const companyId = localStorage.getItem("companyId");
        if (role !== "COMPANY") {
            setError(true);
        } else {
            setError(false);
        }
        if (companyId) {
            axios.get("/api/vacancy/company", {
                params: {
                    companyId: companyId
                }
            })
                .then(response => {
                    setVacancies(response.data);
                })
        }
    }, [role])

    if (error) {
        return <NotFound />
    }
    return (
        <div>
            <div >
                <CompanyVacancyList vacancies={vacancies}/>
            </div>
        </div>
    );
}

export {AllCompanyVacancies};