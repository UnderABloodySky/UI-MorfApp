import React from 'react';

import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'
import HistoricOrder from './HistoricOrders.jsx'
import PendingOrder from './PendingOrder.jsx'
import './css/Orders.css';
import'./css/Order.css';
import jebus from './images/gif-jesucristo.gif';
import snorlax_sleeping from './images/snorlax_sleeping.gif';

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: -1,
          pendingOrders: [],  
          historicOrders: [],
          toShoppingCart: false
        }; 
        this.state.id = this.props.user;  
   
    }

    componentDidMount(){
      getPendingOrdersFrom(this.state.id)
        .then(result => { 
          this.setState({pendingOrders: result})});
      getHistoricOrdersFrom(this.state.id)
        .then(result => {
          this.setState({historicOrders: result})});
   /*   if(this.props.location !== undefined){
          const { q } = this.props.location.state
          if (q !== ''){
                  mySearch(q)
                  .then(result => { 
                      this.setState({toShow: result, mustBeRender:true})});
                  }
     
          }*/    
      }

      
   
    render() {    
      
      const mappingOrderCode = (order) => (<PendingOrder key = {order.code_order_complete}
                                                  id = {this.state.id}
                                                  password = {this.state.password}
                                                  code_order_complete = {order.code_order_complete}
                                                  restaurantName = {order.restaurantName}
                                                  menus = {order.menus}
                                                   />)

      const mappingHistoricOrderCode = (order) => (<HistoricOrder key = {order.code_order_complete}
                                                  id = {this.state.id}
                                                  password = {this.state.password}
                                                  code_order_complete = {order.code_order_complete}
                                                  restaurantName = {order.restaurantName}
                                                  menus = {order.menus}
                                                  />)

        return(
            <div>  
              <div className="ribbon2">
                  <div><h3><p><strong>Bienvenido  {this.state.id}!</strong></p> </h3></div>  
              </div>
                <div><h3><p><strong>Ordenes Pendientes</strong></p></h3></div>
                  <ul>
                    <div className="grid-container3">                      
                      {this.state.pendingOrders.map(mappingOrderCode)}
                      {this.state.pendingOrders.map(mappingOrderCode).length === 0 && 
              <img src={jebus} className="card-img" alt="Ups i did it again !"/>} 
              {this.state.pendingOrders.map(mappingOrderCode).length === 0  && <div className="alert alert-info" role="alert">
                <strong><p>No hay ningún pedido sin puntuar! </p></strong></div>}                      
                    </div>
                  </ul>                                          
                <div><h3><p><strong>Ordenes Históricas</strong></p></h3></div>
                  <ul>
                    <div className="grid-container3">
                      {this.state.historicOrders.map(mappingHistoricOrderCode)}
                      {this.state.historicOrders.map(mappingHistoricOrderCode).length === 0 && 
              <img src={snorlax_sleeping} className="card-img" alt="Ups i did it again !"/>} 
              {this.state.historicOrders.map(mappingHistoricOrderCode).length === 0  && <div className="alert alert-danger" role="alert">
            Aún no has realizado ninguna pedido!</div>}                      
         
                    </div>
                  </ul>
            </div>    
        )}
  }
