import React from 'react';
import { Link } from 'react-router-dom';
import { Redirect } from 'react-router-dom'

export default class NavBarItem extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            user: ''
        }
        console.log("NavbarItem User:")
        console.log(this.props.user)
        this.state.user = this.props.user
    }

    render(){
        console.log("NavBarItem" + this.props.name)
        console.log(this.props.user)
        return(<li className={this.props.isFirstOne? "nav-item active" : "nav-item"}>
            
            <Link className="nav-link" to={{
                pathname: this.props.id,
                state: { id: this.state.user} }}>{this.props.name }</Link>


            <span className={this.props.isFirstOne? "sr-only" : ""}></span></li>);
    }
}


//<Link className="nav-link" to={this.props.id}>{this.props.name }</Link>