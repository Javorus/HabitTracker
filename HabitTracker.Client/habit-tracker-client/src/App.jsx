import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import "./App.css";
import AuthProvider, { useAuth } from "./components/authContext";
import Login from "./components/Login";
import Register from "./components/Register";
import Navbar from "./components/Navbar";
import ListTasks from "./components/ListTasks";

function AuthenticatedRoute({ children }) {
  const { isAuthenticated } = useAuth();
  if (isAuthenticated) return children;

  return <Navigate to="/"/>
}

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <Navbar />
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route
              path="/tasks"
              element={
                <AuthenticatedRoute>
                  <ListTasks />
                </AuthenticatedRoute>
              } 
            />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;
