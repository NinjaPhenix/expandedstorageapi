package ninjaphenix.expandedstorage.api.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import ninjaphenix.expandedstorage.api.block.CursedChestBlock;
import ninjaphenix.expandedstorage.api.block.entity.CursedChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinModelItemRenderer.class)
@Environment(EnvType.CLIENT)
public final class BuiltinModelItemRendererMixin
{
	private static final CursedChestBlockEntity CURSED_CHEST_RENDER_ENTITY = new CursedChestBlockEntity(null);

	@Inject(at = @At("HEAD"), method = "render", cancellable = true)
	private void render(ItemStack itemStack, MatrixStack matrixStack, VertexConsumerProvider consumerProvider, int light, int overlay,
			CallbackInfo info)
	{
		Item item = itemStack.getItem();
		if (item instanceof BlockItem)
		{
			Block block = ((BlockItem) item).getBlock();
			if (block instanceof CursedChestBlock)
			{
				CURSED_CHEST_RENDER_ENTITY.setBlock(Registry.BLOCK.getId(block));
				BlockEntityRenderDispatcher.INSTANCE.renderEntity(CURSED_CHEST_RENDER_ENTITY, matrixStack, consumerProvider, light, overlay);
				info.cancel();
			}
		}
	}
}
