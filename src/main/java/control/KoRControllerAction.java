package control;

import boardifier.control.Controller;
import boardifier.control.ControllerAction;
import boardifier.control.Logger;
import boardifier.model.GameException;
import boardifier.model.Model;
import boardifier.view.View;
import control.action.ConfigController;
import control.action.WindowController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.GameConfigurationModel;
import model.data.AIData;
import model.data.PlayerData;
import model.data.WindowType;
import view.KoRView;
import view.window.ConfigView;

import java.util.List;

/**
 * A basic action controller that only manages menu actions
 * Action events are mostly generated when there are user interactions with widgets like
 * buttons, checkboxes, menus, ...
 */
public class KoRControllerAction extends ControllerAction implements EventHandler<ActionEvent> {

    // to avoid lots of casts, create an attribute that matches the instance type.
    private final KoRView koRView;
    private final GameConfigurationModel gameConfigurationModel;

    public KoRControllerAction(Model model, View view, Controller control,GameConfigurationModel gameConfigurationModel) {
        super(model, view, control);
        // take the view parameter ot define a local view attribute with the real instance type, i.e. BasicView.
        koRView = (KoRView) view;
        this.gameConfigurationModel = gameConfigurationModel;

        // set handlers dedicated to menu items
        setMenuHandlers();

        // If needed, set the general handler for widgets that may be included within the scene.
        // In this case, the current gamestage view must be retrieved and casted to the right type
        // in order to have an access to the widgets, and finally use setOnAction(this).
        // For example, assuming the current gamestage view is an instance of MyGameStageView, which
        // creates a Button myButton :
        // ((MyGameStageView)view.getCurrentGameStageView()).getMyButton().setOnAction(this).

    }

    public Controller getController() {
        return control;
    }

    private void setMenuHandlers() {

        // set event handler on the MenuStart item
        koRView.getMenuStart().setOnAction(e -> {
            try {
                Sound.playSound("sword1.wav");
                Sound.playMusic("Daydream.wav");
                gameConfigurationModel.addPlayers("Player 1 - Blue","Player 2 - Red");
                control.startGame();
            } catch (GameException err) {
                System.err.println(err.getMessage());
                System.exit(1);
            }
        });

        // set event handler on the MenuIntro item
        koRView.getMenuIntro().setOnAction(e -> {
            Sound.playSound("Doorknob.wav");
            Sound.playMusic("main.wav",1000);
            control.stopGame();
            koRView.resetView();
        });

        // set event handler on the Configuration menu item
        koRView.getMenuConfig().setOnAction(e -> {
            Sound.playSound("Doorknob.wav");
            Sound.playMusic("shop.wav",650);
            Logger.info("KoR Config :" + gameConfigurationModel.getPlayerDataAIDataMap().toString());
            control.stopGame();
            koRView.resetView();

            koRView.setContent(WindowType.CONFIG);
        });

        // set event handler to configure sounds volume
        koRView.getmLow().setOnAction(e -> {
            Sound.setMusicVolume(0.25);
            koRView.getMenu3().setText("Volume de la Musique : " + Sound.getMusicVolume());
        });
        koRView.getmMedium().setOnAction(e -> {
            Sound.setMusicVolume(0.50);
            koRView.getMenu3().setText("Volume de la Musique : " + Sound.getMusicVolume());
        });
        koRView.getmHigh().setOnAction(e -> {
            Sound.setMusicVolume(0.75);
            koRView.getMenu3().setText("Volume de la Musique : " + Sound.getMusicVolume());
        });
        koRView.getmMax().setOnAction(e -> {
            Sound.setMusicVolume(1.0);
            koRView.getMenu3().setText("Volume de la Musique : " + Sound.getMusicVolume());
        });

        koRView.getsLow().setOnAction(e -> {
            Sound.setSoundVolume(0.25);
            koRView.getMenu4().setText("Volume des Sons: " + Sound.getSoundVolume());
        });
        koRView.getsMedium().setOnAction(e -> {
            Sound.setSoundVolume(0.50);
            koRView.getMenu4().setText("Volume des Sons: " + Sound.getSoundVolume());
        });
        koRView.getsHigh().setOnAction(e -> {
            Sound.setSoundVolume(0.75);
            koRView.getMenu4().setText("Volume des Sons: " + Sound.getSoundVolume());
        });
        koRView.getsMax().setOnAction(e -> {
            Sound.setSoundVolume(1.0);
            koRView.getMenu4().setText("Volume des Sons: " + Sound.getSoundVolume());
        });
        koRView.getsRule().setOnAction(e -> {
            Sound.playSound("Doorknob.wav");
            Sound.playMusic("shop.wav",650);
            Logger.info("KoR Rule :" + gameConfigurationModel.getPlayerDataAIDataMap().toString());
            control.stopGame();
            koRView.resetView();

            koRView.setContent(WindowType.RULES);
        });

        // set event handler on the MenuQuit item
        koRView.getMenuQuit().setOnAction(e -> System.exit(0));

        //set event handler on the paramètres menu
        koRView.getMenuMusique().setOnAction(e -> Sound.musicSwitch());
        koRView.getMenuSFX().setOnAction(e -> Sound.soundSwitch());

        List<WindowController> controllerList = List.of(
                new ConfigController(this, koRView)
        );

        controllerList.forEach(WindowController::setHandler);
    }


    /**
     * The general handler for action events.
     * this handler should be used if the code to process a particular action event is too long
     * to fit in an arrow function (like with menu items above). In this case, this handler must be
     * associated to a widget w, by calling w.setOnAction(this) (see constructor).
     *
     * @param event An action event generated by a widget of the scene.
     */
    public void handle(ActionEvent event) {
        if (!model.isCaptureActionEvent()) {
        }
    }

    public GameConfigurationModel getGameConfigurationModel() {
        return gameConfigurationModel;
    }
}

