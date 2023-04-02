import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from 'react';

function App() {
  const [userData, setUserData] = useState(null);
  useEffect(() => {
    fetch('auth/test')
        .then(response => response.json())
        .then(data => setUserData(data));
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        {userData && (
            <div>
              <p>Username: {userData.username}</p>
              <p>Email: {userData.email}</p>
            </div>
        )}
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
