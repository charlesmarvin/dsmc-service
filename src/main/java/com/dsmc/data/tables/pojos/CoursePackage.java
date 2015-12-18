/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables.pojos;


import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoursePackage implements Serializable {

	private static final long serialVersionUID = 781236738;

	private final Integer id;
	private final Date    createdOn;
	private final String  active;
	private final String  name;
	private final String  description;
	private final Integer price;
	private final Date    startDate;
	private final Date    endDate;
	private final Integer companyId;

	public CoursePackage(CoursePackage value) {
		this.id = value.id;
		this.createdOn = value.createdOn;
		this.active = value.active;
		this.name = value.name;
		this.description = value.description;
		this.price = value.price;
		this.startDate = value.startDate;
		this.endDate = value.endDate;
		this.companyId = value.companyId;
	}

	public CoursePackage(
		Integer id,
		Date    createdOn,
		String  active,
		String  name,
		String  description,
		Integer price,
		Date    startDate,
		Date    endDate,
		Integer companyId
	) {
		this.id = id;
		this.createdOn = createdOn;
		this.active = active;
		this.name = name;
		this.description = description;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.companyId = companyId;
	}

	public Integer getId() {
		return this.id;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public String getActive() {
		return this.active;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Integer getPrice() {
		return this.price;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CoursePackage (");

		sb.append(id);
		sb.append(", ").append(createdOn);
		sb.append(", ").append(active);
		sb.append(", ").append(name);
		sb.append(", ").append(description);
		sb.append(", ").append(price);
		sb.append(", ").append(startDate);
		sb.append(", ").append(endDate);
		sb.append(", ").append(companyId);

		sb.append(")");
		return sb.toString();
	}
}