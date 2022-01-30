package galactital.ui;

import arc.*;
import arc.graphics.*;
import arc.scene.style.*;
import arc.scene.ui.Label.*;
import arc.scene.ui.Button.*;
import arc.scene.ui.TextButton.*;

public class UIStyles {
    public static Drawable black;

    public static LabelStyle labelStyle;
    public static TextButtonStyle tButtonStyle;
    public static ButtonStyle buttonStyle;

    public static void load() {
        black = ((TextureRegionDrawable) Core.atlas.drawable("whiteui")).tint(0, 0, 0, 0.5f);

        //default styles
        labelStyle = new LabelStyle(){{
            font = Fonts.def;
            fontColor = Color.white;
        }};

        buttonStyle = new ButtonStyle();

        tButtonStyle = new TextButtonStyle() {{
            font = Fonts.def;
        }};

        Core.scene.addStyle(LabelStyle.class, labelStyle);
        Core.scene.addStyle(ButtonStyle.class, buttonStyle);
        Core.scene.addStyle(TextButtonStyle.class, tButtonStyle);
    }
}
