package net.exemc;

import net.exemc.event.ClientUpdateListener;
import net.exemc.event.EventListener;
import net.exemc.event.HudRenderListener;
import net.exemc.mod.ModManager;
import net.exemc.mods.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.util.InputUtil;

public class ExeMCClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModManager modManager = new ModManager();
		modManager.add(new NoFallMod(InputUtil.GLFW_KEY_N, 0x0062ff));
		modManager.add(new FullbrightMod(InputUtil.GLFW_KEY_K, 0x00ff95));
		modManager.add(new SpeedyGonzalesMod(InputUtil.GLFW_KEY_G, 0xfa9a41));
		modManager.add(new TreasureMod(InputUtil.GLFW_KEY_R, 0xd4af37));

		ClientUpdateListener clientUpdateListener = new ClientUpdateListener(modManager);
		ClientTickEvents.END_CLIENT_TICK.register(client -> clientUpdateListener.onEvent(client.getTickDelta(), new ClientUpdateListener.ClientUpdateListenerOptions()));

		EventListener hudRenderListener = new HudRenderListener(modManager);
		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> hudRenderListener.onEvent(tickDelta, new HudRenderListener.HudRenderListenerOptions(drawContext)));
	}
}