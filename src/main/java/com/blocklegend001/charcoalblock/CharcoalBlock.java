package com.blocklegend001.charcoalblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class CharcoalBlock implements ModInitializer {
    public static final String MOD_ID = "charcoalblock";
    public static final Identifier CHARCOAL_BLOCK_ID = Identifier.fromNamespaceAndPath(MOD_ID, "charcoal_block");
    public static final Identifier CHARCOAL_ITEM_ID = Identifier.fromNamespaceAndPath(MOD_ID, "charcoal_block");

    public static Block CHARCOAL_BLOCK = new Block(BlockBehaviour.Properties
            .of().mapColor(MapColor.COLOR_BLACK)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.STONE)
            .setId(ResourceKey.create(Registries.BLOCK, Identifier.parse(CHARCOAL_BLOCK_ID.toString()))));

    public static BlockItem CHARCOAL_BLOCK_ITEM = new BlockItem(CHARCOAL_BLOCK, new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, Identifier.parse(CHARCOAL_ITEM_ID.toString()))));

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK_ITEM);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((content) -> {
            content.accept(CHARCOAL_BLOCK_ITEM);
        });

        FuelValueEvents.BUILD.register(((builder, context) -> {
            builder.add(CHARCOAL_BLOCK, 16000);
        }));
    }
}