import React from 'react';

import '../css/Body.css';
import SignUp from './SignUp';
import SignIn from './SignIn';

export default class Body extends React.Component {
  constructor(props) {
    super(props)  
    this.state = { 
      renderReg: false,
      renderLog: false
    }; 
    this.handlerReg = this.handlerReg.bind(this);
    this.handlerLog = this.handlerLog.bind(this);
  }  

register = () => {
  this.setState({renderReg : !this.state.renderReg})
  if(this.state.renderLog){
    this.setState({renderLog : false})
  }
}

login = () => {
  this.setState({renderLog : !this.state.renderLog})
  if(this.state.renderReg){
    this.setState({renderReg : false})
  }
}

handlerReg() {
    this.setState({ renderReg: false })
}
handlerLog() {
    this.setState({ renderLog: false })
}

componentDidUpdate(){
}

render() {
  return (
    <div>
        <div className="banner">
          <div className="banner-info">
            <div className="container">
              <div className="logo">
                <div className="ribbon">
                  <div className="ribbon-fold"><h1> <a href="index.html">:MorfApp:</a></h1></div>
                </div>
                <h2> Un Subtitulo muy ocurrente </h2>
              </div>			
            </div>
          </div>
        </div>
        {/*//banner*/}
        {/*banner-bottom*/}
        <div className="banner-bottom">
          <div className="container">
            <div className="banner-text" >
              <h3>Comida a un click!</h3>
              <p>Promociones diarias &amp; mas</p>
              <a href="#" className="btn btn-1 btn-1b" onClick={() => this.register() }>Registrarte</a>
              <a href="#" className="btn btn-2 btn-2b" onClick={() => this.login() }>Loguearte</a>
              { this.state.renderReg &&
              <SignUp handlerReg = {this.handlerReg} />}
              { this.state.renderLog &&
              <SignIn handlerLog = {this.handlerLog} />}
            </div>
            {/*welcome*/}
            <div className="welcome">
              <h3 className="title">Bienvenidxs!</h3>
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
                   <p> cargar algo aca </p>			
            </div>
            {/*//welcome*/}
          </div>
        </div>
      </div>
    );

  }
}