package com.innovazions.jbm.view;

import java.text.ParseException;
import java.util.Date;

import com.innovazions.jbm.common.CommonUtils;

public class EventView {

	public static final String[] EVENT_COLOR_ARRAY = new String[] { "RED",
			"GREEN", "BLUE", "ORANGE" };
	private Long id;

	public EventView(Long id, String title, String start, boolean allDay,
			String tooltip) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.allDay = allDay;
		this.tooltip = tooltip;
	}

	public EventView(Long id, String title, String start, String end,
			boolean allDay, String tooltip, String eventDescription) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		this.tooltip = tooltip;
		this.eventDescription = eventDescription;
	}

	private String title;

	private String start;

	private String end;

	private boolean allDay;

	private String tooltip;

	private String eventDescription;

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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
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

	/*
	 * public String getColor() { //return EVENT_COLOR_ARRAY[id.intValue()];
	 * return "RED"; }
	 * 
	 * public String getBackgroundColor(){ return "BLUE"; }
	 * 
	 * public String getTextColor() { return "BLUE"; }
	 */
	public String getClassName() {

		// eventColorActive
		try {
			if (!CommonUtils.isEmpty(start) && !CommonUtils.isEmpty(end)) {
				Date startDate = CommonUtils.getDate(start);
				Date endDate = CommonUtils.getDate(end);
				if (CommonUtils.isCurrentTimeSlot(startDate, endDate)) {
					return "eventColorActive";
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int index = id.intValue();
		if (index > 7) {
			index = (index % 7) + 1;
		}
		return "eventColor" + index;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
}