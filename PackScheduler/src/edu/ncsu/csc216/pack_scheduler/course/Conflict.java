/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * interface for activity
 * @author Xin Rao
 *
 */
public interface Conflict {
	
	/**
	 *  define checkConflict method, handle to Activity to implement
	 * @param possibleConflictingActivity Activity that might have time conflict
	 * @throws ConflictException time conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}
