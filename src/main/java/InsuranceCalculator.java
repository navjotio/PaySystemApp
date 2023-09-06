class InsuranceCalculator {

    public static double getEmployeeContribution(double grossSalary) {
        return grossSalary * 0.0198;
    }

    public static double getEmployerContribution(double grossSalary) {
        return grossSalary * 0.0277;
    }

    public static double getEmploymentInsurance(double grossSalary) {
        return getEmployeeContribution(grossSalary) + getEmployerContribution(grossSalary);
    }
}