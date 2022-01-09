package util.calc.path;

public class AStarNode {
    int x;
    int y;
    double hValue;
    int gValue;
    double fValue;
    AStarNode parent;


    public AStarNode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
