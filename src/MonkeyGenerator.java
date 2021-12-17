
import java.util.Random;

public class MonkeyGenerator {
    public static void getMonkey(int k, int MV, int N, int cur, Ladder[] ladders) {
        if (N - cur < k) {
            k = N - cur;
        }
        for (int i = 0; i < k; i++) {
            Monkey monkey = new Monkey();
            //生成ID
            monkey.setID(cur + i);
            //生成方向
            Random random = new Random();
            int r = random.nextInt(2);
            if (r == 0) {
                monkey.setDirection("L->R");
            } else {
                monkey.setDirection("R->L");
            }
            //生成速度
            int v = random.nextInt(MV) + 1;
            monkey.setVelocity(v);
            //选梯子
            //Strategy strategy;
            //r = random.nextInt(2);
            //strategy = r == 0 ? new StrategyImpl1() : new StrategyImpl2();
            //strategy = new StrategyImpl1();
            //monkey.selectLadder(ladders, strategy);
            Thread crossRiver = new Thread(monkey);
            //crossRiver.setPriority(10);
            crossRiver.start();
        }
    }
}
