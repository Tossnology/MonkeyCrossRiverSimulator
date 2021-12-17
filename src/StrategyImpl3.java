public class StrategyImpl3 implements Strategy{

    @Override
    public void selectLadder(Monkey monkey) {
        // TODO Auto-generated method stub
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

            for (int i = 0; i < Main.ladders.length; i++) {
                Ladder cur = Main.ladders[i];
                synchronized (cur) {
                    if (cur.isNoBlocking(monkey.getDirection(), monkey.getVelocity())) {
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
