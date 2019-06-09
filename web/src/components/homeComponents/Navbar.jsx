import React from 'react';

//import '../css/Nav.css';
import '../css/bootstrap.css';
import '../css/lightbox.css';
import '../css/style.css';
export default () =>  (

      <div className="top-nav">
        <nav className="navbar navbar-default">
          <div className="navbar-header">
            <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span className="sr-only">Toggle navigation</span>
              <span className="icon-bar" />
              <span className="icon-bar" />
              <span className="icon-bar" />
            </button>
          </div>
          <div className="navbar-header">
            <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span className="sr-only">Toggle navigation</span>
              <span className="icon-bar" />
              <span className="icon-bar" />
              <span className="icon-bar" />
            </button>
          </div>
          <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul className="nav navbar-nav navbar-center cl-effect-14">
              <li><a href="index.html" className="active">Home</a></li>
              <li><a href="about.html">Somos</a></li>
              <li><a href="gallery.html">Fotos</a></li>					
              <li><a href="codes.html">Restaurant</a></li>
              <li><a href="contact.html">Contacto</a></li>
            </ul>	
          </div>	
        </nav>		
      </div>
    );
  