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
        console.log(this.state.productsToShow)
        return(
            <div>
                <h1>Products!</h1>
                <Page />
            </div>
        );
    }

}