import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import image1 from "../images/A1.jpg";
import SearchBar from "../Components/SearchBar";
import {
  FaAddressCard,
  FaBoxOpen,
  FaRegPlusSquare,
  FaRegUserCircle,
} from "react-icons/fa";
import { IoBagHandle, IoCartOutline } from "react-icons/io5";
import { FaRegCircleUser } from "react-icons/fa6";
import { FiBox } from "react-icons/fi";
import { BsGift } from "react-icons/bs";
import { MdLogin } from "react-icons/md";
import { FaBox } from "react-icons/fa";
import { useAuth } from "../Auth/AuthProvider";
import { BiLogOutCircle } from "react-icons/bi";
import { useNavigate } from "react-router-dom";
import { MdDashboard } from "react-icons/md";
import axios from "axios";
import { SlOptionsVertical } from "react-icons/sl";
import { PiAddressBookBold } from "react-icons/pi";
import { IoIosContact } from "react-icons/io";
import { IoMdContacts } from "react-icons/io";
const Header = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const [dropdownOpen1, setDropdownOpen1] = useState(false);
  const { user } = useAuth();
  const { setUser } = useAuth(); // Access setUser function from useAuth
  const navigate = useNavigate();
  const { name, role, authenticated, username } = user;
  const handleSearchChange = (value) => {
    setSearchTerm(value);
  };

  const toggleDropdown = () => {
    setDropdownOpen(!dropdownOpen);
  };
  const toggleDropdown1 = () => {
    setDropdownOpen1(!dropdownOpen1);
  };

  const closeDropdown = () => {
    setTimeout(() => {
      setDropdownOpen(false);
    }, 5000);
  };
  const closeDropdown1 = () => {
    setTimeout(() => {
      setDropdownOpen1(false);
    }, 5000);
  };
  useEffect(() => {
    console.log("User Data Is Here:", user);
  }, [user]);

  const handleLogout = async () => {
    const result = window.confirm("Are you sure you want to logout?");
    if (!result) {
      console.log("Logout cancelled.");
      return;
    }
    try {
      // Send a request to the server to delete the cookie
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/logout",
        {},
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true, // Send cookies with the request
        }
      );
      if (response.status === 200) {
        console.log("Logout Is Successful");
        // Clear local storage data
        localStorage.removeItem("userData");
        setUser({
          userId: 0,
          name: "",
          role: "CUSTOMER",
          authenticated: false,
          accessExpiration: 0,
          refreshExpiration: 0,
        });
        navigate("/login");
      } else {
        console.log("Cannot Logout");
        // Handle logout failure
      }
    } catch (error) {
      console.error("Error during logout:", error);
      // Handle network errors or other logout errors
    }
  };
  
  
  if (!authenticated) {
    return (
      <div className="flex items-center justify-around text-slate-100 py-2 text-xl text-center align-middle shadow-xl">
        <div className="translate-x-90">
          <Link to={"/"}>
            <img src={image1} alt="A1" height={40} width={100} />
          </Link>
        </div>
        <div>
          <SearchBar onChange={handleSearchChange} />
        </div>
        <div className="relative" onMouseLeave={closeDropdown}>
          <button
            onMouseOver={toggleDropdown}
            className="text-black h-10 w-30 px-4 bg-white rounded-md focus:outline-none hover:bg-blue-600 hover:text-white"
          >
            <span className="flex items-center">
              <FaRegUserCircle /> &nbsp;&nbsp; Login
            </span>
          </button>
          {dropdownOpen && (
            <div className="absolute right-0 mt-2 w-48 bg-white border border-gray-200 rounded-md shadow-lg z-10">
              <div className="flex flex-col gap-2">
                <Link
                  to={"/Login"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <MdLogin className="ml-2" />
                  <div className="ml-4">Login</div>
                </Link>
                <hr />
                <Link
                  to={"/Register"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaRegCircleUser className="ml-2" />
                  <div className="ml-4">Register</div>
                </Link>
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaRegCircleUser className="ml-2" />
                  <div className="ml-4">My Profile</div>
                </Link>

                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaRegPlusSquare className="ml-2" />
                  <div className="ml-4">Flipkart Plus Zone</div>
                </Link>
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FiBox className="ml-2" />
                  <div className="ml-4">Orders</div>
                </Link>
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaBoxOpen className="ml-2" />
                  <div className="ml-4">Rewards</div>
                </Link>
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <BsGift className="ml-2" />
                  <div className="ml-4">Gift Cards</div>
                </Link>
              </div>
            </div>
          )}
        </div>
        <div className="flex items-center">
          <Link to={"/cart"} className="text-black flex items-center">
            <IoCartOutline className="mr-3" /> Cart
          </Link>
        </div>
        <div className="flex items-center">
          <Link
            to={"/seller/register"}
            className="text-black flex items-center"
          >
            <IoBagHandle className="mr-3" />
            Become A Seller
          </Link>
        </div>
      </div>
    );
  } else if (authenticated && role === "CUSTOMER") {
    return (
      <div className="flex items-center justify-around bg-white text-slate-100 py-2 text-xl text-center align-middle shadow-xl">
        <div className="translate-x-90">
          <Link to={"/"}>
            <div>
              <div>
                <img src={image1} alt="A1" height={40} width={100} />
              </div>
            </div>
          </Link>
        </div>
        <div>
          <SearchBar onChange={handleSearchChange} />
        </div>
        <div className="relative" onMouseLeave={closeDropdown}>
          <button
            onMouseOver={toggleDropdown}
            className="text-black h-10 w-30 px-4 bg-white rounded-md focus:outline-none hover:bg-blue-600 hover:text-white"
          >
            <span className="flex items-center">
              <FaRegUserCircle /> &nbsp;&nbsp; {name}
            </span>
          </button>
          {dropdownOpen && (
            <div className="absolute right-0 mt-2 w-48 bg-white border border-gray-200 rounded-md shadow-lg z-10">
              <div className="flex flex-col gap-2">
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaRegCircleUser className="ml-2" />
                  <div className="ml-4">My Profile</div>
                </Link>

                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaRegPlusSquare className="ml-2" />
                  <div className="ml-4">Flipkart Plus Zone</div>
                </Link>
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FiBox className="ml-2" />
                  <div className="ml-4">Orders</div>
                </Link>
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaBoxOpen className="ml-2" />
                  <div className="ml-4">Rewards</div>
                </Link>
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <BsGift className="ml-2" />
                  <div className="ml-4">Gift Cards</div>
                </Link>
                <Link
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                  onClick={handleLogout} // Add onClick to trigger handleLogout
                >
                  <BiLogOutCircle className="ml-2" />
                  <div className="ml-4">LogOut</div>
                </Link>
              </div>
            </div>
          )}
        </div>
        <div className="flex items-center">
          <Link to={"/cart"} className="text-black flex items-center">
            <IoCartOutline className="mr-3" /> Cart
          </Link>
        </div>
        <div className="flex items-center">
          <Link to={"/wishList"} className="text-black flex items-center">
            <IoBagHandle className="mr-3" />
            Wishlist
          </Link>
        </div>

        <div className="flex items-center">
          <div className="relative" onMouseLeave={closeDropdown1}>
            <SlOptionsVertical
              className="text-black cursor-pointer"
              onMouseOver={toggleDropdown1}
            />
            {dropdownOpen1 && (
              <div className="absolute right-0 mt-2 w-48 bg-white border border-gray-200 rounded-md shadow-lg z-10 text-black text-base">
                <div className="flex flex-col gap-2 ">
                  <Link
                    to={"/addaddress"}
                    className="dropdown-item px-4 py-2 flex items-center hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <FaAddressCard className="mr-2" />
                    <div>Add Address</div>
                  </Link>
                  <Link
                    to={"/updateAddress"}
                    className="dropdown-item px-4 py-2 flex items-center  hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <PiAddressBookBold className="mr-2" />
                    <div>Edit Address</div>
                  </Link>
                  <Link
                    to={"/addcontact"}
                    className="dropdown-item px-4 py-2 flex items-center  hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <IoIosContact className="mr-2" />
                    <div>Add Contact</div>
                  </Link>
                  <Link
                    to={"/profile"}
                    className="dropdown-item px-4 py-2 flex items-center  hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <IoMdContacts className="mr-2" />
                    <div>Edit Contact</div>
                  </Link>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    );
  } else if (authenticated && role === "SELLER") {
    return (
      <div className="flex items-center justify-around bg-white text- py-2 text-xl text-center align-middle shadow-xl">
        <div className="translate-x-90">
          <Link to={"/"}>
            <img src={image1} alt="A1" height={40} width={100} />
          </Link>
        </div>
        <div>
          <SearchBar onChange={handleSearchChange} />
        </div>
        <div className="relative" onMouseLeave={closeDropdown}>
          <button
            onMouseOver={toggleDropdown}
            className="text-black h-10 w-30 px-4 bg-white rounded-md focus:outline-none hover:bg-blue-600 hover:text-white"
          >
            <span className="flex items-center">
              <FaRegUserCircle /> &nbsp;&nbsp;{name}
            </span>
          </button>
          {dropdownOpen && (
            <div className="absolute right-0 mt-2 w-48 bg-white border border-gray-200 rounded-md shadow-lg z-10">
              <div className="flex flex-col gap-2">
                <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaRegCircleUser className="ml-2" />
                  <div className="ml-4">My Profile</div>
                </Link>

                <Link
                  to={"/addProduct"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FaRegPlusSquare className="ml-2" />
                  <div className="ml-4">Add Product</div>
                </Link>
                <Link
                  to={"/sellerDashboard"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <MdDashboard  className="ml-2" />
                  <div className="ml-4">Seller DashBoard</div>
                </Link>
                <Link
                  to={"/orders"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <FiBox className="ml-2" />
                  <div className="ml-4">Orders</div>
                </Link>
                 <Link
                  to={"/profile"}
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                >
                  <BsGift className="ml-2" />
                  <div className="ml-4">Gift Cards</div>
                </Link>
                <Link
                  className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
                  onClick={handleLogout} // Add onClick to trigger handleLogout
                >
                  <BiLogOutCircle className="ml-2" />
                  <div className="ml-4">LogOut</div>
                </Link>
              </div>
            </div>
          )}
        </div>
        <Link
          to={"/orders"}
          className="px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 flex items-center"
        >
          <FaBox className="ml-2 h-4 w-4" />
          <div className="ml-4 text-lg">Orders</div>
        </Link>

        <div className="flex items-center">
          <div className="relative" onMouseLeave={closeDropdown1}>
            <SlOptionsVertical
              className="text-black cursor-pointer"
              onMouseOver={toggleDropdown1}
            />
            {dropdownOpen1 && (
              <div className="absolute right-0 mt-2 w-48 bg-white border border-gray-200 rounded-md shadow-lg z-10 text-black text-base">
                <div className="flex flex-col gap-2 ">
                  <Link
                    to={"/addaddress"}
                    className="dropdown-item px-4 py-2 flex items-center hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <FaAddressCard className="mr-2" />
                    <div>Add Address</div>
                  </Link>
                  <Link
                    to={"/profile"}
                    className="dropdown-item px-4 py-2 flex items-center  hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <PiAddressBookBold className="mr-2" />
                    <div>Edit Address</div>
                  </Link>
                  <Link
                    to={"/addcontact"}
                    className="dropdown-item px-4 py-2 flex items-center  hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <IoIosContact className="mr-2" />
                    <div>Add Contact</div>
                  </Link>
                  <Link
                    to={"/profile"}
                    className="dropdown-item px-4 py-2 flex items-center  hover:bg-gray-100"
                    onMouseLeave={closeDropdown1}
                  >
                    <IoMdContacts className="mr-2" />
                    <div>Edit Contact</div>
                  </Link>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    );
  }
};

export default Header;
