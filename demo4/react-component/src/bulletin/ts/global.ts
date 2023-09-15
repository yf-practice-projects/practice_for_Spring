import {UserDetails} from "./myPageComponentType.js"

declare global {
	interface Window {
	  initMap: () => void;
	  userDetail: UserDetails;
	}
}

export {}