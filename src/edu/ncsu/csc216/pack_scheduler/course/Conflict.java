package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Check conflict activities
 * @author Tianxin Jia
 *
 */
public interface Conflict {

    /**
     * Checking if 2 activities conflict each other.
     * @param possibleConflictingActivity the possible conflicting activity
     * @throws ConflictException when 2 activities conflict
     */
    void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
