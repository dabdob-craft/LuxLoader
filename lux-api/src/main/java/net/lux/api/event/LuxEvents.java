package net.lux.api.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LuxEvents {
    public static final List<Runnable> ON_START = new ArrayList<>();

    public static void registerStart(Runnable action) {
        ON_START.add(action);
    }
}
