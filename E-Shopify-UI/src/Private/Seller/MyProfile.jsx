import React from 'react';
import { Link } from 'react-router-dom';
import img1 from '../Common/images/K10.jpg';
import img2 from '../Common/images/R7.jpg';
import img3 from '../Common/images/P13.jpg';

const Body = () => {
  return (
    <div className='flex items-center justify-evenly h-40 m- pl-20 pr-20 shadow-xl'>
      <div>
        <div>
          <img src={img1} alt="B3" height={50} width={50} />
        </div>
        <div className="font-bold">
          <Link to="/mobile">Mobile</Link>
        </div>
      </div>
      <div>
        <div>
          <img src={img2} alt='B4' height={65} width={65} />
        </div>
        <div className='font-bold'>
        <Link to="/laptop">Laptop</Link>
        </div>
      </div>
      <div>
        <div>
          <img src={img3} alt='B5' height={70} width={70} />
        </div>
        <div className='font-bold'>
          <Link to="/powerBank">PowerBank</Link>
        </div>
      </div>
    </div>
  );
}

export default Body;
