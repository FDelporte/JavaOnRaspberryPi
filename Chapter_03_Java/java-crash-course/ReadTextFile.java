import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class ReadTextFile {
    public static void main (String[] args) {
        List<Person> persons = loadPersons();

        System.out.println("Number of persons loaded from CSV file: " + persons.size());

        for (Person person : persons) {
            System.out.println(person.getFullName() + ", age: " + person.getAge());
        }

        System.out.println("------------------------------------------------");
        System.out.println("Number of persons with first name");
        System.out.println("    * 'Matthew':     " + countFirstName(persons, "Matthew"));
        System.out.println("    * 'Charlotte':   " + countFirstName(persons, "Charlotte"));

        System.out.println("------------------------------------------------");
        IntSummaryStatistics stats = getAgeStats(persons);
        System.out.println("Minimum age: " + stats.getMin());
        System.out.println("Maximum age: " + stats.getMax());
        System.out.println("Average age: " + stats.getAverage());
    }

    public static int countFirstName(List<Person> persons, String firstName) {
        return persons.stream()
                .filter(p -> p.getFirstName().equals(firstName))
                .collect(Collectors.toList())
                .size();
    }

    public static IntSummaryStatistics getAgeStats(List<Person> persons) {
        return persons.stream()
                .mapToInt(p -> p.getAge())
                .summaryStatistics();
    }

    public static List<Person> loadPersons() {
        List<Person> list = new ArrayList<>();

        try {
            File file = new File("resources/testdata.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                list.add(new Person(line));
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        return list;
    }

    public static class Person {
        private int id;
        private String firstName;
        private String lastName;
        private int age;
        private String street;
        private String city;
        private String state;
        private int zip;

        public Person(String csvLine) {
            String[] data = csvLine.split(",");

            this.id = Integer.valueOf(data[0]);
            this.firstName = data[1];
            this.lastName = data[2];
            this.age = Integer.valueOf(data[3]);
            this.street = data[4];
            this.city = data[5];
            this.state = data[6];
            this.zip = Integer.valueOf(data[7]);
        }

        public String getFirstName() {
            return firstName;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public int getAge() {
            return age;
        }
    }
}
