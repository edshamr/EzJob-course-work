import {Routes, Route} from "react-router-dom";

import {LoginForm} from './components/LoginForm';
import {RegistrationForm} from './components/RegistrationForm';
import {HomePage} from './components/HomePage';
import {NotFound} from "./components/NotFound";
import {UserProfile} from "./components/user/UserProfile";
import {ResumeForm} from "./components/user/ResumeForm";
import {AllCompanyVacancies} from "./components/company/AllCompanyVacancies";

import {Layout} from "./components/Layout";
import {CompanyForm} from "./components/company/CompanyForm";
import {VacancyForm} from "./components/company/VacancyForm";
import {CompanyProfile} from "./components/company/CompanyProfile";
import {VacancyDetails} from "./components/user/VacancyDetails";
import {CompanyVacancy} from "./components/company/CompanyVacancy";

export default function App() {
    return (
        <>
            <div>
                <Routes>
                    <Route path="/" element={<Layout/>}>
                        <Route index element={<HomePage/>}/>
                        <Route path="login" element={<LoginForm/>}/>
                        <Route path="registration" element={<RegistrationForm/>}/>
                        <Route path="user/profile" element={<UserProfile/>}/>
                        <Route path="company/profile" element={<CompanyProfile/>}/>
                        <Route path="resume" element={<ResumeForm/>}/>
                        <Route path="vacancy" element={<AllCompanyVacancies/>}/>
                        <Route path="vacancy/form" element={<VacancyForm/>}/>
                        <Route path="vacancy/:id" element={<VacancyDetails/>}/>
                        <Route path="company/vacancy/:id" element={<CompanyVacancy/>}/>
                        <Route path="company" element={<CompanyForm/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Route>
                </Routes>
            </div>
        </>
    );
}
