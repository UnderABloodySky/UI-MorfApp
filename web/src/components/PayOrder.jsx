import React from 'react';

import { findRestaurant } from '../api/api';

export default class PayOrder extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: '',
          paymentMethods: [],
          selectedPaymentMethod: '', 
          selectedMenus: [],
          toShoppingCart: false,
          toOrders: false
        };
        this.state.id = this.props.id;
        this.state.code = this.props.code;
        this.state.selectedMenus = this.props.selectedMenus;
    }

    toOrders = () => {
        this.setState({
            toOrders: true,
            toShoppingCart: false
        })
    };

    backToShoppingCart = () => {
        this.setState({
          toOrders: false,
          toShoppingCart: true
        })
    };

    componentDidMount(){    
        findRestaurant(0) //TENGO QUE RECIBIR POR PROPS EL CODE DEL RESTAURANT
        .then(result => {
            var tempObj = result.availablePaymentMethods;
            this.setState({    
            paymentMethods: tempObj })})        
    }

    render() {
        return (<div>
                <ul>                    
                    this.state.paymentMethods.map((menus) =>
                                                    
                        
                                                    
                    )  
                </ul>    
        </div>
        )
    }
}