package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> N = new AList<Integer>();
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();

        int powerN = 7;
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < (Math.pow(2,powerN+1)-1) * 1000; i++){
            N.addLast(i);
            //if (i == Math.pow(2,Ns.size()) * 1000){
            if (i == Ns.size() * 1000){

                Ns.addLast(Ns.size()*1000);
                times.addLast(sw.elapsedTime());
                opCounts.addLast(i);
            }
        }

        printTimingTable(Ns,times,opCounts);
    }


}
