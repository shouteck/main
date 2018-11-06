package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.workout.Workout;

/**
 * Panel containing the list of workouts.
 */
public class WorkoutListPanel extends UiPart<Region> {
    private static final String FXML = "WorkoutListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WorkoutListPanel.class);

    @FXML
    private ListView<Workout> workoutListView;

    public WorkoutListPanel(ObservableList<Workout> workoutList) {
        super(FXML);
        setConnections(workoutList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Workout> workoutList) {
        workoutListView.setItems(workoutList);
        workoutListView.setCellFactory(listView -> new WorkoutListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Workout} using a {@code WorkoutCard}.
     */
    class WorkoutListViewCell extends ListCell<Workout> {
        @Override
        protected void updateItem(Workout workout, boolean empty) {
            super.updateItem(workout, empty);

            if (empty || workout == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new WorkoutCard(workout, getIndex() + 1).getRoot());
            }
        }
    }

}
