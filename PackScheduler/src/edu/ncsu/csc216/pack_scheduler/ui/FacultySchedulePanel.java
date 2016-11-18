package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
//import edu.ncsu.csc216.pack_scheduler.ui.StudentRegistrationPanel.CourseTableModel;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

public class FacultySchedulePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tableSchedule;
	private JTable tableRoll;
//	private CourseTableModel scheduleTableModel;
//	private StudentTableModel rollTableModel;
	/** Student's Schedule text field */
	private JTextField txtScheduleTitle;
	/** JTable for displaying the catalog of Courses */
	private JTable tableCatalog;
	/** Border for Schedule */
	private TitledBorder borderSchedule;
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
	/** Current user */
	private Faculty currentUser;
	/** Course catalog */
	private CourseCatalog catalog;
	private CourseTableModel scheduleTableModel;



	public FacultySchedulePanel(){
		super(new GridBagLayout());
		
		RegistrationManager manager = RegistrationManager.getInstance();
		currentUser = (Faculty)manager.getCurrentUser();
		catalog = manager.getCourseCatalog();
		
		pnlCourseDetails = new JPanel();
		pnlCourseDetails.setLayout(new GridLayout(3, 1));
		lblNameTitle = new JLabel("Name: ");
		pnlCourseDetails.add(lblNameTitle);
		lblSectionTitle = new JLabel("Section: ");
		pnlCourseDetails.add(lblSectionTitle);
		lblTitleTitle = new JLabel("Title: ");
		pnlCourseDetails.add(lblTitleTitle);
		lblInstructorTitle = new JLabel("Instructor: ");
		pnlCourseDetails.add(lblInstructorTitle);
		lblCreditsTitle = new JLabel("Credits: ");
		pnlCourseDetails.add(lblCreditsTitle);
		lblMeetingTitle = new JLabel("Meeting: ");
		pnlCourseDetails.add(lblMeetingTitle);
		lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
		pnlCourseDetails.add(lblEnrollmentCapTitle);
		lblOpenSeatsTitle = new JLabel("Open Seats: ");
		pnlCourseDetails.add(lblOpenSeatsTitle);
		lblWaitlistTitle = new JLabel("Waitlist: ");
		pnlCourseDetails.add(lblWaitlistTitle);
		
		// Set up Schedule and Course roll tables
		scheduleTableModel = new CourseTableModel(true);
		CourseRollTableModel rollTableModel = new CourseRollTableModel();
		tableCatalog = new JTable(scheduleTableModel);
		tableRoll = new JTable(rollTableModel);
		tableCatalog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCatalog.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableCatalog.setFillsViewportHeight(true);
		tableRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableRoll.setFillsViewportHeight(true);
		
		scrollSchedule = new JScrollPane(tableCatalog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder borderFacultySchedule = BorderFactory.createTitledBorder(border, "Faculty Schedule");
		scrollSchedule.setBorder(borderFacultySchedule);
		scrollSchedule.setPreferredSize(new Dimension(100, 100));
	
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");
		pnlCourseDetails.setPreferredSize(new Dimension(200, 200));
		
		// Set up Course Roll table
		scrollRoll = new JScrollPane(tableRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TitledBorder borderCourseRoll = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");
		scrollRoll.setBorder(borderCourseRoll);
		scrollRoll.setPreferredSize(new Dimension(100,100));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollSchedule, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCourseDetails, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollRoll, c);
	}

		
	/**
	 * {@link CourseTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of {@link Course}s to the user.
	 * @author Sarah Heckman
	 */
	private class CourseTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Days", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		/** Boolean flag if the model applies to the catalog or schedule */
		private boolean isCatalog;
		
		/**
		 * Constructs the {@link CourseTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseTableModel(boolean isCatalog) {
			this.isCatalog = isCatalog;
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Course} information from the {@link WolfScheduler}.
		 */
		private void updateData() {
			
			currentUser = (Faculty)RegistrationManager.getInstance().getCurrentUser();
			if (currentUser != null) {
				schedule = currentUser.getSchedule();
				data = schedule.getScheduledCourses();
					
				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();
			}
		}
	}
	
	private class CourseRollTableModel extends AbstractTableModel {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
		/** Data stored in the table */
		private Object [][] data;
		/** Boolean flag if the model applies to the catalog or schedule */
		
		
		/**
		 * Constructs the {@link CourseTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseRollTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Course} information from the {@link WolfScheduler}.
		 */
		private void updateData() {
			
			currentUser = (Faculty)RegistrationManager.getInstance().getCurrentUser();
			if (currentUser != null) {
				schedule = currentUser.getSchedule();
				
				data = schedule.getScheduledCourses();
					
				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();
			}
		}
		
	}
}

