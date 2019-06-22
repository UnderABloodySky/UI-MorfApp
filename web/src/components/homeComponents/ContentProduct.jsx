import React from 'react';
import '../css/Body.css';

export default class ContentProduct extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
  const myRestos = this.props.k.map((prod, i) => {
    return(
      <div className="card mt-4 col-md-4" key={i}>
          <div className="card-headercard-title text-center">
            <h4>Producto: {prod.name}</h4>
            <span className="badge-pill badge-danger ml-2">
              {"open"}
            </span>
          <div className="card-body">
            {"Descripción: " + prod.description}
            <mark>{prod.code}</mark>
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