import React from 'react';

import { Redirect } from 'react-router-dom'
import { getPendingOrdersFrom } from '../api/api'
import { getHistoricOrdersFrom } from '../api/api'

export default class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          id: '',
          pendingOrders: [],  
          historicOrders: []
        };
        if (this.props.location.state !== undefined){
          this.state.id = this.props.location.state.id;  //Chequeo que haya venido una props
        }
      }

    componentDidMount(){
       getPendingOrdersFrom(this.state.id)
        .then(result => { 
          //console.log(result);
          this.setState({pendingOrders: result})});
      getHistoricOrdersFrom(this.state.id)
        .then(result => {
          this.setState({historicOrders: result})});      
    }

    render() {
      const mappingOrderCode = item => (<li key={item.code_order_complete}>
                                            Código Orden: {item.code_order_complete}
                                            Código Restaurant: {item.restaurant}
                                            {item.menus.map(itMenus => (<li key={itMenus.menuId}> Código Menú: {itMenus.menuId}
                                                                                                  Cantidad: {itMenus.ammount}
                                                                        </li>))}
                                       </li>)
      if (this.state.id === ''){
        return <Redirect to={'/'}/> //Caso que se entre directamente a /orders
      }
        return(
            <div>
                <div>{this.state.id} LOGUEADO </div>  
                <div>Ordenes pendientes</div>
                  <div>
                    <ul>
                      {this.state.pendingOrders.map(mappingOrderCode)}
                    </ul>
                  </div>
                <div>Ordenes Históricas</div>
                  <ul>
                    {this.state.historicOrders.map(mappingOrderCode)}
                  </ul>

            </div>
        );
      }
}