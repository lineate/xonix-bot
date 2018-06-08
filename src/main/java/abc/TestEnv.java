package abc;

import com.lineate.xonix.mind.model.Bot;
import com.lineate.xonix.mind.model.GameState;
import com.lineate.xonix.mind.model.Gameplay;
import com.lineate.xonix.mind.model.ModelGameState;
import com.lineate.xonix.mind.model.Move;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TestEnv {
    public static void main(String[] args) {
        Gameplay gameplay = new Gameplay();
        List<Bot> bots = Arrays.asList(new RandomBot("1"), new RandomBot("2"));
        List<String> botNames = bots.stream().map(Bot::getName).collect(toList());
        ModelGameState mgs = gameplay.createMatch(10, 20, bots, 100L, 0.9, 0).getGameState();
        for (int it = 0; it < 100; it++) {
            for (int k = 0; k < bots.size(); k++) {
                GameState gs = gameplay.getClientGameState(mgs, k);
                Move move = bots.get(k).move(gs);
                gameplay.step(mgs, k, move);
                System.out.println("move = " + move + " current game state = \n" +
                        gameplay.describeGameState(mgs, botNames, false, false));
            }
        }
    }
}
