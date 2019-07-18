import React from 'react';
import NavBarItem from './NavBarItem';
import SearchFormOrder from './SearchFormOrder';

var items = [ {"code":"0", "name":"Nosotros", "id":"/us"},
              {"code":"1", "name":"Restaurants", "id":"/all_restaurants"},
              {"code":"2", "name":"Menus", "id":"/all_menus"},
              {"code":"3", "name":"Productos", "id":"/products"},
              {"code":"4", "name":"Contacto", "id":"/contact"},
              {"code":"5", "name":"Pedidos", "id":"/orders"}
            ]

export default class NavbarOrder extends React.Component {
  constructor(props){
    super(props)
    this.state ={
      user:''
    }
    this.state.user =  this.props.user;
    this.user = this.user.bind(this)
    console.log("Navbar Order")
    console.log(this.state.user)
  }

  user(){
    console.log("Navbar Order")
    console.log(this.state.user)
    return this.state.user
  } 
  
render() {
  return (
<nav className="navbar navbar-expand-lg navbar-light bg-light sticky-top">
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
    <span className="navbar-toggler-icon"></span>
  </button>
  <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
    <a className="navbar-brand" href="/">Home</a>
    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
    {items.map(function(currentValue, index){
      return <NavBarItem key={currentValue.code}
                         name={currentValue.name}
                         id={currentValue.id}
                         isFirstOne={index===0? true : false}
                         //user={this.user}
                         />;  
    })}

    </ul>
        <SearchFormOrder fromWhichComponent="navbar"/>
    </div>
    </nav>
    );
  }
}
