import React from 'react';

import '../css/PayOrder.css';


export default class DeliveryAddress extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          adress: '',
          number: '',
          state: '',
          province: ''          
        };
        this.changeAddress = this.changeAddress.bind(this);
        this.changeNumber = this.changeNumber.bind(this);
        this.changeState = this.changeState.bind(this);
        this.changeProvince = this.changeProvince.bind(this); 
    }

    changeAddress(event){
        this.setState({ address: event.target.value });
    }
    changeNumber(event){
        this.setState({ number: event.target.value });
    }
    changeState(event){
        this.setState({ state: event.target.value });
    }
    changeProvince(event){
        this.setState({ province: event.target.value });
    }
    

    renderInput(label, value, inputType, onChange) {
        return (
        <div className="form-group row">
            <label className="col-sm-3 col-form-label">{label}</label>
            <div className="col-sm-9">
            <input type={inputType} className="form-control" value={value} onChange={onChange} />
            </div>
        </div>
        );
    }

    render() {
        return (<div>
                    <div className="grid-container">
                        {this.renderInput('Dirección:', this.state.address, 'text', this.changeAdress)}
                        {this.renderInput('Número:', this.state.number, 'text', this.changeNumber)}
                        {this.renderInput('Localidad:', this.state.state, 'text', this.changeState)}
                        {this.renderInput('Provincia:', this.state.province, 'text', this.changeProvince)}
                    </div>
                </div>
                        
        )
    }
}