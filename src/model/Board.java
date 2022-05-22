package model;

import java.util.Date;

public class Board {
	
	private int id; //pk => 시퀀스를 통해 1부터 자동증가 하도록
	private String name; //작성자 이름
	private String title; //게시글 제목
	private String content; //게시글 내용
	private Date createdTime; //작성 시간
	// 추가
	// 마지막 수정 시간
	// private Date lastModifyedTime
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "Board [id=" + id + ", name=" + name + ", title=" + title + ", content=" + content + ", createdTime="
				+ createdTime + "]";
	}
	
}
