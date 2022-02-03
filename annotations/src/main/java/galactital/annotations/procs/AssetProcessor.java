package galactital.annotations.procs;

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
        TypeSpec.Builder type = TypeSpec.classBuilder("Test").addModifiers(Modifier.PUBLIC);
        MethodSpec.Builder loadStyles = MethodSpec.methodBuilder("loadStyles").addModifiers(Modifier.PUBLIC, Modifier.STATIC);

        for (Element element : elements) {
            Seq.with(element.getEnclosedElements()).each(e -> e.getKind() == ElementKind.FIELD, e -> {
                String name = e.getSimpleName().toString();
                if (name.startsWith("def")) {
                    loadStyles.addStatement("arc.Core.scene.addStyle(" + e.asType().toString() + ".class, galactital.ui.Styles." + name + ")");
                }
            });
        }
        type.addMethod(loadStyles.build());
        JavaFile.builder(packageName, type.build()).build().writeTo(BaseProcessor.filer);
    }
}
