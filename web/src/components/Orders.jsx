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
      
      const mappingOrderCode = (order) => (<Order key = {order.code_order_complete}
                                                  id = {this.state.id}
                                                  password = {this.state.password}
                                                  code_order_complete = {order.code_order_complete}
                                                  restaurantName = {order.restaurantName}
                                                  menus = {order.menus}
                                                   />)
                                          
      
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div>  
              <div className="ribbon">
                  <div><h1>{this.state.id}! Logueado! </h1></div>  
              </div>
                
                <div>Ordenes Pendientes</div>
                  <ul>
                    <div className="grid-container3">                      
                      {this.state.pendingOrders.map(mappingOrderCode)}                      
                    </div>
                  </ul>                                          
                <div>Ordenes Históricas</div>
                  <ul>
                    <div className="grid-container3">
                      {this.state.historicOrders.map(mappingOrderCode)}
                    </div>
                  </ul>
            </div>    
        )}
  }
