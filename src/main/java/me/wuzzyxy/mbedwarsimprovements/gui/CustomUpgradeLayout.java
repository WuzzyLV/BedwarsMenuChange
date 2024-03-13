package me.wuzzyxy.mbedwarsimprovements.gui;

import de.marcely.bedwars.api.game.shop.layout.ShopLayout;
import de.marcely.bedwars.api.game.upgrade.UpgradeLevel;
import de.marcely.bedwars.api.game.upgrade.layout.UpgradeShopLayout;
import de.marcely.bedwars.api.game.upgrade.layout.UpgradeShopLayoutHandler;
import de.marcely.bedwars.api.game.upgrade.layout.UpgradeShopLayoutType;
import de.marcely.bedwars.tools.gui.GUI;
import me.wuzzyxy.mbedwarsimprovements.MBedwarsImprovements;
import org.bukkit.plugin.Plugin;

public class CustomUpgradeLayout implements UpgradeShopLayout {
    MBedwarsImprovements plugin;
    public CustomUpgradeLayout(MBedwarsImprovements plugin) {
        this.plugin = plugin;
    }

    @Override
    public UpgradeShopLayoutType getType() {
        return UpgradeShopLayoutType.PLUGIN;
    }

    @Override
    public String getName() {
        return "Custom";
    }

    @Override
    public boolean isBeta() {
        return false;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public UpgradeShopLayoutHandler getHandler() {
        return plugin.layoutHandler;
    }
}
