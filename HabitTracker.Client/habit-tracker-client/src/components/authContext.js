import { apiClient } from "./api/ApiClient";
import { createContext, useContext, useState } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {

  const [isAuthenticated, setAuthenticated] = useState(false);
  const [user, setUser] = useState(null);


  function logout() {
    setUser(null);
    setAuthenticated(false);
  }

  async function register(username, password) {
    const response = await apiClient.post('/register', {
      username: username,
      password: password
    })

    if (response.status == 200) {
      console.log('User ID:', response.data);
      setAuthenticated(true);
      setUser(response.data);
    }
    else {
      console.log('Invalid credentials');
    }
    
  }


  async function login(username, password) {
    const response = await apiClient.post('/signin', {
      username: username,
      password: password
    })

    if (response.status == 200) {
      console.log('User ID:', response.data);
      setAuthenticated(true);
      setUser(response.data);
      return true;
    }
    else {
      console.log('Invalid credentials');
      return false;
    }
    
  }


  return (
    <AuthContext.Provider
      value={{ isAuthenticated, setAuthenticated, login,  logout, register, user }}
    >
      {children}
    </AuthContext.Provider>
  );
}
