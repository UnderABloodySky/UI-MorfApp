import axios from 'axios';

const server = 'http://localhost:7000';

const request = (type, path, body) => axios
  .request({ url: `${server}${path}`, method: type, data: body })
  .then(req => req.data);

export const signUp = body => request('post', '/users/register', body);
export const signIn = body => request('post', '/login', body);
export const getPendingOrdersFrom = body => request('get', '/orders_pending/' + body);
export const getHistoricOrdersFrom = body => request('get', '/order_historic/' + body);
export const getMenus = body => request('get', '/restaurant/' + body);

