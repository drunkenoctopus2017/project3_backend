package com.revature.octo.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.user.model.BoardUserJoin;

@Repository
public interface BoardUserJoinRepository extends CrudRepository<BoardUserJoin, Integer> {
}
