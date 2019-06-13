import React from 'react';
import { Redirect } from 'react-router-dom'
import { signUp } from '../../api/api';
import '../css/SignIn.css';

export default class SignUp extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: '',
      email: '',
      password: '',
      name: '',
      address: '',
      latitude: 0.0,
      longitude: 0.0,
      error: '',
      toOrders: false
    };
    this.changeId = this.changeId.bind(this);
    this.changeEmail = this.changeEmail.bind(this);
    this.changePassword = this.changePassword.bind(this);
    this.changeName = this.changeName.bind(this);
    this.changeAddress = this.changeAddress.bind(this);
    this.changeLatitude = this.changeLatitude.bind(this);
    this.changeLongitude = this.changeLongitude.bind(this);
    this.executeSignUp = this.executeSignUp.bind(this);

  }
  changeId(event){
    this.setState({ id: event.target.value });
  }

  changeEmail(event) {
    this.setState({ email: event.target.value });
  }

  changePassword(event) {
    this.setState({ password: event.target.value });
  }

  changeName(event) {
    this.setState({ name: event.target.value });
  }

  changeAddress(event) {
    this.setState({ address: event.target.value });
  }
  changeLongitude(event) {
    this.setState({ longitude: event.target.value });
  }
  changeLatitude(event) {
    this.setState({ latitude: event.target.value });
  }
  executeSignUp() {
    const body = {
      id : this.state.id,
      email: this.state.email,
      password: this.state.password,
      name: this.state.name,
      address: this.state.address,
      latitude: this.state.latitude,
      longitude: this.state.longitude
    };
    signUp(body)
      .then(() => this.setState({ toOrders: true }))
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
    if (this.state.toOrders){
      return <Redirect to={{
        pathname: '/orders',
        state: { id: this.state.id, password: this.state.password } }}/>
    }
    return (
      <div className="container" >
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
              {this.renderInput('Usuario', this.state.id, 'username', this.changeId)}
              {this.renderInput('Contraseña', this.state.password, 'password', this.changePassword)}
              {this.renderInput('Nombre', this.state.name, 'text', this.changeName)}
              {this.renderInput('Direccion', this.state.address, 'text', this.changeAddress)}
              {this.renderInput('Latitud', this.state.latitude, 'text', this.changeLatitude)}
              {this.renderInput('Longitud', this.state.longitude, 'text', this.changeLongitude)}
              
              <div className="col-12">
                <button type="button" className="btn btn-primary btn-block" onClick={this.executeSignUp}>Registrarse</button>
              </div>
              <div className="col-12">
              <button type="button" className="btn btn-link" onClick={this.props.handlerReg}>Cancelar</button>
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