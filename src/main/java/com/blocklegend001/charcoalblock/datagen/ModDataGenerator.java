package com.blocklegend001.charcoalblock.datagen;

import com.blocklegend001.charcoalblock.CharcoalBlock;
import com.blocklegend001.charcoalblock.datagen.providers.ModBlockTagsProvider;
import com.blocklegend001.charcoalblock.datagen.providers.ModLootTableProvider;
import com.blocklegend001.charcoalblock.datagen.providers.ModModelProvider;
import com.blocklegend001.charcoalblock.datagen.providers.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CharcoalBlock.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    private ModDataGenerator() {}

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(packOutput, lookupProvider, CharcoalBlock.MODID, helper);

        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeClient(), new ModModelProvider(packOutput, CharcoalBlock.MODID));
    }
}
