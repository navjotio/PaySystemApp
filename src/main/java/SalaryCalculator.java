import java.util.List;

class SalaryCalculator {

    public static double getGrossSalary(Employee employee) {
        assertEmployeeIsNotNull(employee);

        double grossSalary = 0;
        if (!employee.getDepartment().isPaidMonthly()) {
            List<Float> employeeHours = Ledger.getInstance().getHoursForEmployee(employee);
            for (Float hours : employeeHours) {
                float overtimeHours = Math.min(0, hours - 44);
                float hoursWithoutOvertime = Math.min(44, hours);
                grossSalary += hoursWithoutOvertime * employee.getDepartment().getHourlyRate();
                grossSalary += overtimeHours * (employee.getDepartment().getHourlyRate() * 1.5f);
            }

        } else {
            List<Float> employeeHours = Ledger.getInstance().getMonthlyHoursForEmployee(employee);
            for (Float hours : employeeHours) {
                double monthlySalary = getMonthlySalary(employee, hours);
                grossSalary += monthlySalary;
            }
        }

        return grossSalary;
    }

    public static double getMonthlySalary(Employee employee, Float hours) {
        double monthlySalary = hours * employee.getDepartment().getHourlyRate();
        double commission = 0;
        if (monthlySalary >= 7000) {
            commission = 0.05;
        } else if (monthlySalary >= 4250) {
            commission = 0.025;
        } else if (monthlySalary >= 2500) {
            commission = 0.015;
        }
        monthlySalary += monthlySalary * commission;
        return monthlySalary;
    }

    public static double getNetSalary(Employee employee) {
        assertEmployeeIsNotNull(employee);

        double grossSalary = getGrossSalary(employee);
        double federalTaxes = TaxCalculator.getFederalIncomeTax(grossSalary);
        double provincialTaxes = TaxCalculator.getProvincialIncomeTax(grossSalary);
        double pfc = PFCCalculator.getEmployeeContribution(grossSalary);
        double employmentInsurance = InsuranceCalculator.getEmployeeContribution(grossSalary);

        return grossSalary - federalTaxes - provincialTaxes - pfc - employmentInsurance;
    }

    private static void assertEmployeeIsNotNull(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Must pass employee for calculating gross salary");
        }
    }
}
