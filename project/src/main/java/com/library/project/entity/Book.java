package com.library.project.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="Null values are not Accepted")
	private String bookName;
	private String authorName;
	private String status;
	private String memberName;
	private String entryDate;
	private String dueDate;
	
	public Book() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Book(Integer id, @NotBlank(message = "Null values are not Accepted") String bookName, String authorName,
			String status, String memberName, String entryDate, String dueDate) {
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.status = status;
		this.memberName = memberName;
		this.entryDate = entryDate;
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", authorName=" + authorName + ", status=" + status
				+ ", memberName=" + memberName + ", entryDate=" + entryDate + ", dueDate=" + dueDate + "]";
	}
	
	
	
}
