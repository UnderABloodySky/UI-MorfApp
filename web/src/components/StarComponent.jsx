import React from 'react';
import ReactDOM from 'react-dom';
import StarRatingComponent from 'react-star-rating-component'




class StarComponent extends React.Component{
    constructor(){
        super();
        this.state = {
            rating:1
        }
    };
onStarClick(nextValue, prevValue, name) {
        this.setState({rating: nextValue});
      }
render(){
    const {rating} = this.state;

    return(
        <div>
            <p>Puntuar el pedido:</p>
            <h2> Rating : {rating}  </h2>
            <StarRatingComponent
                name = "rate1"
                starCount = {5}
                value = {rating}
                onStarClick = {this.onStarClick.bind(this)}
            />
            <button type="button" onClick={this.close}>Aceptar</button>
        </div>


    );
    }  
}

export default StarComponent