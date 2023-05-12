import { Routes, Route} from "react-router-dom";

import { LoginForm } from './components/LoginForm';
import { RegistrationForm } from './components/RegistrationForm';
import { HomePage } from './components/HomePage';
import { NotFound } from "./components/NotFound";
import { UserProfile } from "./components/UserProfile";
import { ResumeForm } from "./components/ResumeForm";

import { Layout } from "./components/Layout";

export default function App() {
  return (
      <>
        <div>
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route index element={<HomePage />} />
              <Route path="login" element={<LoginForm />} />
              <Route path="registration" element={<RegistrationForm />} />
              <Route path="profile" element={<UserProfile />} />
              <Route path="resume" element={<ResumeForm />} />
              {/*<Route path="company/home" element={<CompanyPage />} />*/}
              <Route path="*" element={<NotFound />} />
            </Route>
          </Routes>
        </div>
      </>
  );
}
