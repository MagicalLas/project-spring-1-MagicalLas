import { useEffect, useState } from "react";

import './App.css';
import './ShowRecommendationBox.css';

import axios from 'axios';
import { RouteComponentProps, useHistory } from "react-router-dom";

class MusicDTO {
    constructor(title: string, description: string, musicLink: string) {
        this.title = title;
        this.description = description;
        this.musicLink = musicLink;
    }
    title: string;
    description: string;
    musicLink: string;
}

interface ResponseDTO {
    recommendationList: Array<MusicDTO>;
}

interface ReviewListProps {
    data: Array<MusicDTO>;
}

interface MatchParams {
    id: string;
}

function ShowRecommendationBox(props: RouteComponentProps<MatchParams>) {
    const id = props.match.params.id;

    const history = useHistory();
    const [musicList, setMusicList] = useState<MusicDTO[]>([]);

    useEffect(
        () => {
            axios.get("https://api.atmop.dev/music-recommendation-box/" + id).then(
                response => setMusicList(response.data.recommendationList)
            ).catch(
                () => {
                    console.error("Error! HTTP API call is failed. Load default music review!");
                    const list: MusicDTO[] = [
                        new MusicDTO("Sayuri - Mikazuki", "사유리의 메이저 데뷔곡", "https://youtu.be/7iZKcilN7Rw"),
                        new MusicDTO("自己愛主義天使", "엄청 귀여워요!!!! 이게 진짜 냥냥 하는 부분도 그렇고, 진짜 귀엽네요. 게다가 귀엽다고 하는 곡이에요!", "https://youtu.be/7iZKcilN7Rw"),
                    ];
                    setMusicList(list);
                }
            );
        }
        , [])

    const Review = (props: MusicDTO) => {
        return <div><details
            className="eachMusic"
        >
            <summary>{props.title}</summary>
            <p>{props.description}</p>
            <p
            className="link"
            >{props.musicLink}</p>
        </details>
        </div>
    }

    const MusicList = (props: ReviewListProps) => {
        const reviewArray = props.data.map((review: MusicDTO) => Review(review));
        return <div>{reviewArray}</div>
    };
    const moveToRecommendPage = () => {
        history.push("/box/" + id + "/recommend");
    }
    return (
        <div className="App">
            <header className="App-header">
                <h1>지금까지 추천받은 노래에요!</h1>
                <h2
                    onClick={moveToRecommendPage}
                >노래 추천하러 가기~</h2>
                <MusicList data={musicList}></MusicList>
            </header>
        </div>
    );
}

export default ShowRecommendationBox;
