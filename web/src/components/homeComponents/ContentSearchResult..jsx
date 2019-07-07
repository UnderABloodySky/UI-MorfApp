import React from 'react';
import '../css/Body.css';

export default class ContentSearchResult extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
  
  const things = this.props.k.map((thing, i) => {

    return(
      
      <div className="card mt-4 col-md-4" key={i}>
          <div className="card-headercard-title text-center">
            <h4>{thing.name}</h4>
            <div className="card-body">
            {"Descripción: " + thing.description}
            <mark>{thing.code}</mark>
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