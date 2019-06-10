
import React from 'react';
import { Switch, Route } from 'react-router';
import { BrowserRouter } from 'react-router-dom';

import SignIn from './homeComponents/SignIn';
import SignUp from './homeComponents/SignUp';
import Home from './Home';
import Restaurants from './homeComponents/Restaurants';

export default class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <Switch>
          <Route path="/users/register" component={SignUp} />
          <Route path="/signIn/:id" component={SignIn} />
          <Route path="/home" component={Home this.location="Pepe"} />
          <Route path="/" component={Home} />
          <Route path="/restaurants" component={Restaurants} />
        </Switch>
      </BrowserRouter>
    );
  }
}