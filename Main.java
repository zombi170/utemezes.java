import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<Input> rr = new LinkedList<>();
    static LinkedList<Input> srtf = new LinkedList<>();
    static LinkedList<Input> s_ready = new LinkedList<>();
    static LinkedList<Input> r_ready = new LinkedList<>();
    static LinkedList<Input> list = new LinkedList<>();
    static String run = "";

    public static void read(){
        char temp_c;
        boolean temp_b, next = true;
        int temp_1, temp_2;

        Scanner scan = new Scanner(System.in);
        String sor;
        while (next && scan.hasNextLine()) {
            sor = scan.nextLine();
            while (sor.isEmpty() && next){
                if (scan.hasNextLine()){
                    sor = scan.nextLine();
                }
                else{
                    next = false;
                }
            }

            if (next){
                String[] in = sor.split(",");
                temp_c = in[0].charAt(0);
                temp_b = in[1].charAt(0) != '0';
                temp_1 = Integer.parseInt(in[2]);
                temp_2 = Integer.parseInt(in[3]);

                if (temp_b){
                    rr.add(new Input(temp_c, temp_b, temp_1, temp_2));
                }
                else{
                    srtf.add(new Input(temp_c, temp_b, temp_1, temp_2));
                }
            }
        }
        scan.close();
    }

    public static void sort_lists(){
        rr.sort(new NameComp());
        rr.sort(new StartComp());

        srtf.sort(new NameComp());
        srtf.sort(new StartComp());

        list.addAll(rr);
        list.addAll(srtf);
        list.sort(new NameComp());
        list.sort(new StartComp());
    }

    public static int step(Input temp, int i){
        temp.time--;

        for (Input temp_l : list){
            if (temp_l != temp && temp_l.start <= i && temp_l.time != 0){
                temp_l.wait++;
            }
        }
        return ++i;
    }

    public static void run_add(Input temp){
        if (run.length() == 0 || run.charAt(run.length() - 1) != temp.sign){
            run += temp.sign;
        }
    }

    public static void print_list(){
        for (Input p : list){
            if (p != list.getLast()){
                System.out.print(p + ",");
            }
        }
        System.out.print(list.getLast());
    }

    public static void rrReady(int i){
        if (rr.size() != 0){
            for (Input r : rr){
                if (r.start <= i && !r_ready.contains(r) && r.time != 0){
                    r_ready.add(r);
                }
            }
        }
    }

    public static boolean srtfReady(int i){
        boolean is_new = false;

        while (!srtf.isEmpty() && srtf.getFirst().start <= i){
            s_ready.add(srtf.pop());
            is_new = true;
        }

        s_ready.sort(new TimeComp());
        return is_new;
    }

    public static Input getNext(){
        if (!s_ready.isEmpty()){
            return s_ready.pop();
        }
        return null;
    }

    public static void addStack(Input s){
        s_ready.add(s);
        s_ready.sort(new TimeComp());
    }

    public static void main(String[] args) {

        read();
        sort_lists();

        int i = 0;
        Input temp_r, temp_s = null;
        boolean was_new;
        was_new = srtfReady(i);
        if (was_new){
            temp_s = getNext();
        }
        rrReady(i);

        while (rr.size() != 0 || srtf.size() != 0 || s_ready.size() != 0) {
            //If no progress is being made
            while (r_ready.isEmpty() && temp_s == null){
                i++;
                was_new = srtfReady(i);
                if (was_new){
                    temp_s = getNext();
                }
                rrReady(i);
            }
            //RR part
            while (r_ready.size() != 0) {
                temp_r = r_ready.getFirst();
                run_add(temp_r);

                if (temp_r.time >= 2) {
                    i = step(temp_r, i);
                    rrReady(i);
                    i = step(temp_r, i);
                } else {
                    i = step(temp_r, i);
                }

                rrReady(i);
                r_ready.pop();
                rrReady(i);

                if (temp_r.time == 0) {
                    rr.remove(temp_r);
                }

                was_new = srtfReady(i);
                if (was_new){
                    if (!s_ready.isEmpty() && temp_s != null && s_ready.getFirst().time < temp_s.time){
                        addStack(temp_s);
                        temp_s = getNext();
                    }
                }
            }
            //SRTF part
            if (temp_s == null){
                temp_s = getNext();
            }
            while (temp_s != null && r_ready.isEmpty()){
                run_add(temp_s);

                i = step(temp_s, i);
                was_new = srtfReady(i);

                if (temp_s.time == 0){
                    temp_s = getNext();
                }
                else if (was_new){
                    if (!s_ready.isEmpty() && s_ready.getFirst().time < temp_s.time){
                        addStack(temp_s);
                        temp_s = getNext();
                    }
                }

                rrReady(i);
                if (!r_ready.isEmpty() && temp_s != null){
                    addStack(temp_s);
                    temp_s = getNext();
                }
            }
        }

        System.out.println(run);
        print_list();
    }
}