package JDBCInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class MemberDaoImpl implements MemberDao{
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DriveManager = null;	

	@Override
	public Member getMemberByRegion(String region) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			PreparedStatement ps = conn.prepareStatement("select nickname from member where region =?");
			ps.setString(1, region);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getString("nickname")); //생성자 여럿 만들어야... 닉네임 컬럼만 출력할 것이기 때문.
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException e) {					
				}
			}
		}
		return member;
	}

	@Override
	public Member getMemberByYear(String birthday) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			PreparedStatement ps = conn.prepareStatement("select nickname from member where birthday like ?");
			String year = birthday.substring(0,4); // 생년웡일에서 년도만 빼내었음.			
			ps.setString(1,year+"%");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getString("nickname"));
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException e) {					
				}
			}
		}
		return member;
	}

	@Override
	public Member getMemberByMonth(String birthday) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			PreparedStatement ps = conn.prepareStatement("select nickname from member where substr(birthday,5,2) = ?");
			String month = birthday.substring(4,6); // 입력받은 생년웡일에서 년도만 빼내었음.			
			ps.setString(1, month);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getString("nickname"));
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException e) {					
				}
			}
		}
		return member;
	}

	@Override
	public void createMember() {
		Connection conn = null;
		ResultSet rs1;
		
		try {
			conn = DriverManager.getConnection(DB_URL,"hr","hr");
			Statement stmt = conn.createStatement();
			stmt = conn.createStatement();
			String checkSql = "select count(*) from all_tables where table_name = 'MEMBER'";	
			//select count(*) from all_tables where table_name = '테이블 명';	
			rs1 = stmt.executeQuery(checkSql);
			if(rs1.next()) {
				System.out.println("member 테이블을 생성합니다.");
				String sql = "create table member (nickname varchar(30), region varchar(30), birthday char(8))"; 
				
				stmt.executeUpdate(sql);
				stmt.close();				
		}		
			stmt.close();
		} catch (SQLException e1) {			
			//e1.printStackTrace();
		//stmt.executeUpdate("drop table member");		
		//닉네임, 지역, 생년월일을 컬럼으로 하는 member 테이블을 만든다.		
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
				}
			}
		}		
	}

	@Override
	public void insertMember(Member member) {
		Connection conn = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			//Statement stmt = conn.createStatement();
			String sql = "insert into member values(?,?,?)";
			String nn = member.getNickName();
			String rg = member.getRegion();
			String bd = member.getBirthday();
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = null;
			
			pst.setString(1,nn);
			pst.setString(2, rg);
			pst.setString(3, bd);
			
			pst.executeUpdate();			
			pst.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn !=null) {
				try {
					conn.close();
				}catch (SQLException e) {
					
				}
			}
		}		
	}

	@Override
	public boolean getMemberByNickName(String nickname) {
		Connection conn = null;
		ResultSet rs;
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			//Statement stmt = conn.createStatement();
			String sql = "select * from member where nickname = ?";
						
			PreparedStatement pst = conn.prepareStatement(sql);			
			pst.setString(1, nickname); //nickname이 이미 있는지 검사한다.
			
			rs = pst.executeQuery();
			if(rs.next()) { //검색된 데이터가 존재할 경우-- 1개. 				
				return false;
			}			
			conn.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn !=null) {
				try {
					conn.close();
				}catch (SQLException e) {
					
				}
			}
		}
		return true;
					
	}
	
	

}
