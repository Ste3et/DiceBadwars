package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.Merchant;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.MerchantOffer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
public class TNT{
	
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§cTNT");
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 9), Editor.is(Material.FLINT_AND_STEEL, "", null, 0, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 2), Editor.is(Material.TNT, "", null, 0, 5)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 3), Editor.is(Material.WOOD_PLATE, "Tret Mine (Holz)", null, 15, 3)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 3), Editor.is(Material.STONE_PLATE, "Tret Mine (Stein)", null, 15, 3)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 64), Editor.is(Material.MONSTER_EGG, "Suizid Schaf", null, 19, 1)));
		inv.openTrading(p);
	}
}