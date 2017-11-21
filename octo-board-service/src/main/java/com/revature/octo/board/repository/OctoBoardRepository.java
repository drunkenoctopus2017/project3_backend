package com.revature.octo.board.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.board.model.OctoBoard;

@Repository
public interface OctoBoardRepository extends CrudRepository<OctoBoard, Integer> {

}
