import React from 'react';
import { Redirect } from 'react-router-dom';
import NavbarResponseOrder from './NavbarResponseOrder';

export default class SearchFormOrder extends React.Component {
  constructor(props){
    super(props);
    this.state={
      q: ''
    }
    this.handleInput = this.handleInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.toOrders = this.toOrders.bind(this)
  }

  handleSubmit(e) {
    e.preventDefault();
    this.setState({
        evaluate: true
    });  
  } 
  
  handleInput(e){
   const { value, name } = e.target;
   this.setState({
        [name]: value
   })
}

toOrders(){
  if (this.state.q !== ''){  
   window.location.reload()
  } 
} 

render() {
  console.log(this.state.q)
  return (
        <div>
            <form onSubmit={this.handleSubmit} className="form-inline my-2 my-lg-0">
                      <div className="form-group">
                          <input 
                              className="form-control mr-sm-2" 
                              aria-label="Search"
                              type="text"
                              name="q"
                              onChange={this.handleInput}
                              placeholder="..." 
                          />
                      </div>
                      <button type="submit" onClick={this.toOrders} className="btn btn-outline-dark">Buscar!</button>
            </form>
          </div>
          );
  
      }
}