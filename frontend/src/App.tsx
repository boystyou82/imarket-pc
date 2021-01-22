import React from 'react';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import GlobalStyles from './components/GlobalStyles';
import Layout from './components/Layout';
import Event from './pages/Event';
import Exhibition from './pages/Exhibition';
import Main from './pages/Main';

function App() {
  return (
    <BrowserRouter>
      <GlobalStyles />
      <Layout>
        <Switch>
          <Route path="/exhibition/:exhibitionId" component={Exhibition} />
          <Redirect from="/exhibition" to="/exhibition/all" />
          <Route path="/event" component={Event} />
          <Route path="/" exact component={Main} />
          <Redirect from="*" to="/error" />
        </Switch>
      </Layout>
    </BrowserRouter>
  );
}

export default App;
