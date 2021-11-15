package fr.alkanife.myla.commands;

import java.lang.reflect.Method;

public class MylaCommand {

    private final String name;
    private final Object object;
    private final Method method;

    public MylaCommand(String name, Object object, Method method) {
        super();
        this.name = name;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
