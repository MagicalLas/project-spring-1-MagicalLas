import axios from 'axios';
import { useState } from 'react';
import './App.css';
import './BoxCreate.css';

function BoxCreate() {
    const [userId, setUserId] = useState("");
    const createBox = () => {
        const writeReviewRequestBody = {
            userId: userId
        }
        axios.post("https://api.atmop.dev/music-recommendation-box", writeReviewRequestBody);
        return false;
    }
    return (
        <div className="App">
            <header className="App-header">
                <h1>
                    여러분만의 음악 추천 상자를 만들어보세요!
                </h1>
                <h2>
                    여러분의 아이디를 만들어주세요.
                </h2>
                <input
                    placeholder="아이디를 입력해주세요!"
                    onChange={(e) => setUserId(e.target.value)}
                >
                </input>
                <button
                    onClick={createBox}
                >
                    박스 생성하기
                </button>
            </header>
        </div>
    );
}

export default BoxCreate;
