import React from 'react';

import { Redirect } from 'react-router-dom';
import { getMenus } from '../api/api';

import './css/ShoppingCart.css';

export default class ShoppingCart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          password: '',
          code: 0,          
          availableMenus: [],  
          selectedMenus: [],
          orderSubtotal: 0,
          orderTotal: 0,
          toOrders: false,
          toPayment: false,
          fromWhichComponent: ''
        };
        this.state.id = this.props.location.state.id;
        this.state.code = this.props.location.state.code;
        this.state.password = this.props.location.state.password;
        this.state.fromWhichComponent = this.props.fromWhichComponent
                
        if (this.state.fromWhichComponent === "payment"){
            this.state.orderSubtotal = this.props.orderSubtotal
            this.state.orderTotal = this.props.orderTotal
            this.state.availableMenus = this.props.availableMenus; 
        }
    }

    toPayment = () => {
        if (this.state.selectedMenus.length > 0 && this.state.orderTotal > 0) {
            this.setState({
                toOrders: false,
                toPayment: true
            })
        }else{    
            alert("No ha agregado ningún menú al pedido.");
        }
    };

    backToOrders = () => {
        this.setState({
          toOrders: true,
          toPayment: false
        })
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

    addMenuToOrder(givenMenu){
        var found = false;
        for(var i = 0; i < this.state.selectedMenus.length; i++){
            if (this.state.selectedMenus[i].menu.code === givenMenu.code){
                found = true;
                if (found){
                    this.state.selectedMenus[i].ammount++;
                    this.setState({selectedMenus: this.state.selectedMenus});
                }
                break;
            }
        }
        if (!found){
            var tempObj = { ammount: 1, menu: givenMenu };
            this.setState({selectedMenus: this.state.selectedMenus.concat([tempObj])});
        }
        this.setState({orderSubtotal : this.state.orderSubtotal + this.unitPrice(givenMenu)})
        this.setState({orderTotal : this.state.orderTotal + this.discountPrice(givenMenu)})
    }

    addOne(givenMenu){
        givenMenu.ammount++;
        this.setState({selectedMenus : this.state.selectedMenus});
        this.setState({orderSubtotal : this.state.orderSubtotal + this.unitPrice(givenMenu.menu)})
        this.setState({orderTotal : this.state.orderTotal + this.discountPrice(givenMenu.menu)})
    }

    removeOne(givenMenu){
        if(givenMenu.ammount > 1){
            givenMenu.ammount--;
            this.setState({selectedMenus : this.state.selectedMenus});
        }else{            
            this.removeMenuFromOrder(givenMenu);
        }
        this.setState({orderSubtotal : this.state.orderSubtotal - this.unitPrice(givenMenu.menu)})
        this.setState({orderTotal : this.state.orderTotal - this.discountPrice(givenMenu.menu)})        
    }

    removeMenuFromOrder(givenMenu){
        var index = this.state.selectedMenus.indexOf(givenMenu); 
        this.state.selectedMenus.splice(index, 1);
        this.setState({selectedMenus : this.state.selectedMenus});
        this.setState({orderSubtotal : this.state.orderSubtotal - (givenMenu.ammount * this.unitPrice(givenMenu.menu))})
        this.setState({orderTotal : this.state.orderTotal - (givenMenu.ammount * this.discountPrice(givenMenu.menu))})
    }
    renderSelectedMenus(){
        return((menus) => <li className = "li2" key={menus.menu.code}>
                                <div className="grid-container4">
                                    <div>{menus.menu.name} </div>
                                    <div>{menus.ammount} </div>
                                    <div>{this.unitPrice(menus.menu)}$</div>
                                    <div>{this.unitPrice(menus.menu) * menus.ammount}$</div>
                                    <div>{this.discountPrice(menus.menu) * menus.ammount}$</div>
                                    <div><button className="btn btn-secondary" onClick={() => this.removeOne(menus)}>-</button></div>
                                    <div><button className="btn btn-success" onClick={() => this.addOne(menus)}>+</button></div>
                                    <div><button className="btn btn-danger" onClick={() => this.removeMenuFromOrder(menus)}>x</button></div>
                                </div>
                            </li>            
                    )
    }

    renderAvailableMenus(){
        return(
                (menus) =><li key={menus.code}>
                    
                        <div className="cardif">  
                            <div className="grid-container">                      
                                <div className="photo">
                                    <img src={menus.menuImage}/>
                                </div>
                                <div className="description">
                                    <h2>{menus.name}</h2>
                                    <h4>{menus.description}</h4>
                                    <h1>{this.unitPrice(menus)}$</h1>  
                                    <button className="btn btn-secondary" onClick={() => this.addMenuToOrder(menus)}>Agregar</button>
                                </div> 
                            </div>
                        </div>        
                    
                </li>
        )
    }    

    componentDidMount(){           
        getMenus(this.props.location.state.code) 
        .then(result => {
            this.setState({            
            availableMenus: result })})       
    }

    render(){
        
        if(this.state.toOrders && !this.state.toPayment){
            return(<Redirect to={{
                        pathname: '/orders',
                        state: { id: this.state.id, password: this.state.password, code: this.state.code } }}/>)
        }
        if(!this.state.toOrders && this.state.toPayment){
            return(<Redirect to={{
                        pathname: '/payorder',
                        state: { id: this.state.id,
                                 password: this.state.password,
                                 code: this.state.code,
                                 selectedMenus: this.state.selectedMenus,
                                 orderSubtotal: this.state.orderSubtotal,
                                 orderTotal: this.state.orderTotal } }}/>)
        }
        return( <div>
                    <div className="grid-container0">
                        <div className="card">                
                            <h2>Agregue productos del listado:</h2>
                            <div className="grid-container2">
                                {this.state.availableMenus.map(this.renderAvailableMenus())}
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
                                            <div>Restar</div>
                                            <div>Sumar</div>
                                            <div>Quitar</div>
                                        </div>
                                        {this.state.selectedMenus.map(this.renderSelectedMenus())}    
                                                        
                                    </ul>
                                </div>
                            <div className="righty">Subtotal del Pedido: {this.state.orderSubtotal}$</div>
                            <div className="righty"><h4>Total del Pedido: {this.state.orderTotal}$</h4></div>
                            <div className="forward">
                                <button className="btn goForward" onClick={this.toPayment}>Realizar el Pago!</button>
                            </div>
                            <div className="back">
                            <button className="btn comeBack" onClick={this.backToOrders}>Volver a Ordenes</button>
                            </div>
                            
                        </div>
                    </div>
                    
                </div>
                
                );
    }
}