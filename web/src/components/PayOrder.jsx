import React from 'react';

export default class PayOrder extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: '',
          selectedMenus: [],
          toShoppingCart: false,
          toOrders: false
        };
        this.state.id = this.props.id;
        this.state.code = this.props.code;
        //this.state.availableMenus = this.props.availableMenus;
        //this.state.selectedMenus = this.props.selectedMenus;
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
    render() {
        return (<div>

        </div>
        )
    }
}