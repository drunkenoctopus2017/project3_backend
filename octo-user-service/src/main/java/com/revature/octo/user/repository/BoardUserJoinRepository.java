package com.revature.octo.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.octo.user.model.BoardUserJoin;
import com.revature.octo.user.model.SystemUser;

@Repository
public interface BoardUserJoinRepository extends CrudRepository<BoardUserJoin, Integer> {
	@Query("select b.boardId from BoardUserJoin b where b.systemUser = :user")
    List<Object> getScrumBoardIdsByUser(@Param("user") SystemUser systemUser);
	
	@Query("select b.systemUser from BoardUserJoin b where b.boardId = :id")
    List<SystemUser> getSystemUsersByBoardId(@Param("id") int boardId);
	
	@Query("select b from BoardUserJoin b where b.boardId = :id")
    List<BoardUserJoin> getEntriesByBoardId(@Param("id") int boardId);
}
