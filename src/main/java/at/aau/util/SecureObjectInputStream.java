package at.aau.util;

import at.aau.models.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

public class SecureObjectInputStream extends ObjectInputStream {
    private static final Set<String> approvedClasses = new HashSet<>() {{
        add(Request.class.getName());
    }};

    public SecureObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
        if (!approvedClasses.contains(osc.getName()))
            throw new ClassNotFoundException("Class not allowed: " + osc.getName());
        return super.resolveClass(osc);
    }
}
