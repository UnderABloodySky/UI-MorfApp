import React from 'react';
import {menues}  from '../../api/api';
import Page from '../homeComponents/Page'

export default class Response extends React.Component {
    constructor(props){
        super(props);
    }

    render(){
        return(
            <div>
                <Page child={this.props.search} id="6"/>
            </div>            
        );
    }

}