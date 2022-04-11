// _app.js 는 pages 경로내 모든 페이지가 사용한다.
// 중복되는 컴포넌트는 _app.js에 정의한다.
// Component 홈페이지 경로
// pageProps 로는 해당 페이지의 요소
import NavBar from "../components/NavBar";

function App({ Component, pageProps }) {
  return (
    <>
      <header>
        <NavBar />
      </header>
      <Component {...pageProps} />
    </>
  );
}

export default App;
