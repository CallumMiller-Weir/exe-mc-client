package net.exemc.event;

import net.exemc.mod.Mod;
import net.exemc.mod.ModManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

import static net.exemc.ExeMCCommon.*;

public class HudRenderListener implements EventListener {

    private ModManager modManager;

    public static class HudRenderListenerOptions extends EventListener.Options {
        private DrawContext drawContext;

        public HudRenderListenerOptions(DrawContext drawContext) {
            this.drawContext = drawContext;
        }
    }

    public HudRenderListener(ModManager modManager) {
        this.modManager = modManager;
    }

    @Override
    public void onEvent(float tickDelta, Options options) {
        HudRenderListenerOptions optionsCast = (HudRenderListenerOptions) options;
        MinecraftClient minecraftInstance = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftInstance.textRenderer;

        // render client title
        DrawContext drawContext = optionsCast.drawContext;
        drawContext.drawText(
                textRenderer,
                TITLE_TEXT,
                BORDER_OFFSET, BORDER_OFFSET,
                TITLE_TEXT_COLOR,
                true
        );

        // render player position
        ClientPlayerEntity player = minecraftInstance.player;
        BlockPos blockPos = player.getBlockPos();
        String playerPosString = String.format(
                "XYZ: %d %d %d",
                blockPos.getX(), blockPos.getY(), blockPos.getZ()
        );

        drawContext.drawText(
                textRenderer,
                playerPosString,
                BORDER_OFFSET, BORDER_OFFSET + HUD_TEXT_YSPACING,
                PLAYER_POSITION_TEXT_COLOUR,
                false
        );

        // render enabled mod list
        int enabledCount = 0;
        List<Mod> modList = modManager.mods();
        for (Mod mod : modList) {
            if (mod.isEnabled()) {
                int yOff = HUD_MOD_LIST_YBASE + enabledCount * HUD_TEXT_YSPACING;
                drawContext.drawText(
                        textRenderer,
                        mod.getName(),
                        BORDER_OFFSET, BORDER_OFFSET + yOff,
                        mod.getColour(),
                        false
                );

                enabledCount++;
            }
        }
    }
}
