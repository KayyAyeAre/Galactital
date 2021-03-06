package galactital.game;

public class GameState {
    // whether or not the player is controlling their spacecraft
    public boolean spacecraft;
    private State state = State.menu;

    public boolean isMenu() {
        return state == State.menu;
    }

    public boolean isPlaying() {
        return state == State.playing;
    }

    public boolean isPaused() {
        return state == State.paused;
    }

    public boolean isGame() {
        return state != State.menu;
    }

    public State getState() {
        return state;
    }

    public void setState(State to) {
        state = to;
    }

    public enum State {
        playing, paused, menu
    }
}
