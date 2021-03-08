package dev.cernavskis.soupery.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.cernavskis.soupery.init.KSEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@Mixin(Block.class)
public class BlockMixin {
  @Inject(method = "onEntityLand", at = @At("HEAD"), cancellable = true)
  private void onEntityLand(BlockView world, Entity entity, CallbackInfo ci) {
    if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(KSEffects.BOUNCY) && !entity.bypassesLandingEffects()) {
      this.bounce((LivingEntity) entity);
      ci.cancel();
    }
  }
  
  @Inject(method = "onLandedUpon", at = @At("HEAD"), cancellable = true)
  private void onLandedUpon(World world, BlockPos pos, Entity entity, float distance, CallbackInfo ci) {
    if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(KSEffects.BOUNCY)) {
      if (!entity.bypassesLandingEffects()) {
        entity.handleFallDamage(distance, 0.0F);
        ci.cancel();
      }
    }
  }

  @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
  private void onSteppedOn(World world, BlockPos pos, Entity entity, CallbackInfo ci) {
    if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(KSEffects.BOUNCY) && !entity.bypassesLandingEffects()) {
      double d = Math.abs(entity.getVelocity().y);
      if (d < 0.1D && !entity.bypassesSteppingEffects()) {
        double e = 0.4D + d * 0.2D;
        entity.setVelocity(entity.getVelocity().multiply(e, 1.0D, e));
      }
    }
  }

  private void bounce(LivingEntity entity) {
    Vec3d vec3d = entity.getVelocity();
    if (vec3d.y < 0.0D) {
      System.out.println("bounce");
      entity.setVelocity(vec3d.x, -vec3d.y * 1.0D, vec3d.z);
      if (vec3d.y < -0.08D) {
        entity.playSound(SoundEvents.BLOCK_SLIME_BLOCK_FALL, 0.1f, 1.0f);
      }
    }
  }
}