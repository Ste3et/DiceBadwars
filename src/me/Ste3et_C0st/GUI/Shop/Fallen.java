package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.Merchant;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.MerchantOffer;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Fallen {
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§6Fallen");
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 3), Editor.is(Material.WEB, "", null, 0, 2)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 15), Editor.is(Material.SOUL_SAND, "", null, 0, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 10), Editor.is(Material.ICE, "", null, 0, 1)));
		inv.openTrading(p);
	}
}
