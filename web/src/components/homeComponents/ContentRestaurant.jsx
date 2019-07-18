import React from 'react';

import '../css/Body.css';

export default class ContentRestaurant extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {

  const myRestos = this.props.k.map((resto, i) => {
    return(
    
      <div className="card mt-4 col-md-4" key={i}>
          <div className="card-headercard-title text-center">
            {console.log(resto)}
            <img src={resto.restaurantImage}/>
            <h4>Restaurant: {resto.name}</h4>
            <span className="badge-pill badge-danger ml-2">
              {"open"}
            </span>
          <div className="card-body">
            {"Descripción: " + resto.description}
            <mark>{"Dirección: " + resto.address}</mark>
            <mark>{resto.code}</mark>
          </div>  
        </div>
      </div>
     )})

      return(
        <div>
            {
               myRestos 
            }
        </div>
      )
          }
}