package net.fragger.creatoroverlays;

import net.fabricmc.api.ModInitializer;
import net.fragger.creatoroverlays.util.config.COConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class creatoroverlays implements ModInitializer {
	public static final String MOD_ID = "creatoroverlays";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("creatoroverlays successfully initialized!");
		COConfigs.registerConfigs();
	}
}
