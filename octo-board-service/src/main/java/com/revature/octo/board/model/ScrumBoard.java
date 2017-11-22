package com.revature.octo.board.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCRUM_BOARDS")
public class ScrumBoard {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_SEQ")
	//@SequenceGenerator(sequenceName="user_seq", allocationSize=1, name="USER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
		
}
