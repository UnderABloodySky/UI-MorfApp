import React from 'react';

import Navbar from './homeComponents/Navbar';

export default class Orders extends React.Component {
    constructor(props) {
      super(props);

      this.state = {
        error: '',
      };
    }
    componentDidMount(){

    }

      render() {
        const list = ['a', 'b', 'c'];
        return (
        <div className="container" >
        <div className="row centerRow">
          <div className="col-3" />
          <div className="col-6 card newCard">
            <div className="card-body">
            <div className="form-group row">
              <label className="col-sm-3 col-form-label">{"Ordenes Pendientes"}</label>
              <div className="col-sm-9"> </div>
                <ul>
                    {list.map(item => {
                    return <li key={item}>{item}</li>;
                    })}
                </ul>
              );
            </div>              
              <div className="col-12 empty">
                {this.state.error && this.state.error}
              </div>
            </div>
          </div>
        </div>
      </div>
        );
      }
    
}