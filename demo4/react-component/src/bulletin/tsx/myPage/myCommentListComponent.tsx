import { MyComment } from "../../ts/myPageComponentType"


export const MyCommentListComp: React.FC<{ myComments: MyComment[] | undefined}> = ( {myComments}) => {
	if (myComments == undefined) {
		return (
			<></>
		);
	}
	
	return (
		<>
			{myComments.map((comment) => (
				<div className="box-design15" key={comment.id}>
					<a href={"/show?id=" + encodeURIComponent(comment.bulletinId)}>
						<h3 className="box-design15-ttl">掲示板タイトル:{comment.bulletinTitle} コメント日:{comment.createDate.toLocaleString().replace("T"," ")}</h3>
					</a>
					<div className="box-design15-txt">
						<p>
							{comment.comment}
						</p>
					</div>
				</div>
			))}
		</>	

	);	
}