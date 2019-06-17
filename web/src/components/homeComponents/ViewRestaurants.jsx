import React from 'react';
import {restaurants}  from '../../api/api';
import { request } from 'http';

export default class ViewRestaurants extends React.Component {
    constructor(){
        super();
        this.state={
            restaurantsToShow: []
        }
    }

    componentDidMount(){
        restaurants()
        .then(result => { 
          this.setState({restaurantsToShow: result})});
    }

    render(){
        console.log(this.state.restaurantsToShow)
        return(
            <h1>Eureka!</h1>
        );
    }

}