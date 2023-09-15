import ReactDOM from 'react-dom/client';
import { MyPageComp } from './myPage/myPageComponent';
import { StrictMode } from 'react';
import whyDidYouRender from '@welldone-software/why-did-you-render';
import React from 'react';
// if (process.env.NODE_ENV === 'development') {
//   whyDidYouRender(React, {
//     trackAllPureComponents: true,
//   });
// }


const myPage = ReactDOM.createRoot(document.getElementById('main-area')!);
myPage.render(
  //<StrictMode>
    <MyPageComp />
  // </StrictMode>
);