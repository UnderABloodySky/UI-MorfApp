import React from 'react';
import { Link } from 'react-router-dom';

export default class NavBarItem extends React.Component {
    render(){
        return(<li className={this.props.isFirstOne === true? "nav-item active" : "nav-item"}><Link className="nav-link" to={this.props.id}>{this.props.name}</Link><span className={this.props.isFirstOne == true? "sr-only" : ""}></span></li>);
    }
}
