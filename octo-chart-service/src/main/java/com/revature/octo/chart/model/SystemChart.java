package com.revature.octo.chart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class SystemChart {
	@Id
	private int id;

}
