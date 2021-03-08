package dev.cernavskis.soupery;

import dev.cernavskis.soupery.init.KSEffects;
import net.fabricmc.api.ModInitializer;

public class KrillosSoupery implements ModInitializer {
	public static final String MODID = "soupery";

	@Override
	public void onInitialize() {
		KSEffects.registerEffects();
		System.out.println("Initialized Krillo's Soupery!");
	}
}
