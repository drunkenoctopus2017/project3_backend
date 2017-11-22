package com.revature.octo.board.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.board.model.ScrumBoard;

@Repository
public interface ScrumBoardRepository extends CrudRepository<ScrumBoard, Integer> {
	List<ScrumBoard> findByIdIn(ArrayList<Integer> boardList);
}