import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AddAddress = () => {
  const [streetAddress, setStreetAddress] = useState("");
  const [streetAddressAdditional, setStreetAddressAdditional] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [country, setCountry] = useState("");
  const [pincode, setPincode] = useState("");
  const [addressType, setAddressType] = useState("");
  const [buttonColor, setButtonColor] = useState("");
  const [buttonColor1, setButtonColor1] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/addresses",
        {
          streetAddress: streetAddress,
          streetAddressAdditional: streetAddressAdditional,
          city: city,
          state: state,
          country: country,
          pincode: pincode,
          addressType: addressType,
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
      setStreetAddress("");
      setStreetAddressAdditional("");
      setCity("");
      setState("");
      setCountry("");
      setPincode("");
      setAddressType("");
      //Show Success Messgae
      confirm("Are You Sure To Add Address Information?")
      // Redirect to Home page after successful submission
      navigate("/"); // Ensure the route is correct
    } catch (error) {
      console.error("Error submitting form data:", error);
      // Handle error if needed
    }
  };

  return (
    <div className="mx-auto mt-8 p-6 bg-white rounded-lg shadow-2xl max-w-xl w-full max-h-fit">
      <h1 className="text-3xl font-bold mb-4 text-gray-800 text-center">
        Add Address
      </h1>
      <form onSubmit={handleSubmit} className="space-y-6">
        <div>
          <label
            htmlFor="streetAddress"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Street Address:
          </label>
          <input
            type="text"
            id="streetAddress"
            name="streetAddress"
            value={streetAddress}
            onChange={(e) => setStreetAddress(e.target.value)}
            placeholder="Enter Street Address"
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div>
          <label
            htmlFor="streetAddressAdditional"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Additional Street Address:
          </label>
          <input
            type="text"
            id="streetAddressAdditional"
            name="streetAddressAdditional"
            value={streetAddressAdditional}
            onChange={(e) => setStreetAddressAdditional(e.target.value)}
            placeholder="Enter Additional Street Address"
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div>
            <label
              htmlFor="city"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              City:
            </label>
            <input
              type="text"
              id="city"
              name="city"
              value={city}
              onChange={(e) => setCity(e.target.value)}
              placeholder="Enter City"
              className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
            />
          </div>
          <div>
            <label
              htmlFor="state"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              State:
            </label>
            <input
              type="text"
              id="state"
              name="state"
              value={state}
              onChange={(e) => setState(e.target.value)}
              placeholder="Enter State"
              className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
            />
          </div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div>
            <label
              htmlFor="country"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              Country:
            </label>
            <input
              type="text"
              id="country"
              name="country"
              value={country}
              onChange={(e) => setCountry(e.target.value)}
              placeholder="Enter Country"
              className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
            />
          </div>
          <div>
            <label
              htmlFor="pincode"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              PinCode:
            </label>
            <input
              type="text"
              id="pincode"
              name="pincode"
              value={pincode}
              onChange={(e) => setPincode(e.target.value)}
              placeholder="Enter PinCode"
              className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
            />
          </div>
        </div>
        <div>
          <label
            htmlFor="addressType"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Address Type:
          </label>
          <button
            type="button"
            onClick={() => {
              setAddressType("HOME");
              setButtonColor("#FFA500"); // Change button color on click
            }}
            className={`rounded-full px-3 py-1 hover:bg-amber-500 ${
              buttonColor === "#FFA500"
                ? "bg-green-500 text-white"
                : "bg-amber-400 text-gray-700"
            }`}
          >
            HOME
          </button>
          &nbsp;&nbsp;&nbsp;
          <button
            type="button"
            onClick={() => {
              setAddressType("WORK");
              setButtonColor1("#FFA500"); // Change button color on click
            }}
            className={`rounded-full px-3 py-1 hover:bg-amber-500 ${
              buttonColor1 === "#FFA500"
                ? "bg-green-500 text-white"
                : "bg-amber-400 text-gray-700"
            }`}
          >
            WORK
          </button>
        </div>
        <button
          type="submit"
          className="bg-orange-600 hover:bg-orange-700 text-white rounded-md px-4 py-2 w-full font-bold"
          onClick={handleSubmit}
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default AddAddress;
