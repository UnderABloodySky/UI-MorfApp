import React from 'react';
import ReactDOM from 'react-dom';
import { Redirect } from 'react-router-dom'
import './css/Orders.css';
import './css/Body.css'


export default class  HistoricOrder extends React.Component{
    constructor(props){
        super(props);
        this.state = {
        };
    };

priceOfOrder(){
    var total = 0;
    this.props.menus.map(element => { total = total + element.price });
    return total;  
}
 

render(){

const x =   <li key={this.props.code_order_complete}>
                    <div className="container1" >
                        <div className="card-headercard-title text-center">
                            Código Orden: {this.props.code_order_complete}
                        </div>
                            <div className="card-body">
                                <p>Restaurant: {this.props.restaurantName}</p>
                                <p><mark>
                                    {this.props.menus.map(itMenus => (<li key={itMenus.code}>
                                                                        <p>Menú: {itMenus.name}</p>
                                                                        <p>Cantidad: {itMenus.ammountOfMens}</p>
                                                                        <p>Precio: {itMenus.price} $</p>
                                                                    </li>))}
                            
                                    <p>Precio Total: {this.priceOfOrder()} $</p>
                                </mark></p>
                            </div>
                    </div>  
            </li>

    return(
        <div>{x}</div>
    );
 }
}