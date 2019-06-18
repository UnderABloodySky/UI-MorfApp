import React from 'react';
import {restaurants}  from '../../api/api';

export default class NavbarProducts extends React.Component {
    constructor(){
        super();
        this.state={
            productsToShow: []
        }
    }

    componentDidMount(){
        restaurants()
        .then(result => { 
          this.setState({productsToShow: result})});
    }

    render(){
        console.log(this.state.productsToShow)
        return(
            <h1>Products!</h1>
        );
    }

}