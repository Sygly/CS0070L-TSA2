package com.mycompany.bookbridge_tsa2;

public class StudentMember extends Member {

    public StudentMember(String memberId, String memberName) {
        super(memberId, memberName);
    }

    @Override
    public int getMaximumLoanDays() {
        return 14;
    }

    @Override
    public int getDailyFine() {
        return 5;
    }

    @Override
    public String getMembershipType() {
        return "STUDENT";
    }
}