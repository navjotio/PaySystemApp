enum Department {
    RESTAURANT(8.5),
    MAINTENANCE(12.5),
    CLERK(15.75),
    LANDSCAPERS(15.75),
    SALES(15, true);

    private final double hourlyRate;

    private final boolean isPaidMonthly;

    Department(double hourlyRate) {
        this(hourlyRate, false);
    }

    Department(double hourlyRate, boolean paidMonthly) {
        this.hourlyRate = hourlyRate;
        this.isPaidMonthly = paidMonthly;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public boolean isPaidMonthly() {
        return isPaidMonthly;
    }
}
