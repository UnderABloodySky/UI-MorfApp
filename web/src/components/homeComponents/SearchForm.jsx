import React from 'react';
import { Redirect } from 'react-router-dom'

export default class SearchForm extends React.Component {
  constructor(props){
    super(props);
    this.state={
      q: '',
      ex: '',
      evaluate: false
    }
    this.handleInput = this.handleInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();
    const aux = this.state.q;
    console.log("SearchForm"); 
    console.log(aux);
    this.setState({
        q: '',
        ex: aux,
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
  if (this.state.evaluate && this.state.ex !== ''){
      const aux = this.state.ex
      this.setState({q: '', ex: '', evaluate: false})
      return <Redirect to={{pathname: '/content',
      state:{q : aux }}}/>    
  }
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