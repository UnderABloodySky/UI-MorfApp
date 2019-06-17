import React from 'react';

import { Redirect } from 'react-router-dom'
import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'
import StarRatingComponent from 'react-star-rating-component';

import './css/Orders.css';

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          pendingOrders: [],  
          historicOrders: [],
          rating: 1
        };
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

    componentDidMount(){
       getPendingOrdersFrom(this.state.id)
        .then(result => { 
          this.setState({pendingOrders: result})});
      getHistoricOrdersFrom(this.state.id)
        .then(result => {
          this.setState({historicOrders: result})});      
    }

    render() {
      const mappingOrderCode = (order) => (<li key={order.code_order_complete}>
                                          <div className="col-md-4" >
                                              <div className="card mt-4">
                                                <div className="card-headercard-title text-center">
                                                  <h3>Código Orden: {order.code_order_complete}</h3>
                                                </div>
                                                  <div className="card-body">
                                                    <p><h5>Restaurant: {order.restaurantName}</h5></p>
                                                    <p><mark>{order.menus.map(itMenus => (<li key={itMenus.code}>
                                                                                                            <p><h6>Menú: {itMenus.name}</h6></p>
                                                                                                            <p>Cantidad: {itMenus.ammountOfMenus}</p>
                                                                                                            <p>Precio: {itMenus.price} $</p>

                                                                                         </li>))}
                                                      <br></br>
                                                      <p><h4>Precio Total: {this.priceOfOrder(order)} $</h4></p>
                                                      </mark>
                                                    </p>
                                                    <button className="btn btn-danger">Cancelar</button>
                                                    <button className="btn btn-success ml-4"> Puntuar Pedido</button>  
                                                      
                                                  </div>
                                                  </div>  
                                                </div>
                                          </li>)
      
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div>
                <div><h1>{this.state.id} LOGUEADO </h1></div>  
                <br></br>
                <div><h3>Ordenes Pendientes</h3></div>
                  <div>
                    
                    <ul>
                      {this.state.pendingOrders.map(mappingOrderCode)}
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