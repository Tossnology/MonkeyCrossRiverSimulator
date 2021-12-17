import java.util.List;

public class Monkey implements Runnable{
    private int ID;
    private String direction;
    private int velocity;
    private Ladder selected;
    private int position;

    @Override
    public void run() {
        crossRiver();
    }

    private void crossRiver() {
        //System.out.println("猴子ID：" + this.ID + "开始过河，选择的梯子为：" + selected + "方向：" + this.direction);
        selectLadder(new StrategyImpl3());

        while (foward()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (Main.finished) {
            Main.finished++;
        }
        //System.out.println("猴子ID：" + this.ID + "过河成功！");
    }

    public void selectLadder(Strategy strategy) {
        strategy.selectLadder(this);
        //System.out.println("猴子" + ID + "采用策略：" + strategy.getClass().getName());
    }

    public boolean foward() {
        int target = direction.equals("L->R") ? position + velocity : position - velocity;
        if (target < 0) target = 0;
        if (target > selected.getLength() - 1) target = selected.getLength() - 1;
        if (isBlocked(target)) return true;

        int lastPosition = position;

        position = direction.equals("L->R") ? position + velocity : position - velocity;
        if (position < 0 || position > selected.getLength() - 1) {
            synchronized (selected) {
                selected.setUser(null, lastPosition);
            }
            return false;
        }
        synchronized (selected) {
            selected.setUser(null, lastPosition);
            selected.setUser(this, position);
        }
        return true;
    }

    private boolean isBlocked(int target) {
        if (direction.equals("L->R")) {
            for (int i = position + 1; i <= target; i++) {
                synchronized (selected) {
                    if (selected.getUser(i) != null) return true;
                }
            }
        } else {
            for (int i = position - 1; i >= target; i--) {
                synchronized (selected) {
                    if (selected.getUser(i) != null) return true;
                }
            }
        }
        return false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public Ladder getSelected() {
        return selected;
    }

    public void setSelected(Ladder selected) {
        this.selected = selected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
