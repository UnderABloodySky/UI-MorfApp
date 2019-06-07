import React from 'react';


import Navbar from './homeContainer/Navbar';
import SignUp from './SignUp';
import Body from './homeContainer/Body';
import Footer from './homeContainer/Footer';

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
                <div className="logo">
                    <div className="ribbon">        
                        
                        <div className="ribbon-fold"><h1><a href="index.html">:MorfApp:</a></h1></div>

                    </div>
                </div>
                <Navbar />
                <SignUp />
                {/* <Body /> */}
                <Footer />
            </div>
        </div>
        
        {this.state.error && <div>{this.state.error}</div>}
      </div>
    );
  }
}