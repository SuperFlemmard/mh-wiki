package com.mhwiki.toolkit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Sub Type")
    private String subType;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Rarity")
    private int rarity;

    @JsonProperty("Carry Limit")
    private int carryLimit;

    @JsonProperty("Carry Limit (Non-IB)")
    private int carryLimitNonIB;

    @JsonProperty("Sort Order")
    private int sortOrder;

    @JsonProperty("Icon Id")
    private int iconId;

    @JsonProperty("Wiki Icon Name")
    private String wikiIconName;

    @JsonProperty("Icon Color Id")
    private int iconColorId;

    @JsonProperty("Wiki Icon Color")
    private String wikiIconColor;

    @JsonProperty("Sell Price")
    private int sellPrice;

    @JsonProperty("Buy Price")
    private int buyPrice;

    @JsonProperty("Flags")
    private int flags;

    @JsonProperty("Has Infinity Symbol")
    private boolean hasInfinitySymbol;

    @JsonProperty("Is Supply Item")
    private boolean isSupplyItem;

    @JsonProperty("Unknown")
    private boolean unknown;

    @JsonProperty("Is Consumable")
    private boolean isConsumable;

    @JsonProperty("If Fey or Streamstone")
    private boolean ifFeyOrStreamstone;

    @JsonProperty("Is Infinite Use")
    private boolean isInfiniteUse;

    @JsonProperty("Has Star")
    private boolean hasStar;

    @JsonProperty("Has New Palico Gadget Symbol")
    private boolean hasNewPalicoGadgetSymbol;

    @JsonProperty("Is Level 1")
    private boolean isLevel1;

    @JsonProperty("Is Level 2")
    private boolean isLevel2;

    @JsonProperty("Is Level 3")
    private boolean isLevel3;

    @JsonProperty("Is Shiny")
    private boolean isShiny;

    @JsonProperty("Is Huge Carriable")
    private boolean isHugeCarriable;

    @JsonProperty("Not Storable as an Item")
    private boolean notStorableAsAnItem;

    @JsonProperty("Description")
    private String description;
}
