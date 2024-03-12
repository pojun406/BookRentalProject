package com.office.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;
import com.office.library.book.RentalBookVo;

@Service
public class BookService {
	
	final static public int BOOK_ISBN_ALREADY_EXIST = 0;
	final static public int BOOK_REGISTER_SUCCESS = 1;
	final static public int BOOK_REGISTER_FAIL = -1;
	
	@Autowired
	BookDao bookdao;

	public int registerBookConfirm(BookVo bookVo) {
		boolean isISBN = bookdao.isISBN(bookVo.getB_isbn());
		
		if(!isISBN) {
			int result = bookdao.insertBook(bookVo);
			
			if(result > 0)
				return BOOK_REGISTER_SUCCESS;
			else
				return BOOK_REGISTER_FAIL;
		} else {
			return BOOK_ISBN_ALREADY_EXIST;
		}
	}

	public List<BookVo> searchBookConfirm(BookVo bookVo) {
		// TODO Auto-generated method stub
		return bookdao.selectBooksBySearch(bookVo);
	}

	public BookVo bookDetail(int b_no) {
		// TODO Auto-generated method stub
		return bookdao.selectBook(b_no);
	}

	public BookVo modifyBookForm(int b_no) {
		// TODO Auto-generated method stub
		return bookdao.selectBook(b_no);
	}

	public int modifyBookConfirm(BookVo bookVo) {
		// TODO Auto-generated method stub
		return bookdao.updateBook(bookVo);
	}

	public int deleteBookConfirm(int b_no) {
		// TODO Auto-generated method stub
		return bookdao.deleteBook(b_no);
	}

	public List<RentalBookVo> getRentalBooks() {
		// TODO Auto-generated method stub
		return bookdao.selectRentalBooks();
	}

	public int returnBookConfirm(int b_no, int rb_no) {
		int result = bookdao.updateRentalBook(rb_no);
		if(result > 0)
			result = bookdao.updateBook(b_no);
		return result;
	}

	public List<RentalBookVo> listupRentalBookHistory(int u_m_no) {
		// TODO Auto-generated method stub
		return bookdao.selectRentalBookHistory(u_m_no);
	}

}
