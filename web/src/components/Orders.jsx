import React from 'react';

import { Redirect } from 'react-router-dom'
import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'

import '../css/Orders.css';

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          pendingOrders: [],  
          historicOrders: []
        };
        if (this.props.location.state !== undefined){
          this.state.id = this.props.location.state.id;  //Chequeo que haya venido una props
        }
      }

    componentDidMount(){
       getPendingOrdersFrom(this.state.id)
        .then(result => { 
          //console.log(result);
          this.setState({pendingOrders: result})});
      getHistoricOrdersFrom(this.state.id)
        .then(result => {
          this.setState({historicOrders: result})});      
    }

    render() {
      const mappingOrderCode = item => (<li key={order.code_order_complete}>
                                          <div className="col-md-4" key={i}>
                                              <div className="card mt-4">
                                                <div className="card-headercard-title text-center">
                                                  <h4>Código Orden: {order.code_order_complete}</h4>
                                                </div>
                                                  <div className="card-body">
                                                    <p>Código Restaurant: {order.restaurantName}</p>
                                                    <p><mark>{order.menus.map(itMenus => (<li key={itMenus.menuId}> Código Menú: {itMenus.menuId}
                                                                                                  Cantidad: {itMenus.ammount}
                                                                                         </li>))}
                                                      </mark>
                                                    </p>
                                                    <button
                                                      className="btn btn-danger"
                                                      onClick={this.removeOrder.bind(this, i)}>
                                                      Cancelar
                                                    </button>
                                                  </div>
                                                  </div>  
                                                </div>
                                          </li>)
      
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div>
                <div>{this.state.id} LOGUEADO </div>  
                <div>Ordenes pendientes</div>
                  <div>
                    <ul>
                      {this.state.pendingOrders.map(mappingOrderCode)}
                    </ul>
                  </div>
                <div>Ordenes Históricas</div>
                  <ul>
                    {this.state.historicOrders.map(mappingOrderCode)}
                  </ul>

            </div>
        
      /*

      const myOrders = this.state.Orders.map((order, i) => {
          return(
            <div className="col-md-4" key={i}>
              <div className="card mt-4">
                <div className="card-headercard-title text-center">
                  <h4>Order: {order.code}</h4>
                  <span className="badge-pill badge-danger ml-2">
                    {order.state}
                  </span>
                </div>
                <div className="card-body">
                  <p>{"Restaurant: " + order.restaurant}</p>
                  <p><mark>{"Menu: " + order.menu}</mark></p>
                  <p><mark>{order.client}</mark></p>
                  <div className="card-footer">
                  <button
                    className="btn btn-danger"
                    onClick={this.removeOrder.bind(this, i)}>
                    Cancelar
                  </button>
                </div>
                </div>  
              </div>
            </div>
            )
        })
        
        return (
            <div className="App">
              <Navigation title = {this.state.title} orders={this.state.Orders}/>
                <div className="container">
                 <div className="row mt-4">
                    <div className="col-md-3 text-center">
                      <img src={logo} className="App-logo" alt="logo" />
                      <OrderForm onAddOrder={this.handleAddOrder}/>
                    </div>
                    <div className="col-md-8">
                      <div className="row">
                        { myOrders }
                      </div>
                  </div>
                </div>
              </div>
            </div>
      );
      );
      }
        */
    
        )}
  }