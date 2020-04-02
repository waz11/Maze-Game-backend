package algorithms.search;

import java.util.LinkedList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    String Name;
    int numberOfNodesEvaluated;

    public ASearchingAlgorithm() {
        this.Name = this.getClass().toString();
        this.numberOfNodesEvaluated = 0;
    }

    public String getName(){return this.Name;}

    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    protected Solution createSolution (AState curr, AState start){
        Solution finalSolution = new Solution();
        LinkedList<AState> route = new LinkedList<>();
        while (!(curr.getSource() == null)){
            route.add(curr);
            curr = curr.getSource();
        }
        route.add(start);
        for (int i=route.size()-1; i>=0; i--)
            finalSolution.addState(route.get(i));

        return finalSolution;
    }

}
