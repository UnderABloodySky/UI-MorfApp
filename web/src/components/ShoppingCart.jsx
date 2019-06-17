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
    }

    addOne(givenMenu){
        givenMenu.ammount++;
        this.setState({selectedMenus : this.state.selectedMenus});
    }
    removeOne(givenMenu){
        if(givenMenu.ammount > 1){
            givenMenu.ammount--;
            this.setState({selectedMenus : this.state.selectedMenus});
        }else{
            
        }        
    }
    renderSelectedMenus(){
        return(
                (menus) =><li key={menus.menu.code}>
                    <div className="col-md-4" >
                        <div className="card mt-4">
                        <div className="card-headercard-title text-center">
                            Código Menu: {menus.menu.code}
                        </div>
                            <div className="card-body">
                                <p>Menú: {menus.menu.name}</p>
                                <p>Cantidad: {menus.ammount} Precio unitario: {menus.menu.productsOfMenu.reduce(
                                                                                function(prev, cur) {return prev + cur.price; }
                                                                                , 0)}$                                                                    
                                Precio: {menus.menu.productsOfMenu.reduce(
                                            function(prev, cur) {return prev + cur.price; }
                                            , 0) * menus.ammount}$
                                <button className="btn btn-danger" onClick={() => this.removeOne(menus)}>-</button>
                                <button className="btn btn-danger" onClick={() => this.addOne(menus)}>+</button>
                                </p>                                                                             
                                                            
                                <button className="btn btn-danger" onClick={() => this.addMenuToOrder(menus)}>Quitar</button>
                            </div>
                        </div>  
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
                                <p>Precio: {menus.productsOfMenu.reduce(
                                            function(prev, cur) {return prev + cur.price; }
                                            , 0)}                                                                             
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
                        state: { id: this.state.id, password: this.state.password } }}/>)
        }
        return( <div>
                    <div>Su pedido: </div>
                        <div>
                            <ul>
                                {this.state.selectedMenus.map(this.renderSelectedMenus())}
                            </ul>
                        </div>

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