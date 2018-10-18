package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTrackedDataList;
import seedu.address.model.TrackedData;
import seedu.address.model.workout.Workout;

/**
 * An Immutable tracked parameter list that is serializable to XML format
 */
@XmlRootElement(name = "trackeddatalist")
public class XmlSerializableTrackedDataList {

    public static final String MESSAGE_DUPLICATE_WORKOUT = "Tracked data list contains duplicate parameter(s).";

    @XmlElement
    private List<XmlAdaptedWorkout> workouts;

    /**
     * Creates an empty XmlSerializableTrackedData.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableTrackedDataList() {
        workouts = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableTrackedDataList(ReadOnlyTrackedDataList src) {
        this();
        workouts.addAll(src.getTrackedDataList().stream().map(XmlAdaptedWorkout::new).collect(Collectors.toList()));
    }

    /**
     * Converts this tracked data list into the model's {@code TrackedData} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedWorkout}.
     */
    public TrackedData toModelType() throws IllegalValueException {
        TrackedData trackedData = new TrackedData();
        for (XmlAdaptedWorkout p : workouts) {
            Workout workout = p.toModelType();
            if (trackedData.hasWorkout(workout)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_WORKOUT);
            }
            trackedData.addWorkout(workout);
        }
        return trackedData;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableTrackedDataList)) {
            return false;
        }
        return workouts.equals(((XmlSerializableTrackedDataList) other).workouts);
    }
}
