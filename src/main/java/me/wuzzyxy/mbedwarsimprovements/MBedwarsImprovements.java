package me.wuzzyxy.mbedwarsimprovements;

import de.marcely.bedwars.api.GameAPI;
import de.marcely.bedwars.api.message.DefaultMessageMappings;
import de.marcely.bedwars.api.message.Message;
import de.marcely.bedwars.api.message.MessageAPI;
import me.wuzzyxy.mbedwarsimprovements.gui.CustomUpgradeLayout;
import me.wuzzyxy.mbedwarsimprovements.upgrades.RegenUpgradeHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class MBedwarsImprovements extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        GameAPI.get().registerUpgradeShopLayout(new CustomUpgradeLayout(this));
        GameAPI.get().registerUpgradeTriggerHandler(new RegenUpgradeHandler("regen", false, this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public CustomUpgradeLayoutHandler layoutHandler = new CustomUpgradeLayoutHandler();
}
