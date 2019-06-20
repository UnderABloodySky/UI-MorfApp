import React from 'react';
import {restaurants}  from '../../api/api';
import Page from '../homeComponents/Page'

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
            <div>
                <h1>Restaurants!</h1>
                <Page />
            </div>
        );
    }

}