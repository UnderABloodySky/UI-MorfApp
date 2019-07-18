import React from 'react';

export default class ContentContact extends React.Component {
  constructor(props){
    super(props)
  }

  render(){
    return(
      <div>
      <h3 className="title"> :: Contacto :: </h3>
      <div className="contact-top">
		    <div className="container">
			    <div className="contact-info">
				<h4>Lineas rotativas: </h4>
				<div style={{ width: "450px" , height: "170px"}}>
					  <p style={{textAlign: "left"}}>Teléfono: +54 011 1234-5678</p>
					  <p style={{textAlign: "left"}}>FAX: +54 011 2345-6789</p>
					  <p style={{textAlign: "left"}}>E-mail: <a href="mailto:info@example.com">mail@example.com</a></p>
					  <p style={{textAlign: "left"}}> Calle Falsa 123 CP 1826 Bs.As. Argentina.</p>
					  </div>	  
			    </div>
			    <div className="map" style={{padding:"20px 0px"}}>
					<h4>Ubicación:</h4>
					<div style={{width: "50%"}}><iframe width="100%" height="400" src="https://maps.google.com/maps?width=100%&height=600&hl=es&q=corrientes%20800+(MORFAPP)&ie=UTF8&t=&z=14&iwloc=B&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe></div><br />
					
			    </div> 
			</div>
      </div>
      </div>      
    );
  }
}