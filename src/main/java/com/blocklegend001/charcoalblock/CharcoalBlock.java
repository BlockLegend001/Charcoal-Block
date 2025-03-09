package com.blocklegend001.charcoalblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class CharcoalBlock implements ModInitializer {
    public static final String MOD_ID = "charcoalblock";
    public static final Identifier CHARCOAL_BLOCK_ID = Identifier.of(MOD_ID, "charcoal_block");
    public static final Identifier CHARCOAL_ITEM_ID = Identifier.of(MOD_ID, "charcoal_block");

    public static Block CHARCOAL_BLOCK = new Block(Block.Settings
            .create()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, CHARCOAL_BLOCK_ID))
            .mapColor(MapColor.BLACK)
            .requiresTool()
            .strength(5.0F, 6.0F)
            .sounds(BlockSoundGroup.STONE));

    public static BlockItem CHARCOAL_BLOCK_ITEM = new BlockItem(CHARCOAL_BLOCK, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, CHARCOAL_ITEM_ID)));

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK_ITEM);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((content) -> {
            content.add(CHARCOAL_BLOCK_ITEM);
        });

        FuelRegistryEvents.BUILD.register(((builder, context) -> {
            builder.add(CHARCOAL_BLOCK, 16000);
        }));
    }
}