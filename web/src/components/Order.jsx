import React from 'react';
import ReactDOM from 'react-dom';
import StarRatingComponent from 'react-star-rating-component'
import { ratePendingOrder } from '../api/api'
import './css/Orders.css';
import './css/Body.css'


export default class  Order extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            rating:1,
            toComponent:false
            
        };

        this.rateOrder = this.rateOrder.bind(this);
    };


    priceOfOrder(){
        var total = 0;
        this.props.menus.map(element => { total = total + element.price });
        return total;  
      }
  
  rateOrder(nextValue,codeOrder) {
  
       // this.setState({rating: nextValue});
        ratePendingOrder({ code_order:this.props.code_order_complete , rating:this.state.rating})
          .then(() => this.setState({ toComponent: true }))
          .catch(() => this.setState({}));
      }
    

onStarClick(nextValue, prevValue, name) {
        this.setState({rating :  nextValue});
            
      }
componentDidMount(){
    
}

render(){
//ver como mierda renderizar los menus que no le esta llegando nada.


const x =   <li key={this.props.code_order_complete}>
                    <div className="container1" >
                        <div className="card-headercard-title text-center">
                            <h3>Código Orden: {this.props.code_order_complete}</h3>
                        </div>
                            <div className="card-body">
                            <p><h5>Restaurant: {this.props.restaurantName}</h5></p>
                            <p><mark>{this.props.menus.map(itMenus => (<li key={itMenus.code}>
                                                                    <p><h6>Menú: {itMenus.name}</h6></p>
                                                                    <p>Cantidad: {itMenus.ammountOfMens}</p>
                                                                    <p>Precio: {itMenus.price} $</p>
                                                                </li>))}
                            <br></br>
                            <p><h4>Precio Total: {this.priceOfOrder()} $</h4></p>
                            </mark>
                            </p>
                                <StarRatingComponent
                                    name = "rate1"
                                    starCount = {5}
                                    value = {this.state.rating}
                                    onStarClick = {this.onStarClick.bind(this)}
                                    />
                                {<button className ="btn btn-success" 
                                        onClick={this.rateOrder()}>
                                            Aceptar
                                </button> }
                                <button className="btn btn-danger">
                                            Cancelar</button>
                        </div>
                    </div>  
                        
                </li>



    return(
        <div>{x}</div>
        
       
    );
 }
}