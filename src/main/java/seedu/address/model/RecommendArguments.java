package seedu.address.model;

import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;

public class RecommendArguments {

    private final Calories calories;
    private final Difficulty difficulty;
    private final Duration duration;

    public RecommendArguments(Calories calories, Difficulty difficulty, Duration duration) {
        this.calories = calories;
        this.difficulty = difficulty;
        this.duration = duration;
    }

    public Calories getCalories() {
        return calories;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Duration getDuration() {
        return duration;
    }

    public boolean isCaloriesNull() {
        return calories == null;
    }

    public boolean isDifficultyNull() {
        return difficulty == null;
    }

    public boolean isDurationNull() {
        return duration == null;
    }

}
