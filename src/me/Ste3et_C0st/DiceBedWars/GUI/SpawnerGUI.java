package me.Ste3et_C0st.DiceBedWars.GUI;

import java.util.HashMap;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SpawnerGUI {
	public static HashMap<Player, Inventory> inven = new HashMap<Player, Inventory>();
	
	@SuppressWarnings("deprecation")
	public static void openInventory(Player p, String editor, ItemStack is){
		Inventory inv = Bukkit.createInventory(null, 45, "Spawner Gui");
		
		for(int i = 0; i<=8;i++){
			inv.setItem(i, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		}
		
		for(int i = 36; i<=44;i++){
			inv.setItem(i, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		}
		
		inv.setItem(9, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		inv.setItem(17, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		inv.setItem(18, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		inv.setItem(26, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		inv.setItem(27, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		inv.setItem(35, Editor.is(Material.getMaterial(160), "§c", null, 15, 1));
		
		String string = is.getItemMeta().getDisplayName().split(" ")[2];
		String speere = "";
		if(string.equalsIgnoreCase("Bronze")){
			if(Editor.bronze.get(editor) == null){
				speere = "§c§m";
			}
		}
		
		if(string.equalsIgnoreCase("Eisen")){
			if(Editor.eisen.get(editor) == null){
				speere = "§c§m";
			}
		}

		if(string.equalsIgnoreCase("Gold")){
			if(Editor.gold.get(editor) == null){
				speere = "§c§m";
			}
		}
		
		inv.setItem(10, Editor.is(is.getType(), speere + "Spawner Anzeigen Lassen", null, 0, 1));
		inv.setItem(11, Editor.is(is.getType(), speere + "Spawner Verbergen Lassen", null, 0, 1));
		

		inv.setItem(30, Editor.is(Material.ARROW, "§cWeniger Spawner", null, 15, 1));
		inv.setItem(31, Editor.is(is.getType(), "Spawner : " + string, null, 0, is.getAmount()));
		inv.setItem(32, Editor.is(Material.ARROW, "§2Mehr Spawner", null, 15, 1));
		inven.put(p, inv);
		p.openInventory(inven.get(p));
	}
}
