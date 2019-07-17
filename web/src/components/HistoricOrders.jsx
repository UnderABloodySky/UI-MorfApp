import React from 'react';
import ReactDOM from 'react-dom';
import { Redirect } from 'react-router-dom'
import './css/Order.css';



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
                        <div className="card border-info mb-3 car1" >
                            <div className="card-body text-primary">
                            <h5 className="card-title">
                                <p>Restaurant: {this.props.restaurantName}</p>
                            </h5>
                                <h6>
                                     <p> Código Orden: {this.props.code_order_complete}  </p>
                                </h6>
                                <p><mark>
                                    {this.props.menus.map(itMenus => (<li className= "text-info"key={itMenus.code}>
                                                                        <p>Menú: {itMenus.name}</p>
                                                                        <p>Cantidad: {itMenus.ammountOfMenus}</p>
                                                                        <p>Precio: {itMenus.price} $</p>
                                                                    </li>))}
                            
                                    <p className = "text-info">Precio Total: {this.priceOfOrder()} $</p>
                                </mark></p>
                            </div>
                        </div>    
                    </div>  
            </li>

    return(
        <div>{x}</div>
    );
 }
}