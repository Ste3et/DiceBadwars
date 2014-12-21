package me.Ste3et_C0st.GUI.Shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.Merchant;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.MerchantOffer;

public class Redstone {
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§cRedstone");
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 3), Editor.is(Material.WOOD_PLATE, "", null, 0, 3)));
		inv.openTrading(p);
	}
}
