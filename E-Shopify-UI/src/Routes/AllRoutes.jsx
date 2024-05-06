import React, { useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import Login from "../Public/Login.jsx";
import Home from "../Public/Home.jsx";
import Register from "../Public/Register.jsx";
import BecomeASeller from "../Private/Seller/BecomeASeller.jsx";
import Account from "../Private/Common/Account.jsx";
import Cart from "../Private/Customer/Cart.jsx";
import Order from "../Private/Customer/Order.jsx";
import App from "../App.jsx";
import VerifyOTP from "../Public/VerifyOtp.jsx";
import SellerDashboard from "../Private/Seller/SellerDashboard.jsx";
import { useAuth } from "../Auth/AuthProvider.jsx";
import AddAddress from "../Util/AddAddress.jsx";
import AddContact from "../Util/AddContact.jsx";
import UpdateAddress from "../Util/UpdateAddress.jsx";
import AddProduct from "../Private/Seller/AddProduct.jsx";
import MyProfile from "../Private/Seller/MyProfile.jsx";
const AllRoutes = () => {
  const { user } = useAuth();

  const { role, authenticated } = user;
  const routes = [];
  useEffect(() => console.log(user), []);
  if (authenticated) {
    if (role === "CUSTOMER") {
      routes.push(
        <Route key="home" path="/home" element={<Home />} />,
        <Route key="account" path="/account" element={<Account />} />,
        <Route key="cart" path="/cart" element={<Cart />} />,
        <Route key="orders" path="/orders" element={<Order />} />,
        <Route key="addaddress" path="/addaddress" element={<AddAddress />} />,
        <Route key="addcontact" path="/addcontact" element={<AddContact />} />,
        <Route
          key="updateAddress"
          path="/updateAddress"
          element={<UpdateAddress />}
        />
      );
    } else if (role === "SELLER") {
      routes.push(
        <Route
          key="sellerDashboard"
          path="/sellerDashboard"
          element={<SellerDashboard />}
        />,
        <Route key="addaddress" path="/addaddress" element={<AddAddress />} />,
        <Route key="addcontact" path="/addcontact" element={<AddContact />} />,
        <Route
          key="updateAddress"
          path="/updateAddress"
          element={<UpdateAddress />}
        />,
        <Route key="addProduct" path="/addProduct" element={<AddProduct />} />,
        <Route key="myProfile" path="/myProfile" element={<MyProfile/>}/>
      );
    }
  } else {
    routes.push(
      <Route key="login" path="/login" element={<Login />} />,
      <Route
        key="registercustomer"
        path="/register"
        element={<Register role={"CUSTOMER"} />}
      />,
      <Route
        key="registerseller"
        path="seller/register"
        element={<Register role={"SELLER"} />}
      />,
      <Route key="verifyOTP" path="/verifyOTP" element={<VerifyOTP />} />,
      <Route key="addProduct" path="/addProduct" element={<AddProduct />} />,
      <Route
          key="sellerDashboard"
          path="/sellerDashboard"
          element={<SellerDashboard />}
        />
    );
  }

  return (
    <Routes>
      <Route path="/" element={<App />}>
        {routes}
      </Route>
    </Routes>
  );
};

export default AllRoutes;
