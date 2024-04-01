package Classes;


public class Person {
    private final String name;
    private final String surname;
    private final String DOB;
    private final String mobileNumber;

    public Person(String name, String surname, String DOB, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.DOB = DOB;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDOB() {
        return DOB;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "Classes.Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", DOB=" + DOB +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
