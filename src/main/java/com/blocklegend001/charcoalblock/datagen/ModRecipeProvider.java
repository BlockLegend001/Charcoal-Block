package com.blocklegend001.charcoalblock.datagen;

import com.blocklegend001.charcoalblock.CharcoalBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.MISC, CharcoalBlock.CHARCOAL_BLOCK)
                        .pattern("CCC")
                        .pattern("CCC")
                        .pattern("CCC")
                        .input('C', Items.CHARCOAL)
                        .criterion(hasItem(Items.CHARCOAL), conditionsFromItem(Items.CHARCOAL))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.CHARCOAL, 9)
                        .input(CharcoalBlock.CHARCOAL_BLOCK)
                        .criterion(hasItem(CharcoalBlock.CHARCOAL_BLOCK), conditionsFromItem(CharcoalBlock.CHARCOAL_BLOCK))
                        .offerTo(exporter);
            }
        };

    }

    @Override
    public String getName() {
        return "";
    }
}
