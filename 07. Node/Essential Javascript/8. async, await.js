// 프러미스 사용 시 콜백으로 코드의 깊이가 깊어지진 않지만 여전히 길다.
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
      console.log(err);
    });
}

async function findAndSaveUser(Users) {
  let user = await Users.findOne({});
  user.name = "zero";
  user = await user.save(); // await 함수는 해당 프로미스가 resolve될 때까지 기다린 뒤 다음 로직으로 넘어간다.
  user = await Users.findOne({ gender: "m" });
  // 생략
}

// reject 추가
async function findAndSaveUser(Users) {
  try {
    let user = await Users.findOne({});
    user.name = "zero";
    user = await user.save();
    user = await Users.findOne({ gender: "m" });
    // 생략
  } catch (error) {
    console.error(error);
  }
}

// 화살표 함수 사용시
const findAndSaveUser = async (Users) => {
  try {
    let user = await Users.findOne({});
    user.name = "zero";
    user = await user.save();
    user = await Users.findOne({ gender: "m" });
    // 생략
  } catch (error) {
    console.log(promise);
  }
};

// for문과 async/await을 같이 써서 프로미스를 순차적으로 실행하는 법
const promise1 = Promise.resolve("성공1");
const promise2 = Promise.resolve("성공2");
(async () => {
  // for await of 문을 사용해 프로미스 배열을 순회. async 함수의 반환값은 항상 Promise로 감싸진다. 실행 후 then을 붙이거나 또 다른 async함ㄴ수 안에서 await을 붙여 처리가능하다.
  for await (promise of [promise1, promise2]) {
    console.log(promise);
  }
})();

// async 함수의 반환값은 항상 Promise로 감싸진다. 실행 후 then을 붙이거나 또 다른 async함수 안에서 await을 붙여 처리가능하다.
async function findAndSaveUser(Users) {
  // 생략
}

findAndSaveUser().then(() => {
  /* 생략 */
});
// 또는
async function other() {
  const result = await findAndSaveUser();
}
