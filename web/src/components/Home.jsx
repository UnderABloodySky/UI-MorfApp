import React from 'react';

import Navbar from './homeComponents/Navbar';
import Body from './homeComponents/Body';
import Footer from './homeComponents/Footer';

import { getRestaurant } from '../api/api';

export default class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      restaurant: {
        code: 0,
        name: '',
        description: '',
        direcction: '',
        geoLocation: '',
        menus: []        
      },
      error: ''
    };
  }

  render() {
    return (
    <div>
        <div className="banner">
            <div className="container">
                <Navbar />
                <Body />
                <Footer />
            </div>
        </div>
        
        {this.state.error && <div>{this.state.error}</div>}
      </div>
    );
  }
}