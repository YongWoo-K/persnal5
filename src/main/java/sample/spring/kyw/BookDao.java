package sample.spring.kyw;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//Repository는 저장소 개념
@Repository
public class BookDao {
	//Autowired : 스프링 컨테이너에 등록한 빈에게 의존관계주입이 필요할 경우 DI(의존성 주입)를 도와주는 어노테이션
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
		
	//입력쿼리를 실행하는 메소드 생성
	// -> 자바 리스트 안에 래퍼, String이 아닌 객체일 경우 정렬하는 방법 - HashMap
	public int insert(Map<String,Object> map) {
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	
	//sqlSessionTemplate의 selectone()메소드 : 데이터를 하나만 가져올 경우 사용
	// -> 만약 쿼리 행의 수가 0개라면 selectone메소드는 null을 리턴
	// -> 쿼리 결과가 여러개면 TooManyResultsException 예외를 던진다.
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	//책 수정기능 DAO 메소드
	public int update(Map<String, Object> map) {  
		return this.sqlSessionTemplate.update("book.update", map);  
	} 
	/*
	  - sqlSessionTemplate객체의 update()메소드는 insert 메소드와 사용법이 동일하다.
	  - 첫번째 파라미터는 쿼리id, 두번쨰 파라미터는 쿼리파라미터이며 반환값은 영향받은 행 수이다.
	 */
	
	//책 삭제기능 DAO메소드
	public int delete(Map<String, Object> map) {  
		return this.sqlSessionTemplate.delete("book.delete", map);  
	} 
	/*
	 - sqlSessionTemplate 객체의 delete 메소드는 update 메소드와 사용법이 동일하다.
	 - 첫번째 파라미터는 쿼리ID, 두번째 파라미터는 쿼리 파라미터이며 반환값은 영향받은 행 수이다.
	 */
	
	//책 목록 DAO 메소드
	public List<Map<String, Object>> selectList(Map<String, Object> map) {  
		return this.sqlSessionTemplate.selectList("book.select_list", map);  
	}
	/*
	 - 쿼리 결과를 목록으로 받기 위해서는 sqlSessionTemplate의 selectList()를 사용할 수 있다.
	 - 첫번째 파라미터는 쿼리ID, 두번째 ID는 쿼리 파라미터다.
	 - sqlSessionTemplate의 selectList() 메소드는 결과 집합 목록을 반환한다.
		-> 따라서 결과 집합 타입인 Map<String, Object>의 목록 List 타입으로 읽어들일 수 있다.
	 */
}
