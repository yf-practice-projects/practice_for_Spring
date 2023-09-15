import React, { StrictMode, useEffect, useState } from "react";
import { MyPageProps, MyComment, Bulletin} from "../../ts/myPageComponentType";
import { MyBulletinListComp } from "./myBulletinListComponent";
import { MyCommentListComp } from "./myCommentListComponent";
import { UserDetailComp } from "./userDetailComponent";
import axios from "axios";


export const MainContentComp: React.FC<MyPageProps> = ({ activeButton, myDetail }) => {

	const [bulletinList, setBulletinList] = useState<Bulletin[]>();
	const [comments, setComments] = useState<MyComment[]>();

	var urlOrigin: string = new URL(window.location.origin).toString();

	useEffect(() => {
		if (activeButton === "myBulletin") {
			axios.get<Bulletin[]>(urlOrigin + "api/myPage/myBulletinList").then(response => {
				setBulletinList(response.data);
			}).catch(error => {
				console.error("API error:", error);
			})
		} else if (activeButton === "myComment") {
			axios.get<MyComment[]>(urlOrigin + "api/myPage/myCommentList").then(response => {
				setComments(response.data);
			}).catch(error => {
				console.error("API error:", error);
			})
		}
	},[activeButton])
	
	return (
		<>
			<div className='content-area' >
				{activeButton === "userDetails" && <UserDetailComp userDetail={myDetail} />}
				{activeButton === "myBulletin" && <MyBulletinListComp bulletins={bulletinList} />}
				{activeButton === "myComment" && <MyCommentListComp myComments={comments} />}
				<MemoizedMapComponent />
			</div>
		</>
	)
}

const MapComp: React.FC = () => {
	window.initMap;
	return <div id="map"></div>;
}

// google map API
let map;
const initMap = (): void => {
  map = new google.maps.Map(document.getElementById("map")!, {
    mapId: "876806baa0024d81",
    center: { lat: 40.73090330192132, lng: -73.99024079492982 },
    zoom: 17,
  });
  
  new google.maps.Marker({
    position: { lat: 40.73090330192132, lng: -73.99024079492982 },
    map,
    title: "一風堂",
  });
}
window.initMap = initMap;
const MemoizedMapComponent = React.memo(MapComp);
