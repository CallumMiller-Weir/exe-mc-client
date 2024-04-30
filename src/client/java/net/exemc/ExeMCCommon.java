package net.exemc;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class ExeMCCommon {

    // client version
    public static final int CLIENT_MAJOR_VERSION = 1;
    public static final int CLIENT_MINOR_VERSION = 0;
    public static final int CLIENT_PATCH_VERSION = 0;

    // in-game hud general text spacing
    public static final int HUD_TEXT_YSPACING = 12;
    public static final int BORDER_OFFSET = 2;

    // in-game hud title text
    public static final int TITLE_TEXT_COLOR = 0xf54257;
    public static final String TITLE_TEXT = String.format("ExeMC Client %d.%d.%d",
            CLIENT_MAJOR_VERSION, CLIENT_MINOR_VERSION, CLIENT_PATCH_VERSION);

    // in-game hud player position
    public static final int PLAYER_POSITION_TEXT_COLOUR = 0xc5c6d0;

    // in-game hud mod list
    public static final int HUD_MOD_LIST_YBASE = 24;

    // full bright
    public static final double DEFAULT_GAMMA = 2.2d;
    public static final double MAX_GAMMA = 2000.0d;

    // speedy gonzales
    public static final int SPEEDY_GONZALES_PACKET_RATE = 10;

    // xray
    public static List<String> XRAY_BLOCK_LIST = Arrays.asList(new String[] {
            // Ores
            "block.minecraft.ancient_debris",
            "block.minecraft.coal_ore",
            "block.minecraft.copper_ore",
            "block.minecraft.deepslate_coal_ore",
            "block.minecraft.deepslate_copper_ore",
            "block.minecraft.deepslate_diamond_ore",
            "block.minecraft.deepslate_emerald_ore",
            "block.minecraft.deepslate_gold_ore",
            "block.minecraft.deepslate_iron_ore",
            "block.minecraft.deepslate_lapis_ore",
            "block.minecraft.deepslate_redstone_ore",
            "block.minecraft.diamond_ore",
            "block.minecraft.emerald_ore",
            "block.minecraft.gold_ore",
            "block.minecraft.iron_ore",
            "block.minecraft.lapis_ore",
            "block.minecraft.nether_gold_ore",
            "block.minecraft.nether_quartz_ore",
            "block.minecraft.redstone_ore",

            // Blocks
            "block.minecraft.anvil",
            "block.minecraft.beacon",
            "block.minecraft.bone_block",
            "block.minecraft.bookshelf",
            "block.minecraft.brewing_stand",
            "block.minecraft.chain_command_block",
            "block.minecraft.clay",
            "block.minecraft.coal_block",
            "block.minecraft.command_block",
            "block.minecraft.crafter",
            "block.minecraft.crafting_table",
            "block.minecraft.decorated_pot",
            "block.minecraft.diamond_block",
            "block.minecraft.dispenser",
            "block.minecraft.dropper",
            "block.minecraft.emerald_block",
            "block.minecraft.furnace",
            "block.minecraft.glowstone",
            "block.minecraft.gold_block",
            "block.minecraft.hopper",
            "block.minecraft.iron_block",
            "block.minecraft.lapis_block",
            "block.minecraft.lodestone",
            "block.minecraft.mossy_cobblestone",
            "block.minecraft.raw_copper_block",
            "block.minecraft.raw_gold_block",
            "block.minecraft.raw_iron_block",
            "block.minecraft.redstone_block",
            "block.minecraft.repeating_command_block",
            "block.minecraft.spawner",
            "block.minecraft.tnt",
            "block.minecraft.trapped_chest",

            // Special Blocks
            "block.minecraft.end_portal",
            "block.minecraft.end_portal_frame",
            "block.minecraft.nether_portal",
            "block.minecraft.water",
            "block.minecraft.lava",

            // Utilities
            "block.minecraft.chest",
            "block.minecraft.ender_chest",
            "block.minecraft.ladder",
            "block.minecraft.torch",

            // Miscellaneous
            "block.minecraft.suspicious_gravel",
            "block.minecraft.suspicious_sand"
    });

    // ray cast limit
    public static final int RAY_CAST_MAX_LENGTH = 32;

    public static BlockPos getBlockInFrontOfPlayer(ClientPlayerEntity player, World world) {
        Vec3d playerRotation = player.getRotationVector();
        Vec3d rayPath = playerRotation.multiply(ExeMCCommon.RAY_CAST_MAX_LENGTH);

        Vec3d from = player.getEyePos();
        Vec3d to = from.add(rayPath);

        RaycastContext rayCtx = new RaycastContext(from, to, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.ANY, player);
        BlockHitResult rayHit = world.raycast(rayCtx);
        if (rayHit.getType() != HitResult.Type.MISS) {
            return rayHit.getBlockPos();
        }

        return null;
    }
}
