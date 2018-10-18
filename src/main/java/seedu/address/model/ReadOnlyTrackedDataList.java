package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.workout.Workout;

/**
 * Unmodifiable view of a list of tracked parameters
 */
public interface ReadOnlyTrackedDataList {

    /**
     * Returns an unmodifiable view of a list of tracked parameters.
     * This list will not contain any duplicate workout.
     */
    ObservableList<Workout> getTrackedDataList();

}
