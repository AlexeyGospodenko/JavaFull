package lesson5;

public class Employer {

    //1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
    private static int employerId = 0;
    //ФИО
    private String name;
    //Должность
    private String position;
    //Email
    private String email;
    //Телефон
    private String telNumber;
    //Зарплата
    private int salary;
    //Возраст
    private int age;

    public Employer() {
        employerId++;
    }

    //2. Конструктор класса должен заполнять эти поля при создании объекта;
    public Employer(String name, String position, String email, String telNumber, int salary, int age) {
        employerId++;
        this.name = name;
        this.position = position;
        this.email = email;
        this.telNumber = telNumber;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    //3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
    @Override
    public String toString() {
        return "Employer{" +
                "employerId='" + employerId + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
