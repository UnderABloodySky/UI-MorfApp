import React from 'react';

import { Redirect } from 'react-router-dom'
import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'
import SearchForm from './homeComponents/SearchForm';

import HistoricOrder from './HistoricOrders.jsx'
import PendingOrder from './PendingOrder.jsx'
import './css/Orders.css';
import './css/Body.css'

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: -1,
          pendingOrders: [],  
          historicOrders: [],
          rating: 1,
          toShoppingCart: false
        }; 
        if (this.props !== undefined){
          console.log(this.props.id)
            this.state.id = this.props.user;  //Chequeo que haya venido una props
   
        }/*
        if (this.props.location !== undefined){
          this.state.code = this.props.location.state.code
        } 
        */
      }

    backToShoppingCart = () => {
      if (this.state.code > -1){
        this.setState({
          toShoppingCart: true        
        })
      }else{
        alert("No se puede volver al carrito, debe hacer un nuevo pedido desde la búsqueda")
        //renderizar busqueda
      }

    };  
    
    componentDidMount(){
       getPendingOrdersFrom(this.state.id)
        .then(result => { 
          this.setState({pendingOrders: result})});
      getHistoricOrdersFrom(this.state.id)
        .then(result => {
          this.setState({historicOrders: result})});
        //  console.log(this.state.historicOrders)      
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

              
                                                     
      if(this.state.toShoppingCart){
        return <Redirect to={{
          pathname: '/sc',
          state: { id: this.state.id,
                   password: this.state.password,
                   code: this.state.code,
                   fromWhichComponent: 'search' } }}/>
      }                                         
      
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div>  
              <div className="ribbon">
                  <div><h3>{this.state.id}! Logueado! </h3></div>  
              </div>
                <div><h3>Ordenes Pendientes</h3></div>
                  <ul>
                    <div className="grid-container3">                      
                      {this.state.pendingOrders.map(mappingOrderCode)}                      
                    </div>
                  </ul>                                          
                <div><h3>Ordenes Históricas</h3></div>
                  <ul>
                    <div className="grid-container3">
                      {this.state.historicOrders.map(mappingHistoricOrderCode)}
                    </div>
                  </ul>
                  <div className="back">
                        <button className="btn comeBack" onClick={this.backToShoppingCart}>Volver al Carrito</button>
                  </div>
            </div>    
        )}
  }
/*
  <div className="ribbon">
                  <div><h1>{this.state.id}! Logueado! </h1></div>  
              </div>
                <SearchForm fromWhichComponent="orders"/>
                <div>Ordenes Pendientes</div>
              */