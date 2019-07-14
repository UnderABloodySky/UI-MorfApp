import React from 'react';

import { findRestaurant } from '../api/api';
import { deliver } from '../api/api';
import CreditCard from './paymentComponents/CreditCard';
import Cash from './paymentComponents/Cash';
import MercadoPago from './paymentComponents/MercadoPago';
import Select from 'react-select'
import { Redirect } from 'react-router-dom';

import './css/PayOrder.css';

export default class PayOrder extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          password: '',
          code: 0,
          orderSubtotal: 0,
          orderTotal: 0,
          paymentMethods: [],
          selectorPM: [], 
          selectedMenus: [],

          renderCreditCard: false,
          renderCash: false,
          renderMP: false,
          selectedPaymentMethod: null,

          toShoppingCart: false,
          toOrders: false,
        };
        this.state.id = this.props.location.state.id;
        this.state.password = this.props.location.state.password;
        this.state.code = this.props.location.state.code;
        this.state.selectedMenus = this.props.location.state.selectedMenus;
        this.state.orderSubtotal = this.props.location.state.orderSubtotal;
        this.state.orderTotal = this.props.location.state.orderTotal;
        this.paymentHandler = this.paymentHandler.bind(this);
    }

    processOrder = () => {
        var tempObj = { type: this.state.selectedPaymentMethod.value,
                        user: "mercadoPago",                         
                        password: "mercadoPago",
                        cardNumber: this.state.selectedPaymentMethod.number,
                        cardOwnerName: this.state.selectedPaymentMethod.name,
                        cardExpirationDate: this.state.selectedPaymentMethod.expiry,
                        cardCode: this.state.selectedPaymentMethod.cvc};
        const body = {  restaurant: this.state.code,
                        menus: this.makeMenus(),
                        clientID: this.state.id,
                        paymentMethod: tempObj };
        
        deliver(body)
            .then(() => this.toOrders())
            .catch(() => console.log("ERROR"));
    };
    
    makeMenus(){
        var tempObj = [];
        for(var i = 0; i < this.state.selectedMenus.length; i++){
            tempObj.push({ ammount : this.state.selectedMenus[i].ammount, menuId : this.state.selectedMenus[i].menu.code })
        }
        return tempObj;
    };
    

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

    paymentHandler(payment) {
        this.setState({
          selectedPaymentMethod: payment
        })
      }

    changePaymentMethod = selectedPaymentMethod => {
        this.setState({ selectedPaymentMethod });
        switch (selectedPaymentMethod.value) {
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

    unitPrice(givenMenu){
        return (givenMenu.productsOfMenu.reduce(
                    function(prev, cur) {return prev + cur.price; }
                    , 0)); 
    }

    discountPrice(givenMenu){
        var priceWithDiscount = this.unitPrice(givenMenu);
        if(givenMenu.discount.name === "DescuentoPorMonto"){
            priceWithDiscount = priceWithDiscount - givenMenu.discount.value;       
            }else{
                if(givenMenu.discount.name === "DescuentoPorPorcentaje"){
                    priceWithDiscount = priceWithDiscount - (priceWithDiscount * givenMenu.discount.value / 100);
                }   
            }                            
        return(priceWithDiscount);
    }

    renderSelectedMenus(){
    return((menus) => <li className = "li2" key={menus.menu.code}>
                            <div className="grid-container4">
                                <div>{menus.menu.name} </div>
                                <div>{menus.ammount} </div>
                                <div>{this.unitPrice(menus.menu)}$</div>
                                <div>{this.unitPrice(menus.menu) * menus.ammount}$</div>
                                <div>{this.discountPrice(menus.menu) * menus.ammount}$</div>
                            </div>
                        </li>            
                )
    }

    componentDidMount(){    
        findRestaurant(this.state.code)
        .then(result => {
            var tempObj = result.availablePaymentMethods;
            this.setState({    
            paymentMethods: tempObj })    
        for (var i = 0; i < Object.keys(tempObj).length; i++) {
            var tempObj2 = { value: tempObj[i].typePM, label: tempObj[i].typePM, number: "", name: "", expiry: "", cvc: ""}
            this.state.selectorPM.push(tempObj2);
        }})   
    }

    render() {
        const { selectedPaymentMethod } = this.state;  
        if(this.state.toOrders && !this.state.toShoppingCart){
            return(<Redirect to={{
                        pathname: '/orders',
                        state: { id: this.state.id,
                                 password: this.state.password } }}/>)
        }
        if(!this.state.toOrders && this.state.toShoppingCart){
            return(<Redirect to={{
                        pathname: '/sc',
                        state: { id: this.state.id,
                                 password: this.state.password,
                                 code: this.state.code,
                                 selectedMenus: this.state.selectedMenus,
                                 orderSubtotal: this.state.orderSubtotal,
                                 orderTotal: this.state.orderTotal,
                                 fromWhichComponent: 'payment' } }}/>)
        }
        return (<div>
                <div className="grid-container0">
                    <div className="card">          
                        <h2>Seleccione su método de pago: </h2>     
                        <div>
                            <Select 
                                value={selectedPaymentMethod}
                                onChange={this.changePaymentMethod}
                                options={this.state.selectorPM} />
                            <ul>
                                { this.state.renderCreditCard && <CreditCard paymentHandler = {this.paymentHandler} paymentMethod = {this.state.selectedPaymentMethod}/> }
                                { this.state.renderCash && <Cash /> }
                                { this.state.renderMP && <MercadoPago /> }
                            </ul>    
                        </div>
                    </div>
                    <div className="card">
                        <h2>Su pedido: </h2>
                            <div>
                                <ul>
                                    <div className="grid-container3">                            
                                        <div>Menú </div>
                                        <div>Cant.</div> 
                                        <div>P/Uni</div>
                                        <div>Tot.</div>
                                        <div>C/Desc.</div>
                                    </div>
                                    {this.state.selectedMenus.map(this.renderSelectedMenus())}    
                                                    
                                </ul>
                            </div>
                        <div className="righty">Subtotal del Pedido: {this.state.orderSubtotal}$</div>
                        <div className="righty"><h4>Total del Pedido: {this.state.orderTotal}$</h4></div>
                        <div className="forward">
                            <button className="btn goForward" onClick={this.processOrder}>Realizar el Pago!</button>
                        </div>
                        <div className="back">
                        <button className="btn comeBack" onClick={this.backToShoppingCart}>Volver al Carrito</button>
                        </div>
                    </div>
                </div>                                               
            </div>
        )
    }
}