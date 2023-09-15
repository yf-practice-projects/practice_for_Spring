import { UserDetails } from "../../ts/myPageComponentType";


type Props = {
	setActiveButton: React.Dispatch<React.SetStateAction<string | undefined>>;
	myDetail: UserDetails;
}

export const SideContentComp: React.FC<Props> = (Props) => {
	
	const buttonHandler = ( event: React.MouseEvent<HTMLButtonElement, MouseEvent> ) :void => {
		let selectButton = event.currentTarget.dataset["index"];
		Props.setActiveButton(selectButton);
	}

	return (
		<nav className="navigation">
			<button className="userDetailsViewButton button" data-index="userDetails" onClick={buttonHandler} type="button">
				ユーザー詳細
			</button>
			<button className="myBulletinViewButton button" data-index="myBulletin" onClick={buttonHandler} type="button">
				掲示板
			</button>
			<button className="myCommentViewButton button" data-index="myComment" onClick={buttonHandler} type="button">
				コメント
			</button>
		</nav>
	)
}