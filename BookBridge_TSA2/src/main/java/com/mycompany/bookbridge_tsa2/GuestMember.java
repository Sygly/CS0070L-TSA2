
package com.mycompany.bookbridge_tsa2;

public class GuestMember extends Member {

    public GuestMember(String memberId, String memberName) {
        super(memberId, memberName);
    }

    @Override
    public int getMaximumLoanDays() {
        return 7;
    }

    @Override
    public int getDailyFine() {
        return 10;
    }

    @Override
    public String getMembershipType() {
        return "GUEST";
    }
}