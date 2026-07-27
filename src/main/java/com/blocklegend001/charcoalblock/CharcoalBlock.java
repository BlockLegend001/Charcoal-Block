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

    public static final ResourceKey<Block> CHARCOAL_BLOCK_KEY =
            ResourceKey.create(Registries.BLOCK, CHARCOAL_BLOCK_ID);
    public static final ResourceKey<Item> CHARCOAL_ITEM_KEY =
            ResourceKey.create(Registries.ITEM, CHARCOAL_ITEM_ID);

    public static Block CHARCOAL_BLOCK = new Block(BlockBehaviour.Properties
            .of().mapColor(MapColor.COLOR_BLACK)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.STONE)
            .setId(CHARCOAL_BLOCK_KEY));

    public static BlockItem CHARCOAL_BLOCK_ITEM = new BlockItem(CHARCOAL_BLOCK, new Item.Properties()
            .setId(CHARCOAL_ITEM_KEY));

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.BLOCK, CHARCOAL_BLOCK_KEY, CHARCOAL_BLOCK);
        Registry.register(BuiltInRegistries.ITEM, CHARCOAL_ITEM_KEY, CHARCOAL_BLOCK_ITEM);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((content) -> {
            content.accept(CHARCOAL_BLOCK_ITEM);
        });

        FuelValueEvents.BUILD.register(((builder, context) -> {
            builder.add(CHARCOAL_BLOCK, 16000);
        }));
    }
}