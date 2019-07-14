import React from 'react';
import '../css/Body.css';
import Page from './Page'
import {mySearch} from '../../api/api'

export default class NavbarResponse extends React.Component {
    constructor(props){
        super(props);
        this.state={
            toShow: [],
            mustBeRender:false
        }
        console.log("Lluegue a NavbarResponse")
    }

    componentDidMount(){
        const { q } = this.props.location.state  
        if (q !== ''){
            mySearch(q)
            .then(result => { 
                console.log("DidMountNavbarResponse")
                console.log(q)
                console.log(result)
                this.setState({toShow: result, mustBeRender:true})});
        }
    }

     render(){
              return (
                this.state.mustBeRender && <Page child={this.state.toShow} id="6"/>
               );
    }
}