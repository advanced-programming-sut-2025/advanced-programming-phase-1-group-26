package model.player_data;

import model.NPC;
import model.Player;
import view.GameMenu;

import javax.swing.plaf.SplitPaneUI;

public class FriendshipWithNpcData
{
    private final NPC npc;
    private final Player player;

    private int xp = 0; // TODO: ask Moshtagh about base

    private boolean hasTalked = false;
    private boolean hasGifted = false;

    public FriendshipWithNpcData(NPC npc, Player player)
    {
        this.npc = npc;
        this.player = player;
    }

    public int getXp()
    {
        return xp - (200 * getLevel());
    }

    public int getLevel()
    {
        return (xp / 200);
    }

    public void talk()
    {
        if (!hasTalked)
        {
            GameMenu.println("You talked to " + npc.getName() + " for the first time today.");
            GameMenu.println("You received 20 xp.");
            addXp(20);
            hasTalked = true;
        }
    }

    public void gift(boolean favorite)
    {
        if (!hasGifted)
        {
            GameMenu.println("Oh, you gifted " + npc.getName() + " for the first time today!");

            if (favorite)
            {
                GameMenu.println(npc.getName() + " loves your gift! You just received 200 xp!");
                addXp(200);
            } else
            {
                GameMenu.println("You received 50 xp.");
                addXp(50);
            }

            hasGifted = true;
        }
    }

    public void addXp(int amount)
    {
        this.xp += amount;
        doLevelThing();
    }

    public void reset()
    {
        hasTalked = false;
        hasGifted = false;
    }

    public void doLevelThing()
    {
        // TODO : addddddddddddd
    }
}
