package model.element.card;

import boardifier.model.Coord2D;
import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * Represents a movement card in the game.
 */
public class MovementCard extends GameElement {

    private boolean inverted;
    private final int step;
    private Direction direction;
    private Owner owner;

    /**
     * Constructs a new movement card with the specified step, direction, and game stage model.
     *
     * @param step           The step of the movement card.
     * @param direction      The direction of the movement card.
     * @param gameStageModel The game stage model to which this movement card belongs.
     */
    public MovementCard(int step, Direction direction, GameStageModel gameStageModel) {
        super(gameStageModel);

        // Register new element type
        // Associate the word "direction_card" with the integer 51
        ElementTypes.register("direction_card", 51);
        // Retrieve the integer associated with the word "direction_card" and associate it with the type variable
        this.type = ElementTypes.getType("direction_card");

        this.step = step;
        this.direction = direction;
        this.inverted = false;
        this.owner = Owner.STACK;
    }

    /**
     * Gets the owner of the movement card.
     *
     * @return The owner of the movement card.
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the movement card and invert
     * the card if the owner is the red player.
     *
     * @param owner The owner to set.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
        if (owner == Owner.PLAYER_RED) toggleInverted();
    }

    /**
     * Checks if the movement card is inverted.
     *
     * @return True if the movement card is inverted, false otherwise.
     */
    public boolean isInverted() {
        return inverted;
    }

    /**
     * Toggles the inverted status of the movement card.
     */
    public void toggleInverted() {
        this.inverted = !this.inverted;
        this.direction = direction.getOpposite();
    }

    /**
     * Gets the step of the movement card.
     *
     * @return The step of the movement card.
     */
    public int getStep() {
        return step;
    }

    /**
     * Gets the representation of the step as a character.
     *
     * @return The representation of the step as a character.
     */
    public char getStepRepresentation() {
        final int startPoint = 8543;
        if (step < 1 || step > 3) {
            throw new IllegalArgumentException("Invalid step");
        }
        return (char) (startPoint + step);
    }

    @Override
    public String toString() {
        return "MovementCard{" +
                "step=" + step +
                ", direction=" + direction +
                ", owner=" + owner +
                '}';
    }

    /**
     * Gets the direction of the movement card.
     *
     * @return The direction of the movement card.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the direction vector of the movement card.
     *
     * @return The direction vector of the movement card.
     */
    public Coord2D getDirectionVector() {
        return direction.getVector().multiply(step);
    }

    /**
     * Represents the direction of the movement card.
     */
    public enum Direction {
        NORTH(-1, 0, "north"),
        NORTHEAST(-1, 1, "northeast"),
        EAST(0, 1, "east"),
        SOUTHEAST(1, 1, "southeast"),
        SOUTH(1, 0, "south"),
        SOUTHWEST(1, -1, "southwest"),
        WEST(0, -1, "west"),
        NORTHWEST(-1, -1, "northwest");

        private final Coord2D vector;
        private final String path;

        /**
         * Constructs a new direction with the specified column, row, and symbol.
         *
         * @param col    The column of the direction.
         * @param row    The row of the direction.
         * @param path The symbol representing the direction.
         */
        Direction(int col, int row, String path) {
            this(new Coord2D(row, col), path);
        }

        /**
         * Constructs a new direction with the specified vector and symbol.
         *
         * @param vector The vector representing the direction.
         * @param path The symbol representing the direction.
         */
        Direction(Coord2D vector, String path) {
            this.path = path;
            this.vector = vector;
        }

        /**
         * Gets the direction vector.
         *
         * @return The direction vector.
         */
        public Coord2D getVector() {
            return vector;
        }

        /**
         * Gets the symbol representing the direction.
         *
         * @return The symbol representing the direction.
         */
        public String getPath(int step) {
            return "/movement_card/" + path + "_" + step + ".png";
        }

        /**
         * Gets the opposite direction.
         *
         * @return The opposite direction.
         */
        public Direction getOpposite() {
            final Direction opposite;
            switch (this) {
                case NORTH -> opposite = SOUTH;
                case NORTHEAST -> opposite = SOUTHWEST;
                case EAST -> opposite = WEST;
                case SOUTHEAST -> opposite = NORTHWEST;
                case SOUTH -> opposite = NORTH;
                case SOUTHWEST -> opposite = NORTHEAST;
                case WEST -> opposite = EAST;
                case NORTHWEST -> opposite = SOUTHEAST;
                default -> throw new IllegalCallerException("Illegal direction");
            }
            return opposite;
        }
    }

    /**
     * Represents the owner of the movement card.
     */
    public enum Owner {
        PLAYER_RED,
        PLAYER_BLUE,
        STACK,
        // (OUT means that the card doesn't belong to a player and isn't in the stack)
        OUT;

        /**
         * Gets the background color associated with the owner.
         *
         * @return The background color associated with the owner.
         */
        public Color getColor() {
            return (this == OUT) ? Color.YELLOW : Color.WHITE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovementCard that)) return false;
        return step == that.step && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(step, inverted, owner, direction);
    }
}