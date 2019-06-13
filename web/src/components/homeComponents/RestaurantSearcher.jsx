import React,{Component} from 'react';

export default class RestaurantSearcher extends Component{
    render(){
        return(
            <form>
                <div className="row">
                    <div className = "form group col-md-8">
                        <input type = "text" 
                            className = "form control form control-lg"
                             placeholder = "Busca tu restaurant, ej Mc Donals" />
                    </div>
                </div>

            </form>
        );
    }

}
