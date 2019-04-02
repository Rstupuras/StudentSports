package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import studentsports.domain.model.Trainer;
import studentsports.domain.model.TrainerList;

import static org.junit.Assert.*;

public class TrainerListTest {
    private TrainerList trainerList;

    @Before
    public void setUp() throws Exception {
        trainerList = new TrainerList();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSizeWhenNoTrainersAreAdded()
    {
        assertEquals(0,trainerList.size());
    }
    @Test
    public void testAdd() throws Exception {
        trainerList.addTrainer(new Trainer("Name", "Basketball", "Username1", "Password1"));
        assertEquals(1, trainerList.size());


    }

    @Test
    public void testGetTrainerByName() throws Exception {
        Trainer trainer1 = new Trainer("Name1", "Lastname1", "Username1", "Password1");
        Trainer trainer2 = new Trainer("Name2", "Lastname2", "Username2", "Password2");
        trainerList.addTrainer(trainer1);
        trainerList.addTrainer(trainer2);
        assertEquals(trainer2, trainerList.getTrainerByFirstName("Name2"));
    }




    @Test
    public void testDelete() throws Exception {
        Trainer trainer1 = new Trainer("Name1", "Basketball", "Username1", "Password1");
        Trainer trainer2 = new Trainer("Name2", "Basketball", "Username2", "Password2");
        Trainer trainer3 = new Trainer("Name3", "Football", "Username3", "Password3");
        Trainer trainer4 = new Trainer("Name4", "Football", "Username4", "Password4");
        trainerList.addTrainer(trainer1);
        trainerList.addTrainer(trainer2);
        trainerList.addTrainer(trainer3);
        trainerList.addTrainer(trainer4);
        trainerList.delete(trainer2);
        trainerList.delete(trainer1);
        assertEquals(2, trainerList.size());

    }

    @Test
    public void testToString() throws Exception {
        trainerList.addTrainer(new Trainer("Name", "Lastname", "Username1", "Password1"));
        assertEquals("Name Lastname", trainerList.toString());
    }

    @Test
    public void testSize() throws Exception {
        trainerList.addTrainer(new Trainer("Name", "Basketball", "Username1", "Password1"));
        trainerList.addTrainer(new Trainer("Name", "Basketball", "Username2", "Password2"));
        trainerList.addTrainer(new Trainer("Name", "Basketball", "Username3", "Password3"));
        trainerList.addTrainer(new Trainer("Name", "Basketball", "Username4", "Password4"));
        assertEquals(4, trainerList.size());
    }
}