package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.WorkoutPanelSelectionChangedEvent;
import seedu.address.model.workout.Workout;

/**
 * Panel containing the current workout and tracked data.
 */
public class CurrentAndTrackPanel extends UiPart<Region> {
    private static final String FXML = "CurrentAndTrackPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CurrentAndTrackPanel.class);

    @FXML
    private ListView<Workout> currentAndTrackView;

    public CurrentAndTrackPanel(ObservableList<Workout> trackedData) {
        super(FXML);
        setConnections(trackedData);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Workout> trackedData) {
        currentAndTrackView.setItems(trackedData);
        currentAndTrackView.setCellFactory(listView -> new CurrentAndTrackViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        currentAndTrackView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in current and track panel changed to : '" + newValue + "'");
                        raise(new WorkoutPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Workout} using a {@code WorkoutCard}.
     */
    class CurrentAndTrackViewCell extends ListCell<Workout> {
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
