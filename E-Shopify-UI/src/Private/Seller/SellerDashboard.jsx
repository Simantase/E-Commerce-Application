import React from "react";
import { Link } from "react-router-dom";
import { FaRegCircleUser } from "react-icons/fa6";
import {
  FaAddressCard,
  FaBoxOpen,
  FaRegPlusSquare,
  FaRegUserCircle,
} from "react-icons/fa";
import { IoIosContact } from "react-icons/io";

const SellerDashboard = () => {
  return (
    <div>
      {/* <h1 className="font-bold text-4xl text-center  text-rose-900 p-20">Seller Dashboard</h1> */}
      <div className="grid grid-cols-1 md:grid-cols-3 border-black border-solid border-2px">
        <div
          id="mystore"
          className=" p-4 w-full shadow-md bg-white-50 flex flex-col gap-1 justify-around mt-10"
        >
          <div className="flex justify-center items-center bg-blue-200 h-[100px] w-full ">
            <h2 className="font-bold text-4xl  mb-4 text-rose-900">
              Seller DashBoard
            </h2>
          </div>
          <div className="h-[150px] w-full shadow-xl">
            <p className="font-bold ml-5 mt-5 text-xl">Total Revenue</p>
            <p className="text-zinc-500 ml-5 mt-5">No Data</p>
          </div>
          <div className="grid grid-cols-2 gap-4 mt-4">
            <button className="flex flex-col items-center p-2 border rounded-lg shadow-xl">
              <Link to="/myProfile">
                {" "}
                <span className="text-2xl">
                  <FaRegCircleUser className="ml-2" />
                </span>
              </Link>
              <Link to="/myProfile">
                <span>My Profile</span>
              </Link>
            </button>
            <button className="flex flex-col items-center p-2 border rounded-lg shadow-xl">
              <span className="text-2xl">
                <Link to="/addProduct">
                  <FaRegPlusSquare className="ml-2" />
                </Link>
              </span>
              <span>
                <Link to="/addProduct">Add Product</Link>
              </span>
            </button>
          </div>
          <div className="grid grid-cols-2 gap-4 mt-4 bg-white">
            <button className="flex flex-col items-center p-2 border rounded-lg shadow-xl">
              <span className="text-2xl">
                <Link to="/addaddress">
                  {" "}
                  <FaAddressCard className="mr-2" />
                </Link>{" "}
              </span>
              <span>
                {" "}
                <Link to="/addaddress">Add Address</Link>
              </span>
            </button>
            <button className="flex flex-col items-center p-2 border rounded-lg shadow-xl">
              <span className="text-2xl">
                <Link to="/addcontact">
                  {" "}
                  <IoIosContact className="mr-2 h-7 w-7" />
                </Link>
              </span>
              <span>
                <Link to="/addcontact">Add Contact</Link>
              </span>
            </button>
          </div>
        </div>

        {/* Section 2 */}
        <div className="bg-white p-4 shadow-md col-span-2  mt-10">
          <div className="rounded-md border-sky-50 border-2 border-solid pl-10 shadow-xl h-[130px]">
            <h2 className="font-bold text-lg mb-4 mt-4">Orders</h2>
            <div className="grid grid-cols-4 gap-4">
              <div>
                <p>Total Orders</p>
                <p>00</p>
              </div>
              <div>
                <p>Completed Orders</p>
                <p>00</p>
              </div>
              <div>
                <p>Orders Yet to Dispatch</p>
                <p>00</p>
              </div>
              <div>
                <p>Shipped Orders</p>
                <p>00</p>
              </div>
            </div>
          </div>
          <br />
          <div className=" rounded-md border-sky-50 border-2 border-solid pl-10 shadow-xl h-[130px] ">
            <h2 className="font-bold text-lg mt-6 mb-4">Product Listing</h2>
            <div className="grid grid-cols-5 gap-4">
              <div>
                <p>Active</p>
                <p>00</p>
              </div>
              <div>
                <p>Out Of Stock</p>
                <p>00</p>
              </div>
              <div>
                <p>Low Stock</p>
                <p>00</p>
              </div>
            </div>
          </div>
          <br />
          <div className="rounded-md border-sky-50 border-2 border-solid h-[130px] pl-10 shadow-xl">
            <h2 className="font-bold text-lg mt-6 mb-4">
              Your Most Popular Products
            </h2>
            <p className="text-zinc-500">No Data</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SellerDashboard;
