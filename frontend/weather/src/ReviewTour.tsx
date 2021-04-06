import { useEffect, useState } from "react";

import './App.css';
import './ReviewTour.css';

import axios from 'axios';
import { useHistory } from "react-router-dom";

class ReviewDTO {
    constructor(id: number, title: string) {
        this.id = id;
        this.title = title;
    }
    id: number;
    title: string;
}

interface ReviewListProps {
    data: Array<ReviewDTO>;
}

function ReviewTour() {
    const history = useHistory();
    const [reviewList, setReviewList] = useState<ReviewDTO[]>([]);

    useEffect(
        () => {
            axios.get("https://api.atmop.dev/music-reviews").then(
                response => setReviewList(response.data)
            ).catch(
                () => {
                    console.error("Error! HTTP API call is failed. Load default music review!");
                    const list: ReviewDTO[] = [
                        new ReviewDTO(1, "Sayuri - Mikazuki"),
                    ];
                    setReviewList(list);
                }
            );
        }
    , [])

    const Review = (props: ReviewDTO) => {
        const moveToDetailPage = () => {
            history.push("/reviews/" + props.id)
        }
        return <div
            className="eachReview"
            onClick={moveToDetailPage}
        >
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
