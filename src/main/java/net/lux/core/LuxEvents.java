package net.lux.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LuxEvents {
    private static final List<Consumer<String>> ON_GAME_START = new ArrayList<>();

    public static void registerStartEvent(Consumer<String> listener) {
        ON_GAME_START.add(listener);
    }

    public static void fireStartEvent(String version) {
        for (Consumer<String> listener : ON_GAME_START) {
            listener.accept(version);
        }
    }
}
