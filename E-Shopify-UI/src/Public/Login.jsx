import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { useAuth } from "../Auth/AuthProvider";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { setUser } = useAuth();
  const navigate = useNavigate();

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = {
      userName: username,
      password: password,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/login",
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true,
        }
      );

      if (response.status === 200) {
        console.log(response);
        const responseData = {
          userId: response.data.statusData.userId,
          name: response.data.statusData.userName,
          role: response.data.statusData.userRole,
          authenticated: true,
          accessExpiry: response.data.statusData.accessExpiry,
          refreshExpiry: response.data.statusData.refreshExpiry,
        };

        // Store user data in localStorage
        localStorage.setItem("userData", JSON.stringify(responseData));

        setUser(responseData);
        navigate("/myProfile");
      }
    } catch (error) {
      if (error.response) {
        console.error("Server error:", error, error.response.status);
      } else if (error.request) {
        console.error("Request failed, could not reach server.");
      } else {
        console.log("Error creating request:", error);
      }
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
      <div className="bg-white p-8 shadow-md max-w-xl w-full max-h-fit hover:shadow-2xl">
        <h2 className="text-3xl font-bold mb-6 text-center">Login Form</h2>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={handleUsernameChange}
          className="border border-gray-400 rounded-md px-3 py-2 mb-4 w-full focus:outline-none focus:border-blue-500"
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={handlePasswordChange}
          className="border border-gray-400 rounded-md px-3 py-2 mb-4 w-full focus:outline-none focus:border-blue-500"
        />
        <button
          onClick={handleSubmit}
          className="bg-orange-600 hover:bg-orange-700 text-white rounded-md px-4 py-2 w-full font-bold"
        >
          Login
        </button>
        <p className="text-gray-600 mt-4">
          <span className="text-blue-500 font-bold">
            <Link to="/register">
              New to Flipkart? &nbsp; Create an account
            </Link>
          </span>
        </p>
      </div>
    </div>
  );
}

export default Login;
