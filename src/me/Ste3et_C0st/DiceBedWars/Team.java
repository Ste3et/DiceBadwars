package me.Ste3et_C0st.DiceBedWars;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
 
public class Team {

public static ArrayList<Team> teams = new ArrayList<Team>();

public static HashMap<Player, Team> playerTeams = new HashMap<Player, Team>();

public List<Player> size = new ArrayList<Player>();
public String teamColor;
public Location Spawn;
public String Name;
public Block bed;
public BlockFace face;
public Boolean BedState;
public Inventory Enderchest;

public Team(String teamcolor, String name, Location spawn, Block block, BlockFace side){
teamColor = teamcolor;
Spawn = spawn;
Name = name;
bed = block;
face = side;
BedState = true;
Enderchest = null;
setBed();
teams.add(this);
}

public static void addPlayer(Team team, Player p){
playerTeams.put(p, team);
team.size.add(p);
}

public static void removePlayer(Player p){
if(hasTeam(p) == true){
Team t = getTeam(p);
t.size.remove(p);
playerTeams.remove(p);
ArenaManager.getManager().hideP();
}
}

public static boolean hasTeam(Player p){
return playerTeams.containsKey(p);
}

public static Team getTeam(Player p){
if(hasTeam(p) == true){
return playerTeams.get(p);
}

else if(hasTeam(p) == false){

return null;
}
return null;
}

public String getTeamColor(){
return teamColor;
}

@SuppressWarnings("deprecation")
public void setBed() {
	if(face == BlockFace.NORTH){
		Location l = bed.getLocation();
		l.getBlock().setType(Material.AIR);
		l.getBlock().setType(Material.BED_BLOCK);
		Block block = bed;
        BlockState bedFoot = block.getState();
        BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.SOUTH).getState();
        bedFoot.setType(Material.BED_BLOCK);
        bedHead.setType(Material.BED_BLOCK);
        bedFoot.setRawData((byte) 0);
        bedHead.setRawData((byte) 8);
        bedFoot.update(true, false);
        bedHead.update(true, true);
	}else if(face == BlockFace.EAST){
		Location l = bed.getLocation();
		l.getBlock().setType(Material.AIR);
		l.getBlock().setType(Material.BED_BLOCK);
		Block block = bed;
		BlockState bedFoot = block.getState();
        BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.WEST).getState();
        bedFoot.setType(Material.BED_BLOCK);
        bedHead.setType(Material.BED_BLOCK);
        bedFoot.setRawData((byte) 1);
        bedHead.setRawData((byte) 9);
        bedFoot.update(true, false);
        bedHead.update(true, true);
	}else if(face == BlockFace.SOUTH){
		Location l = bed.getLocation();
		l.getBlock().setType(Material.AIR);
		l.getBlock().setType(Material.BED_BLOCK);
		Block block = bed;
		BlockState bedFoot = block.getState();
        BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.NORTH).getState();
        bedFoot.setType(Material.BED_BLOCK);
        bedHead.setType(Material.BED_BLOCK);
        bedFoot.setRawData((byte) 2);
        bedHead.setRawData((byte) 10);
        bedFoot.update(true, false);
        bedHead.update(true, true);
	}else if(face == BlockFace.WEST){
		Location l = bed.getLocation();
		l.getBlock().setType(Material.AIR);
		l.getBlock().setType(Material.BED_BLOCK);
		Block block = bed;
		BlockState bedFoot = block.getState();
        BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.EAST).getState();
        bedFoot.setType(Material.BED_BLOCK);
        bedHead.setType(Material.BED_BLOCK);
        bedFoot.setRawData((byte) 3);
        bedHead.setRawData((byte) 11);
        bedFoot.update(true, false);
        bedHead.update(true, true);
	}
}

public int getSize() {
	if(size.isEmpty()){
		return 0;
	}else{
		return size.size();
	}
}

public List<Player> teamGetList() {
	return size;
}

public String getName() {
	return Name;
}

public void setBedState(boolean b) {
	this.BedState = b;
}

public Location getSpawn() {
	return this.Spawn;
}

public Block getBed() {
	return this.bed;
}

public BlockFace getFace() {
	return this.face;
}

public boolean getState() {
	return this.BedState;
}

public Inventory getEnderchest() {
	return this.Enderchest;
}

public void setEnderchest(Inventory inventory) {
	this.Enderchest = inventory;
}

public void addBed(Block b) {
	this.bed = b;
}

public void addFace(BlockFace b) {
	this.face = b;
}

public boolean check() {
	if(this.face == null || this.bed == null || this.Spawn == null){
		return false;
	}else{
		return true;
	}
}

public void removeBed() {
	this.bed = null;
	this.face = null;
}

public void setSpawn(Location l) {
	this.Spawn = l;
}

public void setName(String name2) {
	this.Name = name2;
}

public void setColor(String color) {
	this.teamColor = color;
}

}