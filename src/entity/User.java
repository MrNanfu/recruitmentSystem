package entity;

public class User {
    private String name;
    private String pwd;
    private String work;
    private String phoneNum;
    private String place;
    private String expMoney;
    private String tag;
    private String systag;
    private String remark;
    private double dealmoney;
    private int id;

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getExpMoney() {
        return expMoney;
    }

    public void setExpMoney(String expMoney) {
        this.expMoney = expMoney;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSystag() {
        return systag;
    }

    public void setSystag(String systag) {
        this.systag = systag;
    }

    public double getDealmoney() {
        return dealmoney;
    }

    public void setDealmoney(double dealmoney) {
        this.dealmoney = dealmoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
