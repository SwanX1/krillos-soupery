package dev.cernavskis.soupery.init;

import dev.cernavskis.soupery.KrillosSoupery;
import dev.cernavskis.soupery.effect.KSStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class KSEffects {
  public static final StatusEffect BOUNCY = new KSStatusEffect(StatusEffectType.NEUTRAL, 0x9ad389);

  public static void registerEffects() {
    Registry.register(Registry.STATUS_EFFECT, new Identifier(KrillosSoupery.MODID, "bouncy"), KSEffects.BOUNCY);
  }
}
