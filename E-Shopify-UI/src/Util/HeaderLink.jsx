import React from 'react';

const LinkWithIcon = ({ icon, text }) => {
  return (
    <span className="flex items-center">
      <img src={icon} alt="" height={20} width={20} />&nbsp;&nbsp;
      {text}
    </span>
  );
};

export default LinkWithIcon;
