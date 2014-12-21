package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.Merchant;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.MerchantOffer;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Blocks{
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("Blöcke");
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 1), Editor.is(Material.SANDSTONE, "", null, 0, 2)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 3), Editor.is(Material.ENDER_STONE, "", null, 0, 2)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 3), Editor.is(Material.STONE, "", null, 1, 2)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 3), Editor.is(Material.STONE, "", null, 3, 2)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 3), Editor.is(Material.STONE, "", null, 5, 2)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 5), Editor.is(Material.IRON_BLOCK, "", null, 0, 2)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 1), Editor.is(Material.GLASS, "", null, 0, 1)));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 7), Editor.is(Material.GLOWSTONE, "", null, 0, 2)));
		inv.openTrading(p);
	}
}