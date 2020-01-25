package ninjaphenix.expandedstorage.api.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import ninjaphenix.expandedstorage.api.ExpandedStorageAPI;
import ninjaphenix.expandedstorage.api.Registries;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public final class ExpandedStorageAPIClient
{
	public static final Identifier CHEST_TEXTURE_ATLAS = new Identifier("expandedstorage", "chest_textures");

	public static void makeAtlas(@NonNull Consumer<SpriteIdentifier> consumer)
	{
		ExpandedStorageAPI.forEachPlugin(plugin ->
				plugin.appendTexturesToAtlas((identifier ->
						consumer.accept(new SpriteIdentifier(CHEST_TEXTURE_ATLAS, identifier)))));
	}

	public static void onInitializeClient()
	{
		if (!Registries.CHEST.isEmpty()) { BlockEntityRendererRegistry.INSTANCE.register(ExpandedStorageAPI.CHEST, CursedChestBlockEntityRenderer::new); }
	}
}
