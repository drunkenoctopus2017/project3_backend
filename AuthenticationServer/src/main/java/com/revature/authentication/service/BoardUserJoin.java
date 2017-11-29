package com.revature.authentication.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BOARD_USER_JOIN")
public class BoardUserJoin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int boardId;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private SystemUser systemUser;
	
	public BoardUserJoin() {}
	
	public BoardUserJoin(int boardId, SystemUser systemUser) {
		super();
		this.boardId = boardId;
		this.systemUser = systemUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public BoardUserJoin(int id, int boardId, SystemUser systemUser) {
		super();
		this.id = id;
		this.boardId = boardId;
		this.systemUser = systemUser;
	}

	@Override
	public String toString() {
		return "BoardUserJoin [id=" + id + ", boardId=" + boardId + ", systemUser=" + systemUser.getUsername() + "]";
	}
	
}
