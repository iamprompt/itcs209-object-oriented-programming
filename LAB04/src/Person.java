public class Person {
    private String firstname;
    private String lastname;
    private MyDate birthday;

    public Person(String aFirstname, String aLastname) {
        this.firstname = aFirstname;
        this.lastname = aLastname;
        this.birthday = new MyDate();
    }

    public Person(String aFirstname, String aLastname, int aYear, int aMonth, int aDay) {
        this.firstname = aFirstname;
        this.lastname = aLastname;
        this.birthday = new MyDate(aYear, aMonth, aDay);
    }

    public int getAge(MyDate aDate) {
        return MyDate.yearDiff(this.birthday, aDate);
    }

    public boolean isEligible(MyDate elecDate) {
        if (getAge(elecDate) >= 18)
            return true;
        else return false;
    }

    public void printPersonInfo() {
        System.out.println("Person: " + firstname + " " + lastname);
        System.out.println("Birthday: " + birthday.toString());
    }
}
