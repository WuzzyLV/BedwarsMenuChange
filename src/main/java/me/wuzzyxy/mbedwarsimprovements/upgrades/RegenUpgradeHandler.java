package me.wuzzyxy.mbedwarsimprovements.upgrades;

import de.marcely.bedwars.api.arena.Arena;
import de.marcely.bedwars.api.event.player.PlayerTriggerUpgradeEvent;
import de.marcely.bedwars.api.game.upgrade.UpgradeTriggerHandler;
import de.marcely.bedwars.api.message.Message;
import me.wuzzyxy.mbedwarsimprovements.MBedwarsImprovements;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class RegenUpgradeHandler extends UpgradeTriggerHandler {
    public RegenUpgradeHandler(String id, boolean isTrap, MBedwarsImprovements plugin) {
        super(id, isTrap, plugin);
    }

    @Override
    public String getName() {
        return Message.buildByKey("UpgradeShop_Name_Regen", "Regeneration").done();
    }

    @Override
    public List<String> getDescriptionLines() {
        return Arrays.asList(Message.buildByKey("UpgradeShop_Desc_Regen", "&7All players in your team get permanent regeneration I")
                .done().split("\\\\n"));
    }

    @Override
    public void onTrigger(PlayerTriggerUpgradeEvent e) {
        final int amplifier= (int) e.getAmplifier();
        final Arena arena = e.getArena();

        arena.addTeamEffect(e.getTeam(), true, PotionEffectType.REGENERATION, amplifier);
    }
}
