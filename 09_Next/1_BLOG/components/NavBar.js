// 재사용하기 위해 노출시키지 않아야 하므로 pages 외부에 저장한다.

import Link from "next/link";
{
  /* a 태그 사용 시 index 페이지와는 별개로 about 페이지를 불러온다.
    클라이언트측에서 모두 탐색이 완료된 상태가 아니다.
    
    React의 싱글페이지의 장점을 사용하기위해 컴포넌트를 사용하는데 Link태그를 사용
    모든 정보를 내포하기 때문에 a태그 처럼 페이지를 새로 요청하지 않는다.*/
}
function NavBar() {
  return (
    <nav>
      <ul>
        <li>
          <Link href="/">Home</Link>
        </li>
        <li>
          <Link href="/about">About</Link>
        </li>
      </ul>
    </nav>
  );
}

export default NavBar;
