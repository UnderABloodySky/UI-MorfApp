import React from 'react';
import {menues}  from '../../api/api';
import Page from '../homeComponents/Page'

export default class NavbarMenues extends React.Component {
    constructor(){
        super();
        this.state={
            menuesToShow: []
        }
    }

    componentDidMount(){
        menues()
        .then(result => { 
          this.setState({menuesToShow: result})});
    }

    render(){
        console.log(this.state.menuesToShow)
        return(
            <div>
                <Page />
            </div>            
        );
    }

}