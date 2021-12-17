import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static Ladder[] ladders;
    public static Integer finished = 0;

    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("hyper-parameters.properties"));
            prop.load(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int LADDER_NUM = Integer.valueOf(prop.getProperty("n"));
        final int RUNG_NUM = Integer.valueOf(prop.getProperty("h"));
        final int TIME_SPAN = Integer.valueOf(prop.getProperty("t"));
        final int TOTAL_NUMBER = Integer.valueOf(prop.getProperty("N"));
        final int PER_NUMBER = Integer.valueOf(prop.getProperty("k"));
        final int MAX_VELOCITY = Integer.valueOf(prop.getProperty("MV"));

        System.out.println(RUNG_NUM);

        //初始化ladder和rung
        ladders = new Ladder[LADDER_NUM];
        for (int i = 0; i < LADDER_NUM; i++) {
            Rung[] rungs = new Rung[RUNG_NUM];
            for (int j = 0; j < RUNG_NUM; j++) {
                rungs[j] = new Rung();
            }
            ladders[i] = new Ladder(rungs);
        }

        //打印梯子
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (finished != TOTAL_NUMBER) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < LADDER_NUM; i++) {
                    sb.append(i + ":");
                    for (int j = 0; j < RUNG_NUM; j++) {
                        sb.append(ladders[i].getRungs()[j].isHeld);
                    }
                    sb.append("\n");
                }
                System.out.println(sb);
                System.out.println(finished);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("用时：" + ((double)(endTime - startTime))/1000 + "s");
        }).start();


        //开始生成猴子并过河
        int cur = 0;
        while (cur < TOTAL_NUMBER) {
            MonkeyGenerator.getMonkey(PER_NUMBER, MAX_VELOCITY, TOTAL_NUMBER, cur, ladders);
            cur += PER_NUMBER;
            try {
                Thread.sleep(TIME_SPAN * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
