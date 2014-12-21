package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ItemShop{

	public static HashMap<Player, Inventory> inven = new HashMap<Player, Inventory>();
	
	@SuppressWarnings("deprecation")
	public static void openshop(Player player, Boolean mobile) {
		
			Inventory inv = Bukkit.createInventory(null, 36, "Shop");
			
			for(int i = 0; i<=8;i++){
				inv.setItem(i, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
			}
			
			for(int i = 27; i<=35;i++){
				inv.setItem(i, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
			}
			
			List<String> lore = new ArrayList<String>();
			lore.add("§3Hier findest du alles zum bauen");
			inv.setItem(9, Editor.is(Material.SANDSTONE, "Blöcke", lore, 0, 1));
			lore.clear();
			lore.add("§3Verteidige dich gegen Gegner !");
			inv.setItem(10, Editor.is(Material.CHAINMAIL_CHESTPLATE, "§dRüstung", lore, 0, 1));
			lore.clear();
			lore.add("§3Klaue Blöcke deines Gegners oder");
			lore.add("§3sogar sein Bett");
			inv.setItem(11, Editor.is(Material.STONE_PICKAXE, "§eSpitzhacken", lore, 0, 1));
			lore.clear();
			lore.add("§3Töte deine Gegner");
			inv.setItem(12, Editor.is(Material.GOLD_SWORD, "§cSchwerter", lore, 0, 1));
			lore.clear();
			lore.add("§3Erschieße deine Gegner");
			inv.setItem(13, Editor.is(Material.BOW, "§aBögen", lore, 0, 1));
			lore.clear();
			lore.add("§3Essen ist Wichtig nicht nur wegen dem Hunger");
			lore.add("§3Sondern das du auch genug leben hast");
			inv.setItem(14, Editor.is(Material.APPLE, "§9Nahrung", lore, 0, 1));
			lore.clear();
			lore.add("§3Bewahre deine Items in Kisten auf");
			inv.setItem(15, Editor.is(Material.CHEST, "§5Kisten", lore, 0, 1));
			lore.clear();
			lore.add("§3Benutze Tränke für Vorteile");
			inv.setItem(16, Editor.is(Material.getMaterial(373), "§bTränke", lore, 0, 1));
			lore.clear();
			lore.add("§3Sachen die man mal brauchen Kann");
			inv.setItem(17, Editor.is(Material.BLAZE_POWDER, "§6Spezial", lore, 0, 1));
			lore.clear();
			lore.add("§3Hier kannst du dein Bronze/Silber & Gold Tauschen");
			
			if(mobile == false){
				inv.setItem(26, Editor.is(Material.EMERALD, "§6Tauschen", lore, 0, 1));
			}
			
			lore.add("§3Hier kannst du dein Bronze/Silber & Gold Tauschen");
			
			
			inv.setItem(18, Editor.is(Material.REDSTONE, "§cRedstone", null, 0, 1));
			inv.setItem(19, Editor.is(Material.ICE, "§cFallen", null, 0, 1));
			inv.setItem(20, Editor.is(Material.TNT, "§cSprengen", null, 0, 1));
			
			lore.clear();
			inven.put(player, inv);
			player.openInventory(inven.get(player));
	}
	
}