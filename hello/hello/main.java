package hello;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.Scanner;


class main {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		int button = -1;
		Scanner scan = new Scanner(System.in);
		Statement stmt; // 정적인 sql문
		PreparedStatement ptmt = null; //동적인 sql문
		Calendar cal = new GregorianCalendar();
		//String today = String.valueOf(cal.get(Calendar.YEAR))+ "-" +String.valueOf(cal.get(Calendar.MONTH)+1)+"-"+String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		Date date = new Date();
		if(conn==null) {
			System.out.println("데이터베이스 연결 실패!");
			System.exit(0);
		}
		
		// 메인메뉴
		while(true) {
			
			System.out.println("Wellcome To Gym Manager \n");
			System.out.println("1. 회원 관리\n");
			System.out.println("2. 강사 관리\n");
			System.out.println("3. 프로그램 관리\n");
			System.out.println("4. 스케쥴 관리\n");
			System.out.println("0. 종료\n");
			System.out.print("입력 : ");
			button = Integer.parseInt(scan.nextLine()); //nextInt로 받으면 커서위치가 \n에 놓여서 불편
			
			if(button == 0) {
				break;
			}
			///////////////////////////회원관리메뉴///////////////////////////
			else if(button == 1) {
				
				while(true) {
					
					System.out.println("회원 관리 메뉴 \n");
					System.out.println("1. 회원 조회\n");
					System.out.println("2. 회원 추가\n");
					System.out.println("3. 회원 삭제\n");
					System.out.println("4. 인바디 관리\n");
					System.out.println("5. 메세지 보내기\n");
					System.out.println("0. 뒤로\n");
					System.out.print("입력 : ");
					button = Integer.parseInt(scan.nextLine());
					
					if(button == 0) {
						break;
					}
					//////////회원 조회////////////////
					else if (button == 1) {
						while(true) {
							
							System.out.println("회원 조회 \n");
							System.out.println("1. 모든 회원 조회\n");
							System.out.println("2. 회원 번호 조회\n");
							System.out.println("3. 회원 이름으로 조회\n");
							System.out.println("0. 뒤로\n");
							System.out.print("입력 : ");
							button = Integer.parseInt(scan.nextLine());
							
							if(button == 0) {
								break;
							}
							/////모든 회원 조회/////
							else if(button == 1) {
								try {
									stmt = conn.createStatement();
									String sql;
								
									sql = "select * from customer ";
									
									ResultSet rs = stmt.executeQuery(sql);
									System.out.printf("%4s %20s %20s %4s %3s\n","id","name","tell","age","sex");
									System.out.println("--------------------------------------------------------");
									while(rs.next()) {
										
										System.out.printf("%4s %20s %20s %4s %3s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("tell"),rs.getString("age"),rs.getString("sex"));
									}
									rs.close();
								}catch(Exception e) {
									System.out.println(e.toString());
								}
							}/////모든 회원 조회/////
							
							/////회원 번호 조회/////
							else if(button == 2) {
								String input;
								
								System.out.print("찾을 아이디를 입력하시오 : ");
								//scan.nextLine(); // int를 입력 받은 후에는 커서가 입력받은 정수 바로뒤에 위치(\n)에 오게 되므로 커서를 한 칸 이동해준다.
								input = scan.nextLine();
								
								try {
									stmt = conn.createStatement();
									String sql;
								
									sql = "select * from customer where customerid = " + input;
									
									ResultSet rs = stmt.executeQuery(sql);
									System.out.printf("%4s %20s %20s %4s %3s\n","id","name","tell","age","sex");
									System.out.println("--------------------------------------------------------");
									while(rs.next()) {
										
										System.out.printf("%4s %20s %20s %4s %3s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("tell"),rs.getString("age"),rs.getString("sex"));
									}
									rs.close();
								}catch(Exception e) {
									System.out.println(e.toString());
								}
							}
							/////회원 이름으로 조회/////
							else if(button == 3) {
								String input;
								
								System.out.print("찾을 이름을 입력하시오 : ");
								//scan.nextLine(); // int를 입력 받은 후에는 커서가 입력받은 정수 바로뒤에 위치(\n)에 오게 되므로 커서를 한 칸 이동해준다.
								input = scan.nextLine();
								
								try {
									stmt = conn.createStatement();
									String sql;
								
									sql = "select * from customer where name = '" + input + "'";
									
									ResultSet rs = stmt.executeQuery(sql);
									System.out.printf("%4s %20s %20s %4s %3s\n","id","name","tell","age","sex");
									System.out.println("--------------------------------------------------------");
									while(rs.next()) {
										
										System.out.printf("%4s %20s %20s %4s %3s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("tell"),rs.getString("age"),rs.getString("sex"));
									}
									rs.close();
								}catch(Exception e) {
									System.out.println(e.toString());
								}
							}/////회원 이름으로 조회/////
						}
					}//////////회원 조회////////////////
					
					///////////회원 추가////////////////
					else if (button == 2) {
						
						int id;
						String name;
						String tell;
						int age;
						String sex;
						
						System.out.println("등록할 회원정보를 입력하시오.");
						System.out.print("ID : ");
						id = Integer.parseInt(scan.nextLine());
						System.out.print("Name : ");
						name = scan.nextLine();
						System.out.print("Tell : ");
						tell = scan.nextLine();
						System.out.print("Age : ");
						age = Integer.parseInt(scan.nextLine());
						System.out.print("Sex(M,W) : ");
						sex = scan.nextLine();
						System.out.printf("\n %s / %s / %s / %s / %s 정말 입력 하시겠습니까? yes(1), no(0) : ",id,name,tell,age,sex);
						button = Integer.parseInt(scan.nextLine());
						if(button == 1) {
							try {
								String sql;
								sql = "insert into customer(customerid,name,tell,age,sex) values(?,?,?,?,?)";
								ptmt = conn.prepareStatement(sql);
								ptmt.setInt(1, id);
								ptmt.setString(2, name);
								ptmt.setString(3, tell);
								ptmt.setInt(4, age);
								ptmt.setString(5, sex);
								int result = ptmt.executeUpdate();
								
								if(result > 0) {
									System.out.println("입력성공");
								}
								System.out.print("인바디를 측정하시겠습니까?y(1),n(0) : ");
								button = Integer.parseInt(scan.nextLine());
								////////인바디 최초 측정////////
								if(button == 1) {
									int count = 1;
									int height;
									int weight;
									int fat;
									int muscle;
									System.out.println("측정한 인바디를 입력하시오.");
									System.out.print("키 : ");
									height = Integer.parseInt(scan.nextLine());
									System.out.print("몸무게 : ");
									weight = Integer.parseInt(scan.nextLine());
									System.out.print("체지방 : ");
									fat = Integer.parseInt(scan.nextLine());
									System.out.print("근육량 : ");
									muscle = Integer.parseInt(scan.nextLine());
									
									System.out.printf("\n %s / %s / %s / %s 정말 입력 하시겠습니까? yes(1), no(0) : ",height,weight,fat,muscle);
									button = Integer.parseInt(scan.nextLine());
									if(button == 1) {
										sql = "insert into inbody(count,measuredate,age,sex,height,weight,fat,muscle,cusid) values(?,?,?,?,?,?,?,?,?)";
										
										ptmt = conn.prepareStatement(sql);
										ptmt.setInt(1, count);
										ptmt.setDate(2,new java.sql.Date(date.getTime()));
										ptmt.setInt(3, age);
										ptmt.setString(4, sex);
										ptmt.setInt(5, height);
										ptmt.setInt(6, weight);
										ptmt.setInt(7, fat);
										ptmt.setInt(8, muscle);
										ptmt.setInt(9, id);
										ptmt.executeUpdate();
									}
									
								}////////인바디 최초 측정////////
								
								ptmt.close();
							}catch(Exception e) {
								System.out.println(e.toString());
							}
						}
						
						
					}///////////회원 추가////////////////
					
					////////////회원 삭제////////////////
					else if (button == 3) {
						int id;
						int input;
						try {
							stmt = conn.createStatement();
							String sql;
						
							sql = "select * from customer ";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %20s %20s %4s %3s\n","id","name","tell","age","sex");
							System.out.println("--------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %20s %20s %4s %3s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("tell"),rs.getString("age"),rs.getString("sex"));
							}
							rs.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
						System.out.println();
						System.out.print("삭제할 ID를 입력하시오 : ");
						id = Integer.parseInt(scan.nextLine());
						System.out.print("정말 삭제 하시겠습니까? yes(1), no(0) : ");
						input = Integer.parseInt(scan.nextLine());
						if(input == 1) {
							try {
								String sql;
								
								sql = "delete from inbody where cusid = ?";
								ptmt = conn.prepareStatement(sql);
								ptmt.setInt(1, id);
								ptmt.executeQuery();
				
								
								sql = "delete from customer where customerid = ?";
								ptmt = conn.prepareStatement(sql);
								ptmt.setInt(1, id);
						
								int result = ptmt.executeUpdate();// 실행된 레코드 수 반환
								if(result > 0) {
									System.out.println("삭제성공");
								}
								ptmt.close();
								
								
							}catch(Exception e) {
								System.out.println(e.toString());
							}
						}
						
						
					}////////////회원 삭제////////////////
					
					////////////인바디 관리////////////////
					else if (button == 4) {
						System.out.println("1. 인바디 조회, 측정\n");
						System.out.println("0. 뒤로\n");
						System.out.print("입력 : ");
						button = Integer.parseInt(scan.nextLine());
						
						if(button == 0) {
							break;
						}
						
						else if(button == 1) {
							String name;
							System.out.print("이름을 입력하시오 : ");
							name = scan.nextLine();
							
							try {
								//stmt = conn.createStatement();
								String sql;
								
								sql = "select i.* from inbody i join customer c on i.cusid = c.customerid where c.name = ?";
								ptmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
								ptmt.setString(1, name);
								ResultSet rs = ptmt.executeQuery();
								System.out.printf("%5s %15s %4s %4s %8s %8s %4s %8s %4s\n","count","measure","Age","Sex","height","weight","fat","muscle","id");
								System.out.println("-------------------------------------------------------------------------------");
								while(rs.next()) {
									
									System.out.printf("%5s %15s %4s %4s %8s %8s %4s %8s %4s\n",rs.getString("count"),rs.getString("measuredate").substring(0,10),rs.getString("age"),rs.getString("sex"),rs.getString("height"),rs.getString("weight"),rs.getString("fat"),rs.getString("muscle"),rs.getString("cusid"));
								}
								
								System.out.print("인바디를 측정하시겠습니까? y(1),n(0) : ");
								button = Integer.parseInt(scan.nextLine());
								if(button == 1) {
									System.out.print("id를 입력하시오 : ");
									int id = Integer.parseInt(scan.nextLine());
			
									int count = 1;
									System.out.print("나이를 입력하시오 : ");
									int age = Integer.parseInt(scan.nextLine());
									
									System.out.print("성별(M,W)을 입력하시오 : ");
									String sex = scan.nextLine();
									
									rs.beforeFirst();
									while(rs.next()) {
										if(rs.getInt("cusid")== id) {
											count += 1;
										}
									}
									int height;
									int weight;
									int fat;
									int muscle;
									System.out.println("측정한 인바디를 입력하시오.");
									System.out.print("키 : ");
									height = Integer.parseInt(scan.nextLine());
									System.out.print("몸무게 : ");
									weight = Integer.parseInt(scan.nextLine());
									System.out.print("체지방 : ");
									fat = Integer.parseInt(scan.nextLine());
									System.out.print("근육량 : ");
									muscle = Integer.parseInt(scan.nextLine());
									
									System.out.printf("\n %s / %s / %s / %s 정말 입력 하시겠습니까? yes(1), no(0) : ",height,weight,fat,muscle);
									button = Integer.parseInt(scan.nextLine());
									if(button == 1) {
										sql = "insert into inbody(count,measuredate,age,sex,height,weight,fat,muscle,cusid) values(?,?,?,?,?,?,?,?,?)";
										
										ptmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
										ptmt.setInt(1, count);
										ptmt.setDate(2,new java.sql.Date(date.getTime()));
										ptmt.setInt(3, age);
										ptmt.setString(4, sex);
										ptmt.setInt(5, height);
										ptmt.setInt(6, weight);
										ptmt.setInt(7, fat);
										ptmt.setInt(8, muscle);
										ptmt.setInt(9, id);
										ptmt.executeUpdate();
									}
								}
								
								rs.close();
							}catch(Exception e) {
								System.out.println(e.toString());
							}
							
						}
						
						
						
					}////////////인바디 관리////////////////
					
					////////////메세지 보내기////////////////
					else if (button == 5) {
						System.out.println("남은 기간이 7일 아래인 회원 명단입니다.");
						try {
							stmt = conn.createStatement();
							String sql;
							String name;
							sql = "select * from schedules_view where leftdays < 7";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n","id","name","program","start","end","Left","Instructor");
							System.out.println("------------------------------------------------------------------------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("programname"),rs.getString("startdate").substring(0,10),rs.getString("enddate").substring(0,10),rs.getString("leftdays"),rs.getString("insname"));
							}
							rs.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
					}////////////메세지 보내기////////////////
					
				}
			}///////////////////////////회원관리메뉴///////////////////////////
			
			//////////////////////////강사관리메뉴///////////////////////////
			else if(button == 2) {
			
				while(true) {
				
					System.out.println("강사 관리 메뉴 \n");
					System.out.println("1. 강사 조회\n");
					System.out.println("2. 강사 추가\n");
					System.out.println("3. 강사 삭제\n");
					System.out.println("0. 뒤로\n");
					System.out.print("입력 : ");
					button = Integer.parseInt(scan.nextLine());
					
					if(button == 0) {
						break;
					}
					////강사조회////
					else if(button == 1) {
						try {
							stmt = conn.createStatement();
							String sql;
						
							sql = "select * from instructor_program_view ";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %20s %4s %3s %20s\n","id","name","age","sex","program");
							System.out.println("----------------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %20s %4s %3s %20s\n",rs.getString("instructorid"),rs.getString("insname"),rs.getString("age"),rs.getString("sex"),rs.getString("programname"));
							}
							rs.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
					}////강사조회////
					
					////강사추가////
					else if(button == 2) {
						int id;
						String name;
						int age;
						String sex;
						int proid;
						
						System.out.println("등록할 강사정보를 입력하시오.");
						System.out.print("ID : ");
						id = Integer.parseInt(scan.nextLine());
						System.out.print("Name : ");
						name = scan.nextLine();
						System.out.print("Age : ");
						age = Integer.parseInt(scan.nextLine());
						System.out.print("Sex(M,W) : ");
						sex = scan.nextLine();
						System.out.print("담당 프로그램 ID : ");
						proid = Integer.parseInt(scan.nextLine());
						
						System.out.printf("\n %s / %s / %s / %s / %s 정말 입력 하시겠습니까? yes(1), no(0) : ",id,name,age,sex,proid);
						button = Integer.parseInt(scan.nextLine());
						if(button == 1) {
							try {
									String sql;
									sql = "insert into instructor(instructorid,insname,age,sex,programid) values(?,?,?,?,?)";
									ptmt = conn.prepareStatement(sql);
									ptmt.setInt(1, id);
									ptmt.setString(2, name);
									ptmt.setInt(3, age);
									ptmt.setString(4, sex);
									ptmt.setInt(5, proid);
									int result = ptmt.executeUpdate();
									
									if(result > 0) {
										System.out.println("입력성공");
									}
									ptmt.close();
							}catch(Exception e) {
								System.out.println(e.toString());
							}
						}
					}////강사추가////
					
					/////강사삭제/////
					else if(button == 3) {
						
						int id;
						
						try {
							stmt = conn.createStatement();
							String sql;
						
							sql = "select * from instructor_program_view ";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %20s %4s %3s %20s\n","id","name","age","sex","program");
							System.out.println("----------------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %20s %4s %3s %20s\n",rs.getString("instructorid"),rs.getString("insname"),rs.getString("age"),rs.getString("sex"),rs.getString("programname"));
							}
							rs.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
						System.out.print("삭제할 id를 입력하시오 : ");
						id = Integer.parseInt(scan.nextLine());
						try {
							String sql;
							sql = "delete from instructor where instructorid = ?";
							ptmt = conn.prepareStatement(sql);
							ptmt.setInt(1, id);
							int result = ptmt.executeUpdate();
							
							if(result > 0) {
								System.out.println("삭제성공");
							}
							ptmt.close();
					}catch(Exception e) {
						System.out.println(e.toString());
					}
						
					}/////강사삭제/////
					
				}
			}//////////////////////////강사관리메뉴///////////////////////////
			
			//////////////////////////프로그램관리메뉴///////////////////////////
			else if(button == 3) {
				
				while(true) {
					
					System.out.println("프로그램 관리 메뉴 \n");
					System.out.println("1. 프로그램 조회\n");
					System.out.println("2. 프로그램 추가\n");
					System.out.println("3. 프로그램 삭제\n");
					System.out.println("0. 뒤로\n");
					System.out.print("입력 : ");
					button = Integer.parseInt(scan.nextLine());
					
					if(button == 0) {
						break;
					}
					////프로그램 조회////
					else if(button == 1) {
						try {
							stmt = conn.createStatement();
							String sql;
						
							sql = "select * from program ";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %30s %15s %20s\n","id","name","day","time");
							System.out.println("----------------------------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %30s %15s %20s\n",rs.getString("programid"),rs.getString("programname"),rs.getString("day"),rs.getString("time"));
							}
							rs.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
					}////프로그램 조회////
					
					////프로그램 추가////
					else if(button == 2) {
						int id;
						String name;
						String day;
						String time;
						
						System.out.println("등록할 프로그램 정보를 입력하시오.");
						System.out.print("ID : ");
						id = Integer.parseInt(scan.nextLine());
						System.out.print("Name : ");
						name = scan.nextLine();
						System.out.print("Day : ");
						day = scan.nextLine();
						System.out.print("Time : ");
						time = scan.nextLine();
						System.out.printf("\n %s / %s / %s / %s 정말 입력 하시겠습니까? yes(1), no(0) : ",id,name,day,time);
						button = Integer.parseInt(scan.nextLine());
						if(button == 1) {
							try {
									String sql;
									sql = "insert into program(programid,programname,day,time) values(?,?,?,?)";
									ptmt = conn.prepareStatement(sql);
									ptmt.setInt(1, id);
									ptmt.setString(2, name);
									ptmt.setString(3, day);
									ptmt.setString(4, time);
									int result = ptmt.executeUpdate();
									
									if(result > 0) {
										System.out.println("입력성공");
									}
									ptmt.close();
							}catch(Exception e) {
								System.out.println(e.toString());
							}
						}
					}////프로그램 추가////
					
					/////프로그램 삭제/////
					else if(button == 3) {
						
						int id;
						
						try {
							stmt = conn.createStatement();
							String sql;
						
							sql = "select * from program ";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %30s %15s %20s\n","id","name","day","time");
							System.out.println("----------------------------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %30s %15s %20s\n",rs.getString("programid"),rs.getString("programname"),rs.getString("day"),rs.getString("time"));
							}
							rs.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
						System.out.print("삭제할 id를 입력하시오 : ");
						id = Integer.parseInt(scan.nextLine());
						try {
							String sql;
							sql = "delete from program where programid = ?";
							ptmt = conn.prepareStatement(sql);
							ptmt.setInt(1, id);
							int result = ptmt.executeUpdate();
							
							if(result > 0) {
								System.out.println("삭제성공");
							}
							ptmt.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
					}/////프로그램 삭제/////
				}
			}//////////////////////////프로그램관리메뉴///////////////////////////
			
			//////////////////////////스케쥴관리메뉴///////////////////////////
			else if(button == 4) {
				
				while(true) {
					
					System.out.println("스케쥴 관리 메뉴 \n");
					System.out.println("1. 스케쥴 조회\n");
					System.out.println("2. 스케쥴 추가\n");
					System.out.println("3. 스케쥴 삭제\n");
					System.out.println("0. 뒤로\n");
					System.out.print("입력 : ");
					button = Integer.parseInt(scan.nextLine());
					
					if(button == 0) {
						break;
					}
					////스케쥴 조회////
					else if(button == 1) {
						
						
						
						try {
							stmt = conn.createStatement();
							String sql;
							String name;
							sql = "select * from schedules_view";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n","id","name","program","start","end","Left","Instructor");
							System.out.println("------------------------------------------------------------------------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("programname"),rs.getString("startdate").substring(0,10),rs.getString("enddate").substring(0,10),rs.getString("leftdays"),rs.getString("insname"));
							}
							rs.close();
							System.out.println("1. 회원 이름으로 조회");
							System.out.println("2. 강사 이름으로 조회");
							System.out.println("3. 프로그램 이름으로 조회");
							System.out.println("0. 뒤로");
							System.out.print("입력 : ");
							button = Integer.parseInt(scan.nextLine());
							if(button == 1) {
								System.out.print("회원 이름 입력 : ");
								name = scan.nextLine();
								sql = "select * from schedules_view where name = ?";
								ptmt = conn.prepareStatement(sql);
								ptmt.setString(1,name);
								rs = ptmt.executeQuery();
								System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n","id","name","program","start","end","Left","Instructor");
								System.out.println("------------------------------------------------------------------------------------------------------------------------");
								while(rs.next()) {
									
									System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("programname"),rs.getString("startdate").substring(0,10),rs.getString("enddate").substring(0,10),rs.getString("leftdays"),rs.getString("insname"));
								}
								rs.close();
							}
							else if(button == 2) {
								System.out.print("강사 이름 입력 : ");
								name = scan.nextLine();
								sql = "select * from schedules_view where insname = ?";
								ptmt = conn.prepareStatement(sql);
								ptmt.setString(1,name);
								rs = ptmt.executeQuery();
								System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n","id","name","program","start","end","Left","Instructor");
								System.out.println("------------------------------------------------------------------------------------------------------------------------");
								while(rs.next()) {
									
									System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("programname"),rs.getString("startdate").substring(0,10),rs.getString("enddate").substring(0,10),rs.getString("leftdays"),rs.getString("insname"));
								}
								rs.close();
							}
							else if(button == 3) {
								System.out.print("프로그램 이름 입력 : ");
								name = scan.nextLine();
								sql = "select * from schedules_view where programname = ?";
								
								ptmt = conn.prepareStatement(sql);
								ptmt.setString(1,name);
								rs = ptmt.executeQuery();
								System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n","id","name","program","start","end","Left","Instructor");
								System.out.println("------------------------------------------------------------------------------------------------------------------------");
								while(rs.next()) {
									
									System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("programname"),rs.getString("startdate").substring(0,10),rs.getString("enddate").substring(0,10),rs.getString("leftdays"),rs.getString("insname"));
								}
								rs.close();
							}
							
							
							
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
						
						
					}////스케쥴 조회////
					
					////스케쥴 추가////
					else if(button == 2) {
						int cusid;
						String start;
						String end;
						int insid;
						int proid;
						
						System.out.println("등록할 스케쥴 정보를 입력하시오.");
						System.out.print("Customer ID : ");
						cusid = Integer.parseInt(scan.nextLine());
						System.out.print("Start Date (YYYY-MM-DD) : ");
						start = scan.nextLine();
						System.out.print("end Date (YYYY-MM-DD) : ");
						end = scan.nextLine();
						System.out.print("Instructor ID : ");
						insid = Integer.parseInt(scan.nextLine());
						
						System.out.printf("\n %s / %s / %s / %s  정말 입력 하시겠습니까? yes(1), no(0) : ",cusid,start,end,insid);
						button = Integer.parseInt(scan.nextLine());
						if(button == 1) {
							try {
									String sql;
									sql = "insert into schedules(customerid,startdate,enddate,instructorid) values(?,?,?,?)";
									ptmt = conn.prepareStatement(sql);
									ptmt.setInt(1, cusid);
									
									Date parse = format.parse(start);
									//java.sql.Date sql_sdate = new java.sql.Date(sdate.getDate())
									ptmt.setDate(2, new java.sql.Date(parse.getTime())); 
									parse = format.parse(end);
									ptmt.setDate(3, new java.sql.Date(parse.getTime()));
									ptmt.setInt(4, insid);
									int result = ptmt.executeUpdate();
									
									if(result > 0) {
										System.out.println("입력성공");
									}
									ptmt.close();
							}catch(Exception e) {
								System.out.println(e.toString());
							}
						}
					}////스케쥴 추가////
					
					/////스케쥴 삭제/////
					else if(button == 3) {
						
						int cusid;
						String proname;
						
						try {
							stmt = conn.createStatement();
							String sql;
							String name;
							sql = "select * from schedules_view";
							
							ResultSet rs = stmt.executeQuery(sql);
							System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n","id","name","program","start","end","Left","Instructor");
							System.out.println("------------------------------------------------------------------------------------------------------------------------");
							while(rs.next()) {
								
								System.out.printf("%4s %20s %20s %15s %15s %8s %20s\n",rs.getString("customerid"),rs.getString("name"),rs.getString("programname"),rs.getString("startdate").substring(0,10),rs.getString("enddate").substring(0,10),rs.getString("leftdays"),rs.getString("insname"));
							}
							rs.close();
						}catch(Exception e) {
							System.out.println(e.toString());
						}
						
						System.out.print("삭제할  스케쥴의 회원 아이디를 입력하시오 : ");
						cusid = Integer.parseInt(scan.nextLine());
						
						System.out.print("삭제할 스케쥴의  프로그램 이름을 입력하시오 : ");
						proname = scan.nextLine();
						
						System.out.print("정말 삭제하시겠습니까? y(1),n(0) : ");
						button = Integer.parseInt(scan.nextLine());
						if(button == 1) {
							try {
								String sql;
								sql = "delete from schedules where (customerid,instructorid) in (select c.customerid, i.instructorid "
										+"from customer c, instructor i, program p ,schedules s " 
										+"where c.customerid = s.customerid and i.instructorid = s.instructorid and p.programid = i.programid and c.customerid = ? and p.programname = ?)";
								ptmt = conn.prepareStatement(sql);
								ptmt.setInt(1, cusid);
								ptmt.setString(2, proname);
								int result = ptmt.executeUpdate();
								
								if(result > 0) {
									System.out.println("삭제성공");
								}
								ptmt.close();
							}catch(Exception e) {
								System.out.println(e.toString());
							}
						}
						
						
					}/////스케쥴 삭제/////
				}
				
			}//////////////////////////스케쥴관리메뉴///////////////////////////
		}// 메인메뉴
		
//		try {
//			Statement stmt = conn.createStatement();
//			String sql;
//			
//			
//			
//			sql = "insert into customer(customerid,name,tell,age,sex) ";
//			sql += "values(3,'kim','01012345678',50,'W')";
//			
//			int result = stmt.executeUpdate(sql);
//			
//			if(result == 1) {
//				System.out.println("입력성공");
//			}
//		}catch(Exception e) {
//			System.out.println(e.toString());
//		}
//		
		DBConn.close();
		
		scan.close();
	}

}
