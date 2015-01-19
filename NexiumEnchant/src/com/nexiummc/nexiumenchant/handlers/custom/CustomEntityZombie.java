package com.nexiummc.nexiumenchant.handlers.custom;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.EntitySkeleton;
import net.minecraft.server.v1_7_R4.EntityZombie;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import net.minecraft.server.v1_7_R4.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R4.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_7_R4.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_7_R4.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.util.UnsafeList;

public class CustomEntityZombie extends EntityZombie
	{

		public CustomEntityZombie(World world)
			{
				super(world);
				this.setCustomName("" + ChatColor.RED + ChatColor.BOLD + "Bandit");
				setCustomNameVisible(true);
				try
					{
						Field bField = PathfinderGoalSelector.class
								.getDeclaredField("b");
						bField.setAccessible(true);
						Field cField = PathfinderGoalSelector.class
								.getDeclaredField("c");
						cField.setAccessible(true);
						bField.set(goalSelector,
								new UnsafeList<PathfinderGoalSelector>());
						bField.set(targetSelector,
								new UnsafeList<PathfinderGoalSelector>());
						cField.set(goalSelector,
								new UnsafeList<PathfinderGoalSelector>());
						cField.set(targetSelector,
								new UnsafeList<PathfinderGoalSelector>());
					} catch (Exception exc)
					{
						exc.printStackTrace();
						// This means that the name of one of the fields changed
						// names or declaration and will have to be re-examined.
					}

				this.goalSelector.a(0, new PathfinderGoalFloat(this));
				this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this,
						EntityHuman.class, 1.0D, false));
				this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this,
						EntitySkeleton.class, 1.0D, true));
				this.goalSelector.a(5,
						new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
				this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(
						this, 1.0D, false));
				// this.goalSelector.a(7, new PathfinderGoalRandomStroll(this,
				// 1.0D));
				// this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this,
				// EntityHuman.class, 8.0F));
				// this.goalSelector
				// .a(8, new PathfinderGoalRandomLookaround(this));
				// this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this,
				// true));
				// this.targetSelector.a(2,
				// new PathfinderGoalNearestAttackableTarget(this,
				// EntityHuman.class, 0, true));
				// this.targetSelector.a(2,
				// new PathfinderGoalNearestAttackableTarget(this,
				// EntitySkeleton.class, 0, false));
			}

		@Override
		protected void aC()
			{
				super.aC();
				this.getAttributeInstance(GenericAttributes.e).setValue(300.0D); // Original
																					// 3.0D
				this.getAttributeInstance(GenericAttributes.d).setValue(0.0D);
			}
		
		@Override
		public void move(double d0, double d1, double d2){
		}
	}
