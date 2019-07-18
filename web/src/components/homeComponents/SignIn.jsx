import React from 'react';
import { signIn } from '../../api/api';
import { Redirect } from 'react-router-dom'
import '../css/SignIn.css';

export default class SignIn extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: '',
      password: '',
      error: '',
      toComponent: false,
      header: '',
      fromWhichComponent: ''
    };
    this.state.fromWhichComponent = this.props.fromWhichComponent
    this.state.header = this.props.header

    this.changeUsername = this.changeUsername.bind(this);
    this.changePassword = this.changePassword.bind(this);
    this.executeSignIn = this.executeSignIn.bind(this);
    
  }

  changeUsername(event) {
    if(event.target.value===''){
      this.setState({ error: 'Se debe completar el usuario' })
      }
    else{ 
      this.setState({ id: event.target.value });}
  }

  changePassword(event) {
    if(event.target.value===''){
      this.setState({error:'Se debe completar la contraseña'})
      }
    else {
      this.setState({ password: event.target.value });}
  }

  executeSignIn() {
    console.log('la pass')
    console.log(this.state.password)
    if (this.state.id===null||this.state.password===null)
    {this.setState({error:'Se debe completar usuario/ contraseña'})}
    else{
    signIn({ id: this.state.id, password: this.state.password })
      .then(() => this.setState({ toComponent: true }))
      .catch(() => this.setState({ error: 'Usuario o contraseña incorrecta' }));
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

  render() {
    if (this.state.fromWhichComponent == 'search'){
      if (this.state.toComponent){
        return <Redirect to={{
          pathname: '/sc',
          state: { id: this.state.id, password: this.state.password, code: this.props.code, fromWhichComponent: 'search' } }}/>
      }
    }
    if (this.state.fromWhichComponent == 'login'){
      if (this.state.toComponent){
        return <Redirect to={{
          pathname: '/orders',
          state: { id: this.state.id, password: this.state.password } }}/>
      }
    }

    return (
      <div className="container" >
        <div className="row centerRow">
          <div className="col-3" />
          <div className="col-6 card newCard">
            <div className="card-body">
              <div className="headerLoader">
                {this.state.header}
              </div>
              {this.renderInput('Usuario', this.state.id, 'text', this.changeUsername)}
              {this.renderInput('Contraseña', this.state.password, 'password', this.changePassword)}
              <div className="col-12">
                <button type="button" className="btn btn-primary btn-block" onClick={this.executeSignIn}>Ingresar</button>
              </div>
              <div className="col-12">
                <button type="button" id="focus" className="btn btn-link" onClick={this.props.handlerLog}>Cancelar</button>
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