package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTrackedData;
import seedu.address.model.ReadOnlyTrackedDataList;
import seedu.address.model.ReadOnlyWorkoutBook;
import seedu.address.model.TrackedData;
import seedu.address.model.TrackedDataList;
import seedu.address.model.WorkoutBook;
import seedu.address.model.tag.Tag;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;
import seedu.address.model.workout.Equipment;
import seedu.address.model.workout.Instruction;
import seedu.address.model.workout.Muscle;
import seedu.address.model.workout.Name;
import seedu.address.model.workout.Remark;
import seedu.address.model.workout.Type;
import seedu.address.model.workout.Workout;

/**
 * Contains utility methods for populating {@code WorkoutBook} with sample data.
 */
public class SampleDataUtil {
    public static Workout[] getSampleWorkouts() {
        return new Workout[] {
            new Workout(new Name("alex yeoh's workout"), new Type("strength"), new Duration("20m"),
                    new Difficulty("beginner"), new Equipment("dumbbell"), new Muscle("tricep"),
                    new Calories("150"), new Instruction("set1: tricep extension reps: 4-6"),
                    getTagSet("heavy", "morning"),
                    new Remark("This is a great upper body workout, targeting the muscles in arms,"
                            + " the shoulders, the center and your back.")
            )
        };
    }

    public static ReadOnlyWorkoutBook getSampleWorkoutBook() {
        WorkoutBook sampleWb = new WorkoutBook();
        for (Workout sampleWorkout : getSampleWorkouts()) {
            sampleWb.addWorkout(sampleWorkout);
        }
        return sampleWb;
    }

    public static ReadOnlyTrackedDataList getEmptyTrackedDataList() {
        TrackedDataList emptyTrackedDataList = new TrackedDataList();
        return emptyTrackedDataList;
    }

    public static ReadOnlyTrackedData getEmptyTrackedData() {
        TrackedData emptyTrackedData = new TrackedData();
        return emptyTrackedData;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
