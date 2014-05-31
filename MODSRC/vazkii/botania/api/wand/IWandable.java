/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Jan 22, 2014, 5:12:53 PM (GMT)]
 */
package vazkii.botania.api.wand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Any block that implements this can be used with the Wand for the Forest for some purpose.
 */
public interface IWandable {

	/**
	 * Called when the item is used by a wand. Note that the player parameter can be null
	 * if this function is called from a dispenser.
	 */
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side);

}