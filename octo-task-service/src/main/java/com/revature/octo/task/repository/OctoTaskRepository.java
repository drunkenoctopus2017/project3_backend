package com.revature.octo.task.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.task.model.OctoTask;

@Repository
public interface OctoTaskRepository extends CrudRepository<OctoTask, Integer> {

}
