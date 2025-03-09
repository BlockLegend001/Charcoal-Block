package com.blocklegend001.charcoalblock;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CharcoalBlock implements ModInitializer {
	public static final String MOD_ID = "charcoalblock";

	public static Block CHARCOAL_BLOCK = new Block(QuiltBlockSettings.
			of(Material.STONE).
			mapColor(MapColor.BLACK).
			requiresTool().
			strength(5.0F, 6.0F));

	public static BlockItem CHARCOAL_BLOCK_ITEM = new BlockItem(CHARCOAL_BLOCK, new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS));

	@Override
	public void onInitialize(ModContainer mod) {
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK_ITEM);

		FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
		fuelRegistry.add(CHARCOAL_BLOCK, 16000);
	}
}
