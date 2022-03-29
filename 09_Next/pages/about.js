// URL Path /about
import NavBar from "../components/NavBar";

function HomePage() {
  console.log("[AboutPage] render");
  return (
    <>
      <header>
        <NavBar />
      </header>
      <main>
        <h1>About</h1>
      </main>
    </>
  );
}

export default HomePage;
