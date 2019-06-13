
import React from 'react';
import { Switch, Route } from 'react-router';
import { BrowserRouter } from 'react-router-dom';
import Restaurants from './homeComponents/Restaurants';
import ShoppingCart from './ShoppingCart';
import Orders from './Orders';
import SignIn from './homeComponents/SignIn';
import SignUp from './homeComponents/SignUp';
import Home from './Home';

export default class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
          <Switch>
            <Route exact path="/shoppingCart" component={ShoppingCart} />
            <Route exact path="/restaurants" component={Restaurants} />
            <Route exact path="/orders" component={Orders} />
            <Route exact path="/users/register" component={SignUp} />
            <Route exact path="/signIn/:id" component={SignIn} />
            <Route exact path="/home" component={Home} />
            <Route path="/" component={Home} />
          </Switch>
      </BrowserRouter>
    );
  }
}