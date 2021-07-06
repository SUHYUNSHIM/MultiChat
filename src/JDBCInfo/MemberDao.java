package JDBCInfo;

public interface MemberDao {
	boolean getMemberByNickName(String nickname); //닉네임 중복을 검사
	/*Member[] getMemberByRegion(String nickname); //같은 지역구에 사는 멤버 검색
	Member[] getMemberByYear(String nickname); //같은 나이의 멤버 검색
	Member[] getMemberByMonth(String birthday); //요번 달에 생일인 멤버를 검색. --> 오늘 날짜 sysdate로 수정해야.
*/	
	Member getMemberByRegion(String nickname); //같은 지역구에 사는 멤버 검색
	Member getMemberByYear(String nickname); //같은 나이의 멤버 검색
	Member getMemberByMonth(String birthday); //요번 달에 생일인 멤버를 검색. --> 오늘 날짜 sysdate로 수정해야.
	
	Member getBirthdayByNickName(String nickname);
	
	void createMember();
	void insertMember(Member member);
}
