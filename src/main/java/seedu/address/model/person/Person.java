package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
//    private final Phone phone;
//    private final Email email;
    private final Type type;
    private final Duration duration;
    private final Difficulty difficulty;

    // Data fields
//    private final Address address;
    private final Equipment equipment;
    private final Muscle muscle;
    private final Calories calories;
    private final Instruction instruction;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Type type, Duration duration, Difficulty difficulty, Equipment equipment,
    Muscle muscle, Calories calories, Instruction instruction, Set<Tag> tags) {
        requireAllNonNull(name, type, duration, difficulty, equipment, muscle, calories, instruction, tags);
        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.address = address;
        this.type = type;
        this.duration = duration;
        this.difficulty = difficulty;
        this.equipment = equipment;
        this.muscle = muscle;
        this.calories = calories;
        this.instruction = instruction;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Type getType() { return type; }

    public Duration getDuration() {
        return duration;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Muscle getMuscle() {
        return muscle;
    }

    public Calories getCalories() {
        return calories;
    }

    public Instruction getInstruction() {
        return instruction;
    }

//    public Phone getPhone() { return phone; }
//
//    public Email getEmail() { return email; }
//
//    public Address getAddress() { return address; }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getType().equals(getType()) || otherPerson.getDuration().equals(getDuration())
                || otherPerson.getDifficulty().equals(getDifficulty()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
//                && otherPerson.getPhone().equals(getPhone())
//                && otherPerson.getEmail().equals(getEmail())
//                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getType().equals(getType())
                && otherPerson.getDuration().equals(getDuration())
                && otherPerson.getDifficulty().equals(getDifficulty())
                && otherPerson.getEquipment().equals(getEquipment())
                && otherPerson.getMuscle().equals(getMuscle())
                && otherPerson.getCalories().equals(getCalories())
                && otherPerson.getInstruction().equals(getInstruction())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, type, duration, difficulty, equipment, muscle, calories, instruction, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
//                .append(" Phone: ")
//                .append(getPhone())
//                .append(" Email: ")
//                .append(getEmail())
//                .append(" Address: ")
//                .append(getAddress())
                .append(" Type: ")
                .append(getType())
                .append(" Duration: ")
                .append(getDuration())
                .append(" Difficulty: ")
                .append(getDifficulty())
                .append(" Equipment: ")
                .append(getEquipment())
                .append(" Muscle: ")
                .append(getMuscle())
                .append(" Calories: ")
                .append(getCalories())
                .append(" Instruction: ")
                .append(getInstruction())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
