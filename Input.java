public class Input {
    char sign;
    boolean priority;
    int start;
    int time;
    int wait = 0;

    Input(char si, boolean p, int st, int t){
        sign = si;
        priority = p;
        start = st;
        time = t;
    }

    public String toString() {
        return sign + ":" + wait;
    }
}
