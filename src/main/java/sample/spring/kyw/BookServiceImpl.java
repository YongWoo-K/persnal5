package sample.spring.kyw;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//����Ŭ������ ����Ͻ� Ŭ������ ��ġ�ϴ� ���̴�.
//-> ������MVC�������� ����Ŭ������ ��Ʈ�ѷ��� DAO�� �����ϴ� �ٸ������� �����Ѵ�.
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookDao bookDao;
	
	//BookDao�� insert()�޼ҵ带 �����Ű�� ���� �޼ҵ� -> BookService �������̽����� �̱����� �޼ҵ带 ������
	//��Ŭ���������� ���� �������̽��� �޼ҵ� �ñ״��İ� ���� ��� ������ ǥ�����ָ� �ڵ����� �������̽��� �޼ҵ� �ñ״��ĸ� �������ش�.
	//�� ����� ����ϱ� ���� Override������̼��� ����Ѵ�.
	//Override������̼��� �ڹٿ� �⺻���� ����� ������̼����� �������̽��� ���ǵ� ���� ������(Override)�Ѵٴ� ���̴�.
	@Override
	public String create(Map<String, Object> map) {
	    int affectRowCount = this.bookDao.insert(map);
	    if (affectRowCount ==  1) {
	        return map.get("book_id").toString();
	    }
	    return null;
	}
	
	//å �� ���� Ŭ���� �޼ҵ� ����
	//-> DAO�� ȣ���� ����� �ٷ� �����ϴ� �ϸ� �Ѵ�.
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.bookDao.selectDetail(map);
	}
	
	//å ���� ��� ���� Ŭ���� �޼ҵ�
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount = this.bookDao.update(map);  
		return affectRowCount == 1; 
	}
	//������ ��� �Է°��� �ٸ��� PK�� �������ų� �ϴ� ������ �ʿ����
	//���� 1���� ���� ����� ����޾Ҵ����� �˻��Ѵ�. -> return affectRowCount == 1
	
	//å ���� ��� ���� Ŭ���� �޼ҵ�
	@Override
	public boolean remove(Map<String, Object> map) {
		int affectRowCount = this.bookDao.delete(map);  
		return affectRowCount == 1; 
	}
	//������ ��� ������ �����ϰ� �Ѱ��� ���� ����� ����޾Ҵ����� �˻��ϸ� �ȴ�.

	//å ��� ���� Ŭ���� �޼ҵ�
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.bookDao.selectList(map);
	}
	
	
	
	
}
