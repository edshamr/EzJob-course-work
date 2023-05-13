import { Outlet } from 'react-router-dom'

import { CustomLink } from './CustomLink';

import '../styles/Header.css'
import useRoles from "../hooks/useRoles";


function Layout() {
    return (
        <>
    <header className='header-menu'>
        <div className='div-header'>
          <nav>
            <div className='menu'>
              <ul>
              <li><CustomLink to="/">Home</CustomLink></li>
              <li><CustomLink to="/login">Login</CustomLink></li>
              <li><CustomLink to="/resume">Резюме</CustomLink></li>
              <li><CustomLink to="/profile">Профиль</CustomLink></li>
              </ul>
            </div>
          </nav>
        </div>
    </header>
    <Outlet />
    </>
    );
  }
  
  export {Layout};