import React from 'react';
import { Redirect } from 'react-router-dom';
import NavbarResponseOrder from './NavbarResponseOrder';
import PopUpOrder from './PopUpOrder';


export default class SearchFormOrder extends React.Component {
  constructor(props){
    super(props);
    this.state={
      q: '',
      showPopup: false,
      things:[] 
    }
    this.handleInput = this.handleInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  closePopup(){
    this.setState({  
      showPopup: false  
    });
  }
  
  togglePopup() {  
    console.log("SearchForm - TogglePopup")
    this.setState({  
         showPopup: !this.state.showPopup});  
  } 
  

  handleSubmit(e) {
    console.log("SearchForm - handleSubmit")
    e.preventDefault();
    this.setState({
        evaluate: true
    });  
  } 
  
  handleInput(e){
   const { value, name } = e.target;
   this.setState({
        [name]: value})
  }

  render() {
    console.log("SearchFormOrder 1")
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
        <button onClick={this.togglePopup.bind(this)} className="btn btn-outline-dark">Buscar!</button>
      </form>
      <div>
            {this.state.showPopup &&
                      <PopUpOrder  
                      closePopup={this.togglePopup.bind(this)}/>}
      </div>
       </div>
          );
  
      }
}