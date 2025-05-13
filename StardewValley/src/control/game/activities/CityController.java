package control.game.activities;

import model.App;
import model.Player;
import model.Result;
import model.enums.Menu;

public class CityController
{
    public Result goOut()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.goToFarm();
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "Going to farm...");
    }
}
