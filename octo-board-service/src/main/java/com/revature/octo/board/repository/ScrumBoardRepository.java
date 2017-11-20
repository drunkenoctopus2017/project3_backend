package com.revature.octo.board.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.board.model.ScrumBoard;

@Repository
public interface ScrumBoardRepository extends CrudRepository<ScrumBoard, Integer>{

}
