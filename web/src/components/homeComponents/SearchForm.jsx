import React from 'react';
import { Redirect } from 'react-router-dom'
//import NavbarResponse from './NavbarResponse';

export default class SearchForm extends React.Component {
  constructor(props){
    super(props);
    this.state={
      q: '',
      evaluate: false,
      redirect: false//,
  //    orders: false,
  //    fromWhichComponent: ''
    }
    this.handleInput = this.handleInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  //this.state.fromWhichComponent = this.props.fromWhichComponent
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

render() {
  if (this.state.redirect){
    window.location.reload();
  }
  if (this.state.evaluate && this.state.q !== ''){
  //  if(this.state.fromWhichComponent === 'navbar' ){
      this.setState({q: '', redirect: true, evaluate: false});
      return <Redirect to={{pathname: '/content',
      state:{q : this.state.q }}}/>
      }
    //  else{
    //    return <NavbarResponse q={this.state.q}/>
    //   }    
  //}
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
                      <button type="submit" className="btn btn-outline-dark">Buscar!</button>
            </form>
          </div>
          );
  
      }
}