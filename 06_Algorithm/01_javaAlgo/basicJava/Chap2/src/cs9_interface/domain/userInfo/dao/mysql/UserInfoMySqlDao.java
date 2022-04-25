package cs9_interface.domain.userInfo.dao.mysql;

import cs9_interface.domain.userInfo.UserInfo;
import cs9_interface.domain.userInfo.dao.UserInfoDao;

public class UserInfoMySqlDao implements UserInfoDao{

	@Override
	public void insertUserInfo(UserInfo userInfo) {
		System.out.println("Insert into MySQL DB user Id = " + userInfo.getUserId());
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		System.out.println("Update into MySQL DB user Id = " + userInfo.getUserId());
		
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		System.out.println("delete from MySQL DB user Id = " + userInfo.getUserId());
	}

}
