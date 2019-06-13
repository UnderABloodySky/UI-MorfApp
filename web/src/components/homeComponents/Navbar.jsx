import React from 'react';

import '../homeComponents/Restaurants';
import Restaurants from '../homeComponents/Restaurants';


export default class Navbar extends React.Component {


  onClick = () => {
   return <Restaurants/>;
}

render() {
  return (

<nav className="navbar navbar-expand-lg navbar-light bg-light">
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
  </button>
  <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
    <a clasNames="navbar-brand" href="#">Home</a>
    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
      <li className="nav-item active">
        <a className="nav-link" href="#">Nosotros <span class="sr-only">(current)</span></a>
      </li>
      <li className="nav-item">
        <a class="nav-link" href="#">Restaurants</a>
      </li>
      <li className="nav-item">
        <a class="nav-link" href="#">Menus</a>
      </li>
      <li className="nav-item">
        <a class="nav-link" href="#">Contacto</a>
      </li>
      <li className="nav-item">
        <a class="nav-link" href="#">Registro</a>
      </li>
    </ul>
    <form className="form-inline my-2 my-lg-0">
      <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"/>
      <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
    </form>
    </div>
    </nav>
    );
  }
}
