package codefarther.findapple;

public class PatUserInformation {
    public String fName,lName,email;
    public int phone,emergPhone;
    public String DOB;

    public PatUserInformation(String email,String fName, String lName, int phone, int emergPhone, String DOB) {
        this.email=email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.emergPhone = emergPhone;
        this.DOB = DOB;
    }

    public PatUserInformation(){

    }
}
