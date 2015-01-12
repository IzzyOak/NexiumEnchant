package com.nexiummc.nexiumenchant.handlers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Handling tools.
 * @author searchndstroy
 */
public final class ToolHandler {
	
	/**
	 * @param itemStack Item to retrieve tool type for.
	 * @return Tool type for the item.
	 * @see {@link ToolHandler#fromMaterial(Material)}
	 */
	public static ToolType fromItem(ItemStack itemStack) {
		return fromMaterial(itemStack.getType());
	}
	
	/**
	 * @param material Material to retrieve tool type for.
	 * @return Tool type for the material.
	 */
	public static ToolType fromMaterial(Material material) {
		String name = material.name();
		String underScore = "_";
		for (ToolType toolType : ToolType.values()) {
			if (name.endsWith(underScore + StringHandler.split(toolType.name(), '_', false))) {
				return toolType;
			}
		}
		return ToolType.ALL;
	}
	
	
	private ToolHandler() {
		
	}
	
	/**
	 * @author searchndstroy
	 */
	public enum ToolType {
		
		/**
		 * All tools. (Including items as dirt.)
		 */
		ALL,
		
		/**
		 * Axe tool type.
		 */
		AXE,
		
		/**
		 * Hoe tool type.
		 */
		HOE,
		
		/**
		 * Pickaxe tool type.
		 */
		PICKAXE,
		
		/**
		 * Shovel tool type.
		 */
		SPADE,
		
		/**
		 * Sword tool type.
		 */
		SWORD;
	}
}