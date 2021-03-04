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

import com.ridanisaurus.emendatusenigmatica.util.ParticleColorHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.awt.*;
import java.util.Random;

public class MetalOreBlockWithParticles extends OreBlock {
	private final String localisedName;

	public MetalOreBlockWithParticles(Material material, float hardness, float resistance, int harvestLevel, ToolType tool, String localisedName) {
		super(Properties.create(material)
				.hardnessAndResistance(hardness, resistance)
				.harvestLevel(harvestLevel)
				.harvestTool(tool)
				.setRequiresTool());
		this.localisedName = localisedName;
	}

	@Override
	public IFormattableTextComponent getTranslatedName() {
		return new StringTextComponent(localisedName);
	}

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.animateTick(stateIn, worldIn, pos, rand);

		float red = (float) ParticleColorHelper.HexToColor("#0099ff").getRed() / 255;
		float green = (float) ParticleColorHelper.HexToColor("#0099ff").getGreen() / 255;
		float blue = (float) ParticleColorHelper.HexToColor("#0099ff").getBlue() / 255;

		if(rand.nextInt(10) == 0) {
			for(Direction direction : Direction.values()) {
				BlockPos blockpos = pos.offset(direction);
				if (!worldIn.getBlockState(blockpos).isOpaqueCube(worldIn, blockpos)) {
					Direction.Axis direction$axis = direction.getAxis();
					double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getXOffset() : (double)rand.nextFloat();
					double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getYOffset() : (double)rand.nextFloat();
					double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getZOffset() : (double)rand.nextFloat();
					worldIn.addParticle(new RedstoneParticleData(red, green, blue, 1.0F), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}
}
