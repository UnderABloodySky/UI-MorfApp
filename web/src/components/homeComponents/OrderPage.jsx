import React from 'react';
import '../css/Body.css';
import Page from './Page';

export default class OrderPage extends React.Component {
    constructor(props){
        super(props);
        this.state={
            toShow: [],
        }
    }

     render(){
              return (
                  <Page child={this.state.toShow} user={this.props.location.state.id} id="7"/>
               );
    }
}