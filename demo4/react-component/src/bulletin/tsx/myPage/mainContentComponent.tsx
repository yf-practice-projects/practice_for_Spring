import React, { useEffect, useState } from "react";
import { MyPageProps, MyComment, Bulletin} from "../../ts/myPageComponentType";
import { MyBulletinListComp } from "./myBulletinListComponent";
import { MyCommentListComp } from "./myCommentListComponent";
import { UserDetailComp } from "./userDetailComponent";
import axios from "axios";


export const MainContentComp: React.FC<MyPageProps> = ({ activeButton, myDetail }) => {

	const [bulletinList, setBulletinList] = useState<Bulletin[] | undefined>();
	const [comments, setComments] = useState<MyComment[]>();

	var urlOrigin: string = new URL(window.location.origin).toString();

	useEffect(() => {
		axios.get<Bulletin[]>(urlOrigin + "api/myPage/myBulletinList").then(response => {
			setBulletinList(response.data);
		}).catch(error => {
			console.error("API error:", error);
		})
	},[activeButton === "myBulletin"])

	useEffect(() => {
		axios.get<MyComment[]>(urlOrigin + "api/myPage/myCommentList").then(response => {
			setComments(response.data);
		}).catch(error => {
			console.error("API error:", error);
		})
	},[activeButton === "myComment"])

	if (activeButton === "myBulletin") {
		return (
		  <div className='content-area' >
				<MyBulletinListComp bulletins={bulletinList} />
		  </div>
		)
	  } else if (activeButton === "myComment") {
			return (
				<div className='content-area' >
					<MyCommentListComp userDetail={myDetail} />
				</div>
			)
	  }
	  
	return (
		<>
			<div className='content-area' >
				<UserDetailComp userDetail={myDetail} />
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
