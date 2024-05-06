import React, { useState } from "react";
import axios from "axios"; // Import Axios
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

const Register = ({ role }) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [nameError, setNameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const navigate = useNavigate(); // Instance of useNavigate

  const handleNameChange = (e) => {
    const newName = e.target.value;
    setName(newName);
    // Validation logic for username
    if (newName && !/^[a-zA-Z\s]*$/.test(newName)) {
      setNameError("Name should contain only alphabetical characters");
    } else {
      setNameError("");
    }
  };

  const handleEmailChange = (e) => {
    const newEmail = e.target.value;
    setEmail(newEmail);
    // Validation logic for email
    if (newEmail && !/^\S+@\S+\.\S+$/.test(newEmail)) {
      setEmailError("Please enter a valid email address");
    } else {
      setEmailError("");
    }
  };

  const handlePasswordChange = (e) => {
    const newPassword = e.target.value;
    setPassword(newPassword);
    // Validation logic for password
    if (
      newPassword &&
      !/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/.test(newPassword)
    ) {
      setPasswordError(
        "Password must contain at least one uppercase letter, one lowercase letter, one number, and be at least 8 characters long"
      );
    } else {
      setPasswordError("");
    }
  };

  const handleSubmit = async () => {
    if (
      !nameError &&
      !emailError &&
      !passwordError &&
      name &&
      email &&
      password
    ) {
      try {
        // Make a POST request to your backend API
        const response = await axios.post(
          "http://localhost:8080/api/v1/users/register",
          {
            name: name,
            email: email,
            password: password,
            userRole: role,
          }
        );
        // Store email in session storage
        sessionStorage.setItem("userEmail", email);

        // Clear form fields
        setName("");
        setEmail("");
        setPassword("");

        // Redirect to Verify OTP page
        navigate("/verifyOTP"); // Ensure the route is correct
      } catch (error) {
        console.error("Error registering:", error);
      }
    } else {
      // Update error states if fields are empty
      if (!name) setNameError("Name is required");
      if (!email) setEmailError("Email is required");
      if (!password) setPasswordError("Password is required");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
      <div className="bg-white p-8 shadow-md max-w-xl w-full max-h-fit hover:shadow-2xl">
        <h2 className="text-3xl font-bold mb-6 text-center ">
          {" "}
          Registration Form As {role}
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <input
              type="text"
              placeholder="Enter Your Name"
              value={name}
              onChange={handleNameChange}
              className="w-full px-3 py-2 rounded border border-gray-300 focus:outline-none focus:border-blue-500"
            />
            {nameError && <p className="text-red-500 text-sm">{nameError}</p>}
          </div>
          <div className="mb-4">
            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={handleEmailChange}
              className="w-full px-3 py-2 rounded border border-gray-300 focus:outline-none focus:border-blue-500"
            />
            {emailError && <p className="text-red-500 text-sm">{emailError}</p>}
          </div>
          <div className="mb-6">
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={handlePasswordChange}
              className="w-full px-3 py-2 rounded border border-gray-300 focus:outline-none focus:border-blue-500"
            />
            {passwordError && (
              <p className="text-red-500 text-sm">{passwordError}</p>
            )}
          </div>
          <button
            type="button"
            className="bg-orange-600 hover:bg-orange-700 text-white rounded-md px-4 py-2 w-full font-bold"
            onClick={handleSubmit}
          >
            Submit
          </button>
          <p className="text-gray-600 mt-4 text-center">
            Already have an account?
            <Link to="/login" className="text-blue-500 font-bold">
              Log In
            </Link>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Register;
