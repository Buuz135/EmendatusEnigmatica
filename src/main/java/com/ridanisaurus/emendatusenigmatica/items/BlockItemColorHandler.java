package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.blocks.*;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class BlockItemColorHandler implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        if (layer == 0) {
            if (stack.getItem() instanceof BlockItem) {
                BlockItem item = (BlockItem) stack.getItem();
                Block block = item.getBlock();
                if (block instanceof GemOreBlock || block instanceof MetalOreBlock || block instanceof GemOreBlockWithParticles || block instanceof  MetalOreBlockWithParticles) {
                    return ((IColorable) block).getHighlight2();
                }
            }
            if (stack.getItem() instanceof BasicStorageBlockItem) {
                BlockItem item = (BasicStorageBlockItem) stack.getItem();
                if (item.getBlock() instanceof BasicStorageBlock) {
                    return ((BasicStorageBlock) item.getBlock()).getHighlight2();
                }
            }
        }
        if (layer == 1) {
            if (stack.getItem() instanceof BlockItem) {
                BlockItem item = (BlockItem) stack.getItem();
                Block block = item.getBlock();
                if (block instanceof GemOreBlock || block instanceof MetalOreBlock || block instanceof GemOreBlockWithParticles || block instanceof  MetalOreBlockWithParticles) {
                    return ((IColorable) block).getHighlight1();
                }
            }
            if (stack.getItem() instanceof BasicStorageBlockItem) {
                BlockItem item = (BasicStorageBlockItem) stack.getItem();
                if (item.getBlock() instanceof BasicStorageBlock) {
                    return ((BasicStorageBlock) item.getBlock()).getHighlight1();
                }
            }
        }
        if (layer == 2) {
            if (stack.getItem() instanceof BlockItem) {
                BlockItem item = (BlockItem) stack.getItem();
                Block block = item.getBlock();
                if (block instanceof GemOreBlock || block instanceof MetalOreBlock || block instanceof GemOreBlockWithParticles || block instanceof  MetalOreBlockWithParticles) {
                    return ((IColorable) block).getBase();
                }
            }
            if (stack.getItem() instanceof BasicStorageBlockItem) {
                BlockItem item = (BasicStorageBlockItem) stack.getItem();
                if (item.getBlock() instanceof BasicStorageBlock) {
                    return ((BasicStorageBlock) item.getBlock()).getBase();
                }
            }
        }
        if (layer == 3) {
            if (stack.getItem() instanceof BlockItem) {
                BlockItem item = (BlockItem) stack.getItem();
                Block block = item.getBlock();
                if (block instanceof GemOreBlock || block instanceof MetalOreBlock || block instanceof GemOreBlockWithParticles || block instanceof  MetalOreBlockWithParticles) {
                    return ((IColorable) block).getShadow1();
                }
            }
            if (stack.getItem() instanceof BasicStorageBlockItem) {
                BlockItem item = (BasicStorageBlockItem) stack.getItem();
                if (item.getBlock() instanceof BasicStorageBlock) {
                    return ((BasicStorageBlock) item.getBlock()).getShadow1();
                }
            }
        }
        if (layer == 4) {
            if (stack.getItem() instanceof BlockItem) {
                BlockItem item = (BlockItem) stack.getItem();
                Block block = item.getBlock();
                if (block instanceof GemOreBlock || block instanceof MetalOreBlock || block instanceof GemOreBlockWithParticles || block instanceof  MetalOreBlockWithParticles) {
                    return ((IColorable) block).getShadow2();
                }
            }
            if (stack.getItem() instanceof BasicStorageBlockItem) {
                BlockItem item = (BasicStorageBlockItem) stack.getItem();
                if (item.getBlock() instanceof BasicStorageBlock) {
                    return ((BasicStorageBlock) item.getBlock()).getShadow2();
                }
            }
        }
        return 0xFFFFFF;
    }
}
