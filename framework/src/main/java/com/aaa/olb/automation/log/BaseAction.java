package com.aaa.olb.automation.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseAction {

	protected Object target;

	protected String targetName;

	protected String action;

	protected LocalDateTime startTime;

	protected LocalDateTime endTime;

	protected boolean completed;

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		LocalDateTime startTime = this.startTime;
		LocalDateTime endTime = this.endTime != null ? this.endTime : LocalDateTime.now();

		if (this.completed) {
			if (startTime != null) {
				return String.format("%s started to %s at %s and completed at %s", this.targetName, this.action,
						startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			} else {
				return String.format("%s end to %s at %s", this.targetName, this.action,
						endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		} else {
			if (startTime == null) {
				startTime = LocalDateTime.now();
			}
			return String.format("%s %s at %s", this.targetName, this.action,
					startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}

	}
}
