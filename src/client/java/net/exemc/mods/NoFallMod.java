package net.exemc.mods;

import net.exemc.mod.Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFallMod extends Mod {

    public NoFallMod(int key, int colour) {
        super("NoFall", key, colour);
    }

    @Override
    public void onTick(float tickDelta) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) {
            return;
        }

        boolean isFallFlying = player.isFallFlying();
        if (player.isCreative() || player.fallDistance <= (isFallFlying ? 1 : 2)) {
            return;
        }

        player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
    }
}
