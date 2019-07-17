import React from 'react';
import Orders from '../Orders';

export default class ContentOrder extends React.Component {
  constructor(props){
    super(props)
  }

  render(){
    return(
      <div >
        <Orders user={this.props.user}/>
      </div>
    );
  }

}