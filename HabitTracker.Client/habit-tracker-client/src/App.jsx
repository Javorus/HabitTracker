import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import "./App.css";
import AuthProvider, { useAuth } from "./components/authContext";
import Login from "./components/Login";
import Register from "./components/Register";
import Navbar from "./components/Navbar";
import ListTasks from "./components/ListTasks";
import TagComponent from "./components/TagComponent";
import TaskComponent from "./components/TaskComponent";


function AuthenticatedRoute({ children }) {

  const authContext = useAuth()

  if (authContext.isAuthenticated) return children;

  return <Navigate to="/"/>
}

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <Navbar />
          <Routes>
          <Route path="/" element={<TagComponent />} />
          <Route path="/tasks2" element={<TaskComponent />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/tags" element={
              <AuthenticatedRoute>
                  <TagComponent />
              </AuthenticatedRoute>
            
            } />
            <Route path="/tasks" element={
                <AuthenticatedRoute>
                  <TaskComponent />
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
