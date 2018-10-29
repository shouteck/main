package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;
import static seedu.address.ui.ProfileWindow.USERPROFILE_FILE_PATH;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;

import guitests.guihandles.ProfileWindowHandle;
import javafx.stage.Stage;

public class ProfileWindowTest extends GuiUnitTest {

    private ProfileWindow profileWindow;
    private ProfileWindowHandle profileWindowHandle;

    @Before
    public void setUp() throws Exception {
        guiRobot.interact(() -> profileWindow = new ProfileWindow());
        FxToolkit.registerStage(profileWindow::getRoot);
        profileWindowHandle = new ProfileWindowHandle(profileWindow.getRoot());
    }

    @Test
    public void display() throws Exception {
        FxToolkit.showStage();
        URL expectedProfilePage = ProfileWindow.class.getResource(USERPROFILE_FILE_PATH);
        assertEquals(expectedProfilePage, profileWindowHandle.getLoadedUrl());
    }

    @Test
    public void isShowing_profileWindowIsShowing_returnsTrue() {
        guiRobot.interact(profileWindow::show);
        assertTrue(profileWindow.isShowing());
    }

    @Test
    public void isShowing_profileWindowIsHiding_returnsFalse() {
        assertFalse(profileWindow.isShowing());
    }

    @Test
    public void focus_profileWindowNotFocused_focused() {
        // TODO: This test skip can be removed once this bug is fixed:
        // https://github.com/javafxports/openjdk-jfx/issues/50
        //
        // When there are two stages (stage1 and stage2) shown,
        // stage1 is in focus and stage2.requestFocus() is called,
        // we expect that stage1.isFocused() will return false while
        // stage2.isFocused() returns true. However, as reported in the bug report,
        // both stage1.isFocused() and stage2.isFocused() returns true,
        // which fails the test.
        assumeFalse("Test skipped in headless mode: Window focus behavior is buggy.", guiRobot.isHeadlessMode());
        guiRobot.interact(profileWindow::show);

        // Focus on another stage to remove focus from the profileWindow
        guiRobot.interact(() -> {
            Stage temporaryStage = new Stage();
            temporaryStage.show();
            temporaryStage.requestFocus();
        });
        assertFalse(profileWindow.getRoot().isFocused());

        guiRobot.interact(profileWindow::focus);
        assertTrue(profileWindow.getRoot().isFocused());
    }
}
