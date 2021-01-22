import 'core-js/stable';
import 'regenerator-runtime/runtime';
import 'react-app-polyfill/ie9';
import 'react-app-polyfill/stable';
import React from 'react';
import ReactDOM from 'react-dom';
import { createStore } from 'redux';
import rootReducer from './modules/index';
import { Provider } from 'react-redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import App from './App';

const store = createStore(rootReducer, composeWithDevTools());

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);
