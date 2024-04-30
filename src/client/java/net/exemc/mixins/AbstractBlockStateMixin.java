package net.exemc.mixins;

import net.exemc.ExeMCCommon;
import net.exemc.mod.ModManager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {

    @Shadow
    public abstract Block getBlock();

    public void getRenderType(CallbackInfoReturnable<BlockRenderType> cir) {
        ModManager modManager = ModManager.INSTANCE;
        if (modManager == null) {
            return;
        }

        if (modManager.get("Treasure").isEnabled() && !ExeMCCommon.XRAY_BLOCK_LIST.contains(getBlock().getTranslationKey())) {
            cir.setReturnValue(BlockRenderType.INVISIBLE);
        }
    }

    @Inject(at = @At("RETURN"), method = "getLuminance", cancellable = true)
    public void getLuminance(CallbackInfoReturnable cir){
        ModManager modManager = ModManager.INSTANCE;
        if (modManager == null) {
            return;
        }
        if(modManager.get("Treasure").isEnabled()) {
            cir.setReturnValue(20000);
        }
    }
}
