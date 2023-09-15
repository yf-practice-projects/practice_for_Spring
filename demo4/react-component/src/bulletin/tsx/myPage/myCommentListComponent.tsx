import { UserDetails } from "../../ts/myPageComponentType"


export const MyCommentListComp: React.FC<{ userDetail: UserDetails}> = ( {userDetail}) => {
	return (
		<>
			<p>コメント</p>
			<div className="userIdArea">
				<p>{userDetail.userId}</p>
			</div>
			<div className="userNameArea">
				<p>{userDetail.name}</p>
			</div>
			<EditButton />
		</>	
	);
}
 
const EditButton: React.FC = () => {
	return (
		<button>編集</button>
	);
}