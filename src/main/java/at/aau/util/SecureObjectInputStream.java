package at.aau.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class SecureObjectInputStream extends ObjectInputStream {
    public SecureObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
        if (!osc.getName().startsWith("at.aau"))
            throw new ClassNotFoundException("Class not allowed: " + osc.getName());
        return super.resolveClass(osc);
    }
}
