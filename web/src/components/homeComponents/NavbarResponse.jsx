import React from 'react';
import '../css/Body.css';
import Page from './Page'
import Container from './Container'
import {mySearch} from '../../api/api'

export default class NavbarResponse extends React.Component {
    constructor(props){
        super(props);
        this.state={
            toShow: [],
            mustBeRender:false
        }
    }

    componentDidMount(){
    //    var  q  = '' 
    //    if(this.props.location === undefined){
    //        q = this.props.q
    //        console.log(q);  
    //    }
    //    else{
    //        q  = this.props.location.state.q  
    //    }
        const { q } = this.props.location.state
        if (q !== ''){
                mySearch(q)
                .then(result => { 
                    this.setState({toShow: result, mustBeRender:true})});
          }
    }
    
     render(){
              return (
                this.state.mustBeRender && <Page child={this.state.toShow} id="6"/>
    //            this.state.mustBeRender && <Container content={this.state.toShow} id="6"/>
        
               );
    }
}