package com.mycompany.bookbridge_tsa2;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final List<LoanRecord> records = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public LoanRecord processRequest(Member member, LibraryBook book,
                                     int requestedDays, int lateDays) {
        if (member == null || book == null) {
            throw new IllegalArgumentException("Member and book are required.");
        }
        if (requestedDays < 1 || requestedDays > 30 || lateDays < 0) {
            throw new IllegalArgumentException("Invalid requested or late days.");
        }

        String outcome;
        if (!book.isAvailable()) {
            outcome = "RESERVED";
        } else if (requestedDays <= member.getMaximumLoanDays()) {
            outcome = "APPROVED";
        } else {
            outcome = "DENIED";
        }

        LoanRecord record = new LoanRecord(
                member, book, requestedDays, lateDays, outcome);

        if ("APPROVED".equals(outcome)) {
            book.markBorrowed();
        } else if ("RESERVED".equals(outcome)) {
            reservations.add(new Reservation(member, book));
        }

        records.add(record);
        return record;
    }

    public void printSummary() {
        int approved = 0;
        int reserved = 0;
        int denied = 0;
        long totalCharges = 0;
        LoanRecord greatest = null;

        for (LoanRecord record : records) {
            switch (record.getOutcome()) {
                case "APPROVED" -> approved++;
                case "RESERVED" -> reserved++;
                case "DENIED" -> denied++;
            }

            totalCharges += record.getEstimatedLateCharge();

            if (greatest == null
                    || record.getRequestedDays() > greatest.getRequestedDays()) {
                greatest = record;
            }
        }

        System.out.println("\n========== BOOKBRIDGE SUMMARY ==========");
        System.out.println("Total Transactions: " + records.size());
        System.out.println("Approved Requests: " + approved);
        System.out.println("Reserved Requests: " + reserved);
        System.out.println("Denied Requests: " + denied);

        if (greatest != null) {
            System.out.println("\nRequest With Greatest Loan Days:");
            System.out.println("Member ID: " + greatest.getMember().getMemberId());
            System.out.println("Book Title: " + greatest.getBook().getTitle());
            System.out.println("Requested Days: " + greatest.getRequestedDays());
        }

        System.out.println("\nTotal Estimated Late Charges: PHP "
                + totalCharges);
        System.out.println("========================================");
    }
}