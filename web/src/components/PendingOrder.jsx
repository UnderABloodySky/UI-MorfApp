import React from 'react';
import ReactDOM from 'react-dom';
import StarRatingComponent from 'react-star-rating-component'
import { Redirect } from 'react-router-dom'
import { ratePendingOrder } from '../api/api'
import './css/Orders.css';
import './css/Body.css'


export default class  PendingOrder extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            rating: 1,
            toComponent: false            
        };
        this.rateOrder = this.rateOrder.bind(this);
    };

priceOfOrder(){
    var total = 0;
    this.props.menus.map(element => { total = total + element.price });
    return total;  
}
  
rateOrder() {  
    ratePendingOrder({ code_order: this.props.code_order_complete ,
                       rating: this.state.rating,
                       id:this.props.id})
        .then(() => this.setState({ toComponent: true }))
        .catch(() => console.log(333));
}    

onStarClick(nextValue) {
    this.setState({rating :  nextValue});
}

render(){
if (this.state.toComponent ){
    window.location.reload()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
}

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
                                
                                    <StarRatingComponent
                                        name = "rate1"
                                        starCount = {5}
                                        value = {this.state.rating}
                                        onStarClick = {this.onStarClick.bind(this)}
                                        />
                                    {<button className ="btn btn-success" 
                                            onClick={this.rateOrder}>
                                                Aceptar
                                    </button> }
                            </div>
                    </div>  
            </li>

    return(
        <div>{x}</div>
    );
 }
}