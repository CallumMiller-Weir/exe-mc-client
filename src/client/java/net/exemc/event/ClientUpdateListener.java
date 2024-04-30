package net.exemc.event;

import net.exemc.mod.Mod;
import net.exemc.mod.ModManager;

import java.util.List;

public class ClientUpdateListener implements EventListener {

    private ModManager modManager;

    public static class ClientUpdateListenerOptions extends EventListener.Options {
    }

    public ClientUpdateListener(ModManager modManager) {
        this.modManager = modManager;
    }

    @Override
    public void onEvent(float tickDelta, Options options) {
        List<Mod> modList = modManager.mods();
        for (Mod mod : modList) {
            if (mod.getKeyBinding().isPressed()) {
                mod.onToggle();
            }

            if (mod.isEnabled()) {
                mod.onTick(tickDelta);
            }
        }
    }
}
