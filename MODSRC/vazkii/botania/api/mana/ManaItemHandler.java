/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Mar 13, 2014, 5:32:24 PM (GMT)]
 */
package vazkii.botania.api.mana;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public final class ManaItemHandler {

	/**
	 * Requests mana from items in a given player's inventory. Note that stack.getItem()
	 * must be instanceof IManaItem. 
	 * @param manaToGet How much mana is to be requested, if less mana exists than this amount,
	 * the amount of mana existent will be returned instead, if you want exact values use requestManaExact.
	 * @param remove If true, the mana will be removed from the target item. Set to false to just check.
	 * @return The amount of mana received from the request.
	 */
	public static int requestMana(ItemStack stack, EntityPlayer player, int manaToGet, boolean remove) {
		if(stack == null)
			return 0;
		
		for(ItemStack stackInSlot : player.inventory.mainInventory) {
			if(stackInSlot == stack)
				continue;
			
			if(stackInSlot != null && stackInSlot.getItem() instanceof IManaItem) {
				IManaItem manaItem = (IManaItem) stackInSlot.getItem();
				if(manaItem.canExportManaToItem(stackInSlot, stack) && manaItem.getMana(stackInSlot) > 0) {
					if(stack.getItem() instanceof IManaItem && !((IManaItem) stack.getItem()).canReceiveManaFromItem(stack, stackInSlot))
						continue;
					
					int mana = Math.min(manaToGet, manaItem.getMana(stackInSlot));
					
					if(remove)
						manaItem.addMana(stackInSlot, -mana);
					
					return mana;
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * Requests an exact amount of mana from items in a given player's inventory. 
	 * Note that stack.getItem() must be instanceof IManaItem. 
	 * @param manaToGet How much mana is to be requested, if less mana exists than this amount,
	 * false will be returned instead, and nothing will happen.
	 * @param remove If true, the mana will be removed from the target item. Set to false to just check.
	 * @return If the request was succesful.
	 */
	public static boolean requestManaExact(ItemStack stack, EntityPlayer player, int manaToGet, boolean remove) {
		if(stack == null)
			return false;
		
		for(ItemStack stackInSlot : player.inventory.mainInventory) {
			if(stackInSlot == stack)
				continue;
			
			if(stackInSlot != null && stackInSlot.getItem() instanceof IManaItem) {
				IManaItem manaItemSlot = (IManaItem) stackInSlot.getItem();
				if(manaItemSlot.canExportManaToItem(stackInSlot, stack) && manaItemSlot.getMana(stackInSlot) > manaToGet) {
					if(stack.getItem() instanceof IManaItem && !((IManaItem) stack.getItem()).canReceiveManaFromItem(stack, stackInSlot))
						continue;
					
					if(remove)
						manaItemSlot.addMana(stackInSlot, -manaToGet);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
}