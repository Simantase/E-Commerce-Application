import React, { useState, useEffect } from "react";
import axios from "axios";

const Mobile = () => {
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/products/allProducts",
          {
            headers: {
              "Content-Type": "application/json",
            },
            withCredentials: true,
          }
        );
        console.log("Response Data:", response.data); // Log the response data
        setProducts(response.data);
        setFilteredProducts(response.data.statusData);
      } catch (error) {
        console.error("Error fetching products:", error); // Log any errors
      }
    };
    
    fetchProducts();
  }, []);

  return (
    <div className="container mx-auto px-4 py-8 mt-16">
    <h1 className="text-3xl font-bold mb-4">Deals On Mobile</h1>
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
      {filteredProducts
        .filter(product => product.productCatagory === 'MOBILE')
        .map(product => (
          <div key={product.productId} className="bg-white shadow-2xl rounded p-4">
            <h2 className="text-xl font-bold mb-2">{product.productName}</h2>
            <p className="text-gray-600 mb-2">Description: {product.productDescription}</p>
            <p className="text-gray-600 mb-2">Price: ₹{product.productPrice}</p>
            <p className="text-gray-600 mb-2">Quantity: {product.productQuantity}</p>
            <p className="text-gray-600 mb-2">Availability Status: {product.availabilityStatus}</p>
            <p className="text-gray-600">Product Category: {product.productCatagory}</p>
            <img src={`http://localhost:8080/api/v1/images/${product.productId}`}/>
          </div>
        ))}
    </div>
  </div>
  
  );
};
export default Mobile;
