package model.enums;

public enum ShopType
{
    BLACK_SMITH("Blacksmith"),
    JOJA_MART("Joja Mart"),
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

    public String getName()
    {
        return name;
    }

    public static ShopType getShop (String name)
    {
        for (ShopType shopType : ShopType.values())
        {
            if (shopType.getName().equalsIgnoreCase(name))
            {
                return shopType;
            }
        }
        return null;
    }
}
