package net.admin.db;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.book.db.BookDTO;
import util.myBatisSetting.sqlMapConfig;

public class AdminDAO {
	SqlSessionFactory sessionf = sqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public AdminDAO() {
		sqlsession = sessionf.openSession(true);
	}
	
	// 책 전체 개수 구하기
	public int getBookCount(){
		int count;
		count=sqlsession.selectOne("getBookCount");
		return count;
	}
	
	// 책 리스트 뿌려주기
	public List<BookDTO> getBookList(int startRow, int pageSize){
		HashMap map = new HashMap();
		map.put("startRow", startRow-1);
		map.put("pageSize", pageSize);
		List<BookDTO> bookList = sqlsession.selectList("getBookList", map);
		return bookList;
	}
	
	// admBookWrite 입고한 책 insert 해주기
	public int insertBook(BookDTO bookdto){
		int result;
		// book_number 값 증가시켜주기
		int book_number = sqlsession.selectOne("getBookNum");
		book_number+=book_number+1;
		bookdto.setBook_number(book_number);
		result = sqlsession.insert("insertBook", bookdto);
		return result;
	}
	
	// 책 정보 눌렀을 때 상세정보 보여주기
	public List<BookDTO> getBookInfo(int book_number){
		HashMap map = new HashMap();
		map.put("book_number", book_number);
		List<BookDTO> bookList = sqlsession.selectList("getBookInfo", map);
		return bookList;
	}
	
	// 손망실 책 insert 해주기
	public int insertDBook(BookDTO bookdto){
		int result;
		result=sqlsession.insert("insertDBook", bookdto);
		return result;
	}
	
	// 손망실 책 전체 리스트 개수 구하기
	public int getDBookCount(){
		int count;
		count = sqlsession.selectOne("getDBookCount");
		return count;
	}
	
	// 손망실 리스트
	public List<BookDTO> getDBookList(int startRow, int pageSize){
		HashMap map = new HashMap();
		map.put("startRow", startRow-1);
		map.put("pageSize", pageSize);
		List<BookDTO> dbookList = sqlsession.selectList("getDBookList", map);
		return dbookList;
	}
	
}