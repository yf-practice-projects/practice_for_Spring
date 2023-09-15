
export type UserDetails = {
	userId: string;
	name: string;
}

export type MyPageProps = {
	activeButton: string | undefined;
	myDetail: UserDetails;
}

export type Bulletin = {
	id: number;
	title: string;
	content: string;
	createDate: Date;
}

export type MyComment = {
	id: number;
	comment: string;
	bulletinBoard_id: number;
	bulletinBoard_title: string;
	createDate: Date;
}
