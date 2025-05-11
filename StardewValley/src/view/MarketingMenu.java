package view;

import control.game.activities.MarketingController;
import model.NPC;
import model.enums.regex_enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MarketingMenu implements AppMenu{
    MarketingController controller = new MarketingController();
    NPC npc;
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if(GameCommands.MEET_NPC.matches(input)) {
            System.out.println(controller.meetNPC(input));
            if(controller.meetNPC(input).isSuccessful()) npc = controller.targetNPC(input);
        } else if(GameCommands.GIFT_NPC.matches(input)) {
            System.out.println(controller.giftNPC(input));
        } else if(GameCommands.QUESTS_LIST.matches(input)) {
            System.out.println(controller.questsNPCList(npc));
        } else if(GameCommands.QUESTS_FINISH.matches(input)) {
            System.out.println(controller.questsFinish(input, npc));
        }


        else {
            System.out.println("Invalid command");
        }
    }
}
