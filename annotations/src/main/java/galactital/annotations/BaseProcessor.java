package galactital.annotations;

import arc.files.*;
import arc.util.*;
import com.squareup.javapoet.*;
import com.sun.source.util.*;

import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;
import javax.tools.*;
import java.io.*;
import java.util.*;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public abstract class BaseProcessor extends AbstractProcessor {
    public static final String packageName = "galactital.gen";
    public static Filer filer;
    public static Trees trees;

    protected int round, rounds = 1;
    protected Fi rootDirectory;
    protected RoundEnvironment roundEnv;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        filer = env.getFiler();
        trees = Trees.instance(env);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (round++ >= rounds) return false;

        if (rootDirectory == null) {
            try {
                String path = Fi.get(filer.getResource(StandardLocation.CLASS_OUTPUT, "no", "no")
                                .toUri().toURL().toString().substring(OS.isWindows ? 6 : "file:".length()))
                        .parent().parent().parent().parent().parent().parent().parent().toString().replace("%20", " ");
                rootDirectory = Fi.get(path).parent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.roundEnv = roundEnv;

        try {
            process(roundEnv);
        } catch (Throwable e) {
            Log.err(e);
            throw new RuntimeException(e);
        }

        return true;
    }

    public abstract void process(RoundEnvironment env) throws Exception;

    public void writeFile(TypeSpec type) throws IOException {
        JavaFile.builder(packageName, type).build().writeTo(filer);
    }
}
