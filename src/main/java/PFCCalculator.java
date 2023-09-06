class PFCCalculator {

    public static double getEmployeeContribution(double grossSalary) {
        return grossSalary * 0.0495;
    }

    public static double getEmployerContribution(double grossSalary) {
        return getEmployeeContribution(grossSalary);
    }

    public static double getPFC(double grossSalary) {
        return getEmployeeContribution(grossSalary) + getEmployerContribution(grossSalary);
    }
}
