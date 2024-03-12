package com.office.library.book.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.book.BookVo;
import com.office.library.book.RentalBookVo;
import com.office.library.book.admin.util.UploadFileService;
import com.office.library.user.member.UserMemberVo;

@Controller
@RequestMapping("/book/admin")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	@GetMapping("/registerBookFrom")
	public String registerBookForm() {
		System.out.println("[bookController] registerBookForm()");
		
		String nextPage = "admin/book/register_book_form";
		
		return nextPage;
	}
	
	@PostMapping("/registerBookonfirm")
	public String registerBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[bookController] registerBookConfirm()");
		
		String nextPage = "admin/book/register_book_ok";
		
		String savedFileName = uploadFileService.upload(file);
		
		if(savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookVo);
			
			if(result <= 0)
				nextPage = "admin/book/register_book_ng";
			else
				nextPage = "admin/book/register_book_ng";
		}
		return nextPage;
	}
	
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo, Model model) {
		System.out.println("[bookController] searchBookConfirm()");
		
		String nextPage = "admin/book/search_book";
		
		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
		
		model.addAttribute("bookVos", bookVos);
		
		return nextPage;
	}
	
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no, Model model) {
		System.out.println("[bookController] bookDetail()");
		
		String nextPage = "admin/book/book_detail";
		
		BookVo bookVo = bookService.bookDetail(b_no);
		
		model.addAttribute("bookVo", bookVo);
		
		return nextPage;
		
	}
	
	@GetMapping("/modifyBookForm")
	public String modifyBookForm(@RequestParam("b_no") int b_no, Model model) {
		System.out.println("[bookController] bookDeatil()");
		
		String nextPage = "admin/book/modify_book_form";
		
		BookVo bookVo = bookService.modifyBookForm(b_no);
		
		model.addAttribute("bookVo", bookVo);
		
		return nextPage;
	}
	
	@PostMapping("/modifyBookConfirm")
	public String modifyBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[bookController] modifyBookConfirm()");
		
		String nextPage = "admin/book/modify_book_ok";
		
		if(!file.getOriginalFilename().equals("")) {
			String savedFileName = uploadFileService.upload(file);
			if (savedFileName != null)
				bookVo.setB_thumbnail(savedFileName);
		}
		
		int result = bookService.modifyBookConfirm(bookVo);
		
		if(result <= 0)
			nextPage = "admin/book/modify_book_ng";
		
		return nextPage;
	}
	
	@GetMapping("/deleteBookConfirm")
	public String deleteBookConfirm(@RequestParam("b_no") int b_no) {
		System.out.println("[bookController] deleteBookConfirm()");
		
		String nextPage = "admin/book/delete_book_ok";
		
		int result = bookService.deleteBookConfirm(b_no);
		
		if(result <= 0)
			nextPage = "admin/book/delete_book_ng";
		
		return nextPage;			
	}
	
	@GetMapping("/getRentalBooks")
	public String getRentalBooks(Model model) {
		System.out.println("[bookController] getRentalBooks()");
		
		String nextPage = "admin/book/rental_books";
		
		List<RentalBookVo> rentalBookVos = bookService.getRentalBooks();
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
	}
	
	@GetMapping("/returnBookConfirm")
	public String returnBookConfirm(@RequestParam("b_no") int b_no, @RequestParam("rb_no") int rb_no) {
		System.out.println("[bookController] returnBookConfirm()");
		
		String nextPage = "admin/book/return_book_ok";
		
		int result = bookService.returnBookConfirm(b_no, rb_no);
		
		if(result <= 0)
			nextPage = "admin/book/return_book_ng";
		
		return nextPage;
	}
	
	@GetMapping("/listupRentalBookHistory")
	public String listupRentalBookHistory(HttpSession session, Model model) {
		System.out.println("[bookController] listupRentalBookHistory()");
		
		String nextPage = "user/book/rental_book_history";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo)session.getAttribute("loginedUserMemberVo");
		
		List<RentalBookVo> rentalBookVos = bookService.listupRentalBookHistory(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
	}
}
