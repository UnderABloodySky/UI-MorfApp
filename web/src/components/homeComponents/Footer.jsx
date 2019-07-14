import React from 'react';

import '../css/Footer.css';

export default () =>  (
  
        <div className="footer">
          <div className="grid-footer-container">
              <div className="col-md-3 footer-left">
                <h4>Direccion</h4>
                <ul>
                  <li>Buenos Aires Argentina</li>
                  <li>Calle Falsa 1234</li>
                  <li>Bs As. CP 4321</li>
                  <li>CUIT: 30 - 7491714 - 1</li>
                </ul>				
              </div>
              <div className="col-md-3 footer-left">
                <h4>Ofrecemos</h4>
                <ul>
                  <li>Confidencialidad</li>
                  <li>Seguridad</li>
                  <li>Calidad</li>
                  <li>Atencion de primera</li>
                </ul>
              </div>
              <div className="col-md-3 footer-left">
                <h4>Detalles</h4>
                <ul>
                  <li><a href="/us">Quienes somos?</a></li>
                  <li><a href="/register">Registracion</a></li> {/* Falta  formulario de registracion */}
                  <li><a href="/us">Politicas de privacidad</a></li>
                  <li><a href="/contact">Contacto</a></li>
                </ul>
              </div>
              <div className="col-md-3 footer-right">
                <p> © 2019 ar.edu.unq.ui Powered by <a href="https://github.com/unq-ui/2019s1-Grupo09-nombrePendiente">nombrePendiente</a></p>
                <div className="icons">
                  <ul>
                    <li><a href="https://twitter.com/?lang=es" className="twitter"> </a></li>
                    <li><a href="https://web.facebook.com/MorfApp-490615881686201/?modal=admin_todo_tour" className="twitter facebook"> </a></li>
                    <li><a href="https://www.google.com/search?client=ubuntu&channel=fs&q=morfapp&ie=utf-8&oe=utf-8" className="twitter chrome"> </a></li>
                  </ul>
                </div>
              </div>
          </div>
        </div>
      );
