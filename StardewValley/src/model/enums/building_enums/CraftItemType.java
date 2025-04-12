package model.enums.building_enums;

public enum CraftItemType
{
    Cherry_Bomb("Cherry Bomb"),
    Bomb("Bomb"),
    Mega_Bomb("Mega Bomb"),
    Sprinkler("Sprinkler"),
    Quality_Sprinkler("Quality Sprinkler"),
    Iridium_Sprinkler("Iridium Sprinkler"),
    Charcoal_Klin("Charcoal Klin"),
    Furnace("Furnace"),
    Scarecrow("Scarecrow"),
    Deluxe_Scarecrow("Deluxe Scarecrow"),
    Bee_House("Bee House"),
    Cheese_Press("Cheese Press"),
    Keg("Keg"),
    Loom("Loom"),
    Mayonnaise_Machine("Mayonnaise Machine"),
    Oil_Maker("Oil Maker"),
    Preserves_Jar("Preserves Jar"),
    Dehydrator("Dehydrator"),
    Fish_Smoker("Fish Smoker"),
    Mystic_Tree_Seed("Mystic Tree Seed"),
    ;

    private final String displayName;

    CraftItemType(String displayName)
    {
        this.displayName = displayName;
    }

    @Override
    public String toString()
    {
        return displayName;
    }
}
