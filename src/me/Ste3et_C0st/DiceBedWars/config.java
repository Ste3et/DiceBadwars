package me.Ste3et_C0st.DiceBedWars;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class config
{
  public JavaPlugin plugin;
  public String fileName;
  public static String path = "plugins/DiceBedwars";
  
  public config(JavaPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public FileConfiguration createConfig(String name, String Folder)
  {
    if (!name.endsWith(".yml")) {
      name = name + ".yml";
    }
    File f = new File(path + Folder + "/");
    if (!f.exists()) {
      f.mkdirs();
    }
    File arena = new File(path + Folder, name);
    if (!arena.exists()) {
      try
      {
        arena.createNewFile();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return YamlConfiguration.loadConfiguration(arena);
  }
  
  public void saveConfig(String name, FileConfiguration config, String Folder)
  {
    if (!name.endsWith(".yml")) {
      name = name + ".yml";
    }
    File arena = new File(path + Folder, name);
    try
    {
      config.save(arena);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public FileConfiguration getConfig(String name, String Folder)
  {
    if (!name.endsWith(".yml")) {
      name = name + ".yml";
    }
    createConfig(name, Folder);
    File arena = new File(path + Folder, name);
    return YamlConfiguration.loadConfiguration(arena);
  }
  
  public static boolean ExistMaps(String folder){
	  if(new File(path + folder).exists()){
		  return true; 
	  }else{
		  return false;
	  }
  }
  
  public static boolean ExistConfig(String folder, String name){
	  System.out.print(path + folder + "," + name);
	  if(new File(path + folder, name).exists()){
		  return true; 
	  }else{
		  return false;
	  }
  }
  
  
  public static boolean isMaps(String folder){
	  if(new File(path + folder).list().length > 0){
		  return true; 
	  }else{
		  return false;
	  }
  }
  
  public void deleteFolder(File folder)
  {

    
    File[] files = folder.listFiles();
    if(files!=null) { //some JVMs return null for empty dirs
        for(File f: files) {
            if(f.isDirectory()) {
                deleteFolder(f);
            } else {
                f.delete();
            }
        }
    }
    folder.delete();
  }
}
