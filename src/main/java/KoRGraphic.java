import boardifier.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameConfigurationModel;
import utils.Boardifiers;

import static model.GameConfigurationModel.*;

public class KoRGraphic extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // create the model
        Model model = new Model();

        // Set up game configuration
        final GameConfigurationModel gameConfigurationModel = new GameConfigurationModel(model, 1,
                0, 3, DEFAULT_PLAYER_INTERACTION, DEFAULT_RENDER_GAME);

        // Init Game
        final Boardifiers boardifiers = new Boardifiers(stage, model, gameConfigurationModel);
        boardifiers.initGame();
    }
}
