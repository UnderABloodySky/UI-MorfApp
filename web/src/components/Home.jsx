import React from 'react';

import Navbar from './homeComponents/Navbar';
import SignUp from './homeComponents/SignUp';
import Body from './homeComponents/Body';
import Footer from './homeComponents/Footer';

import { getTodos } from '../api/api';

export default class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: {
        id: '',
        imageLink: '',
        username: '',
        todos: [],
      },
      error: '',
    };
  }

  componentDidMount() {
    // getTodos(this.props.location.state.userId)
    //   .then(user => this.setState({ user }))
    //   .catch(() => this.setState({ error: '??' }));
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