import ReactDOM from 'react-dom/client';
import {CommentComp} from './BulletinCommentComponent';
import {BrowserRouter as Router} from 'react-router-dom';
// window.onload = ():void => {
	const domContainer = document.getElementById("react-component")
	if (!domContainer) throw new Error('Failed to find the root element');
	const root = ReactDOM.createRoot(domContainer);
	root.render(<Router><CommentComp /></Router>);
// }
