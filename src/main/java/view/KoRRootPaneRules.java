package view;

import boardifier.view.RootPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class KoRRootPaneRules extends RootPane {

    public KoRRootPaneRules() {
        super();
    }

    @Override
    public void createDefaultGroup() {

        Rectangle frame = new Rectangle(600, 100, Color.LIGHTGREY);
        TextArea textArea = new TextArea();
        textArea.setText("2°/ Rules\n" +
                "\n" +
                "2.1°/ Flow of the game\n" +
                "\n" +
                "    The first player is determined by drawing lots or by agreement.\n" +
                "    In turn, each player chooses one of the 3 possible actions, explained below.\n" +
                "    This alternation continues until the end of the game, the conditions of which are given in section 2.3.\n" +
                "\n" +
                "2.2°/ Game actions\n" +
                "\n" +
                "    There are 3 possible actions:\n" +
                "        draw a move card,\n" +
                "        play a move card alone,\n" +
                "        play a move card plus a hero card.\n" +
                "\n" +
                "2.2.1°/ Draw a move card\n" +
                "\n" +
                "    This action is only possible if the player has less than 5 move cards in front of him.\n" +
                "    He draws the top card from the deck and places it face-up, with the number in front of him.\n" +
                "    If the deck is empty at the time of drawing, simply shuffle the discard pile to make a new deck.\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "2.2.2°/ Playing a move card alone.\n" +
                "\n" +
                "    Playing a move card alone consists of moving the king in the indicated direction, by the indicated number of squares.\n" +
                "    You cannot play a card :\n" +
                "        in a direction other than that indicated by the card,\n" +
                "        with fewer squares than the number indicated on the card,\n" +
                "        if the move takes the king off the board,\n" +
                "        if the move brings the king onto a pawn (of the current player or of the opponent).\n" +
                "    Note that the king can \"jump\" over pawns during its move.\n" +
                "    If the card chosen by the player can be played, then :\n" +
                "        place it in a discard pile next to the board,\n" +
                "        place a pawn with its color visible on the arrival square,\n" +
                "        he places the king on the pawn he has just placed on the arrival square.\n" +
                "\n" +
                "2.2.3°/ Playing a move card plus a hero card\n" +
                "\n" +
                "    Playing a pair of move+hero cards consists in moving the king in the indicated direction, by the indicated number of squares, to land on a square occupied by a pawn of the opponent's color.\n" +
                "    Such a pair cannot be played :\n" +
                "        in a direction other than that indicated on the move card,\n" +
                "        with fewer squares than the number indicated on the move card,\n" +
                "        if the move does not place the king on an opponent's pawn. \n" +
                "    Note that the king can \"jump\" over pawns during its move.\n" +
                "    If the pair of cards chosen by the player can be played, then :\n" +
                "        he places the move card in a discard pile next to the board, and the hero card in another discard pile. The hero card can never be retrieved.\n" +
                "        he flips the pawn on the arrival square to change its visible color.\n" +
                "        he places the king on the pawn he has just returned to the arrival square.\n" +
                "\n" +
                "2.3°/ End of game\n" +
                "\n" +
                "    The game ends in 2 cases:\n" +
                "        as soon as a player has 5 move cards in front of him/her, and cannot play any of them.\n" +
                "        as soon as a player places the last pawn available.\n" +
                "    To determine the winner, you need to determine :\n" +
                "        the number of \"adjacency\" zones,\n" +
                "        count the number of pawns in each zone, and square this number to obtain the zone value.\n" +
                "        add up the zone values.\n" +
                "    An adjacency zone is made up of a set of squares whose tokens are the same color AND which touch each other 2 by 2 on the sides (not on the corners).\n" +
                "\n" +
                " \n" +
                "\n" +
                "    The player with the highest sum wins the game.\n" +
                "    In the event of a tie, the winner is the player with the most tokens of his or her color on the board,\n" +
                "    If this number is equal, the game is drawn and there are no winners.");
        textArea.setWrapText(true);
        textArea.setEditable(false);
        // put shapes in the group
        group.getChildren().clear();
        group.getChildren().addAll(frame, textArea);
    }
}
