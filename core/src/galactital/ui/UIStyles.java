package galactital.ui;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.style.*;
import arc.scene.ui.Button.*;
import arc.scene.ui.CheckBox.*;
import arc.scene.ui.Dialog.*;
import arc.scene.ui.Label.*;
import arc.scene.ui.ScrollPane.*;
import arc.scene.ui.TextButton.*;
import arc.scene.ui.TextField.*;
import galactital.annotations.Annotations.*;

import static galactital.gen.UIUtils.*;

@DefaultStyles
public class UIStyles {
    public static Drawable black, halfBlack, gray, blackBordered, whiteBordered, bDown, bUp;

    public static DialogStyle defDialog;
    public static LabelStyle defLabel;
    public static TextButtonStyle defTextButton;
    public static ButtonStyle defButton;
    public static TextFieldStyle defTextField;
    public static ScrollPaneStyle defScrollPane, borderedScrollPane;
    public static CheckBoxStyle defCheckBox;

    public static void load() {
        black = ((TextureRegionDrawable) whiteui).tint(0, 0, 0, 1f);
        halfBlack = ((TextureRegionDrawable) whiteui).tint(0, 0, 0, 0.5f);
        gray = ((TextureRegionDrawable) whiteui).tint(127.5f, 127.5f, 127.5f, 1);

        blackBordered = new NinePatchDrawable(new NinePatch(Core.atlas.find("borderedui"), 7, 7, 7, 7));
        whiteBordered = new NinePatchDrawable(new NinePatch(Core.atlas.find("bordereduiwhite"), 7, 7, 7, 7));
        bUp = new NinePatchDrawable(new NinePatch(Core.atlas.find("button"), 7, 7, 7, 7));
        bDown = new NinePatchDrawable(new NinePatch(Core.atlas.find("button-down"), 7, 7, 7, 7));

        //default styles
        defLabel = new LabelStyle() {{
            font = Fonts.def;
            fontColor = Color.white;
        }};
        defDialog = new DialogStyle() {{
            stageBackground = halfBlack;
            titleFont = Fonts.def;
        }};
        defButton = new ButtonStyle() {{
            up = bUp;
            down = bDown;
        }};
        defTextButton = new TextButtonStyle() {{
            up = bUp;
            down = bDown;
            font = Fonts.def;
        }};
        defTextField = new TextFieldStyle() {{
            font = Fonts.def;
            fontColor = Color.white;
            disabledFontColor = Color.gray;
            messageFont = Fonts.def;
            messageFontColor = Color.gray;
        }};

        defScrollPane = new ScrollPaneStyle() {{
            vScrollKnob = gray;
        }};

        borderedScrollPane = new ScrollPaneStyle() {{
            background = blackBordered;
            vScrollKnob = gray;
        }};

        defCheckBox = new CheckBoxStyle() {{
           font = Fonts.def;
           fontColor = Color.white;
           checkboxOff = checkOff;
           checkboxOn = checkOn;
           checkboxOver = checkOffOver;
           checkboxOnOver = checkOnOver;
        }};
    }
}
