import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>
          안녕하세요. atmosphere에 어서오세요.
        </h1>
        <a
          href="/box-create"
        >
          음악 추천함 만들기
        </a>
        <a
          href="/write-review"
        >
          음악 리뷰 작성하러 가기
        </a>
        <a
          href="/review-tour"
        >
          리뷰들 둘러보기
        </a>
      </header>
    </div>
  );
}

export default App;
