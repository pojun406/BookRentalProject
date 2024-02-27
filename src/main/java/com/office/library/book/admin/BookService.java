package com.office.library.book.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;

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

}
