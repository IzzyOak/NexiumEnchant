package com.nexiummc.nexiumenchant.enchantments;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ToolEnchantment;
import com.nexiummc.nexiumenchant.handlers.ToolHandler.ToolType;

public class ChillingBlow extends BaseEnchantment implements ToolEnchantment, Listener{

	public ChillingBlow() {
		super("Chillingblow", null, (short) 0, (short) 2);
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

	int		SlowStrength =5;
	int		SlowDuration=40;
	int		chanceFreeze=60;
	int		chanceSpecialFreeze=10;
	boolean	specialFreeze=true;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onAttackEntity(ItemStack tool, LivingEntity attacker,
			LivingEntity defender, short enchantLevel,
			boolean usingCorrectToolType, EntityDamageByEntityEvent event) {
		
		
		Random random = new Random();
		int i = random.nextInt(100);

		if(i < chanceFreeze) {
			((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, SlowDuration, SlowStrength, false), true);
			event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.DIG_SNOW, 4f, 2f);
		}
		if(specialFreeze) {
			if(i < chanceSpecialFreeze) {

				if(event.getEntity() instanceof Player) {
					((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 10));

					Location loc = event.getEntity().getLocation();
					loc.setY(loc.getY() + 1);
					Location loc1 = event.getEntity().getLocation();
					Location loc2 = event.getEntity().getLocation();
					Location loc3 = event.getEntity().getLocation();
					Location loc4 = event.getEntity().getLocation();
					Location loc5 = event.getEntity().getLocation();
					Location loc6 = event.getEntity().getLocation();
					Location loc7 = event.getEntity().getLocation();
					Location loc8 = event.getEntity().getLocation();
					Location loc9 = event.getEntity().getLocation();
					Location loc10 = event.getEntity().getLocation();
					loc1.setY(loc1.getY() - 1);
					loc2.setX(loc2.getX() - 1);
					loc3.setX(loc3.getX() + 1);
					loc4.setY(loc4.getY() + 2);
					loc5.setZ(loc5.getZ() - 1);
					loc6.setZ(loc6.getZ() + 1);
					final Material mat1 = loc1.getBlock().getType();
					final Material mat2 = loc2.getBlock().getType();
					final Material mat3 = loc3.getBlock().getType();
					final Material mat4 = loc4.getBlock().getType();
					final Material mat5 = loc5.getBlock().getType();
					final Material mat6 = loc6.getBlock().getType();
					loc1.getBlock().setType(Material.ICE);
					loc2.getBlock().setType(Material.ICE);
					loc3.getBlock().setType(Material.ICE);
					loc4.getBlock().setType(Material.ICE);
					loc5.getBlock().setType(Material.ICE);
					loc6.getBlock().setType(Material.ICE);
					loc7 = loc2.clone();
					loc7.setY(loc7.getY() + 1);
					loc8 = loc3.clone();
					loc8.setY(loc8.getY() + 1);
					loc9 = loc5.clone();
					loc9.setY(loc9.getY() + 1);
					loc10 = loc6.clone();
					loc10.setY(loc10.getY() + 1);
					final Material mat7 = loc7.getBlock().getType();
					final Material mat8 = loc8.getBlock().getType();
					final Material mat9 = loc9.getBlock().getType();
					final Material mat10 = loc10.getBlock().getType();

					loc7.getBlock().setType(Material.ICE);
					loc8.getBlock().setType(Material.ICE);
					loc9.getBlock().setType(Material.ICE);
					loc10.getBlock().setType(Material.ICE);

					event.getEntity().getWorld().playSound(loc, Sound.ITEM_BREAK, 1000f, 1f);

					final Location loc11 = loc1.clone();
					final Location loc12 = loc2.clone();
					final Location loc13 = loc3.clone();
					final Location loc14 = loc4.clone();
					final Location loc15 = loc5.clone();
					final Location loc16 = loc6.clone();
					final Location loc17 = loc7.clone();
					final Location loc18 = loc8.clone();
					final Location loc19 = loc9.clone();
					final Location loc20 = loc10.clone();

					Bukkit.getScheduler().runTaskLater(NexiumEnchant.instance(),new BukkitRunnable() {

						@Override
						public void run() {
							if(mat1 != Material.ICE)
								loc11.getBlock().setType(mat1);
							if(mat2 != Material.ICE)
								loc12.getBlock().setType(mat2);
							if(mat3 != Material.ICE)
								loc13.getBlock().setType(mat3);
							if(mat4 != Material.ICE)
								loc14.getBlock().setType(mat4);
							if(mat5 != Material.ICE)
								loc15.getBlock().setType(mat5);
							if(mat6 != Material.ICE)
								loc16.getBlock().setType(mat6);
							if(mat7 != Material.ICE)
								loc17.getBlock().setType(mat7);
							if(mat8 != Material.ICE)
								loc18.getBlock().setType(mat8);
							if(mat9 != Material.ICE)
								loc19.getBlock().setType(mat9);
							if(mat10 != Material.ICE)
								loc20.getBlock().setType(mat10);
							this.cancel();
						}
					}, 40L);
					//.runTaskLater(getPlugin(), 40l);
				}
			}
		}
	}

	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		return new ChatColor[] {ChatColor.AQUA};
	}
}
