package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String type;
    @XmlElement(required = true)
    private String duration;
    @XmlElement(required = true)
    private String difficulty;
    @XmlElement(required = true)
    private String equipment;
    @XmlElement(required = true)
    private String muscle;
    @XmlElement(required = true)
    private String calories;
    @XmlElement(required = true)
    private String instruction;
/*
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String address;
*/

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedPerson() {}

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given person details.
     */
    public XmlAdaptedPerson(String name, String type, String duration, String difficulty,
                            String equipment, String muscle, String calories,
                            String instruction, List<XmlAdaptedTag> tagged) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.difficulty = difficulty;
        this.equipment = equipment;
        this.muscle = muscle;
        this.calories = calories;
        this.instruction = instruction;
        /*
        this.phone = phone;
        this.email = email;
        this.address = address;
        */
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
    }

    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedPerson(Person source) {
        name = source.getName().fullName;
        type = source.getType().fullType;
        duration = source.getDuration().fullDuration;
        difficulty = source.getDifficulty().fullDifficulty;
        equipment = source.getEquipment().fullEquipment;
        muscle = source.getMuscle().fullMuscle;
        calories = source.getCalories().fullCalories;
        instruction = source.getInstruction().fullInstruction;
        /*
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        */
        tagged = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        if (!Type.isValidType(type)) {
            throw new IllegalValueException(Type.MESSAGE_TYPE_CONSTRAINTS);
        }
        final Type modelType = new Type(type);

        if (duration == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName()));
        }
        if (!Duration.isValidDuration(duration)) {
            throw new IllegalValueException(Duration.MESSAGE_DURATION_CONSTRAINTS);
        }
        final Duration modelDuration = new Duration(duration);

        if (difficulty == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Difficulty.class.getSimpleName()));
        }
        if (!Difficulty.isValidDifficulty(difficulty)) {
            throw new IllegalValueException(Difficulty.MESSAGE_DIFFICULTY_CONSTRAINTS);
        }
        final Difficulty modelDifficulty = new Difficulty(difficulty);

        if (equipment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Equipment.class.getSimpleName()));
        }
        if (!Equipment.isValidEquipment(equipment)) {
            throw new IllegalValueException(Equipment.MESSAGE_EQUIPMENT_CONSTRAINTS);
        }
        final Equipment modelEquipment = new Equipment(equipment);

        if (muscle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Muscle.class.getSimpleName()));
        }
        if (!Muscle.isValidMuscle(muscle)) {
            throw new IllegalValueException(Muscle.MESSAGE_MUSCLE_CONSTRAINTS);
        }
        final Muscle modelMuscle = new Muscle(muscle);

        if (calories == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Calories.class.getSimpleName()));
        }
        if (!Calories.isValidCalories(calories)) {
            throw new IllegalValueException(Calories.MESSAGE_CALORIES_CONSTRAINTS);
        }
        final Calories modelCalories = new Calories(calories);

        if (instruction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Instruction.class.getSimpleName()));
        }
        if (!Instruction.isValidInstruction(instruction)) {
            throw new IllegalValueException(Instruction.MESSAGE_INSTRUCTION_CONSTRAINTS);
        }
        final Instruction modelInstruction = new Instruction(instruction);

        /*
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        */
        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelType, modelDuration, modelDifficulty, modelEquipment,
                modelMuscle, modelCalories, modelInstruction, modelTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedPerson)) {
            return false;
        }

        XmlAdaptedPerson otherPerson = (XmlAdaptedPerson) other;
        return Objects.equals(name, otherPerson.name)
                /*
                && Objects.equals(phone, otherPerson.phone)
                && Objects.equals(email, otherPerson.email)
                && Objects.equals(address, otherPerson.address)
                */
                && Objects.equals(type, otherPerson.type)
                && Objects.equals(duration, otherPerson.duration)
                && Objects.equals(difficulty, otherPerson.difficulty)
                && Objects.equals(equipment, otherPerson.equipment)
                && Objects.equals(muscle, otherPerson.muscle)
                && Objects.equals(calories, otherPerson.calories)
                && Objects.equals(instruction, otherPerson.instruction)
                && tagged.equals(otherPerson.tagged);
    }
}
