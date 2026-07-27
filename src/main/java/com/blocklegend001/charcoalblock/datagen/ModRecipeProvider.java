package com.blocklegend001.charcoalblock.datagen;

import com.blocklegend001.charcoalblock.CharcoalBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, CharcoalBlock.CHARCOAL_BLOCK)
                        .pattern("CCC")
                        .pattern("CCC")
                        .pattern("CCC")
                        .define('C', Items.CHARCOAL)
                        .unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
                        .save(output);

                shapeless(RecipeCategory.MISC, Items.CHARCOAL, 9)
                        .requires(CharcoalBlock.CHARCOAL_BLOCK_ITEM)
                        .unlockedBy(getHasName(CharcoalBlock.CHARCOAL_BLOCK_ITEM), has(CharcoalBlock.CHARCOAL_BLOCK_ITEM))
                        .save(output, "charcoalblock:charcoal_shapeless");
            }
        };

    }

    @Override
    public String getName() {
        return "";
    }
}
