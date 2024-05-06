import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AddContact = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [contactNumber, setContactNumber] = useState("");
  const [priority, setPriority] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/addresses/1/contacts",
        {
          name: name,
          email: email,
          contactNumber: contactNumber,
          priority: priority,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true,
        }
      );
      console.log("Response from backend:", response.data);
// Clear form fields
      setName("");
      setEmail("");
      setContactNumber("");
      setPriority("");
      //Show Success Messgae
      confirm("Are You Sure To Add Contact Information?");
      // Redirect to Home page after successful submission
      navigate("/"); // Ensure the route is correct
    } catch (error) {
      console.error("Error submitting form data:", error);
      // Handle error if needed
    }
  };

  const handlePriorityClick = (priorityType) => {
    setPriority(priorityType);
  };

  return (
    <div className="mx-auto mt-8 p-6 bg-white rounded-lg shadow-2xl max-w-xl w-full max-h-fit">
      <form onSubmit={handleSubmit} className="space-y-6">
        <h1 className="text-3xl font-bold mb-4 text-gray-800 text-center h-max">
          Add Contact Details
        </h1>
        <div>
          <label
            htmlFor="name"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Name:
          </label>
          <input
            type="text"
            id="name"
            name="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter Name"
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div>
          <label
            htmlFor="email"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Email:
          </label>
          <input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter Email"
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div>
          <label
            htmlFor="contactNumber"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Contact Number:
          </label>
          <input
            type="tel"
            id="contactNumber"
            name="phoneNumber"
            value={contactNumber}
            onChange={(e) => setContactNumber(e.target.value)}
            placeholder="Enter Contact Number"
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div>
          <label
            htmlFor="priority"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Priority:
          </label>
          <button
            type="button"
            onClick={() => handlePriorityClick("PRIMARY")}
            className={`bg-green-500 text-white rounded-full px-3 py-1 text-sm ${
              priority === "PRIMARY" ? "bg-green-700" : ""
            }`}
          >
            PRIMARY
          </button>
          <button
            type="button"
            onClick={() => handlePriorityClick("ADDITIONAL")}
            className={`bg-green-500 text-white rounded-full px-3 py-1 ml-7 text-sm ${
              priority === "ADDITIONAL" ? "bg-green-700" : ""
            }`}
          >
            ADDITIONAL
          </button>
        </div>
        <button
          type="submit"
          className="bg-orange-600 hover:bg-orange-700 text-white rounded-md px-4 py-2 w-full font-bold"
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default AddContact;
