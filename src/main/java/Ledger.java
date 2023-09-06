import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Ledger {

    private static final int NUM_OF_WEEKS = 52;
    private static final int NUM_OF_MONTHS = 12;

    private final HashMap<Employee, List<Float>> employeeHours = new HashMap<>();

    private final HashMap<Employee, List<Float>> monthlyPaidEmployeeHours = new HashMap<>();

    private static Ledger ledger;

    private Ledger() {
    }

    public static Ledger getInstance() {
        if (ledger == null) {
            ledger = new Ledger();
        }

        return ledger;
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee must not be null");
        }

        if (employee.getDepartment().isPaidMonthly()) {
            getMonthlyHoursForEmployee(employee);
        } else {
            getHoursForEmployee(employee);
        }
    }

    public void addHours(Employee employee, int payPeriod, float numOfHours) {
        assertNumOfHours(numOfHours);

        if (payPeriod < 1 || payPeriod > 52) {
            throw new IllegalArgumentException("Pay period should be between 1 and 52");
        }

        List<Float> hours = getHoursForEmployee(employee);
        hours.set(payPeriod - 1, numOfHours);
    }

    public void addMonthlyHours(Employee employee, int month, float numOfHours) {
        assertNumOfHours(numOfHours);

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month should be between 1 and 12");
        }

        List<Float> hours = getMonthlyHoursForEmployee(employee);
        hours.set(month - 1, numOfHours);
    }

    public List<Float> getHoursForEmployee(Employee employee) {
        return employeeHours.computeIfAbsent(employee,
                                             k -> new ArrayList<>(NUM_OF_WEEKS));
    }

    public List<Float> getMonthlyHoursForEmployee(Employee employee) {
        return monthlyPaidEmployeeHours.computeIfAbsent(employee, k -> new ArrayList<>(NUM_OF_MONTHS));
    }

    public Collection<Employee> getFixedRateEmployees() {
        return employeeHours.keySet();
    }

    public Collection<Employee> getEmployeesOnCommission() {
        return monthlyPaidEmployeeHours.keySet();
    }

    public float getOvertimeHours(Employee employee) {
        float overtimeHours = 0;
        for (Float hours : getHoursForEmployee(employee)) {
            overtimeHours += Math.max(0, hours - 44);
        }

        return overtimeHours;
    }

    public float[] getOvertimeHours() {
        float[] overtimeHours = new float[NUM_OF_WEEKS];
        for (List<Float> employeeHours : employeeHours.values()) {
            for (int i = 0; i < employeeHours.size(); i++) {
                float hours = employeeHours.get(i) == null ? 0 : employeeHours.get(i);
                float employeeOvertimeHours = Math.max(0, hours - 44);

                overtimeHours[i] += employeeOvertimeHours;
            }
        }

        return overtimeHours;
    }

    public double[] getSales() {
        double[] sales = new double[NUM_OF_MONTHS];
        for (Map.Entry<Employee, List<Float>> employeeListEntry : monthlyPaidEmployeeHours.entrySet()) {
            for (int i = 0; i < employeeListEntry.getValue().size(); i++) {
                float hours = employeeListEntry.getValue().get(i) == null ? 0 : employeeListEntry.getValue().get(i);
                sales[i] += SalaryCalculator.getMonthlySalary(employeeListEntry.getKey(), hours);
            }
        }

        return sales;
    }

    private void assertNumOfHours(float numOfHours) {
        if (numOfHours > 168) {
            throw new IllegalArgumentException("Number of hours in a period should be less than or equal to 168");
        }
    }
}
