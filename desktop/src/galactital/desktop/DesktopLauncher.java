package galactital.desktop;

import arc.backend.sdl.*;
import galactital.*;

public class DesktopLauncher extends Launcher {
    public static void main(String[] args) {
        new SdlApplication(new DesktopLauncher(), new SdlConfig() {{
            title = "Galactical";
            maximized = true;
        }});
    }
}
