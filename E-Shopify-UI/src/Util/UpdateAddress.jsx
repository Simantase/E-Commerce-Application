import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UpdateAddress = ({ addressId }) => {
  const [addressData, setAddressData] = useState(null);

  useEffect(() => {
    const fetchAddressData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/addresses/${addressId}`);
        setAddressData(response.data);
        console.log('Address data:', response.data); // Log address data here
      } catch (error) {
        console.error('Error fetching address data:', error);
      }
    };

    fetchAddressData();
  }, [addressId]);

  return (
    <div>
      {addressData ? (
        <div>
          <h1>{response.addressData.addressId}</h1>
          <p>{addressData.city}, {addressData.state}, {addressData.country}</p>
          {/* Display other address details as needed */}
        </div>
      ) : (
        <p>Loading address data...</p>
      )}
    </div>
  );
};

export default UpdateAddress;
