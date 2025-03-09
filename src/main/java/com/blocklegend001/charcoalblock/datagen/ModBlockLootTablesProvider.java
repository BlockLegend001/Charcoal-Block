package com.blocklegend001.charcoalblock.datagen;

import com.blocklegend001.charcoalblock.CharcoalBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTablesProvider extends FabricBlockLootTableProvider {
    protected ModBlockLootTablesProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(CharcoalBlock.CHARCOAL_BLOCK);
    }
}
