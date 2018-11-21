package binnie.genetics.integration.jei.sequencer;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import binnie.genetics.integration.jei.GeneticsJeiPlugin;
import binnie.genetics.item.GeneticsItems;
import binnie.genetics.machine.sequencer.SequencerLogic;
import binnie.genetics.modules.features.GeneticItems;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;

public class SequencerRecipeWrapper implements IRecipeWrapper {
	private final ItemStack input;
	private final IDrawableAnimated arrowAnimated;

	public SequencerRecipeWrapper(ItemStack input) {
		this.input = input;

		int time = (int) (100 * SequencerLogic.getSequenceStrength(input));
		arrowAnimated = GeneticsJeiPlugin.drawables.createArrowAnimated(time);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		IDrawable arrow = GeneticsJeiPlugin.drawables.getArrow();
		arrow.draw(minecraft, 28, 4);
		arrowAnimated.draw(minecraft, 28, 4);
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, Arrays.asList(
			input,
			GeneticItems.GENETICS.stack(GeneticsItems.FluorescentDye),
			GeneticItems.DATABASE.stack()
		));

		ingredients.setOutput(ItemStack.class, GeneticsItems.EmptySequencer.get(1));
	}
}
