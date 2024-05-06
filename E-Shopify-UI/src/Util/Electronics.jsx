import React from 'react'
import image from '../images/C2.jpg'
const Electronics = () => {
  return (
    <div className=' h-460 m-5 shadow-xl ml-7 mr-7 rounded-sm'>
 <h1 className='text-xl font-bold ml-12 pt-5'>Best Of Electronics</h1><br/>
 <div class="box" className=' flex items-center justify-evenly '>
 <div class="box1"  className='box-border h-230 w-230 p-4 border-2 rounded-md'> 
  <div><img src="https://rukminim2.flixcart.com/image/200/200/kokdci80/dslr-camera/v/e/x/z-24-200mm-z5-nikon-original-imag2zuekuxgxsgg.jpeg?q=70" height={200} width={180} alt="Camera" /></div>
  <div>Top Mirrorless Cameras</div>
  <div className='font-bold'>Shop Now!</div></div>
  <div className='box-border h-230 w-230 p-4 border-2 rounded-md'> 
  <div><img src="https://rukminim2.flixcart.com/image/200/200/kvr01ow0/security-software/l/d/e/quick-heal-original-imag8kpmjygxhzgh.jpeg?q=70" height={120} width={120} alt="Camera" /></div>
  <div>Best Selling Security</div>
  <div className='font-bold'>From $29</div></div>
  <div className='box-border h-230 w-230 p-4 border-2 rounded-md'> 
  <div><img src="https://rukminim2.flixcart.com/image/200/200/xif0q/projector/k/f/0/zeb-pixaplay-22-green-16-zeb-pixaplay-22-green-led-zebronics-original-imagpqgasyrg2gzv.jpeg?q=70" height={120} width={120} alt="Camera" /></div>
  <div>Projectors</div>
  <div className='font-bold'>From $9999</div></div>
  <div className='box-border h-230 w-230 p-4 border-2 rounded-md'> 
  <div><img src="https://rukminim2.flixcart.com/image/200/200/k0lbdzk0pkrrdj/printer-refurbished/a/5/h/c-laserjet-m1005-mfp-hp-original-imafjfx2hvjhmysr.jpeg?q=70" height={220} width={220} alt="Camera" /></div>
  <div>Printers</div>
  <div className='font-bold'>From $3999</div></div>
  <div className='box-border h-230 w-230 p-4 border-2 rounded-md'> 
  <div><img src="https://rukminim2.flixcart.com/image/200/200/xif0q/monitor/0/1/5/-original-imagwcddyrshqve9.jpeg?q=70" height={200} width={200} alt="Camera" /></div>
  <div>BenQ Monitors</div>
  <div className='font-bold'>From $9990</div></div>
  
  <div><img src={image} alt="" height={200}  width={200}/></div>
 
</div>


    </div>
  )
}

export default Electronics
