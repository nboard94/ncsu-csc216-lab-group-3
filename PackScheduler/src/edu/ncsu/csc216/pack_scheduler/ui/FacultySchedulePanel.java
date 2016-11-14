package edu.ncsu.csc216.pack_scheduler.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

public class FacultySchedulePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tableSchedule;
	private JTable tableRoll;
	//private CourseTableModel scheduleTableModel;
	//private StudentTableModel rollTableModel;
	private Border lowerEtched;
	private JScrollPane scrollSchedule;
	private JScrollPane scrollRoll;
	private JPanel pnlCourseDetails;
	private JLabel lblNameTitle;
	private JLabel lblSectionTitle;
	private JLabel lblTitleTitle;
	private JLabel lblInstructorTitle;
	private JLabel lblCreditsTitle;
	private JLabel lblMeetingTitle;
	private JLabel lblEnrollmentCapTitle;
	private JLabel lblOpenSeatsTitle;
	private JLabel lblWaitlistTitle;
	private JLabel lblName;
	private JLabel lblSection;
	private JLabel lblTitle;
	private JLabel lblInstructor;
	private JLabel lblCredits;
	private JLabel lblMeeting;
	private JLabel lblEnrollmentCap;
	private JLabel lblOpenSeats;
	private JLabel lblWaitlist;
	private FacultySchedule schedule;

	
}
