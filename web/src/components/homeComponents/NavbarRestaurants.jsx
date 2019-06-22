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

    componentWillMount(){
        console.log("Will");
        restaurants()
        .then(result => { 
         this.setState({restaurantsToShow: result})});
    }

    render(){
        return(
            <div>
              <Page child={this.state.restaurantsToShow} id="1"/>
            </div>
        );
    }

}