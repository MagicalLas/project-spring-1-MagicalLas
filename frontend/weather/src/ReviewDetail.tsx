import { useEffect, useState } from "react";

import { RouteComponentProps, useHistory } from "react-router-dom";

import './App.css';
import './WriteReview.css';

import axios from 'axios';

interface MatchParams {
    id: string;
}

class ReviewDTO {
    constructor(title: string, description: string, musicLink: string) {
        this.title = title;
        this.description = description;
        this.musicLink = musicLink;
    }
    title: string;
    description: string;
    musicLink: string;
}

const replaceYoutubeLink = (musicLink: string) => {
    if (!musicLink.includes("embed")) {
        musicLink = musicLink.replace("https://www.youtube.com/watch?v=", "https://www.youtube.com/embed/");
        musicLink = musicLink.replace("https://youtu.be/", "https://www.youtube.com/embed/");
        return musicLink;
    }
    return musicLink;
}

function ReviewDetail(props: RouteComponentProps<MatchParams>) {
    const id = props.match.params.id;

    const [reviewTitle, setReviewTitle] = useState("");
    const [musicLink, setMusicLink] = useState("");
    const [reviewDescription, setReviewDescription] = useState("");

    const renderReviewDetail = (review: ReviewDTO) => {
        setMusicLink(replaceYoutubeLink(review.musicLink));
        setReviewDescription(review.description);
        setReviewTitle(review.title);
    }
    useEffect(() => {
        axios.get("https://api.atmop.dev/music-reviews/" + id).then(
            response => renderReviewDetail(response.data)
        ).catch(
            () => {
                console.error("Error! HTTP API call is failed. Load default music review!");
                setMusicLink("https://www.youtube.com/embed/4PN-8RQMgD8");
                setReviewDescription("사유리의 데뷔곡인 '미카즈키'이다. 란포기담 Game of Laplace의 엔딩으로 사용되었다.");
                setReviewTitle("Sayuri - Mikazuki");
            }
        );
    }, [id])

    return (
        <div className="App">
            <header className="App-header">
                <h1>{reviewTitle}</h1>
                <p>{reviewDescription}</p>
                <iframe
                    width="560"
                    height="315"
                    src={musicLink}
                    title="YouTube video player"
                    frameBorder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen>
                </iframe>
            </header>
        </div>
    );
}

export default ReviewDetail;
