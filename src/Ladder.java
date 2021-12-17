public class Ladder {
    private Rung[] rungs;

    public Ladder(Rung[] rungs) {
        this.rungs = rungs;
//        for (int i = 0; i < rungs.length; i++) {
//            rungs[i].left = i == 0 ? null : rungs[i - 1];
//            rungs[i].right = i == rungs.length - 1 ? null : rungs[i + 1];
//        }
    }

    public Rung[] getRungs() {
        return rungs;
    }

    public void setRungs(Rung[] rungs) {
        this.rungs = rungs;
    }

    public boolean isEmpty() {
        for (Rung rung : rungs) {
            if (rung.getUser() != null) return false;
        }
        return true;
    }

    public Monkey getUser(int position) {
        return rungs[position].getUser();
    }

    public void setUser(Monkey monkey, int position) {
        rungs[position].setUser(monkey);
    }

    public int getLength() {
        return rungs.length;
    }

    public boolean isAllDirection(String direction) {
        if (direction.equals("L->R")) {
            for (Rung rung : rungs) {
                if (rung.getUser() != null && rung.getUser().getDirection().equals("R->L")) return false;
            }
            return rungs[0].getUser() == null;
        } else {
            for (Rung rung : rungs) {
                if (rung.getUser() != null && rung.getUser().getDirection().equals("L->R")) return false;
            }
            return rungs[rungs.length - 1].getUser() == null;
        }
    }

    public boolean isNoBlocking(String direction, int velocity) {
        if (direction.equals("L->R")) {
            for (Rung rung : rungs) {
                if (rung.getUser() != null && rung.getUser().getDirection().equals("R->L")) return false;
            }
            for (Rung rung : rungs) {
                if (rung.getUser() != null && rung.getUser().getVelocity() < velocity) return false;
            }
            return rungs[0].getUser() == null;
        } else {
            for (Rung rung : rungs) {
                if (rung.getUser() != null && rung.getUser().getDirection().equals("L->R")) return false;
            }
            for (Rung rung : rungs) {
                if (rung.getUser() != null && rung.getUser().getVelocity() < velocity) return false;
            }
            return rungs[rungs.length - 1].getUser() == null;
        }
    }
}
