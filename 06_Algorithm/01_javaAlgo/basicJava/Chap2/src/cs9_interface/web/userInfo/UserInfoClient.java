package cs9_interface.web.userInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import cs9_interface.domain.userInfo.UserInfo;
import cs9_interface.domain.userInfo.dao.UserInfoDao;
import cs9_interface.domain.userInfo.dao.mysql.UserInfoMySqlDao;
import cs9_interface.domain.userInfo.dao.oracle.UserInfoOracleDao;

public class UserInfoClient {

	public static void main(String[] args) throws IOException {
		
		// web이라 가정한다.
		
		FileInputStream fis = new FileInputStream("db.properties");
		
		Properties prop = new Properties();
		prop.load(fis);
		
		// 키의 값 반환받기
		String dbType = prop.getProperty("DBTYPE");
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("12345");
		userInfo.setPassword("!@#%!@");
		userInfo.setUserName("Lee");
		
		UserInfoDao userInfoDao = null;
		
		// 인터페이스의 다형성 타입에 따라 구현체를 다른 구현체를 불러올 수 있다.
		if(dbType.equals("ORACLE")) {
			userInfoDao = new UserInfoOracleDao();
		}
		else if(dbType.equals("MYSQL")) {
			userInfoDao = new UserInfoMySqlDao();
		}
		else {
			System.out.println("db error");
			return;
		}
		
		userInfoDao.insertUserInfo(userInfo);
		userInfoDao.updateUserInfo(userInfo);
		userInfoDao.deleteUserInfo(userInfo);
	}
	
}
