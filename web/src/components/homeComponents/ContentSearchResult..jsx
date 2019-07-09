import React from 'react';
import '../css/Body.css';
import { thisExpression } from '@babel/types';
import SignIn from './SignIn';

export default class ContentSearchResult extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      renderLog: false
    }  
  }  
login(){
  this.setState({ renderLog: !this.state.renderLog})
}

handlerLog() {
    this.setState({ renderLog: false })
}
  
render() {
  
  const things = this.props.k.map((thing, i) => {
    return(
      
      <div className="card mt-4 col-md-4" key={i}>
          <div className="card-headercard-title text-center">
            <h4>{thing.name}</h4>
            <div className="card-body">
            {"Descripción: " + thing.description}
            <button type="button" className="btn btn-primary btn-block" onClick={() => this.login() }>hacé tu pedido!</button>
            { this.state.renderLog &&
              <SignIn id="2" handlerLog = {this.handlerLog} fromWhichComponent = 'search' code = {thing.code} />}
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