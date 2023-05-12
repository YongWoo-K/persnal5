package sample.spring.kyw;

import java.util.List;
import java.util.Map;

//����Ŭ�������� ������ BookService �������̽� ����
// -> ���� �������̽��� ���� Ž���⿡�� �������� �ʰ� Ŭ�������� �����ϴ� ����� ���Ѵ�.
public interface BookService {
	//BookDao�� insert()�޼ҵ带 �����Ű�� ���� �޼ҵ�
	//-> �̱������·��ۼ�(BookServiceImplŬ�������� ������)
	String create(Map<String, Object> map);
	
	Map<String, Object> detail(Map<String, Object> map);
	
	boolean edit(Map<String, Object> map);
	
	boolean remove(Map<String, Object> map);
	
	List<Map<String, Object>> list(Map<String, Object> map);
}
