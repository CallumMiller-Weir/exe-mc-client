package net.exemc.mod;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

public abstract class Mod {

    private String name;
    private boolean enabled;

    private KeyBinding keyBinding;
    private int colour;

    public Mod(String name, int key, int colour) {
        this.name = name;
        this.enabled = false;

        keyBinding = new KeyBinding(name, key, name);
        KeyBindingHelper.registerKeyBinding(keyBinding);

        this.colour = colour;
    }

    public void onToggle() {
        enabled = !enabled;
    }

    public abstract void onTick(float tickDelta);

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public KeyBinding getKeyBinding() {
        return keyBinding;
    }

    public int getColour() {
        return colour;
    }
}
