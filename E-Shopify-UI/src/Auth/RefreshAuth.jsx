import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const RefreshAuth = () => {
  const [auth, setAuth] = useState({
    userId: "",
    name: "",
    role: "CUSTOMER",
    authenticated: false,
    accessExpiration: 0,
    refreshExpiration: 0,
  });
  const navigate = useNavigate();
  const doRefresh = async () => {
    // Fire Refresh Request To Server
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/refreshLoginAndTokenRotation",
        {},
        { withCredentials: true }
      );
      if (response == 200) {
        user = {
          userId: response.data.statusData.userId,
          name: response.data.statusData.userName,
          role: response.data.statusData.userRole,
          authenticated: true,
          accessExpiry: response.data.statusData.accessExpiry,
          refreshExpiry: response.data.statusData.refreshExpiry,
        };
        localStorage.setItem("userData", JSON.stringify(userData));
        setAuth(userData);
      } else {
        console.log(response.data);
      }
    } catch (error) {
      console.log(error);
    }
  };
  // core logic to validate against localStorage then decide whether to hit the server or not,
  const handleRefresh = async () => {
    const user = JSON.parse(localStorage.getItem("userData")); // Convert The Object Into JSON Format

    if (user != null) {
      const accessExpiry = new Date(user.accessExpiry);
      const refreshExpiry = new Date(user.refreshExpiry);

      if (refreshExpiry > new Date()) {
        console.log("Refresh Token Is Not Expired");
        if (accessExpiry > new Date()) {
          console.log("Access Token Is Not Expired");
          setAuth(user);
        } else {
          doRefresh();
          navigate("/");
        }
      } else {
        console.log("User Login Is Expired");
        doRefresh();
        navigate("/");
      }
    }
  };
  let refreshing = false;
  useEffect(() => {
    if (!refreshing) {
      console.log("Refreshing...");
      refreshing = true;
      handleRefresh();
    }
  }, []);

  return { auth };
};
export default RefreshAuth;
