package galactital.ui;

import arc.*;
import arc.assets.*;
import arc.files.*;
import arc.freetype.FreeTypeFontGenerator.*;
import arc.freetype.*;
import arc.freetype.FreetypeFontLoader.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import arc.struct.*;

public class Fonts {
    public static Font def;
    private static final ObjectSet<String> unscaled = ObjectSet.with("iconLarge");

    public static void load() {
        Core.assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(Core.files::internal));
        Core.assets.setLoader(Font.class, null, new FreetypeFontLoader(Core.files::internal) {
            ObjectSet<FreeTypeFontParameter> scaled = new ObjectSet<>();

            @Override
            public Font loadSync(AssetManager manager, String fileName, Fi file, FreeTypeFontLoaderParameter parameter) {
                if (fileName.equals("outline")) {
                    parameter.fontParameters.borderWidth = Scl.scl(2f);
                    parameter.fontParameters.spaceX -= parameter.fontParameters.borderWidth;
                }

                if (!scaled.contains(parameter.fontParameters) && !unscaled.contains(fileName)) {
                    parameter.fontParameters.size = (int) (Scl.scl(parameter.fontParameters.size));
                    scaled.add(parameter.fontParameters);
                }

                parameter.fontParameters.magFilter = Texture.TextureFilter.linear;
                parameter.fontParameters.minFilter = Texture.TextureFilter.linear;
                return super.loadSync(manager, fileName, file, parameter);
            }
        });

        Core.assets.load("default", Font.class, new FreeTypeFontLoaderParameter("fonts/font.woff", new FreeTypeFontParameter() {{
            size = 18;
            shadowColor = Color.darkGray;
            shadowOffsetY = 2;
            incremental = true;
        }})).loaded = f -> Fonts.def = f;
    }
}
