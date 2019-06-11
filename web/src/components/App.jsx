
import React from 'react';
import { Switch, Route } from 'react-router';
import { BrowserRouter } from 'react-router-dom';

import Orders from './Orders';
import SignIn from './homeComponents/SignIn';
import SignUp from './homeComponents/SignUp';
import Home from './Home';
import Restaurants from './homeComponents/Restaurants';

export default class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <Switch>
          <Route path="/restaurants" component={Restaurants} />
          <Route path="/orders" component={Orders} />
          <Route path="/users/register" component={SignUp} />
          <Route path="/signIn/:id" component={SignIn} />
          <Route path="/home" component={Home} />
          <Route path="/" component={Home} />
        </Switch>
      </BrowserRouter>
    );
  }
}