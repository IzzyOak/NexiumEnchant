package com.nexiummc.nexiumenchant.test;


import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.UniversalEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.EnchantType;
import com.nexiummc.nexiumenchant.handlers.EnchantmentHandler;

public class UniversalTest extends UniversalEnchantment {
	
	private static final ItemStack snowBallItem = new ItemStack(Material.SNOW_BALL);
	
	static {
		EnchantmentHandler.applyEnchantment(snowBallItem, NexiumEnchant.projectileEnchantment, (short) 1);
	}
	
	public UniversalTest() {
		super("Projectile Test", null, (short) 1,(short) 5);
	}
	
	@Override
	public void onShotSuccess(ItemStack bow, int ticksTravelled, short enchantLevel, LivingEntity attacker, LivingEntity defender,
			EntityDamageByEntityEvent event) {
		defender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0), true);
	}
	
	@Override
	public boolean onShotHitObject(ItemStack bow, int ticksTravelled, short enchantLevel, LivingEntity shooter,
			ProjectileHitEvent event) {
		Location location = event.getEntity().getLocation();
		location.getBlock().setType(Material.ANVIL);
		ThrownPotion snowball;
		Random random = new Random();
		location.setY(location.getY() + 4);
		location.getWorld().playSound(location, Sound.AMBIENCE_THUNDER, 100, 0.5f);
		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 16428);
		for (int i = 0; i < 9; i++) {
			snowball = location.getWorld().spawn(location, ThrownPotion.class);
			snowball.setItem(itemStack);
			snowball.setFireTicks(10000);
			snowball.setMetadata(NexiumEnchant.ITEMSTACK_METADATA, new FixedMetadataValue(NexiumEnchant.instance(), snowBallItem));
			Vector velocity = new Vector(randomNegative(random.nextInt(10), random), 10, randomNegative(random.nextInt(10), random)).normalize();
			snowball.setVelocity(velocity);
		}
		return true;
	}
	
	private int randomNegative(int someInt, Random random) {
		return random.nextInt(2) == 0 ? -someInt : someInt;
	}
	
	@Override
	public void onBreakBlock(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType, BlockBreakEvent event) {
		switch (enchantLevel) {
			case 1:
				block.setType(Material.BEACON);
				break;
			case 2:
				block.setType(Material.ANVIL);
				break;
			default:
				block.setType(Material.BEDROCK);
				break;
		}
		event.setCancelled(true); //Illegal statement, SHOULD NEVER BE CALLED ON MONITOR. TODO Find a fix.
	}
	
	@Override
	public void onInteract(ItemStack tool, Block block, short enchantLevel, Player player,
			boolean usingCorrectToolType, PlayerInteractEvent event) {
	}
	
	@Override
	public void onConsume(Player player, ItemStack itemStack, short enchantLevel, PlayerItemConsumeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAttackEntity(ItemStack tool, LivingEntity attacker,
			LivingEntity defender, short enchantLevel, boolean usingCorrectToolType,
			EntityDamageByEntityEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public com.nexiummc.nexiumenchant.particle.Particle applyParticles(
			LivingEntity attacker, LivingEntity defender, short enchantLevel,
			Event event, EnchantType type) {
		// TODO Auto-generated method stub
		return null;
	}
}