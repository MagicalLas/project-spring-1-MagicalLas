import { useState } from "react";

import './App.css';
import './WriteReview.css';

import axios from 'axios';

function WriteReview() {
    const [reviewTitle, setreviewTitle] = useState("");
    const [musicLink, setmusicLink] = useState("");
    const [reviewDescription, setreviewDescription] = useState("");

    const writeReview = () => {
        const writeReviewRequestBody = {
            title: reviewTitle,
            description: reviewDescription,
            musicLink: musicLink,
        }
        axios.post("https://atmop.dev/music-reviews", writeReviewRequestBody)
        return false;
    };

    return (
        <div className="App">
            <header className="App-header">
                <h1>리뷰 작성하기</h1>

                <input
                    placeholder="Review Title"
                    onChange={(e) => setreviewTitle(e.target.value)}
                ></input>
                <input
                    placeholder="Youtube Link"
                    onChange={(e) => setmusicLink(e.target.value)}
                ></input>
                <textarea
                    placeholder="Review 본문"
                    onChange={(e) => setreviewDescription(e.target.value)}
                ></textarea>

                <button onClick={writeReview}>
                    작성 완료!
                </button>
            </header>
        </div>
    );
}

export default WriteReview;
