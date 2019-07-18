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
				    <p>Lorem ipsum dolor sit amet consectetur adipiscing elit in id malesuada lectus aenean convallis interdum gravida donec faucibus bibendum tortor vel facilisis.</p>
			    </div>
			    <div className="map">
				    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d158858.18237072596!2d-0.10159865000003898!3d51.52864165000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sin!4v1436514341845" allowfullscreen></iframe>
			    </div>
			    <div className="mail-grids">
				    <div className="col-md-6 mail-grid-left">
					    <h3 className="title"> :: Dirección :: </h3>
					    <h5>Cras porttitor imperdiet volutpat nulla malesuada lectus eros <span>ut convallis felis consectetur ut </span></h5>
					    <h4>Casa central: </h4>
					  <p>Calle Falsa 1234
						  <span>CP 1826</span>
						  Bs. As., Argentina.
					  </p>
					  <h4>Lineas rotativas: </h4>
					  <p>Telefon0: +54 011 1234-5678
						  <span>FAX: +54 011 2345-6789</span>
						  E-mail: <a href="mailto:info@example.com">mail@example.com</a>
					  </p>
				  </div>
        </div>  
				<div class="col-md-6 contact-form">
					<form>
						<input type="text" placeholder="Nombre" required=""/>
						<input type="text" placeholder="Email" required=""/>
						<input type="text" placeholder="Asunto" required=""/>
						<textarea placeholder=" -- Mensaje -- " required=""></textarea>
						<input type="submit" value="ENVIAR"/>
					</form>
				</div>
			</div>
      </div>
      </div>      
    );
  }
}