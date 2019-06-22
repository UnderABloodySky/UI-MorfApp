import React from 'react';
import '../css/Body.css';

export default class ContentMenu extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
  const myMenus = this.props.k.map((menu, i) => {
    return(
      <div className="card mt-4 col-md-4" key={i}>
          <div className="card-headercard-title text-center">
            <h4>Menu: {menu.name}</h4>
            <span className="badge-pill badge-danger ml-2">
              {menu.enable}
            </span>
          <div className="card-body">
            {"Descripción: " + menu.description}
            <mark>{menu.code}</mark>
          </div>  
        </div>
      </div>
      )})

      return(
        <div>
            {
               myMenus 
            }
        </div>
      )
          }
}