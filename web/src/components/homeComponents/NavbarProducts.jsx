import React from 'react';
import {products}  from '../../api/api';
import Page from '../homeComponents/Page'

export default class NavbarProducts extends React.Component {
    constructor(){
        super();
        this.state={
            productsToShow: []
        }
    }

    componentDidMount(){
        products()
        .then(result => { 
          this.setState({productsToShow: result})});
    }

    render(){
        return(
            <div>
                <Page child={this.state.productsToShow} id="3"/>
            </div>
        );
    }

}