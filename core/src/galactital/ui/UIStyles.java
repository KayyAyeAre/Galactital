package galactital.ui;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.style.*;
import arc.scene.ui.Button.*;
import arc.scene.ui.Dialog.*;
import arc.scene.ui.Label.*;
import arc.scene.ui.ScrollPane.*;
import arc.scene.ui.TextButton.*;
import arc.scene.ui.TextField.*;

public class UIStyles {
    public static Drawable black, halfBlack, gray, blackBordered, bDown, bUp;

    public static DialogStyle dialogStyle;
    public static LabelStyle labelStyle;
    public static TextButtonStyle tButtonStyle;
    public static ButtonStyle buttonStyle;
    public static TextFieldStyle tFieldStyle;
    public static ScrollPaneStyle sPaneStyle;

    public static void load() {
        black = ((TextureRegionDrawable) Core.atlas.drawable("whiteui")).tint(0, 0, 0, 1f);
        halfBlack = ((TextureRegionDrawable) Core.atlas.drawable("whiteui")).tint(0, 0, 0, 0.5f);
        gray = ((TextureRegionDrawable) Core.atlas.drawable("whiteui")).tint(127.5f, 127.5f, 127.5f, 0);
        blackBordered = new NinePatchDrawable(new NinePatch(Core.atlas.find("borderedui"), 7, 7, 7, 7));
        bUp = new NinePatchDrawable(new NinePatch(Core.atlas.find("button"), 7, 7, 7, 7));
        bDown = new NinePatchDrawable(new NinePatch(Core.atlas.find("button-down"), 7, 7, 7, 7));

        //default styles
        labelStyle = new LabelStyle() {{
            font = Fonts.def;
            fontColor = Color.white;
        }};
        dialogStyle = new DialogStyle() {{
            stageBackground = halfBlack;
            titleFont = Fonts.def;
        }};
        buttonStyle = new ButtonStyle() {{
            up = bUp;
            down = bDown;
        }};
        tButtonStyle = new TextButtonStyle() {{
            up = bUp;
            down = bDown;
            font = Fonts.def;
        }};
        tFieldStyle = new TextFieldStyle() {{
            font = Fonts.def;
            fontColor = Color.white;
            disabledFontColor = Color.gray;
            messageFont = Fonts.def;
            messageFontColor = Color.gray;
        }};

        sPaneStyle = new ScrollPaneStyle() {{
            background = black;
            vScrollKnob = gray;
        }};

        Core.scene.addStyle(LabelStyle.class, labelStyle);
        Core.scene.addStyle(ButtonStyle.class, buttonStyle);
        Core.scene.addStyle(TextButtonStyle.class, tButtonStyle);
        Core.scene.addStyle(TextFieldStyle.class, tFieldStyle);
        Core.scene.addStyle(ScrollPaneStyle.class, sPaneStyle);
        Core.scene.addStyle(DialogStyle.class, dialogStyle);
    }
}
