package sample.spring.kyw;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //컨트롤러 네임스페이스
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 Annotation
  - 사전적 의미로는 주석
  - 자바에서 코드사이에 주석처럼 사용하며 특별한 의미를 지님
  - 기능을 수행하도록 하는 기술로 메타데이터라고 볼 수 있음
  - 프로그램 실행 관점에서 보면 프로그램이 처리할 메인 데이터가 아니라 실행 과정에서 데이터를 어떻게 처리할 것인지
       알려주는 서브데이터라고 할 수 있음.
*/
@Controller
public class BookController {
	//입력기능 서비스를 호출하기 위해 서비스 빈을 추가
	@Autowired
	BookService bookService;
	//-> 서비스를 호출하기 위해 BookService를 의존성을 주입한다. 이 때 BookService 인터페이스가 사용되었음을 주의
	
	//브라우저 주소가 /create일 경우 실행되는 컨트롤러 메소드를 작성한다.
	//create메소드는 브라우저에서 /create주소가 get방식으로 입력되었을때 book/create경로의 뷰를보여준다.
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}
	
	//서비스를 이용해 책을 입력하는 컨트롤러 메소드
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		//ModelAndView 객체를 선언
		//-> ModelAndView와 model과의 차이점 
		//		- model은 데이터만 저장함
		//		- ModelAndView는 데이터와 이동하고자 하는 View페이지를 같이 저장
	    ModelAndView mav = new ModelAndView();
	    
	    //서비스 빈의 create()메소드에 map넣은 결과값을 변수 bookId에 저장
	    String bookId = this.bookService.create(map);
	    //bookId == null이라면 다시 create로 보냄
	    if (bookId == null) {
	        mav.setViewName("redirect:/create");
	    }
	    //bookId값이 있다면 그 값에 해당하는 주소로 이동
	    else {
	        mav.setViewName("redirect:/detail?bookId=" + bookId); 
	    }  

	    return mav;
	}
	
	/*
	 상세화면의 URI는 /detail?bookId=1 형식이다.
	 주소창을 통해 파라미터가 서버로 전달되는 형태를 쿼리스트링(Query String)이라고 부른다.
	 HTTP규격에서 쿼리스트링은 URL(Uniform Resource Locator) 끝에 ?로 시작한다.
	  각 항목은 &로 이어지며, 개별학목의 키와 값은 =로 구분하게 된다.
	 ex) /sample/test?a=1&b=2 
	 	-> URL : /sample/test
	 	-> 쿼리스트링 : ?a=1&b=2
	 	-> 쿼리스트링의 시작 : ?
	 	-> 쿼리스트링의 항목 구분 : &
	 	-> 쿼리스트링의 항목들 : a=1, b=2
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
	    Map<String, Object> detailMap = this.bookService.detail(map);

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", detailMap);
	    String bookId = map.get("bookId").toString();
	    mav.addObject("bookId", bookId);
	    mav.setViewName("/book/detail");
	    return mav;
	}
	//-> @RequestParam 어노테이션에 의해 쿼리 스트링 파라미터를 읽을 수 있다. 
	//   스프링은 http 메소드를 구분하지 않고 파라미터를 GET, POST 동일한 방법으로 읽을 수 있게 한다.
	
	//수정정보를 보여주기 위한 화면을 만듦
	@RequestMapping(value = "/update", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) {  
	Map<String, Object> detailMap = this.bookService.detail(map);  

	ModelAndView mav = new ModelAndView();  
	mav.addObject("data", detailMap);  
	mav.setViewName("/book/update");  
	return mav;  
	} 
	
	//수정기능 컨트롤러 메소드
	@RequestMapping(value = "/update", method = RequestMethod.POST)  
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {  
	ModelAndView mav = new ModelAndView();  

	boolean isUpdateSuccess = this.bookService.edit(map);  
	if (isUpdateSuccess) {  
	String bookId = map.get("bookId").toString();  
	mav.setViewName("redirect:/detail?bookId=" + bookId);  
	}else {  
	mav = this.update(map);  
	}  

	return mav;  
	} 
	/*
	- 책 수정 화면에서 책 수정 기능으로 보내주는 파라미터는 총 4개
	 	- 하나는 GET파라미터로 전달되는 bookId
	 	- 나머지는 form태그를 통해 전달되는 title, category, price 
	- 스프링은 http 메소드가 GET인지 POST인지 상관하지 않고 @RequestMapping 어노테이션이 있으면 무조건 파라미터를 넣어준다.
	    따라서 파라미터 map 안에는 4개 데이터가 다 들어있다.
	- if문 -> 정상적으로 데이터가 수정되었을 경우 확인을 위해 해당 글의 상세페이지로 이동
	- else문 -> 갱신이 되지 않았을 경우 해당글의 수정화면을 다시 보여줌
	 */
	
	
	//삭제기능 컨트롤러 메소드
	@RequestMapping(value = "/delete", method = RequestMethod.POST)  
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {  
	ModelAndView mav = new ModelAndView();  
	
	//삭제가 성공했는지 확인
	boolean isDeleteSuccess = this.bookService.remove(map); 
	//삭제 성공시 상세페이지가 없으므로 목록으로 리다이렉트
	if (isDeleteSuccess) {  
	mav.setViewName("redirect:/list");  
	}
	//삭제 실패시 다시 상세페이지로 이동
	else {  
	String bookId = map.get("bookId").toString();  
	mav.setViewName("redirect:/detail?bookId=" + bookId);  
	}  
	
	return mav;  
	} 
	
	//책 목록 컨트롤러 메소드
	@RequestMapping(value = "list")  
	public ModelAndView list(@RequestParam Map<String, Object> map) {  
	//책 목록을 데이터베이스에서 가지고 옴
	List<Map<String, Object>> list = this.bookService.list(map);  

	ModelAndView mav = new ModelAndView(); 
	//가지고온 데이터를 view에 전달할 수 있도록 mav객체에 넣음
	mav.addObject("data", list); 
	
	//파라미터 존재하는지 체크 
	if (map.containsKey("keyword")) {  
		mav.addObject("keyword", map.get("keyword"));  
		//-> 파라미터가 존재한다면 파라미터를 view에 전달할 수 있도록 mav객체에 넣음
	} 
	
	mav.setViewName("/book/list");  
	//"/book/list" 뷰를 리턴
	return mav;  
	} 
}
