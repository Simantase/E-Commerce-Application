import React from 'react';
import { BsSearch } from 'react-icons/bs'; // Importing the search icon from react-icons library

const SearchBar = ({ onChange }) => {
  return (
    <div className="flex items-center">
      <BsSearch className="text-gray-400 h-4 w-4 mr-2" /> {/* Search icon */}
      <input
        type="text"
        placeholder="Search for Products, Brands And More"
        onChange={(e) => onChange(e.target.value)}
        className="w-64 h-10 text-black bg-blue-100 px-5 rounded-md relative text-base placeholder:text-black"
      />
    </div>
  );
};

export default SearchBar;
 