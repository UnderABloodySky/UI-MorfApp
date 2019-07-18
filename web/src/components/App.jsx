
import React from 'react';
import { Switch, Route } from 'react-router';
import { BrowserRouter } from 'react-router-dom';
import Restaurants from './homeComponents/Restaurants';
import ShoppingCart from './ShoppingCart';
import PayOrder from './PayOrder';
import OrderPage from './homeComponents/OrderPage';
import SignIn from './homeComponents/SignIn';
import SignUp from './homeComponents/SignUp';
import Home from './Home';
import NavbarRestaurants from './homeComponents/NavbarRestaurants';
import NavbarUs from './homeComponents/NavbarUs';
import NavbarContact from './homeComponents/NavbarContact';
import NavbarMenues from './homeComponents/NavbarMenues';
import NavbarRegister from './homeComponents/NavbarRegister';
import NavbarProducts from './homeComponents/NavbarProducts';
import NavbarResponse from './homeComponents/NavbarResponse';
export default class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
          <Switch>
            <Route exact path="/payorder" component={PayOrder} />
            <Route exact path="/sc" component={ShoppingCart} />
            <Route exact path="/restaurants" component={Restaurants} />
            <Route exact path="/orders" component={OrderPage} />
            <Route exact path="/users/register" component={SignUp} />
            <Route exact path="/signIn/:id" component={SignIn} />
            <Route exact path="/us" component={NavbarUs} />
            <Route exact path="/all_restaurants" component={NavbarRestaurants} />
            <Route exact path="/all_menus" component={NavbarMenues} />
            <Route exact path="/products" component={NavbarProducts} />
            <Route exact path="/contact" component={NavbarContact} />
            <Route exact path="/register" component={NavbarRegister} />
            <Route exact path="/home" component={Home} />
            <Route path="/content" component={NavbarResponse} />
            <Route path="/" component={Home} />
          </Switch>
      </BrowserRouter>
    );
  }
}
