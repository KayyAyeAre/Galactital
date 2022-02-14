package galactital.annotations.procs;

import arc.files.*;
import arc.struct.*;
import arc.util.*;
import com.squareup.javapoet.*;
import galactital.annotations.*;
import galactital.annotations.Annotations.*;

import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;
import java.util.*;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("galactital.annotations.Annotations.DefaultStyles")
public class AssetProcessor extends BaseProcessor {
    @Override
    public void process(RoundEnvironment env) throws Exception {
        processUI(env.getElementsAnnotatedWith(DefaultStyles.class));
    }

    void processUI(Set<? extends Element> elements) throws Exception {
        TypeSpec.Builder type = TypeSpec.classBuilder("UIUtils").addModifiers(Modifier.PUBLIC);
        MethodSpec.Builder load = MethodSpec.methodBuilder("load").addModifiers(Modifier.PUBLIC, Modifier.STATIC);
        MethodSpec.Builder loadStyles = MethodSpec.methodBuilder("loadStyles").addModifiers(Modifier.PUBLIC, Modifier.STATIC);

        Fi.get(rootDirectory + "/Galactital/core/assets-raw/sprites/ui").walk(file -> {
            if (!file.extEquals("png")) return;

            String fieldname = varName(file.nameWithoutExtension());
            type.addField(ClassName.bestGuess("arc.scene.style.Drawable"), fieldname, Modifier.STATIC, Modifier.PUBLIC);
            load.addStatement(fieldname + " = arc.Core.atlas.drawable($S)", file.nameWithoutExtension());
        });

        for (Element element : elements) {
            Seq.with(element.getEnclosedElements()).each(e -> e.getKind() == ElementKind.FIELD, e -> {
                String name = e.getSimpleName().toString();
                if (name.startsWith("def")) {
                    loadStyles.addStatement("arc.Core.scene.addStyle(" + e.asType().toString() + ".class, galactital.ui.UIStyles." + name + ")");
                }
            });
        }

        type.addMethod(load.build());
        type.addMethod(loadStyles.build());
        JavaFile.builder(packageName, type.build()).build().writeTo(BaseProcessor.filer);
    }

    static String varName(String name) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            if (c != '_' && c != '-') {
                if (i > 0 && (name.charAt(i - 1) == '_' || name.charAt(i - 1) == '-')) {
                    out.append(Character.toUpperCase(c));
                } else {
                    out.append(c);
                }
            }
        }
        return out.toString();
    }
}
