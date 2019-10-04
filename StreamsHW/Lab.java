package GA.homework.StreamsHW;

import org.junit.Test;
import sun.jvm.hotspot.debugger.win32.coff.COMDATSelectionTypes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
        list.forEach(employee -> System.out.println(employee));
    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employeesOver50k = employees.stream()
                .filter(employee -> employee.getSalary() > 50000)
                .collect(Collectors.toList());
        printList(employeesOver50k);
    }


    @Test
    public void getEmployeeNamesHiredAfter2012() {
        LocalDate checker = LocalDate.of(2012, 01, 01);
        List<String> employeesHiredAfter2012 = employees.stream()
                .filter(employee -> employee.getHireDate().isAfter(checker))
                .map(employee -> employee.getName())
                .collect(Collectors.toList());
        printList(employeesHiredAfter2012);
    }

    @Test
    public void getMaxSalary() {
        double max = employees.stream().map(employee -> employee.getSalary())
                .max(Comparator.comparing(salary -> salary)).get();

        System.out.println("Max:" + max);

    }

    @Test
    public void getMinSalary() {
        double min = employees.stream()
                .min(Comparator.comparing(employee -> employee.getSalary())) //returns obj with highest salary
                .get().getSalary();//get the salary double

        System.out.println("Min:" + min);
    }
    @Test
    public void getMinSalary2() {
        double min = employees.stream()
                .min(Comparator.comparing(Employee::getSalary)) //returns an Optional class obj with highest salary
                .get().getSalary();//get the salary double from

        System.out.println("Min:" + min);
    }


    public double getAvg(String Gender) {
        return
                employees.stream()
                        .filter(employee -> employee.getGender() == Gender)
                        .mapToDouble(employee -> employee.getSalary())
                        .average()
                        .getAsDouble();
    }
    @Test
    public void getAverageSalaries() {

        double averageMale = getAvg("Male");
        double averageFemale = getAvg("Female");
        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);

    }



    @Test
    public void getMaximumPaidEmployee() {
        Employee em =
        new Employee("Karasik, Ben", LocalDate.of(2004, 4, 2), 1000000000.1, "Male");

                Employee highest = employees.stream()
                .reduce(em,
                        (acc, el) -> {
                    if(acc.getSalary() < el.getSalary()){
                        return el;
                    }
                    return acc;
                });
        System.out.println(highest);
    }
}
