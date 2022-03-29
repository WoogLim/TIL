// URL Path /
import NavBar from "../components/NavBar";

function HomePage() {
  console.log("[Hompage] render");
  return (
    <>
      <header>
        <NavBar></NavBar>
      </header>
      <main>
        <h1>My Blog</h1>
      </main>
    </>
  );
}

export default HomePage;
