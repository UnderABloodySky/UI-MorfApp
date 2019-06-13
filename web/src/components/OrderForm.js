import React from 'react';

class OrderForm extends React.Component{
    constructor(){
        super();
        this.state={
            code: 0,
            username: '',
            state: 'Pending',
            restaurant:'',
            value:'',
            menu:''
        }
        this.handleInput = this.handleInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        this.props.onAddTodo(this.state);
        this.setState({
          code: '',
          username: '',
          restaurant: '',
          state: 'Pending',
          value: '',
          menu:''
        });
      }

    handleInput(e){
       const { value, name } = e.target;
       this.setState({
            [name]: value
       })
    }
     
    render(){
        return (
            <div className="card mr-4 md-4 mt-4">
                    <form onSubmit={this.handleSubmit} className="card-body">
                        <div className="form-group">
                            <input 
                                type="text"
                                name="id"
                                onChange={this.handleInput}
                                className="form-control"
                                placeholder="Username" 
                            />
                        </div>
                <div className="form-group">
                        <input 
                            type="text"
                            name="value"
                            className="form-control"
                            placeholder="Value" 
                            onChange={this.handleInput}
                        />
                </div>
                <div className="form-group">
                        <select
                            name = "restaurant"
                            className="form-control"
                            onChange={this.handleInput}>
                            <option>"La Conga"</option>
                            <option>"Los Maizales"</option>
                            <option>"Güerrin</option>
                            <option>"El Tano"</option>
                        </select>
                </div>
                <div className="form-group">
                        <select
                            name = "menu"
                            className="form-control"
                            onChange={this.handleInput}>
                            <option>"Menu0"</option>
                            <option>"Menu1"</option>
                            <option>"Menu2</option>
                            <option>"Menu3"</option>
                        </select>
                </div>
                <button type="submit" className="btn btn-primary">
                Enviar!
                </button>
                </form>
            </div>
        )
    }
}; 

export default OrderForm; 