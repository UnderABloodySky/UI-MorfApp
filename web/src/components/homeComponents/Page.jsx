import React from 'react';
import Navbar from '../homeComponents/Navbar';
import Container from '../homeComponents/Container';
import Footer from '../homeComponents/Footer';

export default class Page extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      //child: this.props.child
    }
    };

  render() {
    return (
      <div>    
        <Navbar />
        <Container />
        <Footer />        
      </div>
    );
  }
}