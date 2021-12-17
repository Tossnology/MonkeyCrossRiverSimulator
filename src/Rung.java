public class Rung {
    private Monkey user;
    String isHeld = "==";
//    Rung left;
//    Rung right;
    public void setUser(Monkey monkey) {
        this.user = monkey;
        isHeld = user == null ? "==" : String.valueOf(monkey.getDirection().substring(3, 4) + monkey.getVelocity());
    }

    public Monkey getUser() {
        return user;
    }
}
