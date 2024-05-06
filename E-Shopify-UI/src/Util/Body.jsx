import React from 'react'
import img1 from '../images/B1.jpg'
import img2 from '../images/B2.jpg'
import img6 from '../images/B6.jpg'
import img7 from '../images/B7.jpg'


const Body = () => {
  
  return (
    <div className='flex items-center justify-between h-40 m-2 pl-20 pr-20 shadow-xl'>
        <div><div><img src={img1} alt='B1' height={60} width={60}/></div><div className='font-bold'>Grocery</div></div>
        <div><div><img src={img2} alt='B2' height={60} width={60}/></div><div className='font-bold'>Mobiles</div></div>
        <div><div><img src="https://rukminim2.flixcart.com/fk-p-flap/80/80/image/0d75b34f7d8fbcb3.png?q=100" alt="B3" height={60} width={60}/></div><div className="font-bold">Fashion</div></div>
        <div><div><img src="https://rukminim2.flixcart.com/flap/80/80/image/69c6589653afdb9a.png?q=100" alt='B4' height={60} width={60}/></div><div className='font-bold'>Electronics</div></div>
        <div><div><img src="https://rukminim2.flixcart.com/flap/80/80/image/ab7e2b022a4587dd.jpg?q=100" alt='B5' height={60} width={60}/></div><div className='font-bold'>Home & Furniture</div></div>
        <div><div><img src={img6} alt='B6' height={60} width={60}/></div><div className='font-bold'>Appliances</div></div>
        <div><div><img src={img7} alt='B7' height={60} width={60}/></div><div className='font-bold'>Travel</div></div>
        <div><div><img src="https://rukminim2.flixcart.com/flap/80/80/image/dff3f7adcf3a90c6.png?q=100" alt='B8' height={60} width={60}/></div><div className='font-bold'>Beauty,Toys & More</div></div>
        <div><div><img src="https://rukminim2.flixcart.com/fk-p-flap/80/80/image/05d708653beff580.png?q=100" alt='B9' height={60} width={60}/></div><div className='font-bold'>Two Wheelers</div></div>
    </div>
  )
}

export default Body
// import React from 'react';
// import img1 from '../images/B1.jpg';
// import img2 from '../images/B2.jpg';
// import img6 from '../images/B6.jpg';
// import img7 from '../images/B7.jpg';

// const Body = () => {
//   return (
//     <div className='flex items-center justify-between h-40 m-2 pl-20 pr-20 shadow-xl mt-3'>
//       <div>
//         <div><img src={img1} alt='B1' height={60} width={60} /></div>
//         <div className='font-bold'>Grocery</div>
//       </div>
//       <div>
//         <div><img src={img2} alt='B2' height={60} width={60} /></div>
//         <div className='font-bold'>Mobiles</div>
//       </div>
//       <div>
//         <div><img src="https://rukminim2.flixcart.com/fk-p-flap/80/80/image/0d75b34f7d8fbcb3.png?q=100" alt="B3" height={60} width={60} /></div>
//         <div className="font-bold">Fashion</div>
//         {/* First Dropdown */}
//         <select className="mt-2">
//           <option value="men">Men's Fashion</option>
//           <option value="women">Women's Fashion</option>
//           <option value="kids">Kids' Fashion</option>
//         </select>
//         {/* Second Dropdown */}
//         <select className="mt-2">
//           <option value="shoes">Shoes</option>
//           <option value="clothing">Clothing</option>
//           <option value="accessories">Accessories</option>
//         </select>
//       </div>
//       {/* Remaining items */}
//       {/* Add your remaining items here */}
//     </div>
//   );
// };

// export default Body;
