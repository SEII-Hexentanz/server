package at.aau.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecureObjectInputStream extends ObjectInputStream {

    private static final Set<String> ALLOWED_CLASSES = new HashSet<>(Arrays.asList(
            "java.util.UUID"

    ));
    public SecureObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
        String className = osc.getName();

        if (!osc.getName().startsWith("at.aau") && !ALLOWED_CLASSES.contains(className))
            throw new ClassNotFoundException("Class not allowed: " + osc.getName());
        return super.resolveClass(osc);
    }
}
