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

package com.ridanisaurus.emendatusenigmatica.datagen.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class RecipeBuilder {

	private JsonItemBuilder result;
//	private Item recipeDefault;
	private final String resultName;
//	private final List<String> rows = Lists.newArrayList();
//	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
//	private final Advancement.Builder advancement = Advancement.Builder.advancement();
	private String group;
	private String type;

	private final Map<String, String> fieldValueString = Maps.newLinkedHashMap();
	private final Map<String, Integer> fieldValueInt = Maps.newLinkedHashMap();
	private final Map<String, Float> fieldValueFloat = Maps.newLinkedHashMap();
	private final Map<String, Boolean> fieldValueBoolean = Maps.newLinkedHashMap();
	private final Map<String, ItemLike> fieldValueItem = Maps.newLinkedHashMap();
	private final Map<String, JsonItemBuilder> fieldValueJson = Maps.newLinkedHashMap();

	public RecipeBuilder(String resultName) {
		this.resultName = resultName;
		this.result = new JsonItemBuilder(false);
	}

	public RecipeBuilder(String resultName, ItemLike item, int count, float chance) {
		this.resultName = resultName;
		this.result = new JsonItemBuilder(false).stackWithChance(item, count, chance);
//		this.recipeDefault = item.asItem();
	}

	public RecipeBuilder(String resultName, ItemLike item, int count) {
		this.resultName = resultName;
		this.result = new JsonItemBuilder(false).stackWithCount(item, count);
//		this.recipeDefault = item.asItem();
	}

	public RecipeBuilder(String resultName, ItemLike item, float chance) {
		this.resultName = resultName;
		this.result = new JsonItemBuilder(false).stackWithoutCount(item, chance);
//		this.recipeDefault = item.asItem();
	}

	public RecipeBuilder(String resultName, ItemLike item) {
		this.resultName = resultName;
		this.result = new JsonItemBuilder(false).stack(item);
//		this.recipeDefault = item.asItem();
	}

//	public static RecipeBuilder result(ItemLike item) {
//		return result(item, 1);
//	}
//
//	public static RecipeBuilder result(ItemLike item, int count) {
//		return new RecipeBuilder("result", item, count);
//	}

	public RecipeBuilder fieldString(String key, String value) {
		if (this.fieldValueString.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueString.put(key, value);
			return this;
		}
	}

	public RecipeBuilder fieldInt(String key, int value) {
		if (this.fieldValueInt.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueInt.put(key, value);
			return this;
		}
	}

	public RecipeBuilder fieldFloat(String key, float value) {
		if (this.fieldValueFloat.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueFloat.put(key, value);
			return this;
		}
	}

	public RecipeBuilder fieldBoolean(String key, boolean value) {
		if (this.fieldValueBoolean.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueBoolean.put(key, value);
			return this;
		}
	}

	public RecipeBuilder fieldItem(String key, ItemLike value) {
		if (this.fieldValueItem.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueItem.put(key, value);
			return this;
		}
	}

	public RecipeBuilder fieldJson(String key, JsonItemBuilder builder) {
		if (this.fieldValueJson.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueJson.put(key, builder);
			return this;
		}
	}

	public RecipeBuilder addOutput(Consumer<JsonItemBuilder> output) {
		output.accept(this.result);
		return this;
	}

//	// DO I NEED KEY?
//	public RecipeBuilder define(Character key, TagKey<Item> itemTag) {
//		return this.define(key, Ingredient.of(itemTag));
//	}
//
//	public RecipeBuilder define(Character key, ItemLike item) {
//		return this.define(key, Ingredient.of(item));
//	}
//
//	public RecipeBuilder define(Character key, Ingredient ingredient) {
//		if (this.key.containsKey(key)) {
//			throw new IllegalArgumentException("Symbol '" + key + "' is already defined!");
//		} else if (key == ' ') {
//			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
//		} else {
//			this.key.put(key, ingredient);
//			return this;
//		}
//	}

//	// DO I NEED PATTERN?
//	public RecipeBuilder pattern(String pattern) {
//		if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
//			throw new IllegalArgumentException("Pattern must be the same width on every line!");
//		} else {
//			this.rows.add(pattern);
//			return this;
//		}
//	}
//
//	public RecipeBuilder unlockedBy(String advancementName, CriterionTriggerInstance advancement) {
//		this.advancement.addCriterion(advancementName, advancement);
//		return this;
//	}

	public RecipeBuilder setResult(JsonItemBuilder result) {
		this.result = result;
		return this;
	}

//	public RecipeBuilder setRecipeDefault(Item recipeDefault) {
//		this.recipeDefault = recipeDefault;
//		return this;
//	}

	public RecipeBuilder group(String group) {
		this.group = group;
		return this;
	}

	public RecipeBuilder type(String type) {
		this.type = type;
		return this;
	}

	public RecipeBuilder forceOutputArray(boolean force) {
		this.result.setForceArray(force);
		return this;
	}

//	public void save(Consumer<IFinishedGenericRecipe> consumer) {
//		this.save(consumer, ForgeRegistries.ITEMS.getKey(this.recipeDefault));
//	}

//	public void save(Consumer<IFinishedGenericRecipe> consumer, String recipeName) {
//		if ((new ResourceLocation(recipeName)).equals(ForgeRegistries.ITEMS.getKey(this.recipeDefault))) {
//			throw new IllegalStateException("Shaped Recipe " + recipeName + " should remove its 'save' argument");
//		} else {
//			this.save(consumer, new ResourceLocation(recipeName));
//		}
//	}

	// new ResourceLocation(recipeResourceLocation.getNamespace(), "recipes/" + this.recipeDefault.getItemCategory().getRecipeFolderName() + "/" + recipeResourceLocation.getPath())
	public void save(Consumer<IFinishedGenericRecipe> consumer, ResourceLocation recipeResourceLocation) {
//		this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeResourceLocation))
//				.rewards(AdvancementRewards.Builder.recipe(recipeResourceLocation)).requirements(RequirementsStrategy.OR);
		consumer.accept(new RecipeBuilder.Result(recipeResourceLocation, this.resultName, this.result, this.group == null ? "" : this.group, this.type,
				this.fieldValueString, this.fieldValueInt, this.fieldValueFloat, this.fieldValueBoolean, this.fieldValueItem, this.fieldValueJson));
	}

//	private void ensureValid(ResourceLocation resourceLocation) {
//		if (this.rows.isEmpty()) {
//			throw new IllegalStateException("No pattern is defined for shaped recipe " + resourceLocation + "!");
//		} else {
//			Set<Character> set = Sets.newHashSet(this.key.keySet());
//			set.remove(' ');
//
//			for (String s : this.rows) {
//				for (int i = 0; i < s.length(); ++i) {
//					char c0 = s.charAt(i);
//					if (!this.key.containsKey(c0) && c0 != ' ') {
//						throw new IllegalStateException("Pattern in recipe " + resourceLocation + " uses undefined symbol '" + c0 + "'");
//					}
//
//					set.remove(c0);
//				}
//			}
//
//			if (!set.isEmpty()) {
//				throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + resourceLocation);
//			} else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
//				throw new IllegalStateException("Shaped recipe " + resourceLocation + " only takes in a single item - should it be a shapeless recipe instead?");
//			} else if (this.advancement.getCriteria().isEmpty()) {
//				throw new IllegalStateException("No way of obtaining recipe " + resourceLocation);
//			}
//		}
//	}

	public static class JsonItemBuilder {

		private List<JsonObject> outputs = new ArrayList<>();
		private boolean forceArray;

		public JsonItemBuilder(boolean forceArray) {
			this.forceArray = forceArray;
		}

		public JsonItemBuilder addOutput(Pair<String, Object>... elements) {
			JsonObject object = new JsonObject();
			for (Pair<String, Object> element : elements) {
				if (element.getValue() instanceof Number) {
					object.addProperty(element.getKey(), (Number) element.getValue());
				}
				if (element.getValue() instanceof String) {
					object.addProperty(element.getKey(), (String) element.getValue());
				}
				if (element.getValue() instanceof Boolean) {
					object.addProperty(element.getKey(), (Boolean) element.getValue());
				}
				if (element.getValue() instanceof Character) {
					object.addProperty(element.getKey(), (Character) element.getValue());
				}
				if (element.getValue() instanceof Pair) {
					object.add(element.getKey(), parsePair((Pair<String, Object>) element.getValue()));
				}
			}
			outputs.add(object);
			return this;
		}

		private static JsonObject parsePair(Pair<String, Object>... elements) {
			JsonObject object = new JsonObject();
			for (Pair<String, Object> element : elements) {
				if (element.getValue() instanceof Number) {
					object.addProperty(element.getKey(), (Number) element.getValue());
				}
				if (element.getValue() instanceof String) {
					object.addProperty(element.getKey(), (String) element.getValue());
				}
				if (element.getValue() instanceof Boolean) {
					object.addProperty(element.getKey(), (Boolean) element.getValue());
				}
				if (element.getValue() instanceof Character) {
					object.addProperty(element.getKey(), (Character) element.getValue());
				}
			}
			return object;
		}

		public JsonItemBuilder objectWithChance(String key, ItemLike itemProvider, int count, double chance) {
			return addOutput(Pair.of(key, Pair.of("item", ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).toString())),
					Pair.of("count", count),
					Pair.of("chance", chance)
			);
		}

		public JsonItemBuilder stackWithChance(ItemLike itemProvider, int count, double chance) {
			return addOutput(Pair.of("item", ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).toString()),
					Pair.of("count", count),
					Pair.of("chance", chance)
			);
		}

		public JsonItemBuilder stackWithCount(ItemLike itemProvider, int count) {
			return addOutput(Pair.of("item", ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).toString()),
					Pair.of("count", count));
		}

		public JsonItemBuilder stackWithoutCount(ItemLike itemProvider, float chance) {
			return addOutput(Pair.of("item", ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).toString()),
					Pair.of("chance", chance)
			);
		}

		public JsonItemBuilder stacks(Table<String, Integer, Float> compatTable) {
			for (Table.Cell<String, Integer, Float> compatTableCells : compatTable.cellSet()) {
				String item = compatTableCells.getRowKey();
				Integer count = compatTableCells.getColumnKey();
				Float chance = compatTableCells.getValue();

				ItemLike itemLike = ForgeRegistries.ITEMS.getValue(new ResourceLocation(item));
				addOutput(Pair.of("item", ForgeRegistries.ITEMS.getKey(itemLike.asItem()).toString()), Pair.of("count", count), Pair.of("chance", chance));
			}
			return this;
		}

		public JsonItemBuilder stacksWithCombinedChance(Table<String, Integer, Float> compatTable) {
			for (Table.Cell<String, Integer, Float> compatTableCells : compatTable.cellSet()) {
				String item = compatTableCells.getRowKey();
				Integer count = compatTableCells.getColumnKey();
				Float chance = compatTableCells.getValue();

				ItemLike itemLike = ForgeRegistries.ITEMS.getValue(new ResourceLocation(item));
				addOutput(Pair.of("item", ForgeRegistries.ITEMS.getKey(itemLike.asItem()).toString()), Pair.of("chance", count + chance));
			}
			return this;
		}

		public JsonItemBuilder stack(ItemLike itemProvider) {
			return addOutput(Pair.of("item", ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).toString()));
		}

		public JsonItemBuilder tagWithChance(TagKey<Item> itemTag, int count, double chance) {
			return addOutput(Pair.of("tag", itemTag.location().getNamespace() + ":" + itemTag.location().getPath()),
					Pair.of("count", count),
					Pair.of("chance", chance)
			);
		}

		public JsonItemBuilder tagWithCount(TagKey<Item> itemTag, int count) {
			return addOutput(Pair.of("tag", itemTag.location().getNamespace() + ":" + itemTag.location().getPath()),
					Pair.of("count", count));
		}

		public JsonItemBuilder tag(TagKey<Item> itemTag) {
			return addOutput(Pair.of("tag", itemTag.location().getNamespace() + ":" + itemTag.location().getPath()));
		}

		public JsonElement getOutput() {
			if (outputs.size() > 1 || forceArray) {
				JsonArray array = new JsonArray();
				outputs.forEach(array::add);
				return array;
			}
			return outputs.get(0);
		}

		public void setForceArray(boolean forceArray) {
			this.forceArray = forceArray;
		}
	}

	public class Result implements IFinishedGenericRecipe {
		private final ResourceLocation id;
		private final JsonItemBuilder result;
		private final String resultName;
		private final String group;
		private final String type;
//		private final List<String> pattern;
//		private final Map<Character, Ingredient> key;
//		private final Advancement.Builder advancement;
//		private final ResourceLocation advancementId;

		private final Map<String, String> fieldValueString;
		private final Map<String, Integer> fieldValueInt;
		private final Map<String, Float> fieldValueFloat;
		private final Map<String, Boolean> fieldValueBoolean;
		private final Map<String, ItemLike> fieldValueItem;
		private final Map<String, JsonItemBuilder> fieldValueJson;

		public Result(ResourceLocation id, String resultName, JsonItemBuilder outputBuilder, String group, String type, Map<String, String> fieldValueString, Map<String, Integer> fieldValueInt, Map<String, Float> fieldValueFloat, Map<String, Boolean> fieldValueBoolean, Map<String, ItemLike> fieldValueItem, Map<String, JsonItemBuilder> fieldValueJson) {
			this.id = id;
			this.resultName = resultName;
//			this.advancement = advancement;
//			this.advancementId = advancementId;
			this.group = group;
			this.type = type;

			this.result = outputBuilder;

//			this.pattern = pattern;
//			this.key = keyMap;

			this.fieldValueString = fieldValueString;
			this.fieldValueInt = fieldValueInt;
			this.fieldValueFloat = fieldValueFloat;
			this.fieldValueBoolean = fieldValueBoolean;
			this.fieldValueItem = fieldValueItem;
			this.fieldValueJson = fieldValueJson;
		}

		public void serializeRecipeData(JsonObject recipeJson) {
			if (!this.group.isEmpty()) {
				recipeJson.addProperty("group", this.group);
			}
			if (!this.type.isEmpty()) {
				recipeJson.addProperty("type", this.type);
			}

//			if (this.pattern.size() > 0) {
//				JsonArray jsonarray = new JsonArray();
//				for (String s : this.pattern) {
//					jsonarray.add(s);
//				}
//				recipeJson.add("pattern", jsonarray);
//			}
//
//			if (this.key.size() > 0) {
//				JsonObject jsonobject = new JsonObject();
//				for (Map.Entry<Character, Ingredient> entry : this.key.entrySet()) {
//					jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
//				}
//				recipeJson.add("key", jsonobject);
//			}

			recipeJson.add(this.resultName, result.getOutput());

			if (!this.fieldValueString.isEmpty()) {
				for (Map.Entry<String, String> entry : this.fieldValueString.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (!this.fieldValueInt.isEmpty()) {
				for (Map.Entry<String, Integer> entry : this.fieldValueInt.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (!this.fieldValueFloat.isEmpty()) {
				for (Map.Entry<String, Float> entry : this.fieldValueFloat.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (!this.fieldValueBoolean.isEmpty()) {
				for (Map.Entry<String, Boolean> entry : this.fieldValueBoolean.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (!this.fieldValueItem.isEmpty()) {
				for (Map.Entry<String, ItemLike> entry : this.fieldValueItem.entrySet()) {
					recipeJson.addProperty(entry.getKey(), ForgeRegistries.ITEMS.getKey(entry.getValue().asItem()).toString());
				}
			}

			if (!this.fieldValueJson.isEmpty()) {
				for (Map.Entry<String, JsonItemBuilder> entry : this.fieldValueJson.entrySet()) {
					recipeJson.add(entry.getKey(), entry.getValue().getOutput());
				}
			}
		}

		public String getType() {
			return this.type;
		}

		public ResourceLocation getId() {
			return this.id;
		}

//		@Nullable
//		public JsonObject serializeAdvancement() {
//			return this.advancement.serializeToJson();
//		}
//
//		@Nullable
//		public ResourceLocation getAdvancementId() {
//			return this.advancementId;
//		}
	}
}