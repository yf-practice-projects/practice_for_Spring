import axios from "axios";
import { Bulletin } from "../../ts/myPageComponentType"


export const MyBulletinListComp: React.FC<{bulletins: Bulletin[]}> = ( {bulletins}) => {
	if (bulletins == undefined) {
		return (
			<></>
		);
	}
	
	return (
		<>
			{bulletins.map((bulletin) => (
				<div className="box-design15" key={bulletin.id}>
					<a href={"/show?id=" + encodeURIComponent(bulletin.id)}>
						<h3 className="box-design15-ttl">{bulletin.title} 掲載日:{bulletin.createDate.toLocaleString().replace("T"," ")}</h3>
					</a>
					<div className="box-design15-txt">
						<p>
							{bulletin.content}
						</p>
					</div>
				</div>
			))}
		</>	
	);	
}