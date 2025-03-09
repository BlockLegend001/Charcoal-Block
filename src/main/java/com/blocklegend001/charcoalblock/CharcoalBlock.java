package com.blocklegend001.charcoalblock;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = CharcoalBlock.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(CharcoalBlock.MODID)
public class CharcoalBlock {

    public static final String MODID = "charcoalblock";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceKey<?> CHARCOAL_BLOCK_ID = ResourceKey.create(ForgeRegistries.Keys.BLOCKS, ResourceLocation.fromNamespaceAndPath(MODID, "charcoal_block"));
    public static final ResourceKey<?> CHARCOAL_ITEM_ID = ResourceKey.create(ForgeRegistries.Keys.ITEMS, ResourceLocation.fromNamespaceAndPath(MODID, "charcoal_block"));

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> CHARCOAL_BLOCK = BLOCKS.register("charcoal_block", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).
                    mapColor(MapColor.COLOR_BLACK).
                    requiresCorrectToolForDrops().
                    strength(5.0F, 6.0F).
                    setId(ResourceKey.create(ForgeRegistries.Keys.BLOCKS, CHARCOAL_BLOCK_ID.location()))));

    public static final RegistryObject<Item> CHARCOAL_BLOCK_ITEM = ITEMS.register("charcoal_block", () -> new BlockItem(CHARCOAL_BLOCK.get(), new Item.Properties().
            setId(ResourceKey.create(ForgeRegistries.Keys.ITEMS, CHARCOAL_ITEM_ID.location())))

    {
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
        {
            return 16000;
        }
    });

    public CharcoalBlock(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Mod Loaded");
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(new ItemStack(CHARCOAL_BLOCK_ITEM.get()));
        }
    }
}