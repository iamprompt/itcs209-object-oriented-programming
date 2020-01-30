public class MyDate {
    private int year;
    private int month;
    private int day;
    private int objectNumber;

    public static int objectCounter = 0;
    public static String[] strMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    MyDate() {
        this.year = 1900;
        this.month = 1;
        this.day = 1;
        this.objectCounter += 1;
        this.objectNumber = this.objectCounter;
    }

    MyDate(int aYear, int aMonth, int aDay) {
        this.year = aYear;
        this.month = aMonth;
        this.day = aDay;
        this.objectCounter += 1;
        this.objectNumber = this.objectCounter;
    }

    public int getObjectNumber() {
        return this.objectNumber;
    }

    public void setDate(int aYear, int aMonth, int aDay) {
        this.year = aYear;
        this.month = aMonth;
        this.day = aDay;
    }

    public void setYear(int aYear) {
        this.year = aYear;
    }

    public void setMonth(int aMonth) {
        this.month = aMonth;
    }

    public void setDay(int aDay) {
        this.day = aDay;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String toString() {
        return day + " " + strMonths[month - 1] + " " + year;
    }

    MyDate nextDay() {
        if (month == 12 && day == 31) {
            year += 1;
            month = 1;
            day = 1;
        } else {
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day == 30) {
                    month += 1;
                    day = 1;
                } else day += 1;
            } else if (month != 2) {
                if (day == 31) {
                    month += 1;
                    day = 1;
                } else day += 1;
            } else {
                if (isLeapYear(year) == true && day == 29) {
                    month += 1;
                    day = 1;
                } else if (isLeapYear(year) == false && day == 28) {
                    month += 1;
                    day = 1;
                } else day += 1;
            }
        }
        return this;
    }

    MyDate nextMonth() {
        if (month == 12) {
            month = 1;
            year += 1;
        } else month += 1;
        return this;
    }

    MyDate nextYear() {
        year += 1;
        if (month == 2 && day == 29) day -= 1;
        return this;
    }

    MyDate previousDay() {
        if (month == 1 && day == 1) {
            year -= 1;
            month = 12;
            day = 31;
        } else {
            if (month == 5 || month == 7 || month == 10 || month == 12) {
                if (day == 1) {
                    month -= 1;
                    day = 30;
                } else day -= 1;
            } else if (month != 3) {
                if (day == 1) {
                    month -= 1;
                    day = 31;
                } else day -= 1;
            } else {
                if (isLeapYear(year) == true && day == 1) {
                    month -= 1;
                    day = 29;
                } else if (isLeapYear(year) == false && day == 1) {
                    month -= 1;
                    day = 28;
                } else day -= 1;
            }
        }
        return this;
    }

    MyDate previousMonth() {
        if (month == 1) {
            month = 12;
            year -= 1;
        } else month -= 1;
        return this;
    }

    MyDate previousYear() {
        year -= 1;
        if (month == 2 && day == 29) day -= 1;
        return this;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) return false;
        else if (year % 100 != 0) return true;
        else if (year % 400 != 0) return false;
        else return true;
    }
}
