package view;

import boardifier.model.Model;
import boardifier.view.RootPane;
import boardifier.view.View;
import control.Sound;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.data.WindowType;
import view.window.WindowView;

import java.util.*;

public class KoRView extends View {

    private final Map<WindowType, WindowView> windows = new EnumMap<>(WindowType.class);
    private WindowType currentWindow;

    private MenuItem menuConfig;
    private MenuItem menuStart;
    private MenuItem menuIntro;
    private MenuItem menuQuit;
    private MenuItem menuMusique;
    private MenuItem menuSFX;
    private MenuItem sLow;
    private MenuItem sMedium;
    private MenuItem sHigh;
    private MenuItem sMax;
    private MenuItem mLow;
    private MenuItem mMedium;
    private MenuItem mHigh;
    private MenuItem mMax;
    private Button bValider;
    private Menu menu3;
    private Menu menu4;

    public KoRView(Model model, Stage stage, RootPane rootPane) {
        super(model, stage, rootPane);
        currentWindow = WindowType.NONE;
        createWindows();
    }

    @Override
    protected void createMenuBar(){
        menuBar = new MenuBar();
        Menu menu1 = new Menu("Jeu");
        Menu menu2 = new Menu("Paramètres");
        menu3 = new Menu("Volume de la Musique : " + Sound.getMusicVolume());
        menu4 = new Menu("Volume des Sons : " + Sound.getSoundVolume());
        menuStart = new MenuItem("Nouvelle partie");
        menuIntro = new MenuItem("Retour au menu principal");
        menuQuit = new MenuItem("Quitter");
        menuConfig = new MenuItem("Partie Personnalisée");
        menuMusique = new MenuItem("Basculer la Musique");
        menuSFX = new MenuItem("Basculer les Effets Sonores");
        sLow = new MenuItem("Faible - 25%");
        sMedium = new MenuItem("Moyen - 50%");
        sHigh = new MenuItem("Fort - 75%");
        sMax = new MenuItem("Max - 100%");
        mLow = new MenuItem("Faible - 25%");
        mMedium = new MenuItem("Moyen - 50%");
        mHigh = new MenuItem("Fort - 75%");
        mMax = new MenuItem("Max - 100%");

        menu1.getItems().add(menuStart);
        menu1.getItems().add(menuIntro);
        menu1.getItems().add(menuConfig);
        menu1.getItems().add(menuQuit);

        menu2.getItems().add(menuMusique);
        menu2.getItems().add(menuSFX);
        menu2.getItems().add(menu3);
        menu2.getItems().add(menu4);

        menu3.getItems().addAll(mLow, mMedium, mHigh, mMax);
        menu4.getItems().addAll(sLow, sMedium, sHigh,sMax);

        menuBar.getMenus().addAll(menu1,menu2);
    }

    public WindowView setContent(WindowType windowType) {
        if(this.currentWindow == windowType) return windows.get(currentWindow);
        resetView();
        this.currentWindow = windowType;

        WindowView windowView = windows.get(currentWindow);
        rootPane.getChildren().add(windowView.getPane());

        Rectangle r;
        if(windowView.sizeToScene()) {
            stage.sizeToScene();
            r = new Rectangle(rootPane.getWidth(), rootPane.getHeight());
        } else {
            stage.setWidth(windowView.getWidth());
            stage.setHeight(windowView.getHeight());
            r = new Rectangle(windowView.getWidth(), windowView.getHeight());
        }

        rootPane.getChildren().remove(0);

        // set the clipping area with the boundaries of root pane.

        rootPane.setClip(r);

        return windowView;
    }

    public WindowView getCurrentWindow() {
        return windows.get(currentWindow);
    }

    public WindowType getCurrentWindowType() {
        return currentWindow;
    }

    @Override
    public void resetView() {
        super.resetView();
        this.currentWindow = WindowType.NONE;
    }

    public MenuItem getMenuMusique() {
        return menuMusique;
    }

    public MenuItem getMenuSFX() {
        return menuSFX;
    }

    public MenuItem getMenuConfig(){
        return menuConfig;
    }

    public MenuItem getMenuStart() {
        return menuStart;
    }

    public MenuItem getMenuIntro() {
        return menuIntro;
    }

    public MenuItem getMenuQuit() {
        return menuQuit;
    }

    public MenuItem getsLow() {
        return sLow;
    }

    public MenuItem getsMedium() {
        return sMedium;
    }

    public MenuItem getsHigh() {
        return sHigh;
    }

    public MenuItem getsMax() {
        return sMax;
    }

    public MenuItem getmLow() {
        return mLow;
    }

    public MenuItem getmMedium() {
        return mMedium;
    }

    public MenuItem getmHigh() {
        return mHigh;
    }

    public MenuItem getmMax() {
        return mMax;
    }

    public Menu getMenu3() {
        return menu3;
    }

    public Menu getMenu4() {
        return menu4;
    }

    private void createWindows() {
        for (WindowType windowType : WindowType.values()) {
            if(windowType == WindowType.NONE) continue;
            try {
                final WindowView windowView = windowType.generateWindow();
                windowView.createDefaultPane();
                windows.put(windowType, windowView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public WindowView getWindow(WindowType windowType) {
        return windows.get(windowType);
    }
}
