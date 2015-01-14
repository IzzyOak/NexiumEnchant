package com.nexiummc.nexiumenchant.enchantments;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.Tools;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;
import com.nexiummc.nexiumenchant.scheduler.Timer;

public class Deepwounds extends BaseEnchantment implements ToolEnchantment{
	
	Timer cd;
	Tools tl;
	
	public Deepwounds() {
		super(NexiumEnchant.plugin,"Deepwounds", null, (short) 0, (short) 2);
	}

	@Override
	public ToolType getToolType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onBreakBlock(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType, BlockBreakEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInteract(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType,
			PlayerInteractEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAttackEntity(ItemStack tool, LivingEntity attacker,
			LivingEntity defender, short enchantLevel,
			boolean usingCorrectToolType, EntityDamageByEntityEvent event) {
		// && !defender.hasMetadata("ce.bleed")
		if(!cd.getHasCooldown(attacker)) {

			Random random = new Random();
			if(random.nextInt(100) < 20) {
				cd.generateCooldown(attacker, 140);
				tl.applyBleed(defender, 20*enchantLevel);
			}
			}
		
	}

	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		return new ChatColor[] {ChatColor.DARK_RED};
	}
}
