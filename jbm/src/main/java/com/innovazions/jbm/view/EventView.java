package com.innovazions.jbm.view;

import java.util.Date;

public class EventView {

	private Long id;

	public EventView(Long id, String title, Date start) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.allDay = true;
		this.tooltip = "Test Tooltip";
	}

	private String title;

	private Date start;

	private Date end;

	private boolean allDay;

	private String tooltip;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
}