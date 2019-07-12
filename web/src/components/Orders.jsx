import React from 'react';

import { Redirect } from 'react-router-dom'
import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'
import { ratePendingOrder } from '../api/api'
import StarRatingComponent from 'react-star-rating-component'
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
        
        this.rateOrder = this.rateOrder.bind(this);
        if (this.props.location.state !== undefined){
          this.state.id = this.props.location.state.id;  //Chequeo que haya venido una props
        }
      }
    
      onStarClick(nextValue, prevValue, name) {
        this.setState({rating: nextValue});
        
      }
   
    priceOfOrder(order){
      var total = 0;
      order.menus.map(element => { total = total + element.price });
      return total;  
    }

rateOrder(nextValue,codeOrder) {

     // this.setState({rating: nextValue});
      ratePendingOrder({ code_order:codeOrder , rating: nextValue})
        .then(() => this.setState({ toComponent: true }))
        .catch(() => this.setState({}));
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

      const mappingOrderCode = (order) => (<li key={order.code_order_complete}>
                <div className="container1" >
                    <div className="card-headercard-title text-center">
                        <h3>Código Orden: {order.code_order_complete}</h3>
                      </div>
                        <div className="card-body">
                          <p><h5>Restaurant: {order.restaurantName}</h5></p>
                          <p><mark>{order.menus.map(itMenus => (<li key={itMenus.code}>
                                                                  <p><h6>Menú: {itMenus.name}</h6></p>
                                                                  <p>Cantidad: {itMenus.ammountOfMens}</p>
                                                                  <p>Precio: {itMenus.price} $</p>
                                                              </li>))}
                          <br></br>
                          <p><h4>Precio Total: {this.priceOfOrder(order)} $</h4></p>
                          </mark>
                          </p>
                            <StarRatingComponent
                                  name = "rate1"
                                  starCount = {5}
                                  value = {this.state.rating}
                                 onStarClick = {this.onStarClick.bind(this)}
                                />
                            {<button className ="btn btn-success" 
                                      onClick={this.rateOrder(this.state.rating,order.code_order_complete)}>
                                         Aceptar </button> }
                            <button className="btn btn-danger">Cancelar</button>
                      </div>
                  </div>  
                      
                                          </li>)
      
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
