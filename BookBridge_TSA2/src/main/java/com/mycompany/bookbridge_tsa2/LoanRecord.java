package com.mycompany.bookbridge_tsa2;

public class LoanRecord implements Renewable {
    private static int nextLoanNumber = 1;

    private final int loanNumber;
    private final Member member;
    private final LibraryBook book;
    private int requestedDays;
    private final int lateDays;
    private final String outcome;
    private boolean returned;

    public LoanRecord(Member member, LibraryBook book,
                      int requestedDays, int lateDays, String outcome) {
        if (member == null || book == null) {
            throw new IllegalArgumentException("Member and book are required.");
        }
        if (requestedDays < 1 || requestedDays > 30) {
            throw new IllegalArgumentException("Requested days must be 1-30.");
        }
        if (lateDays < 0) {
            throw new IllegalArgumentException("Late days cannot be negative.");
        }
        if (!"APPROVED".equals(outcome)
                && !"RESERVED".equals(outcome)
                && !"DENIED".equals(outcome)) {
            throw new IllegalArgumentException("Invalid outcome.");
        }

        this.loanNumber = nextLoanNumber++;
        this.member = member;
        this.book = book;
        this.requestedDays = requestedDays;
        this.lateDays = lateDays;
        this.outcome = outcome;
        this.returned = false;
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public Member getMember() {
        return member;
    }

    public LibraryBook getBook() {
        return book;
    }

    public int getRequestedDays() {
        return requestedDays;
    }

    public int getLateDays() {
        return lateDays;
    }

    public String getOutcome() {
        return outcome;
    }

    public int getEstimatedLateCharge() {
        return member.calculateLateCharge(lateDays);
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned() {
        if (!"APPROVED".equals(outcome) || returned) {
            throw new IllegalStateException("No active loan to return.");
        }
        book.markReturned();
        returned = true;
    }

    @Override
    public boolean renew(int additionalDays) {
        if (!"APPROVED".equals(outcome) || returned
                || additionalDays <= 0) {
            return false;
        }

        if ((long) requestedDays + additionalDays
                > member.getMaximumLoanDays()) {
            return false;
        }

        requestedDays += additionalDays;
        return true;
    }
}