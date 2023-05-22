import {Outlet} from 'react-router-dom'

import {CustomLink} from './CustomLink';

import '../styles/Header.css'
import useRoles from "../hooks/useRoles";
import axios from "axios";


function Layout() {
    const role = useRoles();

    function logout() {
        axios.post('/api/logout/expire', {})
            .then(() => {
                localStorage.removeItem('token');
                localStorage.removeItem('companyId')
                localStorage.removeItem('resumeId')
            })
            .catch(error => {
                if (error.status === 403) {
                    localStorage.removeItem('token');
                }
            });
    }

    return (
        <>
            <header className='header-menu'>
                <div className='div-header'>
                    <nav>
                        <div className='menu'>
                            <ul>
                                <li><CustomLink to="/">Home</CustomLink></li>
                                {localStorage.getItem('token') ? (
                                    <>
                                        {role === "USER" && (
                                            <>
                                                <li><CustomLink to="/resume">Резюме</CustomLink></li>
                                                <li><CustomLink to="user/profile">Профиль</CustomLink></li>
                                            </>
                                        )}
                                        {role === "COMPANY" && (
                                            <>
                                                <li><CustomLink to="/company/vacancy">Вакансії</CustomLink></li>
                                                <li><CustomLink to="/company">Компанія</CustomLink></li>
                                                <li><CustomLink to="company/profile">Профиль</CustomLink></li>
                                            </>
                                        )}
                                        <li>
                                            <div className='logout-form'>
                                                <form onSubmit={logout}>
                                                    <button type="submit">Вийти</button>
                                                </form>
                                            </div>
                                        </li>
                                    </>
                                ) : (
                                    <li><CustomLink to="/login">Login</CustomLink></li>
                                )}
                            </ul>
                        </div>
                    </nav>
                </div>
            </header>
            <Outlet/>
        </>
    );
}

export {Layout};