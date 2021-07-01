package JDBCInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
				member = new Member(rs.getString("nickname"), region, rs.getString("birthday"));
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
				member = new Member(rs.getString("nickname"), rs.getString("region"), birthday);
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
				member = new Member(rs.getString("nickname"),rs.getString("region"), birthday);
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
		try {
			conn = DriverManager.getConnection(DB_URL,"hr","hr");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("drop table member");
			stmt.executeUpdate("create table member (nickname varchar(15), region varchar(10), birthday char(8))");
			//닉네임, 지역, 생년월일을 컬럼으로 하는 member 테이블을 만든다.
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
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
			//"insert into employee values ("+ employee.getId() + ",'" + employee.getName() + "')"
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

}
