import React from 'react';
import Navbar from '../homeComponents/Navbar';
import NavbarOrder from '../homeComponents/NavbarOrder';
import Container from '../homeComponents/Container';
import Footer from '../homeComponents/Footer';


export default class Page extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      log:false
    }
    this.state.log = this.state.log || this.props.id === "7";
  };

  render() {
    if(this.props ==='7'){
      this.setState({log:true})
    }
    
    return (
      <div>    
        {!this.state.log && this.props.id !== "7" && <Navbar /> }
        {(this.state.log || this.props.id === "7") && <NavbarOrder user={this.props.user}/> }
        <Container user={this.props.user} content={this.props.child} id={this.props.id}/>
        <Footer />        
      </div>
    );
  }
}
