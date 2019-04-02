package test;

import studentsports.domain.model.Date;
import studentsports.domain.model.Member;
import studentsports.domain.model.MemberList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberListTest {

    private MemberList memberList;

    @Before
    public void setUp() throws Exception {
        memberList = new MemberList();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void noMemberAddTesting() throws Exception {
        assertEquals(0,memberList.size());
    }
    @Test
    public void getElementIfTheListIsEmpty() throws Exception
    {
        assertEquals(null,memberList.getMemberByFirstName(""));
    }
    @Test
    public void testSizeWhenAddingOneMemberTest() throws Exception
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        memberList.add(member1);
        assertEquals(1,memberList.size());
    }
    @Test
    public void testGetMemberWhenAddingOneMemberTest() throws Exception
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        memberList.add(member1);
        assertEquals(member1,memberList.getMemberByFirstName("Name1"));
    }

    @Test
    public void testGetMember() throws Exception {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name2", "Lastname2", "Username2", "Password2", "267772");
        memberList.add(member1);
        memberList.add(member2);
        assertEquals(member2, memberList.getMember(member2));
    }

    @Test
    public void testGetMemberByStudentNumber() throws Exception {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name2", "Lastname2", "Username2", "Password2", "267772");
        Member member3 = new Member("Name3", "Lastname3", "Username3", "Password3", "267773");
        Member member4 = new Member("Name4", "Lastname4", "Username4", "Password4", "267774");
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        assertEquals(member2,memberList.getMemberByStudentNumber("267772"));

    }

    @Test
    public void testGetMemberByName() throws Exception {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name2", "Lastname2", "Username2", "Password2", "267772");
        Member member3 = new Member("Name3", "Lastname3", "Username3", "Password3", "267773");
        Member member4 = new Member("Name4", "Lastname4", "Username4", "Password4", "267774");
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        assertEquals(member2, memberList.getMemberByFirstName("Name2"));
    }

    @Test
    public void testDelete() throws Exception {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name2", "Lastname2", "Username2", "Password2", "267772");
        Member member3 = new Member("Name3", "Lastname3", "Username3", "Password3", "267773");
        Member member4 = new Member("Name4", "Lastname4", "Username4", "Password4", "267774");
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        memberList.delete(member1);
        memberList.delete(member4);
        assertEquals(2, memberList.size());
    }

    @Test
    public void testToString() throws Exception {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        member1.setPaymentDate(new Date(20,12, 2018));
        memberList.add(member1);
        assertEquals("Name: Name1. Last name: Lastname1. Username: Username1. Password: Password1. Student number: 267771. Payment date: 20/12/2018", memberList.toString());
    }

    @Test
    public void testSize() throws Exception {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name2", "Lastname2", "Username2", "Password2", "267772");
        Member member3 = new Member("Name3", "Lastname3", "Username3", "Password3", "267773");
        memberList.add(member1);
        memberList.add(member3);
        memberList.add(member2);
        assertEquals(3,memberList.size());
    }


    @Test
    public void testSettingTheDate()
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Date date1 = new Date(20,12,2018);
        member1.setPaymentDate(date1);
        assertEquals(date1, member1.getPaymentDate());
    }


}