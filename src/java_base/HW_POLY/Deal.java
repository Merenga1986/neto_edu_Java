package java_base.HW_POLY;

public class Deal {
    private String comment;
    private int creditChange;
    private int debitChange;

    public Deal(String comment, int debitChange, int creditChange) {
        this.comment = comment;
        this.debitChange = debitChange;
        this.creditChange = creditChange;
    }

    public String getComment()     { return comment; }
    public int getDebitChange()    { return debitChange; }
    public int getCreditChange()   { return creditChange; }
}