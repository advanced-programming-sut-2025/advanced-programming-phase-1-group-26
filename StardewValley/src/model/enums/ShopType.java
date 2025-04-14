package model.enums;

public enum ShopType
{
    BLACK_SMITH("Blacksmith"),
    JOJO_MART("Jojo Mart"),
    PIERRE_GENERAL_STORE("Pierre's General Store"),
    CARPENTER_SHOP("Carpenter's Shop"),
    FISH_SHOP("Fish Shop"),
    MARINE_RANCH("Marine's Ranch"),
    STARDROP_SALOON("The Stardrop Saloon");

    private final String name;

    ShopType(String name)
    {
        this.name = name;
    }
}
