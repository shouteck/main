package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTrackedData;
import seedu.address.model.ReadOnlyTrackedDataList;
import seedu.address.model.ReadOnlyWorkoutBook;
import seedu.address.model.RecommendArguments;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;
import seedu.address.model.workout.Parameter;
import seedu.address.model.workout.Workout;
import seedu.address.testutil.WorkoutBuilder;

public class RecommendCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    private ModelStub modelStub = new ModelStub();

    @Before
    public void setUpModelStub() {
        Workout validWorkout = new WorkoutBuilder().build();
        modelStub.addWorkout(validWorkout);
    }

    @Test
    public void constructor_nullRecommendArguments_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new RecommendCommand(null);
    }

    @Test
    public void execute_acceptedByModel_recommendSuccessful() throws Exception {
        // Valid Calories
        Optional<Calories> calories = Optional.of(new Calories("1"));
        RecommendArguments recommendArguments = new RecommendArguments.Builder().withCalories(calories,
                Optional.of(false)).build();
        CommandResult commandResult = new RecommendCommand(recommendArguments).execute(modelStub, commandHistory);

        assertEquals(RecommendCommand.MESSAGE_SUCCESS, commandResult.feedbackToUser);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_workoutNotFound_throwsCommandException() throws Exception {
        // Valid Difficulty
        Optional<Difficulty> difficulty = Optional.of(new Difficulty("beginner"));
        RecommendArguments recommendArguments = new RecommendArguments.Builder().withDifficulty(difficulty,
                Optional.of(false)).build();
        RecommendCommand recommendCommand = new RecommendCommand(recommendArguments);

        thrown.expect(CommandException.class);
        thrown.expectMessage(recommendCommand.MESSAGE_NO_SUCH_WORKOUT);
        recommendCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        // Valid Duration
        Optional<Duration> duration = Optional.of(new Duration("1m"));
        RecommendArguments recommendArgumentsDuration = new RecommendArguments.Builder().withDuration(duration,
                Optional.of(false)).build();
        // Valid Optional Calories, Difficulty and Duration
        Optional<Calories> calories = Optional.of(new Calories("1000"));
        Optional<Difficulty> difficulty = Optional.of(new Difficulty("intermediate"));
        duration = Optional.of(new Duration("1000m"));
        RecommendArguments recommendArgumentsCalories = new RecommendArguments.Builder().withCalories(calories,
                Optional.of(true)).withDifficulty(difficulty, Optional.of(true)).withDuration(duration,
                Optional.of(true)).build();
        RecommendCommand recommendCommandArgumentsDuration = new RecommendCommand(recommendArgumentsDuration);
        RecommendCommand recommendCommandArgumentsCalories = new RecommendCommand(recommendArgumentsCalories);

        // Same object -> returns true
        assertTrue(recommendCommandArgumentsDuration.equals(recommendCommandArgumentsDuration));

        // Same values -> return true
        RecommendCommand recommend_recommendArgumentsDurationCopy = new RecommendCommand(recommendArgumentsDuration);
        assertTrue(recommendCommandArgumentsDuration.equals(recommend_recommendArgumentsDurationCopy));

        // Different types -> return false
        assertFalse(recommendCommandArgumentsDuration.equals(1));

        // Null -> returns false
        assertFalse(recommendCommandArgumentsDuration.equals(null));

        // Different recommendArguments -> returns false
        assertFalse(recommendCommandArgumentsDuration.equals(recommendCommandArgumentsCalories));
    }

    /**
     * A model stub.
     */
    private class ModelStub implements Model {
        final ArrayList<Workout> workoutsAdded = new ArrayList<>();

        @Override
        public void addWorkout(Workout workout) {
            requireNonNull(workout);
            workoutsAdded.add(workout);
        }

        @Override
        public void resetData(ReadOnlyWorkoutBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyWorkoutBook getWorkoutBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTrackedDataList getTrackedDataList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTrackedData getTrackedData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasWorkout(Workout workout) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteWorkout(Workout target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateWorkout(Workout target, Workout editedWorkout) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Workout> getFilteredWorkoutList() {
            return FXCollections.observableArrayList(workoutsAdded);
        }

        @Override
        public List<Workout> getFinalFilteredInternalList(RecommendArguments recommendArguments) {
            if (!recommendArguments.isCaloriesNull() && recommendArguments.getCalories().toString() == "1") {
                return getFilteredWorkoutList();
            } else {
                return Collections.emptyList();
            }
        }

        @Override
        public void updateFilteredWorkoutList(Predicate<Workout> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Parameter> getFilteredTrackedDataList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTrackedDataList(Predicate<Parameter> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Workout> getFilteredTrackedData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTrackedData(Predicate<Workout> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean canUndoModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoWorkoutBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoWorkoutBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoWorkoutBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoWorkoutBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoTrackedDataList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoTrackedDataList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoTrackedDataList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoTrackedDataList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoTrackedData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoTrackedData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoTrackedData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoTrackedData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitWorkoutBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitTrackedDataList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitTrackedData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredWorkoutList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDataToTrack(Parameter parameter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeDataFromTrack(Parameter parameter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkDataForTrack(Workout workout) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasParameter(Parameter parameter) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
