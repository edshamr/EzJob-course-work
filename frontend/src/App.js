import {Routes, Route} from "react-router-dom";

import {LoginForm} from './components/LoginForm';
import {RegistrationForm} from './components/RegistrationForm';
import {HomePage} from './components/HomePage';
import {NotFound} from "./components/error/NotFound";
import {UserProfile} from "./components/user/UserProfile";
import {ResumeForm} from "./components/user/ResumeForm";
import {AllCompanyVacancies} from "./components/company/AllCompanyVacancies";

import {Layout} from "./components/Layout";
import {CompanyForm} from "./components/company/CompanyForm";
import {VacancyForm} from "./components/company/VacancyForm";
import {CompanyProfile} from "./components/company/CompanyProfile";
import {VacancyDetails} from "./components/company/VacancyDetails";
import {CompanyVacancy} from "./components/company/CompanyVacancy";
import {UserVacancyList} from "./components/user/UserVacancyList";
import {ResumeDetails} from "./components/user/ResumeDetails";

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
                        <Route path="company/vacancy" element={<AllCompanyVacancies/>}/>
                        <Route path="company/vacancy/form" element={<VacancyForm/>}/>
                        <Route path="company/vacancy/:id" element={<CompanyVacancy/>}/>
                        <Route path="company/resume/:id" element={<ResumeDetails/>}/>
                        <Route path="user/vacancy/:id" element={<VacancyDetails/>}/>
                        <Route path="user/vacancy" element={<UserVacancyList/>}/>
                        <Route path="company" element={<CompanyForm/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Route>
                </Routes>
            </div>
        </>
    );
}
