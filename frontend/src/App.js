import { Routes, Route} from "react-router-dom";

import { LoginForm } from './components/LoginForm';
import { HomePage } from './components/HomePage';
import { NotFound } from "./components/NotFound";

import { Layout } from "./components/Layout";

export default function App() {
  return (
    <>
    <div>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<HomePage />} />
          <Route path="login" element={<LoginForm />} />
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </div>
    </>
  );
}
