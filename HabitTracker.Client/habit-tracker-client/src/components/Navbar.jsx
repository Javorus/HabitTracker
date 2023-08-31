import { Link } from "react-router-dom";
import { useAuth } from "./authContext";

const Navbar = () => {
  const authContext= useAuth();

  return (
    <div>
      {authContext.isAuthenticated ? (
          <button onClick={authContext.logout}>Logout</button>
          ) : (
          <button onClick={authContext.login}>Login</button>
      )}
    </div>
  );
};

export default Navbar;
