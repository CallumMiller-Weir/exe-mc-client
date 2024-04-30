package net.exemc.mods;

import net.exemc.ExeMCCommon;
import net.exemc.mod.Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;

public class FullbrightMod extends Mod {

    private double defaultGammaValue = ExeMCCommon.DEFAULT_GAMMA;

    public FullbrightMod(int key, int colour) {
        super("Fullbright", key, colour);
    }

    @Override
    public void onToggle() {
        super.onToggle();

        MinecraftClient instance = MinecraftClient.getInstance();
        SimpleOption<Double> gamma = instance.options.getGamma();
        if (isEnabled()) {
            defaultGammaValue = gamma.getValue();
            gamma.setValue(ExeMCCommon.MAX_GAMMA);
        } else {
            gamma.setValue(defaultGammaValue);
        }
    }

    @Override
    public void onTick(float tickDelta) {
    }
}
