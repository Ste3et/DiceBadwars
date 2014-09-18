package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import VilligerTradesAPI.Merchant;
import VilligerTradesAPI.MerchantOffer;

public class Switch{
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§6Tausch");

		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 1), Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 5)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 1), Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 5)));
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 7), Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 10), Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 1)));
		inv.openTrading(p);
	}
}