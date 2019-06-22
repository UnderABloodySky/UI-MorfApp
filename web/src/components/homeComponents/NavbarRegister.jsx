import React from 'react';
import {restaurants}  from '../../api/api';
import Page from '../homeComponents/Page'

export default class NavbarRegister extends React.Component {
    constructor(){
        super();
     }

    
    render(){
        return(
            <div>
                <Page child={"#"} id="5"/>
            </div>
        );
    }

}