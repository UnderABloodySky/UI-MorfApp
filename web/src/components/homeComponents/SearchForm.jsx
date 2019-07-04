import React from 'react';
import '../css/Body.css';

export default class SearchForm extends React.Component {
  constructor(props){
    super(props);
    this.state={
      q: ''
    }
    this.handleInput = this.handleInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();
    this.props.search(this.state);
    this.setState({
        q: ''
    });
  }
 
  handleInput(e){
   const { value, name } = e.target;
   console.log(value)
   this.setState({
        [name]: value
   })
}

render() {
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
              
              <button type="submit" className="btn btn-outline-success my-2 my-sm-0">
              Buscar!
              </button>
            </form>
          </div>
          );
      }
  }