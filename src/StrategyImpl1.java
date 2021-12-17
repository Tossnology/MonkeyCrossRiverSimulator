import java.util.List;

public class StrategyImpl1 implements Strategy{
    @Override
    public void selectLadder(Monkey monkey) {
        while (true) {
            for (int i = 0; i < Main.ladders.length; i++) {
                Ladder cur = Main.ladders[i];
                synchronized (cur) {
                    if (cur.isEmpty()) {
                        monkey.setSelected(cur);
                        int position = monkey.getDirection().equals("L->R") ? 0 : monkey.getSelected().getLength() - 1;
                        monkey.setPosition(position);
                        monkey.getSelected().setUser(monkey, position);
                        return;
                    }
                }
            }
        }
    }
}
