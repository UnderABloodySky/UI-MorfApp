import React from 'react';

import { Redirect } from 'react-router-dom'

export default class ShoppingCart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: '',
          availableMenus: [],  
          selectedMenus: [],
          toOrders: false,
          toPayment: false
        };
        this.state.id = this.props.id;
        this.state.code = this.props.code;
        //this.state.availableMenus = this.props.availableMenus;
        //this.state.selectedMenus = this.props.selectedMenus;
    }

    toPayment = () => {
        this.setState({
            toOrders: false,
            payment: true
        })
    };

    backToOrders = () => {
        this.setState({
          toOrders: true,
          toPayment: false
        })
};
    

    render(){
        const mappingSelectedMenus = (menus) =><li key={menus.code_order_complete}>
                                                    <div className="col-md-4" >
                                                        <div className="card mt-4">
                                                        <div className="card-headercard-title text-center">
                                                            <h3>Código Menu: {menus.code}</h3>
                                                        </div>
                                                            <div className="card-body">
                                                                <p>Menú: {menus.name}</p>
                                                                <p>Cantidad: {menus.ammountOfMenus}</p>
                                                                <p>Precio: {menus.price} $</p>
                                                                <button className="btn btn-danger">Quitar</button>
                                                            </div>
                                                        </div>  
                                                    </div>
                                                </li>
        const mappingAvailableMenus = (menus) =><li key={menus.code_order_complete}>
                                                    <div className="col-md-4" >
                                                        <div className="card mt-4">
                                                        <div className="card-headercard-title text-center">
                                                            <h3>Código Menu: {menus.code}</h3>
                                                        </div>
                                                            <div className="card-body">
                                                                <p>Menú: {menus.name}</p>
                                                                <p>Precio: {menus.price} $</p>
                                                                <button className="btn btn-danger">Agregar</button>
                                                            </div>
                                                        </div>  
                                                    </div>
                                                </li>
        
        if(this.state.toOrders && !this.state.toPayment){
            return(<Redirect to={{
                        pathname: '/orders',
                        state: { id: this.state.id, password: this.state.password } }}/>)
        }else{ if(this.state.toOrders && !this.state.toPayment){
            return(<Redirect to={{
                        pathname: '/payOrder',
                        state: { id: this.state.id, password: this.state.password } }}/>)}
        }
        return( <div>
                    <div>Su pedido:</div>
                        <div>
                            <ul>
                                {this.state.selectedMenus.map(mappingSelectedMenus)}
                            </ul>
                        </div>

                    <div>Agregue productos del listado:</div>
                        <div>
                            <ul>
                                {this.state.availableMenus.map(mappingAvailableMenus)}
                            </ul>
                        </div>

                </div>
                
                );
    }
}