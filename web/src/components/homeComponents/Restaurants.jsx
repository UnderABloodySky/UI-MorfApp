import React from 'react';
import '../css/Restaurant.css';
import SignUp from './SignUp';
import RestaurantSearcher from './RestaurantSearcher';

export default class Restaurants extends React.Component {
  state = { render: false }
    constructor(props) {
    super(props)
    }

register = () => {
  this.setState({render : !this.state.render})
}

render() {
  return (
    <div>
        <div className="app container">
          <div className="jumbotron">
            <p className="lead text-center"> 
            "Buscador de Restaurants"
            </p>
            <RestaurantSearcher />
              <div className="logo">
                <div className="ribbon">
                  <div className="ribbon-fold"><h1> <a href="index.html">:MorfApp:</a></h1></div>
                </div>
                <h2> XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx </h2>
              </div>			
          </div>
        </div>
        {/*//banner*/}
        {/*banner-bottom*/}
        <div className="banner-bottom">
          <div className="container">
            <div className="banner-text">
              <h3>Comida a un click!</h3>
              <p>Promociones diarias &amp; mas</p>
              <a href="#" className="btn btn-1 btn-1b" onClick={() => this.register()}>Registrate</a>
              { this.state.render &&
              <SignUp/>}
            </div>
            {/*welcome*/}
            <div className="welcome">
              <h3 className="title">Bienvenidxs!EL DE RESTAURANT</h3>
              <p>Gracias por aportar tu granito de arena por mas precarizacion laboral, para que no tengas que levantar el culo del sillon si se te antojan unas papas con cheddard</p>
              <br />
              <br />
              <div className="welcome-info">
                <div className="grid-body-container">
                    <div className="col-md-6 welcome-grids">
                        <div className="welcome-img">
                            <img src={require('../images/img1.jpg')} className="img-responsive zoom-img" alt />
                        </div>
                    </div>
                    <div className="col-md-6 welcome-grids">
                        <div className="welcome-img">
                            <img src={require('../images/img2.jpg')} className="img-responsive zoom-img" alt />
                        </div>
                    </div>   
                    <div className="clearfix"> </div>
                </div>
              </div>
              <p>Recuerde ordenar una de nuestras pizzas especiales. Pizza pero pizza pizza!!! </p>			
            </div>
            {/*//welcome*/}
          </div>
        </div>
      </div>
    );

  }
  
}