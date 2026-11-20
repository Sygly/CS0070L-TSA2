package com.mycompany.bookbridge_tsa2;

public class BookBridgeApp {

    public static void main(String[] args) {

        LibraryService service = new LibraryService();

        System.out.println("================================");
        System.out.println("       BOOKBRIDGE TSA2");
        System.out.println(" Object-Oriented Library System");
        System.out.println("================================");

        boolean allPassed = true;

        // TSA1 Regression Test 1: Normal Approval
        allPassed &= runTransaction(
                service,
                1,
                new StudentMember("M001", "Maria Santos"),
                new LibraryBook("9781234567890",
                        "Java Programming", true),
                10, 2, "APPROVED", 10);

        // TSA1 Regression Test 2: Unavailable Reservation
        allPassed &= runTransaction(
                service,
                2,
                new FacultyMember("M002", "John Reyes"),
                new LibraryBook("9781234567891",
                        "Database Systems", false),
                20, 0, "RESERVED", 0);

        // TSA1 Regression Test 3: Over-Limit Denial
        allPassed &= runTransaction(
                service,
                3,
                new GuestMember("M003", "Anna Cruz"),
                new LibraryBook("9781234567892",
                        "Computer Networks", true),
                10, 1, "DENIED", 10);

        // TSA1 Regression Test 4: Boundary Value
        allPassed &= runTransaction(
                service,
                4,
                new StudentMember("M004", "Mark Lopez"),
                new LibraryBook("9781234567893",
                        "Data Structures", true),
                14, 3, "APPROVED", 15);

        // TSA1 Regression Test 5: Summary Calculation
        allPassed &= runTransaction(
                service,
                5,
                new FacultyMember("M005", "Sarah Garcia"),
                new LibraryBook("9781234567894",
                        "Software Engineering", true),
                30, 2, "APPROVED", 4);

        service.printSummary();

        System.out.println(
                "\n========== POLYMORPHISM TESTS ==========");

        // Runtime dispatch through abstract Member references.
        Member student = new StudentMember(
                "P001", "Test Student");

        Member faculty = new FacultyMember(
                "P002", "Test Faculty");

        Member guest = new GuestMember(
                "P003", "Test Guest");

        boolean polymorphismTest1 =
                student.getMaximumLoanDays() == 14
                && faculty.getMaximumLoanDays() == 30
                && guest.getMaximumLoanDays() == 7;

        boolean polymorphismTest2 =
                student.getDailyFine() == 5
                && faculty.getDailyFine() == 2
                && guest.getDailyFine() == 10;

        System.out.println(
                "Polymorphism Test 1 (Loan Limits): "
                + (polymorphismTest1 ? "PASS" : "FAIL"));

        System.out.println(
                "Polymorphism Test 2 (Daily Fines): "
                + (polymorphismTest2 ? "PASS" : "FAIL"));

        System.out.println(
                "\nOverall Regression Tests: "
                + (allPassed
                && polymorphismTest1
                && polymorphismTest2 ? "PASS" : "FAIL"));
    }

    // Processes one transaction using domain objects.
    private static boolean runTransaction(
            LibraryService service,
            int transactionNumber,
            Member member,
            LibraryBook book,
            int requestedDays,
            int lateDays,
            String expectedOutcome,
            int expectedCharge) {

        boolean availableAtRequest = book.isAvailable();

        LoanRecord record = service.processRequest(
                member, book, requestedDays, lateDays);

        boolean passed =
                record.getOutcome().equals(expectedOutcome)
                && record.getEstimatedLateCharge() == expectedCharge;

        System.out.println(
                "\n========== TRANSACTION "
                + transactionNumber + " ==========");

        System.out.println(
                "Member ID: " + member.getMemberId());

        System.out.println(
                "Member Name: " + member.getMemberName());

        System.out.println(
                "Membership Type: " + member.getMembershipType());

        System.out.println(
                "ISBN: " + book.getIsbn());

        System.out.println(
                "Book Title: " + book.getTitle());

        System.out.println(
                "Requested Days: " + requestedDays);

        System.out.println(
                "Available at Request: "
                + (availableAtRequest ? "YES" : "NO"));

        System.out.println(
                "Late Days: " + lateDays);

        System.out.println(
                "Maximum Allowed Days: "
                + member.getMaximumLoanDays());

        System.out.println(
                "Daily Late Fee: PHP "
                + member.getDailyFine());

        System.out.println(
                "Request Status: " + record.getOutcome());

        System.out.println(
                "Estimated Late Charge: PHP "
                + record.getEstimatedLateCharge());

        System.out.println(
                "Test Result: " + (passed ? "PASS" : "FAIL"));

        return passed;
    }
}