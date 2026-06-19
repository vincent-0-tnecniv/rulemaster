package net.vincent.rulemaster.event;

import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.loot.BlockBreakHandler;

public class ModEvents {
    public static void registerEvents() {
        BlockBreakHandler.register();

        RuleMaster.LOGGER.info("Registered Events for " + RuleMaster.MOD_ID);
    }
}
