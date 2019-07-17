import React from 'react';
import '../css/Body.css';
import Page from './Page'
import Container from './Container'
import {mySearch} from '../../api/api'

/*
if (this.state.fromWhichComponent == 'login'){
    if (this.state.toComponent){
      return <Redirect to={{
        pathname: '/orders',
        state: { id: this.state.id, password: this.state.password } }}/>
    }
  }
*/

export default class OrderPage extends React.Component {
    constructor(props){
        super(props);
        this.state={
            toShow: [],
        //    mustBeRender:false
        }
     console.log(this.props.location.state.id)
    }

    componentDidMount(){
      const { q } = this.props.location.state  
    }

    
/*    componentDidMount(){
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
  */  
     render(){
              return (
                  <Page child={this.state.toShow} user={this.props.location.state.id} id="7"/>
    //            <Page child={this.state.toShow} user={this.props.location.state.id} id="7"/>
    //             <Page child={this.state.toShow} id={this.props.location.state.id} id="6"/>
    //            this.state.mustBeRender && <Container content={this.state.toShow} id="6"/>
        
               );
    }
}