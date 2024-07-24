import java.util.*;

public class Order {

    int orderNo;
    int orderWidth;
    String grade;
    Date deliveryDate;
    double btrQty;
    String product;
    double btcQty;
    int bucket;
    String l1Group;
    String l2Group;
    String l3Group;
    String l4Group;
    String gradeGroup;
    String vdType;
    String gradeType;
    String rollingMill;
    String scrafingGroup;
    int setWidth;

    public Order(int orderNo, int orderWidth, String grade, Date deliveryDate, double btrQty, String product) {
        this.orderNo = orderNo;
        this.orderWidth = orderWidth;
        this.grade = grade;
        this.deliveryDate = deliveryDate;
        this.btrQty = btrQty;
        this.product = product;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderWidth() {
        return orderWidth;
    }

    public void setOrderWidth(int orderWidth) {
        this.orderWidth = orderWidth;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getBtrQty() {
        return btrQty;
    }

    public void setBtrQty(double btrQty) {
        this.btrQty = btrQty;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getBtcQty() {
        return btcQty;
    }

    public void setBtcQty(double btcQty) {
        this.btcQty = btcQty;
    }

    public int getBucket() {
        return bucket;
    }

    public void setBucket(int bucket) {
        this.bucket = bucket;
    }

    public String getL1Group() {
        return l1Group;
    }

    public void setL1Group(String l1Group) {
        this.l1Group = l1Group;
    }

    public String getL2Group() {
        return l2Group;
    }

    public void setL2Group(String l2Group) {
        this.l2Group = l2Group;
    }

    public String getL3Group() {
        return l3Group;
    }

    public void setL3Group(String l3Group) {
        this.l3Group = l3Group;
    }

    public String getL4Group() {
        return l4Group;
    }

    public void setL4Group(String l4Group) {
        this.l4Group = l4Group;
    }

    public String getGradeGroup() {
        return gradeGroup;
    }

    public void setGradeGroup(String gradeGroup) {
        this.gradeGroup = gradeGroup;
    }

    public String getVdType() {
        return vdType;
    }

    public void setVdType(String vdType) {
        this.vdType = vdType;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public String getRollingMill() {
        return rollingMill;
    }

    public void setRollingMill(String rollingMill) {
        this.rollingMill = rollingMill;
    }

    public String getScrafingGroup() {
        return scrafingGroup;
    }

    public void setScrafingGroup(String scrafingGroup) {
        this.scrafingGroup = scrafingGroup;
    }

    public int getSetWidth() {
        return setWidth;
    }

    public void setSetWidth(int setWidth) {
        this.setWidth = setWidth;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", orderWidth=" + orderWidth +
                ", grade='" + grade + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", btrQty=" + btrQty +
                ", product='" + product + '\'' +
                ", btcQty=" + btcQty +
                ", bucket=" + bucket +
                ", l1Group='" + l1Group + '\'' +
                ", l2Group='" + l2Group + '\'' +
                ", l3Group='" + l3Group + '\'' +
                ", l4Group='" + l4Group + '\'' +
                ", gradeGroup='" + gradeGroup + '\'' +
                ", vdType='" + vdType + '\'' +
                ", gradeType='" + gradeType + '\'' +
                ", rollingMill='" + rollingMill + '\'' +
                ", scrafingGroup='" + scrafingGroup + '\'' +
                ", setWidth=" + setWidth +
                '}';
    }
}
