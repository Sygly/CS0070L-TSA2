package com.mycompany.bookbridge_tsa2;

public abstract class Member {
    private final String memberId;
    private final String memberName;

    public Member(String memberId, String memberName) {
        if (memberId == null || memberId.trim().isEmpty()
                || memberName == null || memberName.trim().isEmpty()) {
            throw new IllegalArgumentException("Member details cannot be blank.");
        }
        this.memberId = memberId.trim();
        this.memberName = memberName.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public abstract int getMaximumLoanDays();

    public abstract int getDailyFine();

    public abstract String getMembershipType();

    public int calculateLateCharge(int lateDays) {
        if (lateDays < 0) {
            throw new IllegalArgumentException("Late days cannot be negative.");
        }
        return Math.multiplyExact(lateDays, getDailyFine());
    }
}