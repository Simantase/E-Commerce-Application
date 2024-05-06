import React from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import img1 from '../images/C1.jpg';
import img3 from '../images/C3.jpg';
import img4 from '../images/C4.jpg';
import img5 from '../images/C5.jpg';
import img6 from '../images/C6.jpg';
const ImageSlider = () => {
    const settings = {
      dots: true,
      infinite: true,
      speed: 800,
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 4000,
    };
  
    return (
      <div className="slider-container">
        <Slider {...settings}>
          <div>
            <img src={img1} alt="Image 1" />
          </div>
          <div>
            <img src={img3} alt="Image 2" />
          </div>
          <div>
            <img src={img4} alt="Image 3" />
          </div>
          <div>
            <img src={img5} alt="Image 3" />
          </div>
          <div>
            <img src={img6} alt="Image 3" />
          </div>
        </Slider>
      </div>
    );
  };
  
  export default ImageSlider;
  