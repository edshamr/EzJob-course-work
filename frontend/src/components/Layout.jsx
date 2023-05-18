import {Outlet} from 'react-router-dom'

import {CustomLink} from './CustomLink';

import '../styles/Header.css'
import useRoles from "../hooks/useRoles";


function Layout() {
    const role = useRoles();

    return (
        <>
            <header className='header-menu'>
                <div className='div-header'>
                    <nav>
                        <div className='menu'>
                            <ul>
                                <li><CustomLink to="/">Home</CustomLink></li>
                                <li><CustomLink to="/login">Login</CustomLink></li>
                                {role === "USER" && (
                                    <>
                                        <li><CustomLink to="/resume">Резюме</CustomLink></li>
                                        <li><CustomLink to="user/profile">Профиль</CustomLink></li>
                                    </>
                                )}
                                {role === "COMPANY" && (
                                    <>
                                        <li><CustomLink to="/vacancy">Вакансії</CustomLink></li>
                                        <li><CustomLink to="/company">Компанія</CustomLink></li>
                                        <li><CustomLink to="company/profile">Профиль</CustomLink></li>
                                    </>
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