package hello;

import java.sql.*;

public class DBConn {
	private static Connection dbConn; //db�����ڸ� ���� �� �ִ� ����
		
		public static Connection getConnection() {
			//static ������ �̹� �޸𸮿� �ö������Ƿ� ������ ����ϸ� ��
			if (dbConn == null) { //null�� ��� ���� ���� ���� ����
				try {
					String user = "GYM";
					String pw = "1234";
					String url = "jdbc:oracle:thin:@localhost:1521:orcl";
					
					Class.forName("oracle.jdbc.driver.OracleDriver"); //�ٸ�Ŭ������ ������ �о��
					dbConn = DriverManager.getConnection(url,user,pw); //����̹��Ŵ����� ���� url,user,pw�� ��Ʈ�� �����ؼ� dbConn�� ���
				
				}catch(Exception e) {
					System.out.println(e.toString());
				}
				
			}
	
			return dbConn;
		}
		
		public static void close() {
			if (dbConn != null) {// ����Ǿ�������
				try {
					if(!dbConn.isClosed()) {
						dbConn.close();
					}
				}catch(Exception e) {
					System.out.println(e.toString());
				}
			}
			dbConn = null; //������������ ����Ǿ� �ִ� ���¿��� ������ ���� �Ǹ� ��Ʈ���ȿ� �����Ⱚ�� ���Ե�
							//�ι�° ����� �����Ⱚ���� ���Ͽ� adapter���� �߻�
							//���� ���� ����� �׻� �ʱ�ȭ ����.
			
		}
}
