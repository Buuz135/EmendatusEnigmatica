package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public class VanillaDepositModel extends CommonDepositModelBase {
	public static final Codec<VanillaDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("type").forGetter(it -> it.type),
			Codec.STRING.fieldOf("dimension").forGetter(it -> it.dimension),
			Codec.list(Codec.STRING).fieldOf("biomes").orElse(List.of()).forGetter(it -> it.biomes),
			Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
			VanillaDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
	).apply(x, VanillaDepositModel::new));

	private VanillaDepositConfigModel config;

	public VanillaDepositModel(String type, String dimension, List<String> biomes, String name, VanillaDepositConfigModel config) {
		super(type, dimension, biomes, name);
		this.config = config;
	}

	public VanillaDepositConfigModel getConfig() {
		return config;
	}
	public String getType() {
		return super.getType();
	}
}
