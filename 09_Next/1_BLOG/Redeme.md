#### next.js와 react

- next.js 는 서버에서 렌더링되어 만들어진 파일로 클라이언트측으로 반환하지만 racet.js 는 클라이언트측에서 root 컨테이너 내부로 자바스크립트가 실행되어 클라이언트측에서 완성된다.

#### 명령어

```json
"scripts": {
    "build": "next build",
    "dev": "next dev",
    "start": "next start"
  }
```

- 개발모드 npm run dev

- 빌드 및 시작 npm run build / npm start

#### 개발모드와 빌드

- 개발모드에서는 localhost에서 새로고침되면 재생산하지만 빌드단계에서는 이미 빌드가 완료되어 새로고침을 하더라도 다시 재생산하지않음
