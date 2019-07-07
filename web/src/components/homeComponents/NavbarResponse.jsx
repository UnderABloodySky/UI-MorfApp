import React from 'react';
import '../css/Body.css';
import Page from './Page'
import {mySearch} from '../../api/api'


export default class NavbarResponse extends React.Component {
    constructor(props){
        super(props);
        this.state={
            toShow: [],
            flag: false
        }
    }

    componentDidMount(){
        const { q } = this.props.location.state  
        console.log(q)
        mySearch(q)
        .then(result => { 
         this.setState({toShow: result, flag: true })});
    }


     render(){
        return(
            <div>
              {this.state.flag && <Page child={this.state.toShow} id="6"/>}
            </div>
        );
    }

}