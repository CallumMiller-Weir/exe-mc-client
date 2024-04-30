package net.exemc.mods;

import net.exemc.ExeMCCommon;
import net.exemc.mod.Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SpeedyGonzalesMod extends Mod {

    public SpeedyGonzalesMod(int key, int colour) {
        super("Speedy Gonzales", key, colour);
    }

    @Override
    public void onTick(float tickDelta) {
        MinecraftClient instance = MinecraftClient.getInstance();

        ClientPlayerEntity player = instance.player;
        World world = instance.world;
        if (player == null || world == null) {
            return;
        }

        BlockPos rayCastBlockPos = ExeMCCommon.getBlockInFrontOfPlayer(player, world);
        if (rayCastBlockPos == null) {
            return;
        }

        ClientPlayerInteractionManager interactionManager = instance.interactionManager;
        if (interactionManager.isBreakingBlock()) {
            for (int i = 0; i < ExeMCCommon.SPEEDY_GONZALES_PACKET_RATE; i++) {
                interactionManager.updateBlockBreakingProgress(rayCastBlockPos, Direction.UP);
                player.networkHandler.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
            }
        }
    }
}
