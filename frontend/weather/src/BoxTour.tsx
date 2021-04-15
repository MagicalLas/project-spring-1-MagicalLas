import { useState } from 'react';
import { useHistory } from "react-router-dom";

import './App.css';

function BoxTour() {
  const history = useHistory();

  const [userName, setUserName] = useState("")
  const goToBox = () => {
    history.push("/box/" + userName + "/show")
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>
          만들었던 이름을 입력해주세요
        </h1>
        <input
          placeholder="User Name"
          onChange={(e) => setUserName(e.target.value)}
        >
        </input>
        <button
          onClick={goToBox}
        >
          이동하기!
        </button>
      </header>
    </div>
  );
}

export default BoxTour;
