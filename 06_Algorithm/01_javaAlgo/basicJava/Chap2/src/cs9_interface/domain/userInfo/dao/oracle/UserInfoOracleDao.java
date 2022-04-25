package cs9_interface.domain.userInfo.dao.oracle;

import cs9_interface.domain.userInfo.UserInfo;
import cs9_interface.domain.userInfo.dao.UserInfoDao;

public class UserInfoOracleDao implements UserInfoDao{

	@Override
	public void insertUserInfo(UserInfo userInfo) {
		System.out.println("Insert into ORACLE DB user Id = " + userInfo.getUserId());
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		System.out.println("Update into ORACLE DB user Id = " + userInfo.getUserId());
		
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		System.out.println("delete from ORACLE DB user Id = " + userInfo.getUserId());
	}
}
