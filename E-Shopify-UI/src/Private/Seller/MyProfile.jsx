import React, { useState, useEffect } from "react";
import axios from "axios";

const MyProfile = () => {
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProductDetails = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/products/1"); // Replace "1" with the actual product ID
        console.log(response.data)
        setProduct("Data" +response.data);
        setLoading(false);
      } catch (error) {
        setError(error.message);
        setLoading(false);
      }
    };

    fetchProductDetails();
  }, []); // Empty dependency array ensures the effect runs only once on component mount

  if (loading) return <p>Loading product details...</p>;
  if (error) return <p>Error fetching product details: {error}</p>;

  return (
    <div>
      <h1>{product.productName}</h1>
      <p>Description: {product.productDescription}</p>
      <p>Price: {product.productPrice}</p>
      <p>Quantity: {product.productQuantity}</p>
      {/* Render other product details as needed */}
    </div>
  );
};

export default MyProfile;
