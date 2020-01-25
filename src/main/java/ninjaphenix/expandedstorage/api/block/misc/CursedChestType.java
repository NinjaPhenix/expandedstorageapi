package ninjaphenix.expandedstorage.api.block.misc;

import net.minecraft.block.enums.ChestType;
import net.minecraft.util.StringIdentifiable;
import org.checkerframework.checker.nullness.qual.NonNull;

public enum CursedChestType implements StringIdentifiable
{
	SINGLE("single"), TOP("top"), BACK("back"), RIGHT("right"), BOTTOM("bottom"), FRONT("front"), LEFT("left");

	private final String name;

	CursedChestType(String string) { name = string; }

	@NonNull
	public static CursedChestType valueOf(ChestType type)
	{
		if (type == ChestType.SINGLE) { return SINGLE; }
		else if (type == ChestType.RIGHT) { return LEFT; }
		else if (type == ChestType.LEFT) { return RIGHT; }
		throw new IllegalArgumentException("Unexpected chest type passed.");
	}

	@NonNull
	public CursedChestType getOpposite()
	{
		if (this == FRONT) { return BACK; }
		else if (this == BACK) { return FRONT; }
		else if (this == BOTTOM) { return TOP; }
		else if (this == TOP) { return BOTTOM; }
		else if (this == LEFT) { return RIGHT; }
		else if (this == RIGHT) { return LEFT; }
		throw new IllegalArgumentException("this cannot be SINGLE");
	}

	public boolean isRenderedType() { return this == FRONT || this == BOTTOM || this == LEFT || this == SINGLE; }

	@NonNull
	public String asString() { return name; }
}