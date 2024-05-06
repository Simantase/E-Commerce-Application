import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios"; // Import axios for API requests

const VerifyOTP = () => {
  const [otp, setOTP] = useState(""); // State to store the OTP
  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate(); // Initialize navigate from useNavigate hook

  useEffect(() => {
    const userEmail = sessionStorage.getItem("userEmail");
    if (userEmail) {
      // console.log("User Email from session storage:", userEmail);
      // You can use userEmail in your API request or other logic here
    }
  }, []); // Empty dependency array ensures this effect runs only once on mount

  const handleOTP = (e) => {
    const enteredOTP = e.target.value;
    const otpPattern = /^[0-9]{6}$/;

    if (!enteredOTP.match(otpPattern)) {
      setErrorMessage("Please Enter A Valid OTP Of 6 Digits");
    } else {
      setErrorMessage("");
    }
    setOTP(enteredOTP);
  };

  const handleSignup = async () => {
    // Check for valid OTP, userEmail, and handle errors
    if (otp && sessionStorage.getItem("userEmail")) {
      const userEmail = sessionStorage.getItem("userEmail");
      try {
        // Make a POST request to your backend API
        const response = await axios.post(
          "http://localhost:8080/api/v1/users/verifyEmail",
          {
            otp: otp,
            email: userEmail, // Include userEmail in the request
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        console.log("Verification successful:");

        // Redirect to login page after successful OTP verification and backend interaction
        navigate("/login"); // Ensure the route is correct
      } catch (error) {
        console.error("Error verifying OTP:", error);
        // Alert if OTP is incorrect
        alert("Incorrect OTP. Please try again.");
      }
    } else {
      // Update error states if OTP or userEmail are invalid or empty
      if (!otp) setErrorMessage("OTP is Required");
      if (!sessionStorage.getItem("userEmail"))
        setErrorMessage("User email not found");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
      <div className="bg-white p-8 shadow-md max-w-xl w-full max-h-fit hover:shadow-2xl">
        <h2 className="text-3xl font-bold mb-6 text-center ">Verify OTP</h2>
        <input
          type="text"
          placeholder="Enter 6-Digit OTP"
          value={otp}
          onChange={handleOTP}
          className="border border-gray-400 rounded-md px-3 py-2 mb-4 w-full focus:outline-none focus:border-blue-500"
        />
        {errorMessage && (
          <div className="text-red-500 text-xs">{errorMessage}</div>
        )}
        <button
          onClick={handleSignup}
          className="bg-orange-600 hover:bg-orange-700 text-white rounded-md px-4 py-2 w-full font-bold"
        >
          Submit
        </button>
      </div>
    </div>
  );
};

export default VerifyOTP;
