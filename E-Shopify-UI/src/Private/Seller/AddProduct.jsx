import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AddProduct = () => {
  const [productName, setProductName] = useState("");
  const [productDescription, setProductDescription] = useState("");
  const [productPrice, setProductPrice] = useState("");
  const [productQuantity, setProductQuantity] = useState("");
  const [productCatagory, setProductCatagory] = useState("");
  const navigate = useNavigate();
  const [imageType, setImageType] = useState("");
  const [productImage, setProductImage] = useState(null);
  const handlePriorityClick = (priorityType) => {
    setProductCatagory(priorityType);
  };
  const handlePriorityClick1 = (priorityType) => {
    setImageType(priorityType);
  };
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // First, send product data without image
      const productResponse = await axios.post(
        "http://localhost:8080/api/v1/products",
        {
          productName,
          productDescription,
          productPrice,
          productQuantity,
          productCatagory,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true,
        }
      );
      console.log("Response from backend:", productResponse.data);
      console.log("Give Response " + productResponse.data.statusData.productId);

      // Then, handle image upload
      const formData = new FormData();

      formData.append("image", productImage); // Assuming productImage contains the image file
      console.log("Image File:" + productImage);
      const imageResponse = await axios.post(
        `http://localhost:8080/api/v1/products/${productResponse.data.statusData.productId}/imageType/${imageType}/images`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
          withCredentials: true,
        }
      );

      console.log("Image response from backend:", imageResponse.data);

      // Clear form fields
      setProductName("");
      setProductDescription("");
      setProductPrice("");
      setProductQuantity("");
      setProductCatagory("");
      //setProductImage(null);

      // Show Success Message
      confirm("Are You Sure To Add Product And Product Image Information?");

      // Redirect to Home page after successful submission
      navigate("/"); // Ensure the route is correct
    } catch (error) {
      console.error("Error submitting form data:", error);
      // Handle error if needed
    }
  };

  return (
    <div className="mx-auto mt-8 p-6 bg-white rounded-lg shadow-xl max-w-xl w-full max-h-fit hover:shadow-2xl">
      <h1 className="text-3xl font-bold mb-4 text-gray-800 text-center">
        Add Product
      </h1>
      <form onSubmit={handleSubmit} className="space-y-6">
        <div>
          <label
            htmlFor="productName"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Product Name:
          </label>
          <input
            type="text"
            id="productName"
            name="productName"
            value={productName}
            onChange={(e) => setProductName(e.target.value)}
            placeholder="Enter Product Name"
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div>
          <label
            htmlFor="productDescription"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Product Description:
          </label>
          <input
            type="text"
            id="productDescription"
            name="productDescription"
            value={productDescription}
            onChange={(e) => setProductDescription(e.target.value)}
            placeholder="Enter Product Description"
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div>
            <label
              htmlFor="productPrice"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              Product Price:
            </label>
            <input
              type="number"
              id="productPrice"
              name="productPrice"
              value={productPrice}
              onChange={(e) => setProductPrice(e.target.value)}
              placeholder="Enter Product Price"
              className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
            />
          </div>
          <div>
            <label
              htmlFor="productQuantity"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              Product Quantity:
            </label>
            <input
              type="number"
              id="productQuantity"
              name="productQuantity"
              value={productQuantity}
              onChange={(e) => setProductQuantity(e.target.value)}
              placeholder="Enter Product Quantity"
              className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
            />
          </div>
        </div>
        <div>
          <label
            htmlFor="productCategory"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Product Category:
          </label>
          <button
            type="button"
            onClick={() => handlePriorityClick("MOBILE")}
            className={`bg-green-500 text-white rounded-full px-3 py-1 text-sm ${
              productCatagory === "MOBILE" ? "bg-green-700" : ""
            }`}
          >
            MOBILE
          </button>
          &nbsp;&nbsp;&nbsp;
          <button
            type="button"
            onClick={() => handlePriorityClick("LAPTOP")}
            className={`bg-green-500 text-white rounded-full px-3 py-1 text-sm ${
              productCatagory === "LAPTOP" ? "bg-green-700" : ""
            }`}
          >
            LAPTOP
          </button>
          &nbsp;&nbsp;&nbsp;
          <button
            type="button"
            onClick={() => handlePriorityClick("POWER BANK")}
            className={`bg-green-500 text-white rounded-full px-3 py-1 text-sm ${
              productCatagory === "POWER BANK" ? "bg-green-700" : ""
            }`}
          >
            POWER BANK
          </button>
        </div>
        <div>
          <label
            htmlFor="productImage"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Product Image:
          </label>
          <input
            type="file"
            id="productImage"
            name="productImage"
            onChange={(e) => setProductImage(e.target.files[0])} // Use e.target.files[0] to access the selected file
            className="border border-gray-400 rounded-md px-3 py-2 w-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div>
          <label
            htmlFor="imageType"
            className="block text-sm font-medium text-gray-700 mb-1"
          >
            Image Type:
          </label>
          <button
            type="button"
            onClick={() => {
              setImageType("cover");
              handlePriorityClick1("cover");
            }}
            className={`bg-orange-500 text-white rounded-full px-3 py-1 text-sm ${
              imageType === "cover" ? "bg-orange-700" : ""
            }`}
          >
            COVER
          </button>
          &nbsp;&nbsp;&nbsp;
          <button
            type="button"
            onClick={() => handlePriorityClick1("OTHERS")}
            className={`bg-orange-500 text-white rounded-full px-3 py-1 text-sm ${
              imageType === "OTHERS" ? "bg-orange-700" : ""
            }`}
          >
            OTHERS
          </button>
        </div>
        <button
          type="submit"
          className="bg-blue-500 hover:bg-blue-600 text-white rounded-md px-4 py-2 w-full font-bold"
          onClick={handleSubmit}
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default AddProduct;
