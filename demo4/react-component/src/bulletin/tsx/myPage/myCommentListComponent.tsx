import { useState } from "react";
import { MyComment } from "../../ts/myPageComponentType"
import axios from "axios";

type Props = {
	myComments: MyComment[] | undefined
	setComments: React.Dispatch<React.SetStateAction<MyComment[]>>
}

export const MyCommentListComp: React.FC<Props> = (Props) => {
	
	
	const commentHandler = (id:number):void => {
		var csrfToken = document.querySelector('input[name="_csrf"]');
		var urlOrigin: string = new URL(window.location.origin).toString();
  
		axios.delete(urlOrigin + "api/myPage/deleteComment",{
      headers: {
        "X-CSRF-TOKEN": (csrfToken as HTMLInputElement).value,
        'Content-Type': 'application/json'
			},
			params: { commentId:id }
    }).then(() => {
      // 更新後のコメントを再取得する
      return axios.get<MyComment[]>(urlOrigin + "api/myPage/myCommentList");
    }).then(response => {
      Props.setComments(response.data); 
    })
    .catch(error => {
      console.error("API error:", error);
    });
	}
	
	if (Props.myComments == undefined) {
		return (
			<></>
		);
	}
	return (
		<>
			{Props.myComments.map((comment) => (
				<div className="box-design15" key={comment.id}>
					<div className="comment-header">
						<a href={"/show?id=" + encodeURIComponent(comment.bulletinId)} className="bulletin-link">
							<h3 className="box-design15-ttl">掲示板タイトル:{comment.bulletinTitle} コメント日:{comment.createDate.toLocaleString().replace("T"," ")}</h3>
						</a>
						{!comment.deleteFlag ? 
							<button type="button" className="comment-delete-btn" onClick={(): void => commentHandler(comment.id)}>削除</button>
							: <button type="button" disabled={true} className="comment-delete-btn" onClick={(): void => commentHandler(comment.id)}>削除済み</button>
							
						}
							
							
						
						</div>
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