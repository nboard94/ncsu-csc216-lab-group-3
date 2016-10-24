package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Activity is superclass of Event and Course
 * 
 * @author Xin Rao
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * constructor of Activity
	 * 
	 * @param title
	 *            title
	 * @param meetingDays
	 *            meeting days
	 * @param startTime
	 *            start time
	 * @param endTime
	 *            end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * get class's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set class's title if the title is null, or empty String an
	 * IllegalArgumentException is throw
	 * 
	 * @param title
	 *            the title to set
	 * @throws IllegalArgumentException
	 *             if title is null, or empty String
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Invalid title");
		}
		if (title.length() == 0) {
			throw new IllegalArgumentException("Invalid title");
		}
		this.title = title;
	}

	/**
	 * get class's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * set class's meeting days meeting days should only consist of
	 * 'M',''T','W','H','F', or 'A' if 'A' is in the meeting days list it must
	 * be the only character
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 * @throws IllegalArgumentException
	 *             if meeting days is null, empty String, having characters
	 *             other than 'M',''T','W','H','F', or 'A' or having 'A' and
	 *             other characters at same time
	 */
	public void setMeetingDays(String meetingDays) {
//		if (meetingDays == null || meetingDays.length() == 0) {
//			throw new IllegalArgumentException();
//		}
//		if (!meetingDays.matches("[MTWHFA]+")) {
//			throw new IllegalArgumentException();
//		}
//		for (int i = 0; i < meetingDays.length(); i++) {
//			if (meetingDays.charAt(i) == 'A' && meetingDays.length() != 1) {
//				throw new IllegalArgumentException();
//			}
//		}
		this.meetingDays = meetingDays;
	}

	/**
	 * get class's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * get class's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * set the start time and end time for the Course both start time and end
	 * time should be between 0000 and 2359 end time should be greater than
	 * start time when meeting days is A, both start time and end time should be
	 * 0
	 * 
	 * @param startTime
	 *            course's start time
	 * @param endTime
	 *            course's end time
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < 0 || startTime > 2359 || endTime < 0 || endTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if ((startTime % 100) > 59 || (endTime % 100) > 59) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
//		if (meetingDays.equals("A") && (startTime != 0 && endTime != 0)) {
//			throw new IllegalArgumentException("Invalid meeting times");
//		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * return the meeting time in format of String including the time in
	 * standard format and the days of meeting
	 * 
	 * @return meeting time String
	 */
	public String getMeetingString() {
		if (meetingDays.equals("A")) {
			return "Arranged";
		}
		String temp = meetingDays + " ";
		if (startTime < 1300) {
			if (startTime % 100 < 10) {
				temp = temp + (startTime / 100) + ":" + "0" + (startTime % 100);
			} else {
				temp = temp + (startTime / 100) + ":" + (startTime % 100);
			}
		} else {
			if (startTime % 100 < 10) {
				temp = temp + (startTime / 100 - 12) + ":" + "0" + (startTime % 100);
			} else {
				temp = temp + (startTime / 100 - 12) + ":" + (startTime % 100);
			}
		}
		if (startTime >= 1200) {
			temp = temp + "PM-";
		} else {
			temp = temp + "AM-";
		}
		if (endTime < 1300) {
			if (endTime % 100 == 0) {
				temp = temp + (endTime / 100) + ":" + (endTime % 100) + "0";
			} else {
				temp = temp + (endTime / 100) + ":" + (endTime % 100);
			}
		} else {
			if (endTime % 100 == 0) {
				temp = temp + (endTime / 100 - 12) + ":" + (endTime % 100) + "0";
			} else {
				temp = temp + (endTime / 100 - 12) + ":" + (endTime % 100);
			}
		}
		if (endTime >= 1200) {
			temp = temp + "PM";
		} else {
			temp = temp + "AM";
		}
		return temp;
	}

	/** 
	 * generate hash code
	 */
	@Override
	public abstract int hashCode();

	/** 
	 * decide whether the object is the same
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * show a short String of activity for GUI
	 * @return short version String
	 */
	public abstract String[] getShortDisplayArray();
	/**
	 * show a long String of activity for GUI
	 * @return long version String
	 */
	public abstract String[] getLongDisplayArray();
	/**
	 * compare activity is the same
	 * @param activity activity to compare
	 * @return boolean if is duplicate of given activity
	 */
	public abstract boolean isDuplicate(Activity activity);

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Conflict#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)
	 */
	/**
	 * receive an Activity as parameter, and check if two activities have time conflict
	 * do commutative test 
	 */
	@Override
	public void checkConflict(Activity other) throws ConflictException {
		//check time conflict
		if(conflictTimeAndDay(this.startTime, this.endTime, this.meetingDays, other.getStartTime(), other.getEndTime(), other.getMeetingDays())){
			throw new ConflictException();
		}
		//commutative check
		if(conflictTimeAndDay(other.getStartTime(), other.getEndTime(), other.getMeetingDays(), this.startTime, this.endTime, this.meetingDays)){
			throw new ConflictException();
		}
	}

	private boolean conflictTimeAndDay(int st, int et, String md, int st1, int et1, String md1) {
		if(md.equals("A") || md1.equals("A")){
			return false;
		}
		if(((st >= st1 && st <= et1) || (et >= st1 && et < et1)) && checkMeetingDay(md, md1)){
			return true;
		}
		return false;
	}

	private boolean checkMeetingDay(String md, String md1) {
		for(int i = 0; i < md.length(); i++){
			if(("" + md.charAt(i)).matches("[" + md1 + "]+")){
				return true;
			}
		}
		return false;
	}	
}