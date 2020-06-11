package hello;

import java.sql.*;

public class DBConn {
	private static Connection dbConn; //db연결자를 담을 수 있는 변수
		
		public static Connection getConnection() {
			//static 변수가 이미 메모리에 올라가있으므로 가져다 사용하면 됨
			if (dbConn == null) { //null일 경우 연결 되지 않은 상태
				try {
					String user = "GYM";
					String pw = "1234";
					String url = "jdbc:oracle:thin:@localhost:1521:orcl";
					
					Class.forName("oracle.jdbc.driver.OracleDriver"); //다른클래스의 정보를 읽어옴
					dbConn = DriverManager.getConnection(url,user,pw); //드라이버매니저를 통해 url,user,pw로 스트림 생성해서 dbConn에 담기
				
				}catch(Exception e) {
					System.out.println(e.toString());
				}
				
			}
	
			return dbConn;
		}
		
		public static void close() {
			if (dbConn != null) {// 연결되어있으면
				try {
					if(!dbConn.isClosed()) {
						dbConn.close();
					}
				}catch(Exception e) {
					System.out.println(e.toString());
				}
			}
			dbConn = null; //파이프라인이 연결되어 있는 상태에서 연결을 끊게 되면 스트림안에 쓰레기값이 남게됨
							//두번째 연결시 쓰레기값으로 인하여 adapter오류 발생
							//따라서 연결 종료시 항상 초기화 진행.
			
		}
}
