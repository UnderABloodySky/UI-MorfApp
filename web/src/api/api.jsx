import axios from 'axios';

const server = 'http://localhost:7000';

const request = (type, path, body) => axios
  .request({ url: `${server}${path}`, method: type, data: body })
  .then(req => req.data);

export const signIn = body => request('post', '/login', body);
export const getTodos = userId => request('get', '/users');
export const getRestaurant = userRestaurant => request('get', '/restaurants/0');
export const signUp = body => request('post', '/users/register/', body);