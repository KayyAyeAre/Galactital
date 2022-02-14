package galactital.input;

import arc.KeyBinds.*;
import arc.input.*;
import arc.input.InputDevice.*;

public enum Binding implements KeyBind {
    move_x(new Axis(KeyCode.a, KeyCode.d)),
    move_y(new Axis(KeyCode.s, KeyCode.w)),
    hotbar_scroll(new Axis(KeyCode.i, KeyCode.o)),
    select(KeyCode.mouseLeft),
    deconstruct(KeyCode.mouseRight)
    ;

    private final KeybindValue defaultValue;

    @Override
    public KeybindValue defaultValue(DeviceType type) {
        return defaultValue;
    }

    Binding(KeybindValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
