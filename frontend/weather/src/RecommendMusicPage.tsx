import axios from 'axios';
import { useState } from 'react';
import { RouteComponentProps, useHistory } from 'react-router-dom';
import './App.css';
import './RecommendMusicPage.css'

interface MatchParams {
    id: string;
}

function RecommendMusicPage(props: RouteComponentProps<MatchParams>) {
    const id = props.match.params.id;

    const history = useHistory();
    const [reviewTitle, setreviewTitle] = useState("");
    const [musicLink, setmusicLink] = useState("");
    const [reviewDescription, setreviewDescription] = useState("");

    const recommendMusic = () => {
        const recommendMusicRequestBody = {
            title: reviewTitle,
            description: reviewDescription,
            musicLink: musicLink,
        }
        axios.post("https://api.atmop.dev/music-recommendation-box/" + id + "/recommend", recommendMusicRequestBody).then(
            () => {history.push("/box/" + id + "/show")}
        );
        return false;
    };
    
    return (
        <div className="App">
            <header className="App-header">
                <h1>
                    {id} 에게 음악을 추천해주세요!
                </h1>

                <input
                    placeholder="추천하는 노래 이름"
                    onChange={(e) => setreviewTitle(e.target.value)}
                ></input>
                <input
                    placeholder="Youtube Link"
                    onChange={(e) => setmusicLink(e.target.value)}
                ></input>
                <textarea
                    placeholder="추천 이유"
                    onChange={(e) => setreviewDescription(e.target.value)}
                ></textarea>

                <button
                    onClick={recommendMusic}
                >
                    추천하기!!!!
                </button>
            </header>
        </div>
    );
}

export default RecommendMusicPage;
