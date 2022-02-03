package galactital.annotations;

import java.lang.annotation.*;

public class Annotations {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.SOURCE)
    public @interface DefaultStyles {}
}
