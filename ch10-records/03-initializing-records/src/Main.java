record Settings(String name, int volume, boolean muted) {
    Settings() {
        this("default", 5, false);
    }

    Settings(String name) {
        this(name, 5, false);
    }
}

public class Main {
    static void show(Settings s) {
        System.out.println(s.name() + " " + s.volume() + " " + (s.muted() ? "yes" : "no"));
    }

    public static void main(String[] args) {
        Settings a = new Settings();
        Settings b = new Settings("Scott", 9, true);
        Settings c = new Settings(b.name(), 1, b.muted());

        show(a);
        show(b);
        show(c);
    }
}
