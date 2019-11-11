package de.db8.os.sample.fakerabbit.hello;

public class Hello {
    public static final String WORLD = "World";

    private final String name;

    public Hello(String name) {
        this.name = name;
    }

    public String getMessage() {
        if(null == name || name.length() < 1) {
            return "Hello World!";
        }
        else {
            return "Hello " + name + "!";
        }
    }

}
