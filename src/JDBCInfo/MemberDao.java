package JDBCInfo;

public interface MemberDao {
	boolean getMemberByNickName(String nickname); //닉네임 중복을 검사
	Member getMemberByRegion(String region); //같은 지역구에 사는 멤버 검색
	Member getMemberByYear(String birthday); //같은 나이의 멤버 검색
	Member getMemberByMonth(String birthday); //요번 달에 생일인 멤버를 검색.
	
	void createMember();
	void insertMember(Member member);
}
