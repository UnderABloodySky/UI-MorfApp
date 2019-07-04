import React from 'react';

import '../css/Body.css';

import RegisterForm from '../homeComponents/RegisterForm'

class ContentRegister extends React.Component {
  constructor(){
    super();
    this.state = {
      flag: false 
    }
    this.send = this.send.bind(this);
  }

  send(user) {
    this.setState({
      flag: true
    })
    console.log("flag:");
    console.log(this.state.flag);
  }

  
  render(){
    return (
      <div>  
          <h3 className="title"> :: Registro! :: </h3>
                <RegisterForm send={this.send}/>
      </div>
  );
  }
}

export default ContentRegister;