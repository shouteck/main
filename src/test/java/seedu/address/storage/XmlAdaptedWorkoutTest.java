package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedWorkout.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalWorkouts.BENSON_WORKOUT;

import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.workout.Name;
import seedu.address.model.workout.Type;
import seedu.address.model.workout.Duration;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Equipment;
import seedu.address.model.workout.Muscle;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Instruction;
import seedu.address.testutil.Assert;

public class XmlAdaptedWorkoutTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TYPE = "";
    private static final String INVALID_DURATION = "";
    private static final String INVALID_DIFFICULTY = "";
    private static final String INVALID_EQUIPMENT = "";
    private static final String INVALID_MUSCLE = "";
    private static final String INVALID_CALORIES = "";
    private static final String INVALID_INSTRUCTION = "";

    private static final String VALID_NAME = BENSON_WORKOUT.getName().toString();
    private static final String VALID_TYPE = BENSON_WORKOUT.getType().toString();
    private static final String VALID_DURATION = BENSON_WORKOUT.getDuration().toString();
    private static final String VALID_DIFFICULTY = BENSON_WORKOUT.getDifficulty().toString();
    private static final String VALID_EQUIPMENT = BENSON_WORKOUT.getEquipment().toString();
    private static final String VALID_MUSCLE = BENSON_WORKOUT.getMuscle().toString();
    private static final String VALID_CALORIES = BENSON_WORKOUT.getCalories().toString();
    private static final String VALID_INSTRUCTION = BENSON_WORKOUT.getInstruction().toString();
    private static final List<XmlAdaptedTag> VALID_TAGS = BENSON_WORKOUT.getTags().stream()
            .map(XmlAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validWorkoutDetails_returnsPerson() throws Exception {
        XmlAdaptedWorkout person = new XmlAdaptedWorkout(BENSON_WORKOUT);
        assertEquals(BENSON_WORKOUT, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        XmlAdaptedWorkout workout =
                new XmlAdaptedWorkout(INVALID_NAME, VALID_TYPE, VALID_DURATION, VALID_DIFFICULTY, VALID_EQUIPMENT,
                        VALID_MUSCLE, VALID_CALORIES, VALID_INSTRUCTION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        XmlAdaptedWorkout workout = new XmlAdaptedWorkout(null, VALID_TYPE, VALID_DURATION, VALID_DIFFICULTY, VALID_EQUIPMENT,
                VALID_MUSCLE, VALID_CALORIES, VALID_INSTRUCTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        XmlAdaptedWorkout workout =
                new XmlAdaptedWorkout(INVALID_NAME, VALID_TYPE, VALID_DURATION, VALID_DIFFICULTY, VALID_EQUIPMENT,
                        VALID_MUSCLE, VALID_CALORIES, VALID_INSTRUCTION, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_PHONE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        XmlAdaptedWorkout workout = new XmlAdaptedWorkout(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        XmlAdaptedWorkout workout =
                new XmlAdaptedWorkout(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_EMAIL_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        XmlAdaptedWorkout workout = new XmlAdaptedWorkout(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        XmlAdaptedWorkout workout =
                new XmlAdaptedWorkout(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_ADDRESS_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        XmlAdaptedWorkout workout = new XmlAdaptedWorkout(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, workout::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<XmlAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new XmlAdaptedTag(INVALID_TAG));
        XmlAdaptedWorkout workout =
                new XmlAdaptedWorkout(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        Assert.assertThrows(IllegalValueException.class, workout::toModelType);
    }

}
