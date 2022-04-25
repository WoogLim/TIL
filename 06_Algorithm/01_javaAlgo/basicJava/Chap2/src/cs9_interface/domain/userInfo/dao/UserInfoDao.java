package cs9_interface.domain.userInfo.dao;

import cs9_interface.domain.userInfo.UserInfo;

public interface UserInfoDao {
	
	// mysql, oracle 버전에 맞게 구현하도록 메서드 정의
	void insertUserInfo(UserInfo userInfo);
	void updateUserInfo(UserInfo userInfo);
	void deleteUserInfo(UserInfo userInfo);
	
}
