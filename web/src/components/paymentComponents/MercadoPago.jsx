import React from 'react';

import qr1 from '../images/QR1.png';


export default class CreditCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          code: '',
          paymentMethods: [],
          selectedPaymentMethod: '', 
          selectedMenus: []
        };
    }

    render() {
        return (<div>
                    <div className="grid-container">
                            <div className="item1"> 
                            <img src={qr1}/>
                            </div>
                    </div>      
                </div>
        )
    }
} 