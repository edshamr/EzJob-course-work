import {Routes, Route} from "react-router-dom";

import {LoginForm} from './components/LoginForm';
import {RegistrationForm} from './components/RegistrationForm';
import {HomePage} from './components/HomePage';
import {NotFound} from "./components/NotFound";
import {UserProfile} from "./components/user/UserProfile";
import {ResumeForm} from "./components/user/ResumeForm";
import {CompanyVacancies} from "./components/company/CompanyVacancies";

import {Layout} from "./components/Layout";
import {CompanyForm} from "./components/company/CompanyForm";
import {VacancyForm} from "./components/company/VacancyForm";

export default function App() {
    return (
        <>
            <div>
                <Routes>
                    <Route path="/" element={<Layout/>}>
                        <Route index element={<HomePage/>}/>
                        <Route path="login" element={<LoginForm/>}/>
                        <Route path="registration" element={<RegistrationForm/>}/>
                        <Route path="profile" element={<UserProfile/>}/>
                        <Route path="resume" element={<ResumeForm/>}/>
                        <Route path="vacancy" element={<CompanyVacancies/>}/>
                        <Route path="vacancy/form" element={<VacancyForm/>}/>
                        <Route path="company" element={<CompanyForm/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Route>
                </Routes>
            </div>
        </>
    );
}
