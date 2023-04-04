import './App.css';
import React, { useState, useEffect } from 'react';
import User from "./utils/User";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          Learn React
        </p>
        < User/>
      </header>
    </div>
  );
}

export default App;