import React from 'react';
import StarRatingComponent from 'react-star-rating-component'
import { ratePendingOrder } from '../api/api'
import './css/Order.css';


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
                    <div className="card border-info mb-3 car1" >
                        <div className="card-body text-primary">
                            <h5 className="card-title">
                                <p>Restaurant: {this.props.restaurantName}</p>
                            </h5>
                            <h6>
                                <p> Código Orden: {this.props.code_order_complete}  </p>
                            </h6>
                            <p><mark>
                                    {this.props.menus.map(itMenus => (<li className = "text-info" key={itMenus.code}>
                                                                        <p>Menú: {itMenus.name}</p>
                                                                        <p>Cantidad: {itMenus.ammountOfMenus}</p>
                                                                        <p>Precio: {itMenus.price} $</p>
                                                                    </li>))}
                            
                                    <p className = "text-info">Precio Total: {this.priceOfOrder()} $</p>
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
                    </div>  
            </li>

    return(
        <div>{x}</div>
    );
 }
}