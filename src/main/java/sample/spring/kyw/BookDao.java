package sample.spring.kyw;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//Repository�� ����� ����
@Repository
public class BookDao {
	//Autowired : ������ �����̳ʿ� ����� �󿡰� �������������� �ʿ��� ��� DI(������ ����)�� �����ִ� ������̼�
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
		
	//�Է������� �����ϴ� �޼ҵ� ����
	// -> �ڹ� ����Ʈ �ȿ� ����, String�� �ƴ� ��ü�� ��� �����ϴ� ��� - HashMap
	public int insert(Map<String,Object> map) {
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	
	//sqlSessionTemplate�� selectone()�޼ҵ� : �����͸� �ϳ��� ������ ��� ���
	// -> ���� ���� ���� ���� 0����� selectone�޼ҵ�� null�� ����
	// -> ���� ����� �������� TooManyResultsException ���ܸ� ������.
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	//å ������� DAO �޼ҵ�
	public int update(Map<String, Object> map) {  
		return this.sqlSessionTemplate.update("book.update", map);  
	} 
	/*
	  - sqlSessionTemplate��ü�� update()�޼ҵ�� insert �޼ҵ�� ������ �����ϴ�.
	  - ù��° �Ķ���ʹ� ����id, �ι��� �Ķ���ʹ� �����Ķ�����̸� ��ȯ���� ������� �� ���̴�.
	 */
	
	//å ������� DAO�޼ҵ�
	public int delete(Map<String, Object> map) {  
		return this.sqlSessionTemplate.delete("book.delete", map);  
	} 
	/*
	 - sqlSessionTemplate ��ü�� delete �޼ҵ�� update �޼ҵ�� ������ �����ϴ�.
	 - ù��° �Ķ���ʹ� ����ID, �ι�° �Ķ���ʹ� ���� �Ķ�����̸� ��ȯ���� ������� �� ���̴�.
	 */
	
	//å ��� DAO �޼ҵ�
	public List<Map<String, Object>> selectList(Map<String, Object> map) {  
		return this.sqlSessionTemplate.selectList("book.select_list", map);  
	}
	/*
	 - ���� ����� ������� �ޱ� ���ؼ��� sqlSessionTemplate�� selectList()�� ����� �� �ִ�.
	 - ù��° �Ķ���ʹ� ����ID, �ι�° ID�� ���� �Ķ���ʹ�.
	 - sqlSessionTemplate�� selectList() �޼ҵ�� ��� ���� ����� ��ȯ�Ѵ�.
		-> ���� ��� ���� Ÿ���� Map<String, Object>�� ��� List Ÿ������ �о���� �� �ִ�.
	 */
}
