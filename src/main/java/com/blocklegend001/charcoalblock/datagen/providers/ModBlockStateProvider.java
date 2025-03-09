package com.blocklegend001.charcoalblock.datagen.providers;

import com.blocklegend001.charcoalblock.CharcoalBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        String block = CharcoalBlock.CHARCOAL_BLOCK.getId().getPath();

        ModelFile blockModel = models().cubeAll(block, ResourceLocation.fromNamespaceAndPath(CharcoalBlock.MODID, "block/" + block));

        simpleBlockWithItem(CharcoalBlock.CHARCOAL_BLOCK.get(), blockModel);
    }

    @Override
    public String getName() {
        return "CharcoalBlock Blockstates";
    }
}
