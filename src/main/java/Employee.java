import java.util.Objects;

class Employee {

    private final String employeeNumber;

    private final String name;

    private final Department department;

    public Employee(String employeeNumber, String name, Department department) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.department = department;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(employeeNumber, employee.employeeNumber) &&
                Objects.equals(name, employee.name) &&
                department == employee.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, name, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}