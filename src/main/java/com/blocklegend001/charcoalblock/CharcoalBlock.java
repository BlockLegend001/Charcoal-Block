package com.blocklegend001.charcoalblock;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
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

	public static BlockItem CHARCOAL_BLOCK_ITEM = new BlockItem(CHARCOAL_BLOCK, new QuiltItemSettings());

	@Override
	public void onInitialize(ModContainer mod) {
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK_ITEM);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((content) -> {
			content.addItem(CHARCOAL_BLOCK_ITEM);
		});

		FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
		fuelRegistry.add(CHARCOAL_BLOCK, 16000);
	}
}
