import React from 'react';
import {restaurants}  from '../../api/api';

export default class NavbarRegister extends React.Component {
    constructor(){
        super();
        this.state={
            menuesToShow: []
        }
    }

    componentDidMount(){
        restaurants()
        .then(result => { 
          this.setState({menuesToShow: result})});
    }

    render(){
        console.log(this.state.menuesToShow)
        return(
            <h1>Register!</h1>
        );
    }

}