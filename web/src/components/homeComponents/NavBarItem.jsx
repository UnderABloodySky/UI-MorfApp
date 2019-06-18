import React from 'react';

export default class NavBarItem extends React.Component {
    render(){
        return(<li className={this.props.isFirstOne === true? "nav-item active" : "nav-item"}><a className="nav-link" href={this.props.id}>{this.props.name}</a><span className={this.props.isFirstOne == true? "sr-only" : ""}></span></li>);
    }
}
