import React from 'react';
import {restaurants}  from '../../api/api';

export default class NavbarRestaurants extends React.Component {
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
            <h1>Restaurants!</h1>
        );
    }

}