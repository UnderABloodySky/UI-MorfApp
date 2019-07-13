import React from 'react';

import { Redirect } from 'react-router-dom'
import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'

import Order from './Order.jsx'
import './css/Orders.css';
import './css/Body.css'

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          pendingOrders: [],  
          historicOrders: [],
          rating: 1,
          toComponent:false
        };
        
        if (this.props.location.state !== undefined){
          this.state.id = this.props.location.state.id;  //Chequeo que haya venido una props
        }
      }
    
   
    componentDidMount(){
       getPendingOrdersFrom(this.state.id)
        .then(result => { 
          this.setState({pendingOrders: result})});
      getHistoricOrdersFrom(this.state.id)
        .then(result => {
          this.setState({historicOrders: result})});      
    }

    render() {
      
      if (this.state.toComponent){
                  return <Redirect to={{
                    pathname: '/orders',
                    state: { id: this.state.id, password: this.state.password } }}/>
        }
    
      const mappingOrderCode = (order) => (<Order order />)
      
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div>  <div className="ribbon">
                  <div><h1>{this.state.id}! Loggeado </h1></div>  
              </div>
                <br></br>
                <div><h3>Ordenes Pendientes</h3></div>
                  <div>
                      <ul>
                         <div className="grid-container3">
                      
                      {this.state.pendingOrders.map(mappingOrderCode)}
                      console.log(pendingOrders);
                          </div>
                       </ul>
                        
                  </div>
                  <br></br>
                <div><h3>Ordenes Históricas</h3></div>
                  <ul>
                    {this.state.historicOrders.map(mappingOrderCode)}
                  </ul>
            </div>    
        )}
  }
