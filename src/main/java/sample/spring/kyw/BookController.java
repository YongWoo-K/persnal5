package sample.spring.kyw;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //��Ʈ�ѷ� ���ӽ����̽�
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 Annotation
  - ������ �ǹ̷δ� �ּ�
  - �ڹٿ��� �ڵ���̿� �ּ�ó�� ����ϸ� Ư���� �ǹ̸� ����
  - ����� �����ϵ��� �ϴ� ����� ��Ÿ�����Ͷ�� �� �� ����
  - ���α׷� ���� �������� ���� ���α׷��� ó���� ���� �����Ͱ� �ƴ϶� ���� �������� �����͸� ��� ó���� ������
       �˷��ִ� ���굥���Ͷ�� �� �� ����.
*/
@Controller
public class BookController {
	//�Է±�� ���񽺸� ȣ���ϱ� ���� ���� ���� �߰�
	@Autowired
	BookService bookService;
	//-> ���񽺸� ȣ���ϱ� ���� BookService�� �������� �����Ѵ�. �� �� BookService �������̽��� ���Ǿ����� ����
	
	//������ �ּҰ� /create�� ��� ����Ǵ� ��Ʈ�ѷ� �޼ҵ带 �ۼ��Ѵ�.
	//create�޼ҵ�� ���������� /create�ּҰ� get������� �ԷµǾ����� book/create����� �並�����ش�.
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}
	
	//���񽺸� �̿��� å�� �Է��ϴ� ��Ʈ�ѷ� �޼ҵ�
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		//ModelAndView ��ü�� ����
		//-> ModelAndView�� model���� ������ 
		//		- model�� �����͸� ������
		//		- ModelAndView�� �����Ϳ� �̵��ϰ��� �ϴ� View�������� ���� ����
	    ModelAndView mav = new ModelAndView();
	    
	    //���� ���� create()�޼ҵ忡 map���� ������� ���� bookId�� ����
	    String bookId = this.bookService.create(map);
	    //bookId == null�̶�� �ٽ� create�� ����
	    if (bookId == null) {
	        mav.setViewName("redirect:/create");
	    }
	    //bookId���� �ִٸ� �� ���� �ش��ϴ� �ּҷ� �̵�
	    else {
	        mav.setViewName("redirect:/detail?bookId=" + bookId); 
	    }  

	    return mav;
	}
	
	/*
	 ��ȭ���� URI�� /detail?bookId=1 �����̴�.
	 �ּ�â�� ���� �Ķ���Ͱ� ������ ���޵Ǵ� ���¸� ������Ʈ��(Query String)�̶�� �θ���.
	 HTTP�԰ݿ��� ������Ʈ���� URL(Uniform Resource Locator) ���� ?�� �����Ѵ�.
	  �� �׸��� &�� �̾�����, �����и��� Ű�� ���� =�� �����ϰ� �ȴ�.
	 ex) /sample/test?a=1&b=2 
	 	-> URL : /sample/test
	 	-> ������Ʈ�� : ?a=1&b=2
	 	-> ������Ʈ���� ���� : ?
	 	-> ������Ʈ���� �׸� ���� : &
	 	-> ������Ʈ���� �׸�� : a=1, b=2
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
	//-> @RequestParam ������̼ǿ� ���� ���� ��Ʈ�� �Ķ���͸� ���� �� �ִ�. 
	//   �������� http �޼ҵ带 �������� �ʰ� �Ķ���͸� GET, POST ������ ������� ���� �� �ְ� �Ѵ�.
	
	//���������� �����ֱ� ���� ȭ���� ����
	@RequestMapping(value = "/update", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) {  
	Map<String, Object> detailMap = this.bookService.detail(map);  

	ModelAndView mav = new ModelAndView();  
	mav.addObject("data", detailMap);  
	mav.setViewName("/book/update");  
	return mav;  
	} 
	
	//������� ��Ʈ�ѷ� �޼ҵ�
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
	- å ���� ȭ�鿡�� å ���� ������� �����ִ� �Ķ���ʹ� �� 4��
	 	- �ϳ��� GET�Ķ���ͷ� ���޵Ǵ� bookId
	 	- �������� form�±׸� ���� ���޵Ǵ� title, category, price 
	- �������� http �޼ҵ尡 GET���� POST���� ������� �ʰ� @RequestMapping ������̼��� ������ ������ �Ķ���͸� �־��ش�.
	    ���� �Ķ���� map �ȿ��� 4�� �����Ͱ� �� ����ִ�.
	- if�� -> ���������� �����Ͱ� �����Ǿ��� ��� Ȯ���� ���� �ش� ���� ���������� �̵�
	- else�� -> ������ ���� �ʾ��� ��� �ش���� ����ȭ���� �ٽ� ������
	 */
	
	
	//������� ��Ʈ�ѷ� �޼ҵ�
	@RequestMapping(value = "/delete", method = RequestMethod.POST)  
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {  
	ModelAndView mav = new ModelAndView();  
	
	//������ �����ߴ��� Ȯ��
	boolean isDeleteSuccess = this.bookService.remove(map); 
	//���� ������ ���������� �����Ƿ� ������� �����̷�Ʈ
	if (isDeleteSuccess) {  
	mav.setViewName("redirect:/list");  
	}
	//���� ���н� �ٽ� ���������� �̵�
	else {  
	String bookId = map.get("bookId").toString();  
	mav.setViewName("redirect:/detail?bookId=" + bookId);  
	}  
	
	return mav;  
	} 
	
	//å ��� ��Ʈ�ѷ� �޼ҵ�
	@RequestMapping(value = "list")  
	public ModelAndView list(@RequestParam Map<String, Object> map) {  
	//å ����� �����ͺ��̽����� ������ ��
	List<Map<String, Object>> list = this.bookService.list(map);  

	ModelAndView mav = new ModelAndView(); 
	//������� �����͸� view�� ������ �� �ֵ��� mav��ü�� ����
	mav.addObject("data", list); 
	
	//�Ķ���� �����ϴ��� üũ 
	if (map.containsKey("keyword")) {  
		mav.addObject("keyword", map.get("keyword"));  
		//-> �Ķ���Ͱ� �����Ѵٸ� �Ķ���͸� view�� ������ �� �ֵ��� mav��ü�� ����
	} 
	
	mav.setViewName("/book/list");  
	//"/book/list" �並 ����
	return mav;  
	} 
}
