package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
/**
 * GUI Panel for faculty user
 * @author dndereef
 *
 */
public class FacultySchedulePanel extends JPanel {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/** JTable for displaying faculty course schedule */
	private JTable tableSchedule;
	/** JTable for displaying course roll */
	private JTable tableRoll;
	/** Border for Schedule */
	private TitledBorder borderSchedule;
	/** Border for border of TitledBorder*/
	private Border lowerEtched;
	/** Scroll panel for faculty schedule */
	private JScrollPane scrollSchedule;
	/** Scroll panel for course roll */
	private JScrollPane scrollRoll;
	/** JPanel for displaying course details */
	private JPanel pnlCourseDetails;
	/** Label for course details name title */
	private JLabel lblNameTitle;
	/** Label for course details section title */
	private JLabel lblSectionTitle;
	/** Label for course details title title */
	private JLabel lblTitleTitle;
	/** Label for course details instructor title */
	private JLabel lblInstructorTitle;
	/** Label for course details credits title */
	private JLabel lblCreditsTitle;
	/** Label for course details meeting title */
	private JLabel lblMeetingTitle;
	/** Label for course details enrollment cap title */
	private JLabel lblEnrollmentCapTitle;
	/** Label for course details open seats title */
	private JLabel lblOpenSeatsTitle;
	/** Label for course details waitlist title */
	private JLabel lblWaitlistTitle;
	/** Label for course details name */
	private JLabel lblName = new JLabel("");
	/** Label for course details section */
	private JLabel lblSection = new JLabel("");
	/** Label for course details title */
	private JLabel lblTitle = new JLabel("");
	/** Label for course details instructor */
	private JLabel lblInstructor = new JLabel("");
	/** Label for course details credits */
	private JLabel lblCredits = new JLabel("");
	/** Label for course details meeting */
	private JLabel lblMeeting = new JLabel("");
	/** Label for course details enrollment cap */
	private JLabel lblEnrollmentCap = new JLabel("");
	/** Label for course details open seats */
	private JLabel lblOpenSeats = new JLabel("");
	/** Label for course details waitlist */
	private JLabel lblWaitlist = new JLabel("");
	/** Holds faculty schedule */
	private FacultySchedule schedule;
	/** Current user */
	private Faculty currentUser;
	/** List of courses */
	private CourseCatalog catalog;
	/** Roll of students for a course */
	private CourseRoll roll;
	/** Model for course table */
	private CourseTableModel scheduleTableModel;
	/** Model for course roll table */
	private CourseRollTableModel rollTableModel;

	/**
	 * Creates the viewing panel
	 */
	public FacultySchedulePanel(){
		super(new GridBagLayout());
		
		RegistrationManager manager = RegistrationManager.getInstance();
		currentUser = (Faculty)manager.getCurrentUser();
		catalog = manager.getCourseCatalog();
		
		// Set up Schedule and Course roll tables
		scheduleTableModel = new CourseTableModel();
		rollTableModel = new CourseRollTableModel();
		tableSchedule = new JTable(scheduleTableModel);
		tableRoll = new JTable(rollTableModel);
		tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableSchedule.setFillsViewportHeight(true);
		tableSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
				Course c = catalog.getCourseFromCatalog(name, section);
				roll = c.getCourseRoll();
				updateCourseDetails(c);
				rollTableModel.updateData();
			}
		});
		tableRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableRoll.setFillsViewportHeight(true);
		
		scrollSchedule = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		borderSchedule = BorderFactory.createTitledBorder(border, "Faculty Schedule");
		scrollSchedule.setBorder(borderSchedule);
		scrollSchedule.setPreferredSize(new Dimension(100, 100));
	
		// Set up Course Roll table
		scrollRoll = new JScrollPane(tableRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TitledBorder borderCourseRoll = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");
		scrollRoll.setBorder(borderCourseRoll);
		scrollRoll.setPreferredSize(new Dimension(100, 100));
		
		updateTables();
		// Set up Course Details panel
		pnlCourseDetails = new JPanel();
		pnlCourseDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		lblNameTitle = new JLabel("Name: ");
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		lblSectionTitle = new JLabel("Section: ");
		pnlName.add(lblSectionTitle);
		pnlName.add(lblSection);
		
		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		lblTitleTitle = new JLabel("Title: ");
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);
		
		JPanel pnlInstructor = new JPanel(new GridLayout(1, 4));
		lblInstructorTitle = new JLabel("Instructor: ");
		pnlInstructor.add(lblInstructorTitle);
		pnlInstructor.add(lblInstructor);
		lblCreditsTitle = new JLabel("Credits: ");
		pnlInstructor.add(lblCreditsTitle);
		pnlInstructor.add(lblCredits);
		
		JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		lblMeetingTitle = new JLabel("Meeting: ");
		pnlMeeting.add(lblMeetingTitle);
		pnlMeeting.add(lblMeeting);
		
		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 6));
		lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		lblOpenSeatsTitle = new JLabel("Open Seats: ");
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblOpenSeats);
		lblWaitlistTitle = new JLabel("Waitlist: ");
		pnlEnrollment.add(lblWaitlistTitle);
		pnlEnrollment.add(lblWaitlist);
		
		pnlCourseDetails.add(pnlName);
		pnlCourseDetails.add(pnlTitle);
		pnlCourseDetails.add(pnlInstructor);
		pnlCourseDetails.add(pnlMeeting);
		pnlCourseDetails.add(pnlEnrollment);
		
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");
		pnlCourseDetails.setPreferredSize(new Dimension(200, 200));
		
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
	 * Updates tables
	 */
	public void updateTables() {
		scheduleTableModel.updateData();
		rollTableModel.updateData();
	}
	
	private void updateCourseDetails(Course c) {
		if (c != null) {
			lblName.setText(c.getName());
			lblSection.setText(c.getSection());
			lblTitle.setText(c.getTitle());
			lblInstructor.setText(c.getInstructorId());
			lblCredits.setText("" + c.getCredits());
			lblMeeting.setText(c.getMeetingString());
			lblEnrollmentCap.setText("" + c.getCourseRoll().getEnrollmentCap());
			lblOpenSeats.setText("" + c.getCourseRoll().getOpenSeats());
		}
	}
	/**
	 * Table Model for faculty course schedule
	 * @author dndereef
	 */
	private class CourseTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Days", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link CourseTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseTableModel() {
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
	/**
	 * Table Model for course roll
	 * @author dndereef
	 *
	 */
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
			if (roll != null) {
				Object[][] info = new Object[roll.roll.size()][3];
				for(int i = 0; i < roll.roll.size(); i++) {
					info[i][0] = roll.roll.get(i).getFirstName();
					info[i][1] = roll.roll.get(i).getLastName();
					info[i][2] = roll.roll.get(i).getId();
				}
				data = info;
			}
			FacultySchedulePanel.this.repaint();
			FacultySchedulePanel.this.validate();
		}
	}
}

