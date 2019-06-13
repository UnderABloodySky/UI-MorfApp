import React from 'react';
import logo, { ReactComponent } from './logo.svg';
import './App.css';

import { Orders } from './Orders.json'
import OrderForm from './components/OrderForm'
import Navigation from './components/Navigation'
 

class App extends React.Component {
  constructor(){
    super();
    this.state = {
      Orders,
      title: "Orders" 
    }
    this.handleAddTodo = this.handleAddTodo.bind(this);
  
  }

  removeTodo(index) {
    console.log(index);
    this.setState({
      Orders: this.state.Orders.filter((e, i) => {
        return i !== index
      })
    });
  }

  handleAddTodo(order) {
    this.setState({
      Orders: [...this.state.Orders, order]
    })
  }
  render(){
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
                onClick={this.removeTodo.bind(this, i)}>
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
                  <OrderForm onAddTodo={this.handleAddTodo}/>
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
  }
}

export default App;
