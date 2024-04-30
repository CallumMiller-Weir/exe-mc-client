package net.exemc.mixins;

import net.exemc.ExeMCCommon;
import net.exemc.mod.ModManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(at = { @At("HEAD") }, method = {
            "shouldDrawSide(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Lnet/minecraft/util/math/BlockPos;)Z"
    }, cancellable = true)
    private static void onShouldDrawSide(BlockState state, BlockView world, BlockPos pos, Direction direction,
                                         BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        ModManager modManager = ModManager.INSTANCE;
        if (modManager == null) {
            return;
        }

        if (modManager.get("Treasure").isEnabled()) {
            boolean isXrayBlock = ExeMCCommon.XRAY_BLOCK_LIST.contains(state.getBlock().getTranslationKey());
            cir.setReturnValue(isXrayBlock);
        }
    }
}
