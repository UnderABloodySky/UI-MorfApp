import React from 'react';

import '../css/Body.css';
import SignUp from './SignUp';
import SignIn from './SignIn';
import imag1 from '../images/img1.jpg';

export default class Content extends React.Component {
  constructor(props) {
    super(props)  
  }  
  
render() {
 
 return (
   <div>
    <h1>ASD</h1>
    <div>
      <ul>
        <ul>
        {this.props.k.map(home => <div>{home.name}</div>)}
        </ul>
      </ul>
    </div>
   </div>
    );

  }
}