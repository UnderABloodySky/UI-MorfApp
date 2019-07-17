import React from 'react';
import '../css/Body.css';
import Popup from './PopUp';
import snorlax from '../images/snorlax.png';
import img3 from '../images/img3.jpg';


const divStyle ={
  padding: "10px",
  width: "100%"
}

export default class ContentSearchResult extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showPopup: false,
      things:this.props.k 
    }
    console.log(this.state.things);
  }

  
closePopup(){
  this.setState({  
    showPopup: false  
  });
}

togglePopup() {  
  this.setState({  
       showPopup: !this.state.showPopup  
  });  
   } 
  
   /*
  render() {
    if (this.state.things.length==0){
      return(
        <div>
          <br/><br/>  
           <div className="alert alert-info" role="alert">
            <strong>Ups!</strong> No se encontraron Resultados.
           </div>
           <img src={snorlax} className="img-responsive zoom-img" />
      </div>);
    }


    const things = this.state.things.map((thing, i) => {

      return(
        
        <div className="card mt-4 col-md-4" key={i}>
            <div className="card-headercard-title text-center">
              <h4>{thing.name}</h4>
              <div className="card-body">
                {"Descripción: " + thing.description}
                <button type="button" className="btn btn-primary btn-block" onClick={this.togglePopup.bind(this)}> Hacé tu pedido!</button>  

                {this.state.showPopup &&
                <Popup  
                      closePopup={this.togglePopup.bind(this)}
                      code = {thing.code}
                />  
                }  
              </div> 
            
            </div>
        </div>
    
        
        )})

        return(
          <div>
              {
                things 
              }
          </div>
        )
            }

  */

  render() {
    if (this.state.things.length==0){
      return(
        <div>
          <br/><br/>  
           <div className="alert alert-info" role="alert">
            <strong>Ups!</strong> No se encontraron Resultados.
           </div>
           <img src={snorlax} className="img-responsive zoom-img" />
      </div>);
    }


    const things = this.state.things.map((thing, i) => {

      return(
        <div>
          <div className="card mt-4 col-md-4" key={i}>
          <div style={divStyle}>
              <img src={img3} className="card-img" alt="Shummy!"/>
          </div>
            <div className="card-body">
              <h5 className="card-title">{thing.name}</h5>
              <p className="card-text">{thing.description}</p>
              <button type="button" className="btn btn-outline-dark" onClick={this.togglePopup.bind(this)}> Hacé tu pedido!</button>  
                {this.state.showPopup &&
                <Popup  
                      closePopup={this.togglePopup.bind(this)}
                      code = {thing.code}
                />  
                } 
            </div>     
          </div> 
        </div>
      
        
        )})

        return(
          <div>
              {
                things 
              }
          </div>
        )
            }

}

