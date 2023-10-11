package com.library.project.controller;
 import com.project.Member.entity.Member;
 import org.springframework.security.core.Authentication;
import com.project.Member.repository.MemberRepository;
import com.project.Member.controller.MemberController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.entity.Book;
import com.library.project.exception.ResourceNotFoundException;
import com.library.project.pojo.AuthRequest;
import com.library.project.repository.BookRepository;
import com.library.project.service.BookService;
import com.library.project.service.JwtService;
import com.library.project.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/librarian")
public class BookController {

	@Autowired
	ProjectService projectService;
	
	@GetMapping("/getmembers")
	public List<Member> getMembers() {
	    List<Member>member=projectService.retrieveData();
	    return member;  
	}
	
	@GetMapping("/getname/{id}")
	public String getMemberName(@PathVariable("id")int id) {
		String name=projectService.getMemberName(id);
		return name;
	}
	
	@PutMapping("{userId}/borrowbook/{id}")
	@PreAuthorize("hasAuthority('user')")
	public Book Borrow(@RequestBody Book book, @PathVariable("id") Integer bookId,@PathVariable("userId")int id) {
	    Optional<Book> optionalExistingBook = this.bookRepository.findById(bookId);
	        Book existingBook = optionalExistingBook.get();
	        existingBook.setMemberName(getMemberName(id));
	        existingBook.setStatus("unavaliable");
	        existingBook.setEntryDate(book.getEntryDate());
	        existingBook.setDueDate(book.getDueDate());
	        //date-service
	       bookservice.updateDate(existingBook);
	        return this.bookRepository.save(existingBook);
	}
	   

	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('user')")
	public List<Book> getAllBooks(){
		return this.bookRepository.findAll();
	}
	
	
	 @Autowired
	 BookService bookservice;
	 
	@PostMapping("/newbook")
	@PreAuthorize("hasAuthority('admin')")
	public @Valid Book createBook(@RequestBody @Valid Book book) {
		
		return this.bookRepository.save(book);
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public Optional<Book> getBookById(@PathVariable (value="id")Integer bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if(book.isPresent()) {
			return this.bookRepository.findById(bookId);
		}else {
			throw new ResourceNotFoundException("Book with Id "+bookId+" Not found");
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public Optional<Book> deleteBook(@PathVariable("id")Integer Id){
		Optional<Book>book = bookRepository.findById(Id);
		if(book.isPresent()) {
			 this.bookRepository.deleteById(Id);
		}else {
			throw new ResourceNotFoundException("Book Id "+ Id+" is Not Found");
		}
		return book;
		
		}
	
	@PutMapping("/updatebook/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public Book updateBook(@RequestBody Book book, @PathVariable("id") Integer bookId) {
	    Optional<Book> optionalExistingBook = this.bookRepository.findById(bookId);
	    if (optionalExistingBook.isPresent()) {
	    	
	        Book existingBook = optionalExistingBook.get();
	        existingBook.setBookName(book.getBookName());
	        existingBook.setAuthorName(book.getAuthorName());
	        existingBook.setStatus(book.getStatus());
	        return this.bookRepository.save(existingBook);
	    }else {
			throw new ResourceNotFoundException("Book with Id "+bookId+" Not found");
		}
		
	}
	
	@PutMapping("/returnbook/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public Book returnBook(@RequestBody Book book, @PathVariable("id") Integer bookId) {
	    Optional<Book> optionalExistingBook = this.bookRepository.findById(bookId);
	    if (optionalExistingBook.isPresent()) {
	    	
	        Book existingBook = optionalExistingBook.get();
	        existingBook.setStatus("avaliable");
	        existingBook.setEntryDate(book.getEntryDate());
	        existingBook.setDueDate(book.getDueDate());
	        existingBook.setMemberName(book.getMemberName());
	        
	        return this.bookRepository.save(existingBook);
	    }else {
			throw new ResourceNotFoundException("Book with Id "+bookId+" Not found");
		}
	}
	    
	    @Autowired
	    private JwtService jwtService;
	    
	    @Autowired
	    private AuthenticationManager authenticationManager;

	       @PostMapping("/auth")
	       public String generateToken(@RequestBody AuthRequest authRequest ) {
	    	   Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
	    	   if(authentication.isAuthenticated()) {
	    	   return jwtService.generateToken(authRequest.getUsername());
	    	   }else {
	    		   throw new UsernameNotFoundException("Invalid User");
	    	   }
	       }	
	
	//@GetMapping("/getmembers")
		//public MemberController getAlMember() {
//			MemberController control= new MemberController();
//			control.getAllMembers();
//			return control;
		//}

		//@GetMapping("/members")
		//public List<Member>getMembers(){
//			MemberRepository repository = new MemberRepository();
//			 return repository.findAll();
		//}

		//@GetMapping("/members")
		//public List<Member>getAllMember(){
//			MemberController memberController = new MemberController();
//			return memberController.getAllMembers();
		//}
		//
//		    @GetMapping("/getmember")
//		    public Member getMember() {
//		    	Member member = new Member();
//		    	member.getId();
//		    	member.getMemberName();
//		    	member.getPhoneNumber();
//		    	return member;	
//		    }


}
