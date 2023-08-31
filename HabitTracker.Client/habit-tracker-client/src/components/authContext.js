import { createContext, useContext, useState } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
  function logout() {
    setAuthenticated(false);
  }
  function login() {
    setAuthenticated(true);
  }

  const [isAuthenticated, setAuthenticated] = useState(false);

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, setAuthenticated, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
}
