package models;

public class Production {

    private String leftSide;
    private String rightSide;

    public Production(String leftSide, String rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public String toString() {
        return leftSide + " ----> " + rightSide;
    }
}
