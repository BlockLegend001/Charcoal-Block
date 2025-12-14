package com.blocklegend001.charcoalblock;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CharcoalBlock.MODID)
public class CharcoalBlock {

    public static final String MODID = "charcoalblock";

    public static final ResourceKey<?> CHARCOAL_BLOCK_ID = ResourceKey.create(ForgeRegistries.Keys.BLOCKS, Identifier.fromNamespaceAndPath(MODID, "charcoal_block"));
    public static final ResourceKey<?> CHARCOAL_ITEM_ID = ResourceKey.create(ForgeRegistries.Keys.ITEMS, Identifier.fromNamespaceAndPath(MODID, "charcoal_block"));

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> CHARCOAL_BLOCK = BLOCKS.register("charcoal_block", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).
                    mapColor(MapColor.COLOR_BLACK).
                    requiresCorrectToolForDrops().
                    strength(5.0F, 6.0F).
                    setId(ResourceKey.create(ForgeRegistries.Keys.BLOCKS, CHARCOAL_BLOCK_ID.identifier()))));

    public static final RegistryObject<Item> CHARCOAL_BLOCK_ITEM = ITEMS.register("charcoal_block", () -> new BlockItem(CHARCOAL_BLOCK.get(), new Item.Properties().
            setId(ResourceKey.create(ForgeRegistries.Keys.ITEMS, CHARCOAL_ITEM_ID.identifier())))

    {
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
        {
            return 16000;
        }
    });

    public CharcoalBlock(FMLJavaModLoadingContext context) {
        var modEventBus = context.getModBusGroup();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BuildCreativeModeTabContentsEvent.BUS.addListener(this::addCreative);
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(new ItemStack(CHARCOAL_BLOCK_ITEM.get()));
        }
    }
}