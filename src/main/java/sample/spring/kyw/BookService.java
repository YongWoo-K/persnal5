package sample.spring.kyw;

import java.util.List;
import java.util.Map;

//서비스클래스에서 선언한 BookService 인터페이스 생성
// -> 서비스 인터페이스는 직접 탐색기에서 생성하지 않고 클래스에서 생성하는 방법을 취한다.
public interface BookService {
	//BookDao의 insert()메소드를 실행시키는 서비스 메소드
	//-> 미구현상태로작성(BookServiceImpl클래스에서 재정의)
	String create(Map<String, Object> map);
	
	Map<String, Object> detail(Map<String, Object> map);
	
	boolean edit(Map<String, Object> map);
	
	boolean remove(Map<String, Object> map);
	
	List<Map<String, Object>> list(Map<String, Object> map);
}
