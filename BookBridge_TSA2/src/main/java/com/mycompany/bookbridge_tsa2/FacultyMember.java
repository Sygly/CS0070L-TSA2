package com.mycompany.bookbridge_tsa2;

public class FacultyMember extends Member {

    public FacultyMember(String memberId, String memberName) {
        super(memberId, memberName);
    }

    @Override
    public int getMaximumLoanDays() {
        return 30;
    }

    @Override
    public int getDailyFine() {
        return 2;
    }

    @Override
    public String getMembershipType() {
        return "FACULTY";
    }
}