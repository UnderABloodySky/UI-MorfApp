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
            <img src={prod.productImage}/>
            <h4>{prod.name}</h4>
            <div className="card-body">
            {prod.description}
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