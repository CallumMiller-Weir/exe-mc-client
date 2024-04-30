package net.exemc.event;

public interface EventListener {
    class Options {
    }

    void onEvent(float tickDelta, Options options);
}
