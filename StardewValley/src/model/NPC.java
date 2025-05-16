package model;

import model.enums.GameObjectType;
import model.enums.NpcDetails;
import model.player_data.FriendshipWithNpcData;

import java.util.ArrayList;
import java.util.List;

public class NPC {
    private final Point location;
    private final NpcDetails npcDetails;
    private final String name;
    private final ArrayList<String> dialogues = new ArrayList<>();
    private final List<GameObjectType> favorites;
    private final List<GameObject> requests;
    private final List<GameObject> rewards;
    private final ArrayList<GameObject> openQuests = new ArrayList<>();
    private final String appearance;

    private boolean firstQuestDone = false;
    private boolean secondQuestDone = false;
    private boolean thirdQuestDone = false;

    public NPC(NpcDetails npcDetails, Point location) {
        this.location = location;
        this.npcDetails = npcDetails;
        this.name = npcDetails.getName();

        this.favorites = npcDetails.getFavorites();
        this.requests = npcDetails.getRequests();
        this.rewards = npcDetails.getRewards();
        this.openQuests.add(requests.getFirst());
        this.appearance = npcDetails.getAppearance();
    }

    public NpcDetails getNpcDetails() {
        return npcDetails;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isNearPlayer(Point playerLocation)
    {
        int dx = Math.abs(location.getX() - playerLocation.getX());
        int dy = Math.abs(location.getY() - playerLocation.getY());

        return (dx <= 1 && dy <= 1) && !(dx == 0 && dy == 0);
    }

    public boolean isQuestFinish = false;
    public boolean isGifted = false; //TODO, should be reset everyday

    public String talk() {
        Time currentTime = App.getCurrentGame().getCurrentTime();
        return this.getNpcDetails().getDialogue(currentTime.getSeason(), currentTime.getTimeOfDay());
    }

    public void removeQuest(int index) {
        openQuests.remove(index);
    }

    public String getAppearance()
    {
        return appearance;
    }

    public String getName()
    {
        return name;
    }

    public boolean isFavorite(GameObjectType type)
    {
        return favorites.contains(type);
    }

    public boolean isFirstQuestAvailable()
    {
        return !firstQuestDone;
    }

    public boolean isSecondQuestAvailable()
    {
        if (!secondQuestDone)
        {
            Player player = App.getCurrentGame().getCurrentPlayer();
            FriendshipWithNpcData friendship = player.getNpcFriendship(this);
            return friendship.getLevel() >= 1;
        }
        return false;
    }

    public boolean isThirdQuestAvailable()
    {
        if(!thirdQuestDone)
        {
            Time time = App.getCurrentGame().getCurrentTime();
            return (time.getTotalDaysPassed() >= npcDetails.getDaysUntilQuestUnlocked());
        }
        return false;
    }

    public String getQuestDescription(int index)
    {
        StringBuilder output = new StringBuilder();
        output.append(name).append("'s Quest: (index ").append(index + 1).append(") \n");

        GameObject request = npcDetails.getQuestRequest(index);
        output.append("\trequest: ").append(request.getObjectType().toString()).append(" x").append(request.getNumber()).append("\n");

        GameObject reward = npcDetails.getQuestReward(index);
        output.append("\treward: ").append(reward.getObjectType().toString()).append(" x").append(reward.getNumber()).append("\n");

        output.append("--------------------------------");

        return output.toString();
    }

    public void firstQuestDone()
    {
        firstQuestDone = true;
    }

    public void secondQuestDone()
    {
        secondQuestDone = true;
    }

    public void thirdQuestDone()
    {
        thirdQuestDone = true;
    }
}
