package ninjaphenix.expandedstorage.api.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import ninjaphenix.expandedstorage.api.client.ExpandedStorageAPIClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(TexturedRenderLayers.class)
@Environment(EnvType.CLIENT)
public final class TexturedRenderLayersMixin
{
	@Inject(at = @At("HEAD"), method = "addDefaultTextures(Ljava/util/function/Consumer;)V")
	private static void method(Consumer<SpriteIdentifier> consumer, CallbackInfo ci) { ExpandedStorageAPIClient.makeAtlas(consumer); }
}
