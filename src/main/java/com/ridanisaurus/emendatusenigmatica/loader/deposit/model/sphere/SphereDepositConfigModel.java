package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;

import java.util.List;
import java.util.Optional;

public class SphereDepositConfigModel {
	public static final Codec<SphereDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("blocks").forGetter(it -> it.blocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").forGetter(it -> it.chance),
			Codec.INT.fieldOf("radius").forGetter(it -> it.radius),
			Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel)
	).apply(x, SphereDepositConfigModel::new));

	private final List<CommonBlockDefinitionModel> blocks;
	private final List<String> fillerTypes;
	private final int chance;
	private final int radius;
	private final int minYLevel;
	private final int maxYLevel;

	public SphereDepositConfigModel(List<CommonBlockDefinitionModel> blocks, List<String> fillerTypes, int chance, int radius, int minYLevel, int maxYLevel) {

		this.blocks = blocks;
		this.chance = chance;
		this.radius = radius;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.fillerTypes = fillerTypes;
	}

	public int getChance() {
		return (100 - chance) + 1;
	}

	public int getMaxYLevel() {
		return maxYLevel;
	}

	public int getMinYLevel() {
		return minYLevel;
	}

	public List<CommonBlockDefinitionModel> getBlocks() {
		return blocks;
	}

	public List<String> getFillerTypes() {
		return fillerTypes;
	}

	public int getRadius() {
		return radius;
	}
}
