import React from 'react';  
import '../css/Popup.css';  
import SignIn from './SignIn';

class Popup extends React.Component {  
    constructor(props) {
    super(props);
        this.state = {
            renderLog: false,
        }
    this.handlerLog = this.handlerLog.bind(this);
    }
handlerLog(){
    
    this.props.closePopup();    
}
    
login(){
    this.setState({ renderLog: !this.state.renderLog})
}

    render() {  
    return (  
        <div className='popup'>  
            <div className='pop-inner'>             
                <SignIn id="2" 
                                handlerLog = {this.handlerLog}
                                fromWhichComponent = 'search'
                                code = {this.props.code} 
                />
            
            </div>  
        </div>  
        );  
    }  
}  

export default Popup;