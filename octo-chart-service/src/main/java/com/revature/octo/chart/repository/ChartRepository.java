package com.revature.octo.chart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.chart.model.SystemChart;

@Repository
public interface ChartRepository extends CrudRepository<SystemChart, Integer>{

}
