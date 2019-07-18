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

  console.log("PopUpOrder 2")
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
                    console.log(this.state.q)
                    console.log(result)
                    this.setState({toShow: result, mustBeRender:true})});
                }
    }    
    
    render() {  
        return (  
            
            <div className='popup'>  
                <div className='pop-inner'>             
                    <div>
                        <ContentSearchResult k={this.state.toShow}/>
                    </div>
                </div>  
            </div>  
        );  
    }  
}  

export default PopUpOrder;