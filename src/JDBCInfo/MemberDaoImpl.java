package JDBCInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
			PreparedStatement ps = conn.prepareStatement("select nickname from employee where region =?");
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
			PreparedStatement ps = conn.prepareStatement("select nickname from employee where birthday like ?");
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
			PreparedStatement ps = conn.prepareStatement("select nickname from employee where substr(birthday,5,2) = ?");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertMember(Member member) {
		// TODO Auto-generated method stub
		
	}
	
	//main
	public static void main(String[] args) {
		
	}

}
