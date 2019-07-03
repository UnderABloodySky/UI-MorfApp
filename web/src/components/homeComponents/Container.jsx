import React from 'react';
import ContentRestaurant from '../homeComponents/ContentRestaurant';  
import ContentMenu from '../homeComponents/ContentMenu'; 
import ContentProduct from '../homeComponents/ContentProduct';
import ContentRegister from '../homeComponents/ContentRegister';
import ContentContact from '../homeComponents/ContentContact';
import ContentUs from '../homeComponents/ContentUs';
import '../css/Body.css';

export default class Container extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
  console.log("Container");
  console.log(this.props.content);
  
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
        <div className="banner-bottom container ">
          
            {/*welcome*/}
            <div className="welcome">
              {this.props.id === "0" && <ContentUs k={this.props.content} />}
              {this.props.id === "1" && <ContentRestaurant k={this.props.content}/>}
              {this.props.id === "2" && <ContentMenu k={this.props.content}/>}
              {this.props.id === "3" && <ContentProduct k={this.props.content}/>}
              {this.props.id === "4" && <ContentContact k={this.props.content}/>}
              {this.props.id === "5" && <ContentRegister/>}
             
             
             
             
                         <div className="welcome-info">
                <div className="grid-body-container">
                    <div className="col-md-6 welcome-grids">
                    </div>
                    <div className="col-md-6 welcome-grids">
                    </div>   
                    <div className="clearfix"> </div>
              </div>
            </div>
            {/*//welcome*/}
          </div>
        </div>
      </div>
    );

  }
}