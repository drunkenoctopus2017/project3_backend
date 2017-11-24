package com.revature.octo.board.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.octo.board.model.ScrumBoard;

@Repository
public interface ScrumBoardRepository extends CrudRepository<ScrumBoard, Integer> {
	
	//@Query( "select o from MyObject o where inventoryId in :ids" )
	//List<MyObject> findByInventoryIds(@Param("ids") List<Long> inventoryIdList);
	
	@Query("select b.id, b.name from ScrumBoard b where b.id in :ids")
    List<Object> getScrumBoardIdAndNameByIds(@Param("ids") List<Integer> ids);
	
	List<ScrumBoard> findByIdIn(List<Integer> boardList);
	
	ScrumBoard findByName(String name);
}