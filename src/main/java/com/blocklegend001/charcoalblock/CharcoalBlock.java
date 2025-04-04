package com.blocklegend001.charcoalblock;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = CharcoalBlock.MODID, bus = EventBusSubscriber.Bus.MOD)
@Mod(CharcoalBlock.MODID)
public class CharcoalBlock {
    public static final String MODID = "charcoalblock";

    public static final ResourceKey<?> CHARCOAL_BLOCK_ID = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, "charcoal_block"));
    public static final ResourceKey<?> CHARCOAL_ITEM_ID = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "charcoal_block"));

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredBlock<Block> CHARCOAL_BLOCK = BLOCKS.registerSimpleBlock("charcoal_block", BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .strength(5.0F, 6.0F)
            .sound(SoundType.STONE)
            .setId(ResourceKey.create(Registries.BLOCK, CHARCOAL_BLOCK_ID.location()))
            .requiresCorrectToolForDrops());

    public static final DeferredItem<BlockItem> CHARCOAL_BLOCK_ITEM = ITEMS.register("charcoal_block", () -> new BlockItem(CHARCOAL_BLOCK.get(), new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, CHARCOAL_ITEM_ID.location()))) {

        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
            return 16000;
        }
    });

    public CharcoalBlock(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(new ItemStack(CHARCOAL_BLOCK_ITEM.get()));
    }
}