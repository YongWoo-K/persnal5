package sample.spring.kyw;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//서비스클래스는 비즈니스 클래스가 위치하는 곳이다.
//-> 스프링MVC구조에서 서비스클래스는 컨트롤러와 DAO를 연결하는 다리역할을 수행한다.
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookDao bookDao;
	
	//BookDao의 insert()메소드를 실행시키는 서비스 메소드 -> BookService 인터페이스에서 미구현된 메소드를 재정의
	//이클립스에서는 상위 인터페이스에 메소드 시그니쳐가 없을 경우 오류를 표시해주며 자동으로 인터페이스에 메소드 시그니쳐를 생성해준다.
	//이 기능을 사용하기 위해 Override어노테이션을 사용한다.
	//Override어노테이션은 자바에 기본으로 내장된 어노테이션으로 인터페이스에 정의된 것을 재정의(Override)한다는 뜻이다.
	@Override
	public String create(Map<String, Object> map) {
	    int affectRowCount = this.bookDao.insert(map);
	    if (affectRowCount ==  1) {
	        return map.get("book_id").toString();
	    }
	    return null;
	}
	
	//책 상세 서비스 클래스 메소드 생성
	//-> DAO를 호출한 결과를 바로 리턴하는 일만 한다.
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.bookDao.selectDetail(map);
	}
	
	//책 수정 기능 서비스 클래스 메소드
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount = this.bookDao.update(map);  
		return affectRowCount == 1; 
	}
	//수정의 경우 입력과는 다르게 PK를 가져오거나 하는 절차가 필요없음
	//그저 1개의 행이 제대로 영향받았는지만 검사한다. -> return affectRowCount == 1
	
	//책 삭제 기능 서비스 클래스 메소드
	@Override
	public boolean remove(Map<String, Object> map) {
		int affectRowCount = this.bookDao.delete(map);  
		return affectRowCount == 1; 
	}
	//삭제의 경우 수정과 동일하게 한개의 행이 제대로 영향받았는지만 검사하면 된다.

	//책 목록 서비스 클래스 메소드
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.bookDao.selectList(map);
	}
	
	
	
	
}
