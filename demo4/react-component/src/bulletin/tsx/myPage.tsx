import ReactDOM from 'react-dom/client';
import { MyPageComp } from './myPage/myPageComponent';

const myPage = ReactDOM.createRoot(document.getElementById('main-area')!);
myPage.render(<MyPageComp />);