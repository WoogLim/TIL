function add1(x, y) {
  return x + y;
}

// arrow
const add2 = (x, y) => {
  return x + y;
};

const add3 = (x, y) => x + y;

// this 바인드 방식

var relationship1 = {
  name: "zero",
  friends: ["nero", "hero", "xero"],
  logFriends: function () {
    var that = this; // relationship1 을 간접적으로 참조
    this.friends.forEach(function (friend) {
      console.log(that.name, friend); // 만일 this를 사용한다면 forEach의 function() 선언문 내 스코프로 this.name은 존재하지 않는다.
    });
  },
};
relationship1.logFriends();

const relationship2 = {
  name: "zero",
  friends: ["nero", "hero", "xero"],
  logFriends() {
    // logFriends: function ()
    this.friends.forEach((friend) => {
      // 화살표 함수로 직접 접근
      console.log(this.name, friend); // 화살표 함수를 사용했기 때문에 relationship2 선언문이 this로 바인드된다. this 사용 용도에 따라 화살표 함수를 사용한다.
    });
  },
};
relationship2.logFriends();
