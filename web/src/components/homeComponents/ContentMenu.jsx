import React from 'react';
import '../css/Order.css';

export default class ContentMenu extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {

  const myMenus = this.props.k.map((menu, i) => {

    return(
      <div className="container2" >
      <div class="row">
        <div class="col-sm d-flex">
        <div className="card card-body flex-fill" key={i}>
          <img src={menu.img}/>
          <div className="card-body text-primary">
          <h5 className="card-title"><p>
            Menu: {menu.name}
            </p></h5>            
            <h6 className= "text-info">
              <p>
            {"Descripción: " + menu.description}            
            </p></h6>
          </div>
        </div>
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