import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "./authContext";

import { Button } from 'react-bootstrap';

export default function Navbar() {
  const authContext = useAuth();

  const handleLogout = () => {
    authContext.logout();
  };

  return (
    <nav> 

      <Button>Button</Button>

        <button>
          <Link to="/">Home</Link>
        </button>
        {authContext.isAuthenticated ? (
          <>
            <button>
              <Link to="/tags">Tags</Link>
            </button>
            <button>
              <Link to="/tasks">Tasks</Link>
            </button>
        
              <button onClick={handleLogout}>Logout</button>
        
          </>
        ) : (
          <>
            <button>
              <Link to="/login">Login</Link>
            </button>
            <button>
              <Link to="/register">Register</Link>
            </button>
          </>
        )}

    </nav>
  );
}
