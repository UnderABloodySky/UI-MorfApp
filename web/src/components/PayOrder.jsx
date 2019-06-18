import React from 'react';

import { findRestaurant } from '../api/api';
import CreditCard from './paymentComponents/CreditCard';
import Cash from './paymentComponents/Cash';
import MercadoPago from './paymentComponents/MercadoPago';
import Select from 'react-select'

export default class PayOrder extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: '',
          paymentMethods: [],
          selectorPM: [], 
          selectedMenus: [],

          renderCreditCard: false,
          renderCash: false,
          renderMP: false,
          selectedOption: null,

          toShoppingCart: false,
          toOrders: false,
        };
        this.state.id = this.props.id;
        this.state.code = this.props.code;
        this.state.selectedMenus = this.props.selectedMenus;
    }

    toOrders = () => {
        this.setState({
            toOrders: true,
            toShoppingCart: false
        })
    };

    backToShoppingCart = () => {
        this.setState({
          toOrders: false,
          toShoppingCart: true
        })
    };

    changePaymentMethod = selectedOption => {
        this.setState({ selectedOption });
        switch (selectedOption.value) {
            case 'CreditCard':
                this.setState({
                    renderCreditCard: true,
                    renderCash: false,
                    renderMP: false,    
                })  
            break;
            case 'DebitCard':
                this.setState({
                    renderCreditCard: true,
                    renderCash: false,
                    renderMP: false,    
                })
            break;
            case 'Cash':
                this.setState({
                    renderCreditCard: false,
                    renderCash: true,
                    renderMP: false,    
                })
            break;
            case 'MercadoPago':
                this.setState({
                    renderCreditCard: false,
                    renderCash: false,
                    renderMP: true,    
                })
            break;              
            default:
                this.setState({
                    renderCreditCard: false,
                    renderCash: false,
                    renderMP: false,    
                })
            break;
          }
      };

    componentDidMount(){    
        findRestaurant(0) //TENGO QUE RECIBIR POR PROPS EL CODE DEL RESTAURANT
        .then(result => {
            var tempObj = result.availablePaymentMethods;
            this.setState({    
            paymentMethods: tempObj })    
        for (var i = 0; i < Object.keys(tempObj).length; i++) {
            var tempObj2 = { value: tempObj[i].typePM, label: tempObj[i].typePM }
            this.state.selectorPM.push(tempObj2);
        }})   
    }

    render() {
        const { selectedOption } = this.state;  
        return (<div>
                    <Select 
                        value={selectedOption}
                        onChange={this.changePaymentMethod}
                        options={this.state.selectorPM} />
                    <ul>
                    { this.state.renderCreditCard && <CreditCard /> }
                    { this.state.renderCash && <Cash /> }
                    { this.state.renderMP && <MercadoPago /> }
                    </ul>    
                </div>
        )
    }
}