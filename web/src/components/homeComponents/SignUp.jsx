import React from 'react';
import { Link } from 'react-router-dom';

import '../css/SignIn.css';
import { signUp } from '../../api/api';

export default class SignUp extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      username: '',
      password: '',
      name: '',
      adress: '',
      error: '',
    };
    this.changeEmail = this.changeEmail.bind(this);
    this.changeUsername = this.changeUsername.bind(this);
    this.changePassword = this.changePassword.bind(this);
    this.changeName = this.changeName.bind(this);
    this.changeAdress = this.changeAdress.bind(this);
    this.executeSignUp = this.executeSignUp.bind(this);
  }
  changeEmail(event) {
    this.setState({ name: event.target.value });
  }

  changeUsername(event) {
    this.setState({ username: event.target.value });
  }

  changePassword(event) {
    this.setState({ password: event.target.value });
  }

  changeName(event) {
    this.setState({ name: event.target.value });
  }

  changeAdress(event) {
    this.setState({ name: event.target.value });
  }

  executeSignUp() {
    const body = {
      email: this.state.email,
      username: this.state.username,
      password: this.state.password,
      name: this.state.name,
      adress: this.state.adress
    };
    signUp(body)
      .then(userId => this.props.history.push('/home', { userId }))
      .catch(() => this.setState({ error: 'Usuario ya utilizado' }));  
  }

  renderInput(label, value, inputType, onChange) {
    return (
      <div className="form-group row">
        <label className="col-sm-3 col-form-label">{label}</label>
        <div className="col-sm-9">
          <input type={inputType} className="form-control" value={value} onChange={onChange} />
        </div>
      </div>
    );
  }

  componentDidMount(){
    this.nameInput.focus(); 
  }

  render() {
    return (
      <div className="container" ref = { (ref) => this.myRef=ref }>
        <div className="row centerRow">
          <div className="col-3" />
          <div className="col-6 card newCard">
            <div className="card-body">
            <div className="form-group row">
              <label className="col-sm-3 col-form-label">{"Email"}</label>
              <div className="col-sm-9">
                <input type='text' className="form-control" value={this.state.email} onChange={this.changeEmail} ref={(input) => { this.nameInput = input}}/>
              </div>
            </div>
              {this.renderInput('Usuario', this.state.username, 'username', this.changeUsername)}
              {this.renderInput('Contraseña', this.state.password, 'password', this.changePassword)}
              {this.renderInput('Nombre', this.state.name, 'text', this.changeName)}
              {this.renderInput('Direccion', this.state.adress, 'text', this.changeAdress)}
              
              <div className="col-12">
                <button type="button" className="btn btn-primary btn-block" onClick={(event) => {this.executeSignUp();}}>Registrarse</button>
              </div>
              <div className="col-12">
                <Link to="/" className="btn btn-link">Cancelar</Link>
              </div>
              <div className="col-12 empty">
                {this.state.error && this.state.error}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}