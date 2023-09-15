
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
	bulletinId: number;
	bulletinTitle: string;
	createDate: Date;
}
