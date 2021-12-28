// 프러미스 이용법

// 1. 객체 생성
const condition = true; // true면 resolve, false reject
const promise = new Promise((resolve, reject) => {
  if (condition) {
    resolve("성공");
  } else {
    reject("실패");
  }
});

// 프러미스가 수행되고 결과값을 받아온다.
promise
  .then((message) => {
    // function(message){}
    console.log(message); // 성공(resolve)한 경우 실행
  })
  .catch((error) => {
    console.log(error); // 실패(reject)한 경우 실행
  })
  .finally(() => {
    console.log("끝나면 무조건 수행");
  });

// then이나 catch에서 다시 다른 then이나 catch를 붙일 수 있다.

promise
  .then((message) => {
    return new Promise((resolve, reject) => {
      resolve(message);
    });
  })
  .then((message2) => {
    // 다시 다른 then이나 catch를 붙일 수 있으며 이전 then의 return값을 다음 then의 매개변수로 넘긴다.
    console.log(message2);
    return new Promise((resolve, reject) => {
      resolve(message2);
    });
  })
  .then((message3) => {
    // 이전 then 에서 return new Promise를 한 경우에만 넘겨받을 수 있다.
    console.log(message3);
  })
  .catch((error) => {
    console.error(error);
  });

// 콜백을 프로미스로 변환하는 법

function findAndSaveUser(Users) {
  Users.findOne({}, (err, user) => {
    // 첫 번째 콜백
    if (err) {
      return console.error(err);
    }
    user.name = "zero";
    user.save((err) => {
      // 두 번째 콜백
      if (err) {
        return console.error(err);
      }
      Users.findOne({ gender: "m" }, (err, user) => {
        // 세 번째 콜백
        // 생략
      });
    });
  });
}

// 콜백 함수가 나올 때마다 각 콜백 함수마다 에러도 따로 처리해주어야 한다 다음과 같이 변환한다.
// findOne, save 메서드가 내부적으로 프로미스 객체를 가지고 있다고 생각하자.

function findAndSaveUser(Users) {
  Users.findOne({})
    .then((user) => {
      user.name = "zero";
      return user.save();
    })
    .then((user) => {
      return Users.findOne({ gender: "m" });
    })
    .then((user) => {
      // 생략
    })
    .catch((err) => {
      console.error(err);
    });
}

// 프로미스 여러 개를 한 번에 실행하는 법

const promise1 = Promise.resolve("성공1");
const promise2 = Promise.resolve("성공2");
Promise.all([promise1, promise2])
  .then((result) => {
    console.log(result);
  })
  .catch((error) => {
    console.error(error);
  });

// Promise.resolve는 즉시 resolve하는 프로미스를 만드는 방법이다. reject도 동일하게 사용가능
// Promise.all에 넣으면 모두 resolve될 때까지 기다렸다가 then으로 넘어가며 result 매개변수에 각각 프로미스 결과값이 배열로 들어간다.
// Promise 중 하나라도 reject가 되면 catch로 넘어간다.
