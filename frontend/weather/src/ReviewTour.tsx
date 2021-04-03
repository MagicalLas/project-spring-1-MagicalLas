import { useState } from "react";

import './App.css';
import './ReviewTour.css';

import axios from 'axios';

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

interface ReviewListProps {
    data: Array<ReviewDTO>;
}

function ReviewTour() {
    const [reviewList, setReviewList] = useState([]);

    axios.get("https://api.atmop.dev/music-reviews").then(
        response => setReviewList(response.data)
    );
    
    const Review = (props: ReviewDTO) => {
        return <div className="eachReview">
            <h2>
                {props.title}
            </h2>
        </div>
    }

    const ReviewList = (props: ReviewListProps) => {
        const reviewArray = props.data.map((review: ReviewDTO) => Review(review));
        return <div>{reviewArray}</div>
    };

    return (
        <div className="App">
            <header className="App-header">
                <h1>리뷰 둘러보기</h1>
                <ReviewList data={reviewList}></ReviewList>
            </header>
        </div>
    );
}

export default ReviewTour;
