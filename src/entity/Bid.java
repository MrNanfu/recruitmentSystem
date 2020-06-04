package entity;

public class Bid {
    private int numOfBid;
    private String tag;
    private String phoneNumber;
    private String topic;
    private String content;
    private String expectMoney;
    private String timeLimit;
    private String callName;
    private String throwName;
    private int state;
    /*
    crf
    * */
    public Bid(){}

    public Bid(String tag,String expectMoney,String timeLimit,int numOfBid){
        this.numOfBid = numOfBid;
        this.tag = tag;
        this.expectMoney = expectMoney;
        this.timeLimit = timeLimit;
    }



    public Bid(String callName,String throwName,String tem){
        this.callName = callName;
        this.throwName = throwName;
    }
        /*
    crf
    * */


        /*
    ywj
    * */
    public Bid(String topic,String callName){
        this.callName = callName;
        this.topic = topic;
    }
            /*
    ywj
    * */


    public void setNumOfBid(int numOfBid) {
        this.numOfBid = numOfBid;
    }

    public int getNumOfBid() {
        return numOfBid;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setExpectMoney(String expectMoney) {
        this.expectMoney = expectMoney;
    }

    public String getExpectMoney() {
        return expectMoney;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public String getCallName() {
        return callName;
    }

    public void setThrowName(String throwName) {
        this.throwName = throwName;
    }

    public String getThrowName() {
        return throwName;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
