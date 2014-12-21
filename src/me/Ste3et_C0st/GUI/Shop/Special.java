package me.Ste3et_C0st.GUI.Shop;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.Merchant;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.MerchantOffer;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Special{
	
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§6Spezial");
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 2), Editor.is(Material.LADDER, "", null, 0, 3)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 7), Editor.is(Material.FISHING_ROD, "", null, 0, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 5), Editor.is(Material.ENDER_PEARL, "", null, 0, 1)));
		if(p.hasPermission("group.Vip") || p.hasPermission("group.Mayor")){
			List<String> lore = new ArrayList<String>();
			lore.add("3x Benutzbar");
			inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 2), Editor.is(Material.SKULL_ITEM,Main.head +  "Mobiler Händler", lore, 4, 1)));
		}else{
			List<String> lore = new ArrayList<String>();
			lore.add("1x Benutzbar");
			inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 2), Editor.is(Material.SKULL_ITEM,Main.head +  "Mobiler Händler", lore, 4, 1)));
		}
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 10), Editor.is(Material.GLASS, "Rettungs Platform", null, 4, 1)));
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 10), Editor.is(Material.MONSTER_EGG, "Test Schaf", null, 19, 1)));
		inv.openTrading(p);
	}
}