package fr.virthia.utils.GUI;

import fr.virthia.utils.AnvilGUI.AnvilGUI;
import fr.virthia.utils.bukkit.plugin.Utils;
import fr.virthia.utils.item.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public final class GUI {
	private final String displayName;
	private final String name;
	private final Type type;
	private final Inventory inventory;
	private static final HashMap<String, GUI> s_all_display_name_to_gui;
	private static final HashMap<String, GUI> s_all_name_to_gui;

	static {
		s_all_display_name_to_gui = new HashMap<>();
		s_all_name_to_gui = new HashMap<>();
	}

	private final HashMap<Integer, Button> button = new HashMap<>();
	private final HashMap<String, Displayer> displayers = new HashMap<>();
	private final HashMap<Displayer, Integer> displayersToId = new HashMap<>();
	public GUI(String name, Type type, Inventory inventory, String displayName) {
		this.displayName = displayName;
		this.name=name;
		this.type=type;
		this.inventory=inventory;
		s_all_display_name_to_gui.put(displayName, this);
		s_all_name_to_gui.put(name, this);
	}

	public static void openGUI(String name, Player player) {
		try{
			player.openInventory(s_all_name_to_gui.get(name).getInventory());
		}catch (NullPointerException e){System.out.println("GUI "+name+" don't exists");}
	}

	public void addDisplayer(int id, Displayer displayer) {
		displayers.put(displayer.getName(), displayer);
		displayersToId.put(displayer, id);
		inventory.setItem(id, displayer.toItemStack());
	}

	public void addToDisplayer(int value, String name){
		Displayer displayer = displayers.get(name);
		displayer.addValue(value);
		getInventory().setItem(displayersToId.get(displayer),
						displayer.toItemStack());
	}
	public Displayer getDisplayer(String name){
		return displayers.get(name);
	}
	public static void modifyDisplayerWithAnvilGUI(GUI gui, Displayer displayer, Player player){
		AnvilGUI anvilGui = new AnvilGUI(player, new AnvilGUI.AnvilClickEventHandler() {
			@Override
			public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
				if(event.getSlot()==AnvilGUI.AnvilSlot.OUTPUT){
					event.setWillClose(true);
					event.setWillDestroy(true);
					displayer.setValueStr(event.getName());
					gui.getInventory().setItem(gui.displayersToId.get(displayer),
							displayer.toItemStack());
					Bukkit.getScheduler().runTaskLater(Utils.INSTANCE, ()->{
						player.openInventory(gui.getInventory());
					},2);
				}else {
					event.setWillDestroy(false);
					event.setWillClose(false);
				}
			}
		});
		anvilGui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemCreator(displayer.getValueStr(), Material.NAME_TAG, 1).make());
		anvilGui.open();
	}

	public void switchDisplayer(String name){
		Displayer displayer = displayers.get(name);
		displayer.toogleActevated();
		getInventory().setItem(displayersToId.get(displayer),
				displayer.toItemStack());
	}

	public boolean getDisplayerBoolean(String name){
		Displayer displayer = displayers.get(name);
		return displayer.isActivated();
	}

	public void setDisplayerText(String name, String text){
		Displayer displayer = displayers.get(name);
		displayer.setValueStr(text);
		getInventory().setItem(displayersToId.get(displayer),
				displayer.toItemStack());
	}

	public double getDisplayerValue(String name){
		Displayer displayer =displayers.get(name);
		return displayer.getMultiplier()*displayer.getValue();
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public void addButton(Button button, int slot) {
		this.button.put(slot, button);
	}
	
	public final Inventory getInventory() {
		return inventory;
	}

	public static GUI getGUI(String str) {
		return s_all_name_to_gui.get(str);
	}

	public static GUI getGUIWithDisplayName(String str) {
		return s_all_display_name_to_gui.get(str);
	}
	
	public final String getName() {
		return name;
	}
	
	public static void clickInInventory(Inventory inv, int slot, Player player, ClickType clickType) {
		GUI gui = getGUIWithDisplayName(inv);
		if(gui != null) {
			if(gui.button.containsKey(slot)) {
				if(gui.button.get(slot)!=null) {
					getGUIWithDisplayName(inv).button.get(slot).use(clickType, player);
				}
			}
		}
	}
	
	public static GUI getGUIWithDisplayName(Inventory inv) {
		for(String str : s_all_display_name_to_gui.keySet()) {
			if(inv.getName().contains(str)) {
				if(inv.getName().startsWith(s_all_display_name_to_gui.get(str).type.getCode())) {
					return s_all_display_name_to_gui.get(str);
				}
			}
		}
		return null;
			
	}
}
