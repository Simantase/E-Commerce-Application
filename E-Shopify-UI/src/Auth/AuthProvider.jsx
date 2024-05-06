import React, { createContext, useContext, useEffect, useState } from "react";
import RefreshAuth from "./RefreshAuth";

// Context Holding The Auth User Details
export const authContext = createContext({});

// Component That Returns The Authentication By Enclosing Its Child Components Within The Context
const AuthProvider = ({ children }) => {
  const [user, setUser] = useState({
    userId: "",
    name: "",
    role: "CUSTOMER",
    authenticated: false,
    accessExpiration: 0,
    refreshExpiration: 0,
  });

  const { auth } = RefreshAuth();
  useEffect(() => setUser(auth), [auth]);

  return (
    // Returning The Authentication With Values "user" and "setUser"
    // by enclosing the child components within it
    <authContext.Provider value={{ user, setUser }}>
      {children}
    </authContext.Provider>
  );
};

export default AuthProvider;

// Custom Hook That Returns The Context Values
export const useAuth = () => useContext(authContext);
