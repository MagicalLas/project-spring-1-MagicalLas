import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';

import { BrowserRouter, Switch, Route } from 'react-router-dom';

import App from './App';
import WriteReview from './WriteReview';
import ReviewTour from './ReviewTour';
import ReviewDetail from './ReviewDetail';
import BoxCreate from './BoxCreate';
import RecommendMusicPage from './RecommendMusicPage';
import BoxTour from './BoxTour';
import ShowRecommendationBox from './ShowRecommendationBox';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={App} />
        <Route path="/box-create" component={BoxCreate} />
        <Route path="/box-tour" component={BoxTour} />
        <Route path="/box/:id/show" component={ShowRecommendationBox} />
        <Route path="/box/:id/recommend" component={RecommendMusicPage} />
        <Route path="/write-review" component={WriteReview} />
        <Route path="/review-tour" component={ReviewTour} />
        <Route path="/reviews/:id" component={ReviewDetail} />
      </Switch>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
