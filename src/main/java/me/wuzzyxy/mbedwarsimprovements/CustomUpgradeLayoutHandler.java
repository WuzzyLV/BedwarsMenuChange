package me.wuzzyxy.mbedwarsimprovements;

import de.marcely.bedwars.api.GameAPI;
import de.marcely.bedwars.api.arena.Arena;
import de.marcely.bedwars.api.game.upgrade.Upgrade;
import de.marcely.bedwars.api.game.upgrade.UpgradeLevel;
import de.marcely.bedwars.api.game.upgrade.layout.UpgradeShopLayoutHandler;
import de.marcely.bedwars.api.message.Message;
import de.marcely.bedwars.tools.gui.GUI;
import de.marcely.bedwars.tools.gui.GUIItem;
import de.marcely.bedwars.tools.gui.type.ChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomUpgradeLayoutHandler implements UpgradeShopLayoutHandler {

    @Override
    public GUI build(OpenEvent e) {
        Player player = e.getPlayer();
        ChestGUI gui = new ChestGUI(5, "Upgrade Shop");

        List<UpgradeLevel> levels = e.getUpgradeLevels();

        int itemI=0;
        itemI=fillOut3Upgrades(levels, gui, e, 10, itemI);
        itemI=fillOut3Upgrades(levels, gui, e, 14, itemI);

        Arena arena = GameAPI.get().getArenaByPlayer(player);
        UpgradeLevel[] queue=arena.getQueuedUpgradeTraps(arena.getPlayerTeam(player));

        if (queue.length>0) {
            List<String> list=new ArrayList<String>(Arrays.asList(Message.buildByKey("UpgradeShop_QueuedTrap_Icon")
                    .placeholder("queue-level", "1")
                    .placeholder("trap-name", queue[0].getTriggerHandler().getName())
                    .done().split("\\\\n")));

            ItemStack item= new ItemStack(queue[0].getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(list.get(0));
            list.remove(0);
            meta.setLore(list);
            item.setItemMeta(meta);
            gui.setItem(item ,31);
        }else{
            List<String> list=new ArrayList<String>(Arrays.asList(Message.buildByKey("UpgradeShop_UnQueuedTrap_Icon")
                    .placeholder("queue-level", "1")
                    .done().split("\\\\n")));

            ItemStack item= new ItemStack(Material.GRAY_STAINED_GLASS);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(list.get(0));
            list.remove(0);
            meta.setLore(list);
            item.setItemMeta(meta);
            gui.setItem(item ,31);
        }

        gui.open(e.getPlayer());

        return gui;
    }
    int fillOut3Upgrades(List<UpgradeLevel> levels, ChestGUI gui, OpenEvent e,  int startPos, int startItem) {
        for (int i =0; i<3; i++) {
            if(levels.size()-1<startItem) {
                break;
            }
            GUIItem currItem=e.build(levels.get(startItem));
            if (currItem.getItem().getType()==Material.POTION){
                ItemStack item=currItem.getItem();
                ItemMeta meta = item.getItemMeta();
                meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                item.setItemMeta(meta);
            }
            gui.setItem(currItem, startPos);
            //Sets the dye color based on if u can buy it or not
            if (e.getUpgradeState(levels.get(startItem).getUpgrade()) == Upgrade.State.MAXIMUM) {
                ItemStack item= new ItemStack(Material.RED_DYE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GRAY + "");
                item.setItemMeta(meta);
                gui.setItem(item, startPos+9);
            } else if (levels.get(startItem).canAfford(e.getPlayer()) ) {
                //if the upgrade is maxed out
                ItemStack item= new ItemStack(Material.LIME_DYE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GRAY + "");
                item.setItemMeta(meta);
                gui.setItem(item, startPos+9);
            } else {
                ItemStack item= new ItemStack(Material.GRAY_DYE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GRAY + "");
                item.setItemMeta(meta);
                gui.setItem(item, startPos+9);
            }
            startItem++;
            startPos++;
        }
        return startItem;
    }
}
