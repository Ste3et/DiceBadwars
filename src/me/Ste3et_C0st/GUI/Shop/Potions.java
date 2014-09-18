package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import VilligerTradesAPI.Merchant;
import VilligerTradesAPI.MerchantOffer;

public class Potions{
	@SuppressWarnings("deprecation")
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§bTränke");
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 2), Editor.is(Material.getMaterial(373), "", null, 8194, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 3), Editor.is(Material.getMaterial(373), "", null, 8226, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 4), Editor.is(Material.getMaterial(373), "", null, 8258, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 3), Editor.is(Material.getMaterial(373), "", null, 8193, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 4), Editor.is(Material.getMaterial(373), "", null, 8201, 1)));
		inv.openTrading(p);
	}
}