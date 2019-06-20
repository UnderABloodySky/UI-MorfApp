import React from 'react';

import '../css/Body.css';
import SignUp from './SignUp';
import SignIn from './SignIn';
import imag1 from '../images/img1.jpg';

export default class Container extends React.Component {
  constructor(props) {
    super(props)  
    this.state = { 
      child: this.props.child
    }; 
  }  

render() {
  return (
    <div>
        <div className="banner">
          <div className="banner-info">
            <div className="container">
              <div className="logo">
                <div className="ribbon">
                  <div className="ribbon-fold"><h1>::MorfApp::</h1></div>
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
            {/*welcome*/}
            <div className="welcome">
              <br />
              <br />
              <div className="welcome-info">
                <div className="grid-body-container">
                    <div className="col-md-6 welcome-grids">
                    </div>
                    <div className="col-md-6 welcome-grids">
                    </div>   
                    <div className="clearfix"> </div>
                </div>
              </div>
            </div>
            {/*//welcome*/}
          </div>
        </div>
      </div>
    );

  }
}