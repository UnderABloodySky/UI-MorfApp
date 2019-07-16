import axios from 'axios';

const server = 'http://localhost:7000';

const request = (type, path, body) => axios
  .request({ url: `${server}${path}`, method: type, data: body })
  .then(req => req.data);

export const signUp = body => request('post', '/users/register', body);
export const signIn = body => request('post', '/login', body);
export const getPendingOrdersFrom = body => request('get', '/orders_pending/' + body);
export const getHistoricOrdersFrom = body => request('get', '/order_historic/' + body);
export const deliver = body => request('post', '/deliver', body);
export const getMenus = body => request('get', '/restaurant/' + body);
export const restaurants  = body => request('get', '/restaurant/');
export const menues  = body => request('get', '/menus');
export const products  = body => request('get', '/products/');
export const findRestaurant = body => request('get', '/findrestaurant/' + body);
export const mySearch = body => request('get','/search?q='+ body);
export const ratePendingOrder = body =>request('put','/orders_pending/'+':'+body.id , body);
//export const ratePendingOrder = body =>request(console.log(body));

