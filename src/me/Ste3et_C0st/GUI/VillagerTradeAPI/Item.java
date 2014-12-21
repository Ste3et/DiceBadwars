package me.Ste3et_C0st.GUI.VillagerTradeAPI;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Item
{
  private ItemStack i;
  private ItemMeta m;
  private List<String> l = new ArrayList<String>();
  private byte dyecolor = 20;
  private byte potion = 0;
  private byte dataid = 0;
  private LeatherArmorMeta lc;
  private String skull;
  
  public Item(Material m, int amount)
  {
    i = new ItemStack(m, amount);
    this.m = i.getItemMeta();
    if (isLeather()) {
      lc = ((LeatherArmorMeta)i.getItemMeta());
    }
  }
  
  public Item(Material m)
  {
    i = new ItemStack(m);
    this.m = i.getItemMeta();
    if (isLeather()) {
      lc = ((LeatherArmorMeta)i.getItemMeta());
    }
  }
  
  public Item(ItemStack is)
  {
    i = is;
    m = is.getItemMeta();
    if (isLeather()) {
      lc = ((LeatherArmorMeta)i.getItemMeta());
    }
  }
  
  public void setName(String name)
  {
    m.setDisplayName(name);
  }
  
  public Material getMaterial()
  {
    return i.getType();
  }
  
  public int getAmount()
  {
    return i.getAmount();
  }
  
  public void removeLore()
  {
    l.clear();
  }
  
  public void addLore(String... lore)
  {
    for (String s : lore) {
      l.add(s);
    }
  }
  
  public void addLore(List<String> lore)
  {
    for (String s : lore) {
      l.add(s);
    }
  }
  
  public void setLore(String... lore)
  {
    clearLore();
    for (String s : lore) {
      l.add(s);
    }
  }
  
  public void setLore(List<String> lore)
  {
    l = lore;
  }
  
  public void clearLore()
  {
    l.clear();
  }
  
  public void setSkullOwner(String name)
  {
    skull = name;
  }
  
  public void setMaterial(Material m)
  {
    i.setType(m);
  }
  
  public void setAmount(int amount)
  {
    i.setAmount(amount);
  }
  
  public void setColor(int colorid)
  {
    dyecolor = ((byte)colorid);
  }
  
  public void setData(int data)
  {
    dataid = ((byte)data);
  }
  
  public byte getData()
  {
    return dataid;
  }
  
  public void addEnchantment(Enchantment ench, int level)
  {
    m.addEnchant(ench, level, true);
  }
  
  private boolean isLeather()
  {
    if ((getMaterial() == Material.LEATHER_BOOTS) || 
      (getMaterial() == Material.LEATHER_CHESTPLATE) || 
      (getMaterial() == Material.LEATHER_HELMET) || 
      (getMaterial() == Material.LEATHER_LEGGINGS)) {
      return true;
    }
    return false;
  }
  
  public void setLeatherColor(Color c)
  {
    if (isLeather()) {
      lc.setColor(c);
    }
  }
  
  public void setPotion(int id)
  {
    potion = ((byte)id);
  }
  
  public ItemStack getItem()
  {
    if (dyecolor < 20) {
      i = new ItemStack(i.getType(), i.getAmount(), dyecolor);
    }
    if (potion > 0) {
      i = new ItemStack(i.getType(), i.getAmount(), potion);
    }
    if (dataid != 0) {
      i = new ItemStack(i.getType(), i.getAmount(), dataid);
    }
    if ((skull != null) && (i.getType() == Material.SKULL_ITEM))
    {
      SkullMeta meta = (SkullMeta)i.getItemMeta();
      meta.setOwner(skull);
      meta.setDisplayName(m.getDisplayName());
      meta.setLore(l);
      i.setItemMeta(meta);
    }
    m.setLore(l);
    if (isLeather())
    {
      lc.setDisplayName(m.getDisplayName());
      lc.setLore(l);
      i.setItemMeta(lc);
    }
    else
    {
      i.setItemMeta(m);
    }
    return i;
  }
}
