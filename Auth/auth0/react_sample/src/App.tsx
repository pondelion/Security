import React from 'react';
import './App.css';
import { useAuth0 } from "@auth0/auth0-react";

import LoginButton from './components/LoginButton';
import LogoutButton from './components/LogoutButton';
import UserProfile from './components/UserProfile';

function App() {
  const { user, isAuthenticated, isLoading } = useAuth0();

  return (
    <div className="App">
      {
        (() => {
          if (isLoading) {
            return (
              <div>Loading ...</div>
            )
          } else if (!isAuthenticated) {
            return (
              <LoginButton />
            );
          } else {
            return (
              <div>
                <UserProfile />
                <LogoutButton />
              </div>
            )
          }
        })()
      }
    </div>
  );
}

export default App;
