import React from 'react';

import { Redirect } from 'react-router-dom'

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          pendingOrders: [],
          finishedOrders: []
        };
        if (this.props.location.state != undefined){
          this.state.id = this.props.location.state.id;  //Chequeo que haya venido una props
        }
      }

    componentDidMount(){
      //pegarle a la API
    }

    render() {
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div>
                <div>{this.state.id} LOGUEADO</div>  
            </div>
        );
      }
}