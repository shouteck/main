package seedu.address.model;

import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;

import java.util.Optional;

/**
 * Represents a Recommend arguments in the workout book.
 */
public class RecommendArguments {

    private Optional<Calories> calories;
    private Optional<Difficulty> difficulty;
    private Optional<Duration> duration;

    public static class Builder {

        private Optional<Calories> calories = Optional.empty();
        private Optional<Difficulty> difficulty = Optional.empty();
        private Optional<Duration> duration = Optional.empty();

        public Builder() {

        }

        public Builder withCalories(Optional<Calories> calories) {
            this.calories = calories;

            return this;
        }

        public Builder withDifficulty(Optional<Difficulty> difficulty) {
            this.difficulty = difficulty;

            return this;
        }

        public Builder withDuration(Optional<Duration> duration) {
            this.duration = duration;

            return this;
        }

        public RecommendArguments build() {
            RecommendArguments recommendArguments = new RecommendArguments();
            recommendArguments.setCalories(this.calories);
            recommendArguments.setDifficulty(this.difficulty);
            recommendArguments.setDuration(this.duration);
            return recommendArguments;
        }
    }

    private RecommendArguments() {

    }

    /**
     * Returns true if both recommend arguments have the same identity and data fields.
     * This defines a stronger notion of equality between two recommend arguments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecommendArguments)) {
            return false;
        }

        RecommendArguments otherRecommendArguments = (RecommendArguments) other;

        return ((otherRecommendArguments.isCaloriesNull() && isCaloriesNull())
                || otherRecommendArguments.getCalories().equals(getCalories()))
                && ((otherRecommendArguments.isDifficultyNull() && isDifficultyNull())
                || otherRecommendArguments.getDifficulty().equals(getDifficulty()))
                && ((otherRecommendArguments.isDurationNull() && isDurationNull())
                || otherRecommendArguments.getDuration().equals(getDuration()));
    }

    public Calories getCalories() {
        return calories.get();
    }

    public Difficulty getDifficulty() {
        return difficulty.get();
    }

    public Duration getDuration() {
        return duration.get();
    }

    public void setCalories(Optional<Calories> calories) {
        this.calories = calories;
    }

    public void setDifficulty(Optional<Difficulty> difficulty) {
        this.difficulty = difficulty;
    }

    public void setDuration(Optional<Duration> duration) {
        this.duration = duration;
    }

    public boolean isCaloriesNull() {
        return !calories.isPresent();
    }

    public boolean isDifficultyNull() {
        return !difficulty.isPresent();
    }

    public boolean isDurationNull()
    {
        return !duration.isPresent();
    }

}
