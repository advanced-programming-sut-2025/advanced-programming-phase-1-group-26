package model;

import model.enums.BlacksmithItem;
import model.enums.BlacksmithUpgrade;
import model.enums.GameObjectType;

public class Blacksmith extends Shop{
    BlacksmithItem blacksmithItem;
    BlacksmithUpgrade blacksmithUpgrade;

    @Override
    public void workabality(GameObjectType gameObjectType) {
    }
}
