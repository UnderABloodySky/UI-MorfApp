import React from 'react';
import '../css/Order.css';

export default class ContentProduct extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
  const myRestos = this.props.k.map((prod, i) => {
    return(
      <div className="container2" >
        <div class="row">
        <div class="col-sm d-flex">
        <div className="card card-body flex-fill" key={i}>
        <img src={prod.productImage}/>
          <div className="card-body text-primary">
              <h5 className="card-title"><p> {prod.name}</p></h5>
              <h6 className= "text-info">
              <p>{prod.description}</p>
            </h6>  
          </div>
        </div>
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