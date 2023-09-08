import { useState, useEffect, Fragment } from "react";
import { useLocation } from 'react-router-dom';
import axios from "axios";

export function CommentComp() {
  type Comment = {
    id?: number;
    comment?: string;
    bulletinId: string | null;
    user?: User;
    createDate?: Date;
  }

  type User = {
    id: number;
    name: string;
  }
  var urlOrigin: string = new URL(window.location.origin).toString();
  var search: string = useLocation().search;

  var searchParams = new URLSearchParams(search)
  const [comments, setComments] = useState<Comment[]>([]);
  const [newComment, setNewComment] = useState<Comment>({comment: "", bulletinId: searchParams.get("id")});

  

  useEffect(() => {
    // コメントを取得する
    axios.get<Comment[]>(urlOrigin + "api/bulletinComment/list" + search).then(response => {
      setComments(response.data);
    })
      .catch(error => {
        console.error("API error:", error);
    });
  }, []);

  const handleSubmit = () => {
    var csrfToken = document.querySelector('input[name="_csrf"]');
    axios.post(urlOrigin + "api/bulletinComment/add", newComment, {
      headers: {
        "X-CSRF-TOKEN": (csrfToken as HTMLInputElement).value,
        'Content-Type': 'application/json'
      }
    }).then(() => {
      // 更新後のコメントを再取得する
      return axios.get<Comment[]>(urlOrigin + "api/bulletinComment/list" + search);
    }).then(response => {
      setComments(response.data);
      setNewComment({comment: "", bulletinId: searchParams.get("id")}); // テキストエリアをクリア
    })
    .catch(error => {
      console.error("API error:", error);
    });
  };

  return (
    <div className="comment-component">
      <textarea value={newComment.comment} onChange={e => setNewComment({ comment:e.target.value,bulletinId: searchParams.get("id") })} />
      <button onClick={handleSubmit}>投稿</button>
      <div className="comment-list">
        {comments.map((comment) => (
          <div key={comment.id} className="comment">
            <div className="comment-header">
              <div className="comment-user-name">{comment.user?.name}</div>
              <div className="comment-user-name">{comment.createDate?.toLocaleString().replace("T"," ")}</div>
            </div>
              <div className="cooment-content">{comment.comment}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
