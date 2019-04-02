package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import studentsports.domain.model.Date;
import studentsports.domain.model.SportClass;
import studentsports.domain.model.SportClassList;

import static org.junit.Assert.*;

public class SportClassListTest {

    private SportClassList sportClassList;

    @Before
    public void setUp() throws Exception {
        sportClassList = new SportClassList();
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testSizeWhenThereIsNoSportsClasses()
    {
        assertEquals(0,sportClassList.size());
    }

    @Test
    public void testAdd() throws Exception {
        sportClassList.addSportClass(new SportClass("Basketball", 20,  new Date(10,12,2018)));
        assertEquals(1, sportClassList.size());


    }

    @Test
    public void testGetSportsClassByName() throws Exception {
        SportClass sportClass1 = new SportClass("Basketball", 20,  new Date(10,12,2018));
        SportClass sportClass2 = new SportClass("Basketball", 20,  new Date(17,12,2018));
        sportClassList.addSportClass(sportClass1);
        sportClassList.addSportClass(sportClass2);
        assertEquals(sportClass1, sportClassList.getClassByName("Basketball"));
    }




    @Test
    public void testDelete() throws Exception {
        SportClass sportClass1 = new SportClass("Basketball", 20,  new Date(10,12,2018));
        SportClass sportClass2 = new SportClass("Basketball", 20,  new Date(17,12,2018));
        SportClass sportClass3 = new SportClass("Football", 20,  new Date(11,12,2018));
        SportClass sportClass4 = new SportClass("Badminton", 20,  new Date(18,12,2018));
        sportClassList.addSportClass(sportClass1);
        sportClassList.addSportClass(sportClass2);
        sportClassList.addSportClass(sportClass3);
        sportClassList.addSportClass(sportClass4);
        sportClassList.deleteSportClass(sportClass2);
        sportClassList.deleteSportClass(sportClass1);
        assertEquals(2, sportClassList.size());

    }
    

    @Test
    public void testSize() throws Exception {
        SportClass sportClass1 = new SportClass("Basketball", 20,  new Date(10,12,2018));
        SportClass sportClass2 = new SportClass("Basketball", 20,  new Date(17,12,2018));
        SportClass sportClass3 = new SportClass("Football", 20,  new Date(11,12,2018));
        SportClass sportClass4 = new SportClass("Badminton", 20,  new Date(18,12,2018));
        sportClassList.addSportClass(sportClass1);
        sportClassList.addSportClass(sportClass2);
        sportClassList.addSportClass(sportClass3);
        sportClassList.addSportClass(sportClass4);
        assertEquals(4, sportClassList.size());
    }
}
