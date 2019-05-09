package Model;

public class Member {

    private String name;
    private String personal_number;
    private int member_id;

    public Member(String name, String personal_number, int member_id){
        this.name = name;
        this.personal_number = personal_number;
        this.member_id = member_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPersonal_number(String personal_number) {
        this.personal_number = personal_number;
    }

    public String getPersonal_number() {
        return personal_number;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getMember_id() {
        return member_id;
    }
    public String toString() {
        return "Name - " + getName() + "\n Personal Number = " + getPersonal_number()
                + " \n Member ID - " + getMember_id();
    }
}
