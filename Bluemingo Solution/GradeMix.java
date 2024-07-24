public class GradeMix {
    String orderGrade;
    String mixingPossible;

    public GradeMix(String orderGrade, String mixingPossible) {
        this.orderGrade = orderGrade;
        this.mixingPossible = mixingPossible;
    }

    public String getOrderGrade() {
        return orderGrade;
    }

    public void setOrderGrade(String orderGrade) {
        this.orderGrade = orderGrade;
    }

    public String getMixingPossible() {
        return mixingPossible;
    }

    public void setMixingPossible(String mixingPossible) {
        this.mixingPossible = mixingPossible;
    }

}
