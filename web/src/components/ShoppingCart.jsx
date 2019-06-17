import React from 'react';

import { Redirect } from 'react-router-dom';
import { getMenus } from '../api/api';

import './css/ShoppingCart.css';

export default class ShoppingCart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: '',
          availableMenus: [],  
          selectedMenus: [],
          orderSubtotal: 0,
          orderTotal: 0,
          toOrders: false,
          toPayment: false
        };
        this.state.id = this.props.id;
        this.state.code = this.props.code;
        //this.state.availableMenus = this.props.availableMenus; // ESPERO A QUE HORA ME PASE LOS PROPS DESDE EL BOTON DEL PEDIDO
    }

    toPayment = () => {
        this.setState({
            toOrders: false,
            toPayment: true
        })
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
        console.log(priceWithDiscount);
        console.log(givenMenu);
        if(givenMenu.discount.name == "DescuentoPorMonto"){
            priceWithDiscount = priceWithDiscount - givenMenu.discount.value;       
            }else{
                if(givenMenu.discount.name == "DescuentoPorPorcentaje"){
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
                    this.state.selectedMenus[i].ammount++
                    this.setState({selectedMenus : this.state.selectedMenus});
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
        return((menus) => <li key={menus.menu.code}>
                            <div className="grid-container"> 
                                <div>{menus.menu.name}</div> 
                                <div>{menus.ammount}</div> 
                                <div>{this.unitPrice(menus.menu)}$</div>                                                                  
                                <div>{this.unitPrice(menus.menu) * menus.ammount}$</div> 
                                <div>{this.discountPrice(menus.menu) * menus.ammount}$</div>                                
                                <div><button className="btn btn-danger" onClick={() => this.removeOne(menus)}>-</button></div> 
                                <div><button className="btn btn-danger" onClick={() => this.addOne(menus)}>+</button></div> 
                                <div><button className="btn btn-danger" onClick={() => this.removeMenuFromOrder(menus)}>Quitar</button></div> 
                            </div>
                        </li>            
                    )
    }

    renderAvailableMenus(){
        return(
                (menus) =><li key={menus.code}>
                    <div className="col-md-4" >
                        <div className="card mt-4">
                        <div className="card-headercard-title text-center">
                            <h3>Código Menu: {menus.code}</h3>
                        </div>
                            <div className="card-body">
                                <p>Menú: {menus.name}</p>
                                <p>Precio: {this.unitPrice(menus)}                                                                             
                                $</p>                                                                
                                <button className="btn btn-danger" onClick={() => this.addMenuToOrder(menus)}>Agregar</button>
                            </div>
                        </div>  
                    </div>
                </li>
        )
    }    

    componentDidMount(){    
        getMenus(0)
        .then(result => {
            this.setState({            
            availableMenus: result })})       
    }

    render(){
        
        if(this.state.toOrders && !this.state.toPayment){
            return(<Redirect to={{
                        pathname: '/orders',
                        state: { id: this.state.id, password: this.state.password } }}/>)
        }
        if(!this.state.toOrders && this.state.toPayment){
            return(<Redirect to={{
                        pathname: '/payorder',
                        state: { id: this.state.id,
                                 password: this.state.password,
                                 selectedMenus: this.state.selectedMenus,
                                 orderSubtotal: this.state.orderSubtotal,
                                 orderTotal: this.state.orderTotal } }}/>)
        }
        return( <div>
                    <div>Su pedido: </div>
                        <div>
                            <ul>
                                <div className="grid-container">                            
                                    <div>Menú</div>
                                    <div>Cant.</div>     
                                    <div>P/Uni</div>     
                                    <div>Total</div>    
                                    <div>P/C/Desc.</div>
                                    <div>Restar</div>
                                    <div>Agregar</div>
                                    <div>Quitar</div> 
                                </div>                                                  
                                    {this.state.selectedMenus.map(this.renderSelectedMenus())}
                                                   
                            </ul>
                        </div>
                        <p>Subtotal del Pedido: {this.state.orderSubtotal}</p>
                        <p>Total del Pedido: {this.state.orderTotal}</p>
                    <div>Agregue productos del listado:</div>
                        <div>
                            <ul>
                                
                                {this.state.availableMenus.map(this.renderAvailableMenus())}
                            </ul>
                        </div>
                    <button className="btn btn-danger" onClick={this.backToOrders}>Volver a Ordenes</button>
                    <button className="btn btn-danger" onClick={this.toPayment}>Realizar el Pago</button>
                </div>
                
                );
    }
}