class TaxCalculator {

    public static double getFederalIncomeTax(double grossSalary) {
        double firstSalary = Math.min(grossSalary, 35000);
        double secondSalary = Math.min(Math.max(0, grossSalary - 35000), 35000);

        return firstSalary * 0.16 + secondSalary * 0.22;
    }

    public static double getProvincialIncomeTax(double grossSalary) {
        double firstSalary = Math.min(grossSalary, 35000);
        double secondSalary = Math.min(Math.max(0, grossSalary - 35000), 35000);

        return firstSalary * 0.0605 + secondSalary * 0.0725;
    }
}