package io.github.ennuil.damageincorporated.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ennuil.damageincorporated.DamageIncorporatedMod;
import net.minecraft.entity.mob.EndermanEntity;

@Mixin(EndermanEntity.PlaceBlockGoal.class)
public class PlaceBlockGoalMixin {
	@Shadow
	@Final
	private EndermanEntity enderman;

	@Inject(
		at = @At("RETURN"),
		method = "canStart()Z",
		cancellable = true
	)
	private void controlEndermanPlaceDown(CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValueZ()) {
			if (!this.enderman.world.getGameRules().getBoolean(DamageIncorporatedMod.CAN_ENDERMEN_PLACE_BLOCKS_RULE)) {
				cir.setReturnValue(false);
			}
		}
	}
}