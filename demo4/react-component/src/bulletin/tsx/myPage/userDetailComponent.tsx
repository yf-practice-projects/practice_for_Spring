import { UserDetails } from "../../ts/myPageComponentType"


export const UserDetailComp: React.FC<{ userDetail: UserDetails}> = ( {userDetail}) => {
return (
	<>
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
		<button type="button">編集</button>
	);
}