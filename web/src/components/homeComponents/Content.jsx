import React from 'react';

import '../css/Body.css';
import SignUp from './SignUp';
import SignIn from './SignIn';
import imag1 from '../images/img1.jpg';

export default class Content extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
  const myRestos = this.props.k.map((resto, i) => {
    return(
      <div className="card mt-4 col-md-4" key={i}>
          <div className="card-headercard-title text-center">
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