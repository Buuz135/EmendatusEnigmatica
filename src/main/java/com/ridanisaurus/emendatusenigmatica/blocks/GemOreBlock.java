/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class GemOreBlock extends DropExperienceBlock implements IColorable {
	private final String localisedName;
	private final int minExp;
	private final int maxExp;
	public final int highlightColor;
	public final int baseColor;
	public final int shadeColor;

	public GemOreBlock(Material material, float hardness, float resistance, String localisedName, int minExp, int maxExp, int highlightColor, int baseColor, int shadeColor) {
		super(BlockBehaviour.Properties.of(material)
				.strength(hardness, resistance)
				.requiresCorrectToolForDrops());
		this.localisedName = localisedName;
		this.minExp = minExp;
		this.maxExp = maxExp;
		this.highlightColor = highlightColor;
		this.baseColor = baseColor;
		this.shadeColor = shadeColor;
	}

	@Override
	public MutableComponent getName() {
		return Component.translatable(localisedName);
	}

	protected int getExperience(RandomSource rand) {
		return Mth.nextInt(rand, minExp, maxExp);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, RandomSource random, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? this.getExperience(random) : 0;
	}

	@Override
	public int getHighlightColor() {
		return highlightColor;
	}

	@Override
	public int getBaseColor() {
		return baseColor;
	}

	@Override
	public int getShadeColor() {
		return shadeColor;
	}
}