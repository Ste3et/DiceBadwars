package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import VilligerTradesAPI.Merchant;
import VilligerTradesAPI.MerchantOffer;

public class Chests{
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§5Kisten");
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 2), Editor.is(Material.CHEST, "", null, 0, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 5), Editor.is(Material.ENDER_CHEST, "", null, 0, 1)));
		inv.openTrading(p);
	}
}