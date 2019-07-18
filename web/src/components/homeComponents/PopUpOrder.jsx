import React from 'react';  
import '../css/Popup.css';  
import {mySearch} from '../../api/api';
import ContentSearchResult from './ContentSearchResult.';

class PopUpOrder extends React.Component {  
    constructor(props) {
    super(props);
        this.state = {
            toShow: [],
            renderLog: false,
            q: ''
        }
       this.state.q = this.props.q  
       this.handlerLog = this.handlerLog.bind(this);
    }

handlerLog(){
    this.props.closePopup();    
}
    
login(){
    this.setState({ renderLog: !this.state.renderLog})
}

    componentDidMount(){
        if (this.state.q !== ''){
                mySearch(this.state.q)
                .then(result => { 
                    console.log("componentDidMount")
                    console.log(this.props.q)
                    console.log(result)
                    this.setState({toShow: result, mustBeRender:true})});
                }
    }    
    
    render() {  
        console.log("ACA")
        console.log(this.state.toShow)
        return (  
            
            <div className='popup'>  
                <div className='pop-inner'>             
                    <div>
                        {this.state.mustBeRender && <ContentSearchResult k={this.state.toShow}/>}
                    </div>
                    <div className="col-12">
                        <button type="button" id="focus" className="btn btn-link" onClick={this.handlerLog}>Cancelar</button>
                    </div>
                </div>  
            </div>  
        );  
    }  
}  

export default PopUpOrder;