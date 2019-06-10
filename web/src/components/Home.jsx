import React from 'react';

import Navbar from './homeComponents/Navbar';
import SignUp from './homeComponents/SignUp';
import Body from './homeComponents/Body';
import Footer from './homeComponents/Footer';

import { getTodos } from '../api/api';
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

  componentDidMount() {
    getRestaurant()
    .then(restaurant => this.setState({ restaurant: restaurant }))
    .catch(() => this.setState({ error: '??' }));
  }

  componentDidUpdate() {
    console.log(this.state.restaurant);
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