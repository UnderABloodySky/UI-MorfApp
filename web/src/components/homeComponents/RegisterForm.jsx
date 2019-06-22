import React from 'react';
import '../css/Body.css';

export default class RegisterForm extends React.Component {
  constructor(props){
    super(props);
    this.state={
      username: '',
      email: '',
      address: '',
      password: '',
      longitud:'',
      latitud:'',
    }
    this.handleInput = this.handleInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();
    this.props.send(this.state);
    this.setState({
      username: '',
      email: '',
      address: '',
      password: '',
      longitud:'',
      latitud:'',
    });
  }

 

handleInput(e){
   const { value, name } = e.target;
   this.setState({
        [name]: value
   })
}

reset(){
  this.setState({
    username: '',
    email: '',
    address: '',
    password: '',
    longitud:'',
    latitud:'',
  });
}
  
  render() {
      return (
        <div>
          <h1> :: Registro! ::</h1>
          <div className="card mr-4 md-4 mt-4">
                  <form onSubmit={this.handleSubmit} className="card-body">
                      <div className="form-group">
                          <input 
                              type="text"
                              name="username"
                              onChange={this.handleInput}
                              className="form-control"
                              placeholder="Username" 
                          />
                      </div>
              <div className="form-group">
                      <input 
                          type="text"
                          name="password"
                          className="form-control"
                          placeholder="****" 
                          onChange={this.handleInput}
                      />
              </div>
             <div className="form-group">
                      <input 
                          type="text"
                          name="email"
                          className="form-control"
                          placeholder="email" 
                          onChange={this.handleInput}
                      />
              </div>
              <div className="form-group">
                      <input 
                          type="text"
                          name="address"
                          className="form-control"
                          placeholder="direccion" 
                          onChange={this.handleInput}
                      />
              </div>
              <div className="form-group">
                      <input 
                          type="text"
                          name="latitud"
                          className="form-control"
                          placeholder="latitud" 
                          onChange={this.handleInput}
                      />
              </div>
              <div className="form-group">
                      <input 
                          type="text"
                          name="longitud"
                          className="form-control"
                          placeholder="latitud" 
                          onChange={this.handleInput}
                      />
              </div>
              <div className="form-group">
                      <select
                          name = "genero"
                          className="form-control">
                          <option>Femenino</option>
                          <option>Masculino</option>
                          <option>No Binario</option>
                          <option>No Binario</option>
                          <option>Otro</option>
                      </select>
              </div>
              <button type="submit" className="btn btn-primary">
              Enviar!
              </button>
              <button action={this.reset} type="reset" className="btn btn-primary">
              Limpiar!
              </button>
              </form>
          </div>
          </div>
          );
      }
  }
