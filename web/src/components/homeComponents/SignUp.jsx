import React from 'react';
import { Redirect } from 'react-router-dom'
import { signUp } from '../../api/api';
import {Gmaps, Marker, InfoWindow, Circle} from 'react-gmaps';
import '../css/SignIn.css';

const coords = {
  lat: -34.605407,
  lng: -58.373566
};

const params = {v: '3.exp', key: 'AIzaSyDJQvobjtc-Z_bZJENwtbTDj23reetxkDk'};

export default class SignUp extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: '',
      email: '',
      password: '',
      name: '',
      address: '',
      latitude: -34.605407,
      longitude: -58.373566,
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
    this.onClick = this.onClick.bind(this);

  }

  onMapCreated(map) {
    map.setOptions({
      disableDefaultUI: true
    });
  }
 
  onClick(event) {
    this.setState({ latitude: event.latLng.lat(), longitude: event.latLng.lng()});
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
    if (this.state.id===''||this.state.password===''||
        this.state.email===''||this.state.name===''||this.state.address==='')
          {this.setState({error:'Se debe completar todos los campos'})}
    else{
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
      <div id="signUp" className="containerSign">
        <div className="row centerRow">
          <div className="col-3" />
          <div className="col-6 card newCard">
            <div className="card-body" >
            <div className="form-group row" >
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
              <Gmaps
                width={'450px'}
                height={'320px'}
                lat={this.state.latitude}
                lng={this.state.longitude}
                zoom={12}
                loadingMessage={'Cargando'}
                params={params}
                onMapCreated={this.onMapCreated}
                onClick={this.onClick}>
                <Marker
                  lat={this.state.latitude}
                  lng={this.state.longitude}
                  draggable={true}/>
                <InfoWindow
                  lat={this.state.latitude}
                  lng={this.state.longitude}
                  content={'Elegí del mapa tu ubicación'} />
              </Gmaps>
              
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