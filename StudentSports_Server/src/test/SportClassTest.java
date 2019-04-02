package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import studentsports.domain.model.Member;
import studentsports.domain.model.SportClass;

import static org.junit.Assert.*;

public class SportClassTest {

    private SportClass sportClass;

    @Before
    public void setUp() throws Exception {
        sportClass = new SportClass("Basketball",5,null);
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testCapacityWhenOneMemberIsAdded()
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        sportClass.addParticipant(member1);
        assertEquals(1,sportClass.getAllParticipants().size());
    }
    @Test
    public void testCapacityWhenNoMembersAreAdded()
    {
        assertEquals(0,sportClass.getAllParticipants().size());
    }
    @Test
    public void testCapacityWhenMoreMembersAreAdded()
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member3 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        sportClass.addParticipant(member1);
        sportClass.addParticipant(member2);
        sportClass.addParticipant(member3);
        assertEquals(3,sportClass.getAllParticipants().size());
    }
    @Test
    public void testCapacity()
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member3 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member4 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member5 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member6 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        sportClass.addParticipant(member1);
        sportClass.addParticipant(member2);
        sportClass.addParticipant(member3);
        sportClass.addParticipant(member4);
        sportClass.addParticipant(member5);
        sportClass.addParticipant(member6);
        assertEquals(5,sportClass.getAllParticipants().size());

    }

    @Test
    public void testAddingMembersAndThenDeletingThem()
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member3 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member4 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member5 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        sportClass.addParticipant(member1);
        sportClass.addParticipant(member2);
        sportClass.addParticipant(member3);
        sportClass.addParticipant(member4);
        sportClass.addParticipant(member5);
        sportClass.deleteParticipant(member1);
        sportClass.deleteParticipant(member2);
        assertEquals(3,sportClass.getAllParticipants().size());

    }
    @Test
    public void testDeletingTheSameMember()
    {
        Member member1 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member2 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member3 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member4 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        Member member5 = new Member("Name1", "Lastname1", "Username1", "Password1", "267771");
        sportClass.addParticipant(member1);
        sportClass.addParticipant(member2);
        sportClass.addParticipant(member3);
        sportClass.addParticipant(member4);
        sportClass.addParticipant(member5);
        sportClass.deleteParticipant(member1);
        sportClass.deleteParticipant(member2);
        sportClass.deleteParticipant(member2);
        assertEquals(3,sportClass.getAllParticipants().size());

    }


}

