package fr.virthia.utils.item;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class ItemCreator {
    private String name, headName = "stone";
    private Material type = Material.STONE;
    private int amount = 1;
    private ItemFlag[] itemFlags = new ItemFlag[0];
    private String[] lores = new String[0];
    private ImmutablePair[] echantements = new ImmutablePair[0];
    private short data = 0;
    private CustomItemType itemType;
    private Pattern[] bannerPatterns = new Pattern[0];

    public ItemCreator(){this.itemType = CustomItemType.NORMAL;}

    public ItemCreator(Material type, byte data) {
        this.itemType = CustomItemType.NORMAL;
        this.type = type;
        this.data = data;
    }

    public ItemCreator(String name){
        this.name = name;
        this.itemType = CustomItemType.NORMAL;
    }

    public ItemCreator(String name, Material type, int amount) {
        this.itemType = CustomItemType.NORMAL;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public ItemCreator(String name, Material type) {
        this.itemType = CustomItemType.NORMAL;
        this.name = name;
        this.type = type;
    }

    public ItemCreator(String name, Material type, short data) {
        this.itemType = CustomItemType.NORMAL;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public ItemCreator(String name, Material type, int amount, short data) {
        this.itemType = CustomItemType.NORMAL;
        this.name = name;
        this.type = type;
        this.data = data;
        this.amount = amount;
    }

    public ItemCreator(String name, Material type, int amount, String[] lores) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.lores = lores;
    }

    public ItemCreator(String name, Material type, int amount, ItemFlag[] itemFlags, String[] lores) {
        this.itemType = CustomItemType.NORMAL;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.itemFlags = itemFlags;
        this.lores = lores;
    }

    public ItemCreator(String name, Material type, int amount, ImmutablePair<Enchantment, Integer>[] echantements) {
        this.itemType = CustomItemType.NORMAL;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.echantements = echantements;
    }

    public ItemCreator(String name, int amount, String itemType) {
        this.itemType = CustomItemType.getType(itemType);
        this.name = name;
        this.amount = amount;
    }

    public ItemCreator(String name, String itemType) {
        this.itemType = CustomItemType.getType(itemType);
        this.name = name;
    }

    public ItemCreator addGlow(){
        echantements = new ImmutablePair[] {new ImmutablePair(Enchantment.PROTECTION_ENVIRONMENTAL, 1)};
        ItemFlag[] tmpItemFlags = new ItemFlag[itemFlags.length+1];
        for (int i = 0; i < itemFlags.length; i++) {
            tmpItemFlags[i] = itemFlags[i];
        }
        tmpItemFlags[itemFlags.length]=ItemFlag.HIDE_ENCHANTS;
        itemFlags=tmpItemFlags;
        return this;
    }

    public ItemCreator setHeadName(String headName) {
        if(CustomItemType.HEAD != itemType) return this;
        this.headName = headName;
        return this;
    }

    public ItemCreator addEnchantment(Enchantment enchantment, int lvl){
        ImmutablePair[] tmpEnchantments = new ImmutablePair[echantements.length+1];
        for (int i = 0; i < echantements.length; i++) {
            tmpEnchantments[i] = echantements[i];
        }
        tmpEnchantments[echantements.length] = new ImmutablePair(enchantment, lvl);
        echantements = tmpEnchantments;
        return this;
    }

    public ItemCreator addItemFlag(ItemFlag itemFlag){
        ItemFlag[] tmpItemFlags = new ItemFlag[itemFlags.length+1];
        for (int i = 0; i < itemFlags.length; i++) {
            tmpItemFlags[i] = itemFlags[i];
        }
        tmpItemFlags[itemFlags.length]=itemFlag;
        itemFlags=tmpItemFlags;
        return this;
    }

    public ItemCreator addBannerPattern(Pattern bannerPattern){
        if(CustomItemType.BANNER != itemType) return this;
        Pattern[] tmpBannerPatterns = new Pattern[bannerPatterns.length+1];
        for (int i = 0; i < bannerPatterns.length; i++) {
            tmpBannerPatterns[i] = bannerPatterns[i];
        }
        tmpBannerPatterns[bannerPatterns.length]=bannerPattern;
        bannerPatterns=tmpBannerPatterns;
        return this;
    }

    public ItemCreator setBannerPatterns(Pattern[] bannerPatterns) {
        if(CustomItemType.BANNER != itemType) return this;
        this.bannerPatterns = bannerPatterns;
        return this;
    }

    public ItemCreator setName(String name) {
        this.name = name;
        return this;
    }

    public ItemCreator setType(Material type) {
        this.type = type;
        return this;
    }

    public ItemCreator setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemCreator setItemFlags(ItemFlag[] itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    public ItemCreator setLores(String[] lores) {
        this.lores = lores;
        return this;
    }

    public ItemCreator setEchantements(ImmutablePair<Enchantment, Integer>[] echantements) {
        this.echantements = echantements;
        return this;
    }

    public ItemCreator setData(short data){
        this.data = data;
        return this;
    }



    public ItemStack make(){
        ItemStack item;
        switch (itemType){
            case NORMAL:
                ItemStack itemNorm = new ItemStack(type, amount, data);
                ItemMeta itemMNorm = itemNorm.getItemMeta();
                if(name != null)
                    itemMNorm.setDisplayName(name);
                if(!(lores.length<=0)){
                    ArrayList<String> loresArrayList = new ArrayList<>();
                    for(String lore : lores){
                        loresArrayList.add(lore);
                    }
                    itemMNorm.setLore(loresArrayList);
                }
                if(!(echantements.length<=0)){
                    for(ImmutablePair<Enchantment, Integer> echantement : echantements){
                        itemMNorm.addEnchant(echantement.left, echantement.right, true);
                    }
                }
                if(!(itemFlags.length<=0)){
                    itemMNorm.addItemFlags(itemFlags);
                }
                itemNorm.setItemMeta(itemMNorm);
                item = itemNorm;
                break;
            case ENCHANTED_BOOK:
                ItemStack itemBook = new ItemStack(Material.ENCHANTED_BOOK, amount);
                EnchantmentStorageMeta itemMBook = (EnchantmentStorageMeta) itemBook.getItemMeta();
                if(name != null)
                    itemMBook.setDisplayName(name);
                if(!(lores.length<=0)){
                    ArrayList<String> loresArrayList = new ArrayList<>();
                    for(String lore : lores){
                        loresArrayList.add(lore);
                    }
                    itemMBook.setLore(loresArrayList);
                }
                if(!(echantements.length<=0)){
                    for(ImmutablePair<Enchantment, Integer> echantement : echantements){
                        itemMBook.addStoredEnchant(echantement.left, echantement.right, true);
                    }
                }
                if(!(itemFlags.length<=0)){
                    itemMBook.addItemFlags(itemFlags);
                }
                itemBook.setItemMeta(itemMBook);
                item = itemBook;
                break;
            case BANNER:
                ItemStack itemBann = new ItemStack(Material.BANNER, amount, data);
                BannerMeta itemMBann = (BannerMeta) itemBann.getItemMeta();
                for(Pattern pattern : bannerPatterns){
                    itemMBann.addPattern(pattern);
                }
                if(name != null)
                    itemMBann.setDisplayName(name);
                if(!(lores.length<=0)){
                    ArrayList<String> loresArrayList = new ArrayList<>();
                    for(String lore : lores){
                        loresArrayList.add(lore);
                    }
                    itemMBann.setLore(loresArrayList);
                }
                if(!(echantements.length<=0)){
                    for(ImmutablePair<Enchantment, Integer> echantement : echantements){
                        itemMBann.addEnchant(echantement.left, echantement.right, true);
                    }
                }
                if(!(itemFlags.length<=0)){
                    itemMBann.addItemFlags(itemFlags);
                }
                itemBann.setItemMeta(itemMBann);
                item = itemBann;
                break;
            case HEAD:
                ItemStack itemHead = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
                SkullMeta itemMHead = (SkullMeta) itemHead.getItemMeta();
                itemMHead.setOwner(headName);
                if(name != null)
                    itemMHead.setDisplayName(name);
                if(!(lores.length<=0)){
                    ArrayList<String> loresArrayList = new ArrayList<>();
                    for(String lore : lores){
                        loresArrayList.add(lore);
                    }
                    itemMHead.setLore(loresArrayList);
                }
                if(!(echantements.length<=0)){
                    for(ImmutablePair<Enchantment, Integer> echantement : echantements){
                        itemMHead.addEnchant(echantement.left, echantement.right, true);
                    }
                }
                if(!(itemFlags.length<=0)){
                    itemMHead.addItemFlags(itemFlags);
                }
            default:
                item=new ItemStack(Material.STONE);
        }


        return item;
    }
}

enum CustomItemType{
    BANNER, ENCHANTED_BOOK, NORMAL, HEAD;
    public static CustomItemType getType(String str){
        switch (str){
            case "banner":
            case "BANNER":
                return BANNER;
            case "enchanted_book":
            case "ENCHANTED_BOOK":
                return ENCHANTED_BOOK;
            case "head":
            case "HEAD":
                return HEAD;
            default:
                return NORMAL;
        }
    }
}
