package com.nexiummc.nexiumenchant.enchantments;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;

public class Thunderblow extends BaseEnchantment implements ToolEnchantment, Listener{


	public Thunderblow() {
		super(NexiumEnchant.plugin, "Thunder", null, (short) 0, (short) 2);
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

	@SuppressWarnings("deprecation")
	@Override
	public void onAttackEntity(ItemStack tool, LivingEntity attacker,
			LivingEntity defender, short enchantLevel,
			boolean usingCorrectToolType, EntityDamageByEntityEvent event) {
		
		Random random = new Random();
		int i = enchantLevel;
		while(i != 0) {
			if(random.nextInt(100) < 20) {
	//			Player light = (Player) event.getDamager();
		//		double before = ((Damageable) light).getHealth();
				
				defender.getWorld().strikeLightning(event.getEntity().getLocation());
				if(event.getCause() == DamageCause.LIGHTNING){
					if(event.getEntity() == attacker){
						event.setDamage(0);
					}
					
				}
				
				//Attacker doesn't receive lightning dmg
				
			}
			--i;
		}		
		if(event.getCause() == DamageCause.LIGHTNING){
			if(event.getEntity() == attacker){
				event.setDamage(0);
			}
			
		}
	}

	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		return new ChatColor[] {ChatColor.BLUE};
	}
}