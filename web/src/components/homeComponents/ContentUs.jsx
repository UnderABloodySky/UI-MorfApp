import React from 'react';

import '../css/Body.css';
import imag2 from '../images/2.png';

export default class ContentRestaurant extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
      return(
           <div>
       	<div className="about-top">
	        <div className="container">
			<div className="about-info">
				<h3 className="title"> :: Sobre nosotros :: </h3>
			</div>

		        <div className="about-top-grids">
				<div className="col-md-5 about-top-grid">
				<img src={imag2} className="img-responsive zoom-img" />
                    </div>
				<div className="col-md-7 about-top-grid">
					<br></br><br></br>
					<h4>No tengo ni idea... Vamos a flashearla en latin:</h4>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sed odio consequat, tristique elit sed, molestie nulla. 
						Mauris et quam leo. Quisque tincidunt facilisis rutrum. Etiam mattis arcu vitae velit sagittis vehicula. Duis posuere 
						ex in mollis iaculis. Suspendisse tincidunt ut velit id euismod.vulputate turpis porta ex sodales, dignissim hendrerit 
						eros sagittis. Curabitur lacinia dui ut luctus congue. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras quis 
						eleifend augue. Integer sit amet euismod odio, at pretium lectus lorem ipsum dolor sit amet, consectetur adipiscing elit.
					</p>
				</div>
				<div className="clearfix"> </div>
			</div>
		</div>
	</div>



        </div>
      );
          }
}