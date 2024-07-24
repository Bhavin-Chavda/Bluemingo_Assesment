public class GradeDetail {

    String grade;
    String gradeGrp;
    String gradeGrp1;
    String vdType;

    public GradeDetail(String grade, String gradeGrp, String gradeGrp1, String vdType, String gradeType, String rollingMill, String scrafingGroup) {
        this.grade = grade;
        this.gradeGrp = gradeGrp;
        this.gradeGrp1 = gradeGrp1;
        this.vdType = vdType;
        this.gradeType = gradeType;
        this.rollingMill = rollingMill;
        this.scrafingGroup = scrafingGroup;
    }

    String gradeType;
    String rollingMill;
    String scrafingGroup;

    public GradeDetail() {

    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeGrp() {
        return gradeGrp;
    }

    public void setGradeGrp(String gradeGrp) {
        this.gradeGrp = gradeGrp;
    }

    public String getGradeGrp1() {
        return gradeGrp1;
    }

    public void setGradeGrp1(String gradeGrp1) {
        this.gradeGrp1 = gradeGrp1;
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


}
