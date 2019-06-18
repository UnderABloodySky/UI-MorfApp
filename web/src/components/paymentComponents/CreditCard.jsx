import React from 'react';

import Cards from 'react-credit-cards';
import 'react-credit-cards/es/styles-compiled.css';
import {
    formatCreditCardNumber,
    formatCVC,
    formatExpirationDate
  } from './CardUtils'


export default class CreditCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: '',
          paymentMethods: [],
          selectedPaymentMethod: '', 
          selectedMenus: [],
          toShoppingCart: false,
          toOrders: false,
          number: 0,
          name: '',
          expiry: 0,
          cvc: 0,


        };
        this.changeNumber = this.changeNumber.bind(this);
        this.changeName = this.changeName.bind(this);
        this.changeExpiry = this.changeExpiry.bind(this);
        this.changeCvc = this.changeCvc.bind(this); 
        this.state.id = this.props.id;
        this.state.code = this.props.code;
        this.state.selectedMenus = this.props.selectedMenus;
    }
    changeNumber(event){
        this.setState({ number: formatCreditCardNumber(event.target.value) });
      }
    changeName(event){
        this.setState({ name: event.target.value });
    }
    changeExpiry(event){
        this.setState({ expiry: formatExpirationDate(event.target.value) });
    }
    changeCvc(event){
        this.setState({ cvc: formatCVC(event.target.value) });
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
    return (<div>
                <div class="grid-container">
                        <div class="item1"><Cards
                                                number={this.state.number}
                                                name={this.state.name}
                                                expiry={this.state.expiry}
                                                cvc={this.state.cvc}
                                                focused={this.state.focused}
                                            />
                        </div>
                        
                        <div class="item2"> {this.renderInput('Número de tarjeta:', this.state.number, 'text', this.changeNumber)}
                                            {this.renderInput('Nombre:', this.state.name, 'text', this.changeName)}
                                            {this.renderInput('Expiración:', this.state.expiry, 'text', this.changeExpiry)}
                                            {this.renderInput('Código Seguridad:', this.state.cvc, 'text', this.changeCvc)}
                        </div>
                </div>      
            </div>
    )
}
}