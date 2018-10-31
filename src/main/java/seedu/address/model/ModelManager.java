package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.TrackedDataChangedEvent;
import seedu.address.commons.events.model.TrackedDataListChangedEvent;
import seedu.address.commons.events.model.WorkoutBookChangedEvent;

import seedu.address.model.workout.Parameter;
import seedu.address.model.workout.Workout;

/**
 * Represents the in-memory model of the workout book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedWorkoutBook versionedWorkoutBook;
    private final VersionedTrackedDataList versionedTrackedDataList;
    private final VersionedTrackedData versionedTrackedData;
    private final FilteredList<Workout> filteredWorkouts;
    private final FilteredList<Parameter> filteredParameters;
    private final FilteredList<Workout> filteredTrackedData;

    /**
     * Initializes a ModelManager with the given workoutBook and userPrefs.
     */
    public ModelManager(ReadOnlyWorkoutBook workoutBook, ReadOnlyTrackedDataList trackedDataList,
                        ReadOnlyTrackedData trackedData, UserPrefs userPrefs) {
        super();
        requireAllNonNull(workoutBook, userPrefs);

        logger.fine("Initializing with workout book: " + workoutBook + " and user prefs " + userPrefs);

        versionedWorkoutBook = new VersionedWorkoutBook(workoutBook);
        filteredWorkouts = new FilteredList<>(versionedWorkoutBook.getWorkoutList());
        versionedTrackedDataList = new VersionedTrackedDataList(trackedDataList);
        filteredParameters = new FilteredList<>(versionedTrackedDataList.getTrackedDataList());
        versionedTrackedData = new VersionedTrackedData(trackedData);
        filteredTrackedData = new FilteredList<>(versionedTrackedData.getTrackedData());
    }

    public ModelManager() {
        this(new WorkoutBook(), new TrackedDataList(), new TrackedData(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyWorkoutBook newData) {
        versionedWorkoutBook.resetData(newData);
        indicateWorkoutBookChanged();
    }

    @Override
    public ReadOnlyWorkoutBook getWorkoutBook() {
        return versionedWorkoutBook;
    }

    @Override
    public ReadOnlyTrackedDataList getTrackedDataList() {
        return versionedTrackedDataList;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateWorkoutBookChanged() {
        raise(new WorkoutBookChangedEvent(versionedWorkoutBook));
    }

    private void indicateTrackedDataChanged() {
        raise(new TrackedDataChangedEvent(versionedTrackedData));
    }

    private void indicateTrackedDataListChanged() {
        raise(new TrackedDataListChangedEvent(versionedTrackedDataList));
    }

    @Override
    public boolean hasWorkout(Workout workout) {
        requireNonNull(workout);
        return versionedWorkoutBook.hasWorkout(workout);
    }

    @Override
    public void deleteWorkout(Workout target) {
        versionedWorkoutBook.removeWorkout(target);
        indicateWorkoutBookChanged();
    }

    @Override
    public void addWorkout(Workout workout) {
        versionedWorkoutBook.addWorkout(workout);
        updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);
        indicateWorkoutBookChanged();
    }

    @Override
    public void addDataToTrack(Parameter parameter) {
        versionedTrackedDataList.addParameter(parameter);
        updateFilteredTrackedDataList(PREDICATE_SHOW_ALL_PARAMETERS);
        indicateTrackedDataListChanged();
    }

    @Override
    public void removeDataFromTrack(Parameter parameter) {
        versionedTrackedDataList.removeParameter(parameter);
        indicateTrackedDataListChanged();
    }

    @Override
    public void checkDataForTrack(Workout workout) {
        versionedTrackedData.addWorkout(workout);
        indicateTrackedDataChanged();
    }

    @Override
    public boolean hasParameter(Parameter parameter) {
        requireNonNull(parameter);
        return versionedTrackedDataList.hasParameter(parameter);
    }


    @Override
    public void sortFilteredWorkoutList() {
        versionedWorkoutBook.sortFilteredWorkoutList();
        indicateWorkoutBookChanged();
    }

    @Override
    public List<Workout> getFilteredInternalList(RecommendArguments recommendArguments) {
        return versionedWorkoutBook.getFilteredInternalList(recommendArguments);
    }

    @Override
    public void updateWorkout(Workout target, Workout editedWorkout) {
        requireAllNonNull(target, editedWorkout);

        versionedWorkoutBook.updateWorkout(target, editedWorkout);
        indicateWorkoutBookChanged();
    }

    //=========== Filtered Workout List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Workout} backed by the internal list of
     * {@code versionedWorkoutBook}
     */
    @Override
    public ObservableList<Workout> getFilteredWorkoutList() {
        return FXCollections.unmodifiableObservableList(filteredWorkouts);
    }

    @Override
    public void updateFilteredWorkoutList(Predicate<Workout> predicate) {
        requireNonNull(predicate);
        filteredWorkouts.setPredicate(predicate);
    }

    //=========== Filtered Tracked Data List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Parameter} backed by the internal list of
     * {@code versionedTrackedDataList}
     */
    @Override
    public ObservableList<Parameter> getFilteredTrackedDataList() {
        return FXCollections.unmodifiableObservableList(filteredParameters);
    }

    @Override
    public void updateFilteredTrackedDataList(Predicate<Parameter> predicate) {
        requireNonNull(predicate);
        filteredParameters.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoModel() {
        return canUndoWorkoutBook()
                && canUndoTrackedDataList()
                && canUndoTrackedData();
    }

    @Override
    public boolean canRedoModel() {
        return canRedoWorkoutBook()
                && canRedoTrackedDataList()
                && canRedoTrackedData();
    }

    @Override
    public void undoModel() {
        undoWorkoutBook();
        undoTrackedDataList();
        undoTrackedData();;
    }

    @Override
    public void redoModel() {
        redoWorkoutBook();
        redoTrackedDataList();
        redoTrackedData();
    }

    @Override
    public boolean canUndoWorkoutBook() {
        return versionedWorkoutBook.canUndo();
    }

    @Override
    public boolean canRedoWorkoutBook() {
        return versionedWorkoutBook.canRedo();
    }

    @Override
    public void undoWorkoutBook() {
        versionedWorkoutBook.undo();
        indicateWorkoutBookChanged();
    }

    @Override
    public void redoWorkoutBook() {
        versionedWorkoutBook.redo();
        indicateWorkoutBookChanged();
    }

    @Override
    public boolean canUndoTrackedDataList() {
        return versionedTrackedDataList.canUndo();
    }

    @Override
    public boolean canRedoTrackedDataList() {
        return versionedTrackedDataList.canRedo();
    }

    @Override
    public void undoTrackedDataList() {
        versionedTrackedDataList.undo();
        indicateTrackedDataListChanged();
    }

    @Override
    public void redoTrackedDataList() {
        versionedTrackedDataList.redo();
        indicateTrackedDataListChanged();
    }

    @Override
    public boolean canUndoTrackedData() {
        return versionedTrackedData.canUndo();
    }

    @Override
    public boolean canRedoTrackedData() {
        return versionedTrackedData.canRedo();
    }

    @Override
    public void undoTrackedData() {
        versionedTrackedData.undo();
        indicateTrackedDataChanged();
    }

    @Override
    public void redoTrackedData() {
        versionedTrackedData.redo();
        indicateTrackedDataChanged();
    }

    @Override
    public void commitModel() {
        commitWorkoutBook();
        commitTrackedDataList();
        commitTrackedData();
    }

    @Override
    public void commitWorkoutBook() {
        versionedWorkoutBook.commit();
    }

    @Override
    public void commitTrackedDataList() {
        versionedTrackedDataList.commit();
    }

    @Override
    public void commitTrackedData() {
        versionedTrackedData.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedWorkoutBook.equals(other.versionedWorkoutBook)
                && filteredWorkouts.equals(other.filteredWorkouts);
    }

}
