package galactital.game;

public class GameState {
    // whether or not the player is controlling their spacecraft
    public boolean spacecraft;
    public State state = State.menu;

    public enum State {
        playing, menu
    }
}
