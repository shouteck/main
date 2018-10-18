package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;
import seedu.address.model.workout.UniqueWorkoutList;
import seedu.address.model.workout.Workout;

/**
 * Wraps all data at the workout-book level, for each tracked parameter
 * Duplicates are not allowed (by .isSameWorkout comparison)
 */
public class TrackedData implements ReadOnlyTrackedData {

    private final UniqueWorkoutList workouts;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        workouts = new UniqueWorkoutList();
    }

    public TrackedData() {}

    /**
     * Creates an WorkoutBook using the Workouts in the {@code toBeCopied}
     */
    public TrackedData(ReadOnlyTrackedData toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Workout list with {@code workouts}.
     * {@code workouts} must not contain duplicate workouts.
     */
    public void setWorkouts(List<Workout> workouts) {
        this.workouts.setWorkouts(workouts);
    }

    public List<Workout> getFilteredInternalList (Difficulty difficulty) {
        return workouts.getFilteredInternalList(difficulty);
    }

    public List<Workout> getFilteredInternalList (Duration duration) {
        return workouts.getFilteredInternalList(duration);
    }

    public List<Workout> getFilteredInternalList (Calories calories) {
        return workouts.getFilteredInternalList(calories);
    }


    /**
     * Resets the existing data of this {@code TrackedData} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackedData newData) {
        requireNonNull(newData);

        setWorkouts(newData.getTrackedData());
    }

    //// workout-level operations

    /**
     * Returns true if a workout with the same identity as {@code workout} exists in the tracked data.
     */
    public boolean hasWorkout(Workout workout) {
        requireNonNull(workout);
        return workouts.contains(workout);
    }

    /**
     * Adds a workout to the tracked data.
     * The workout must not already exist in the tracked data.
     */
    public void addWorkout(Workout w) {
        workouts.add(w);
    }

    public void sortFilteredWorkoutList() {
        workouts.sort();
    }

    /**
     * Removes {@code key} from this {@code WorkoutBook}.
     * {@code key} must exist in the workout book.
     */
    public void removeWorkout(Workout key) {
        workouts.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return workouts.asUnmodifiableObservableList().size() + " workouts";
        // TODO: refine later
    }

    @Override
    public ObservableList<Workout> getTrackedData() {
        return workouts.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrackedData // instanceof handles nulls
                && workouts.equals(((TrackedData) other).workouts));
    }

    @Override
    public int hashCode() {
        return workouts.hashCode();
    }
}
