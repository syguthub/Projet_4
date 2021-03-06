package com.openclassrooms.mareu;

import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.services.ApiService;
import com.openclassrooms.mareu.services.DummyApiService;

import org.junit.Test;

import java.util.List;

import static com.openclassrooms.mareu.services.Generate.get_List_Meeting_For_Test;
import static com.openclassrooms.mareu.services.Generate.get_Random_Meeting;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    private List<Meeting>list= get_List_Meeting_For_Test();

    private ApiService apiService=new DummyApiService();

    @Test
    public void test_AddMeeting() {
        Meeting meeting= get_Random_Meeting(list);
        apiService.add_Meeting(meeting);
        assertTrue(apiService.get_Meeting_List().contains(meeting));
    }

    @Test
    public void test_Meeting_Deletion() {
        Meeting meeting= get_Random_Meeting(list);

        apiService.add_Meeting(meeting);
        assertEquals(1, apiService.get_Meeting_List().size());

        assertTrue(apiService.get_Meeting_List().contains(meeting));
        apiService.delete_Meeting(meeting);
        assertEquals(0, apiService.get_Meeting_List().size());

        assertFalse(apiService.get_Meeting_List().contains(meeting));

    }

    @Test
    public void test_Meeting_List() {
        for (int i = 0; i < list.size(); i++) {
            apiService.add_Meeting(list.get(i));
        }
        assertEquals(apiService.get_Meeting_List().size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            assertTrue(apiService.get_Meeting_List().contains(list.get(i)));
        }
    }

    @Test
    public void test_Meeting_List_Filter() {
        for (int i = 0; i < list.size(); i++) {
            apiService.add_Meeting(list.get(i));
        }
// _____ CALANDAR == TRUE   LOCATION(ROOM) == TRUE
        assertEquals(1, apiService.get_Meeting_List_Filter("21/1/1980", "réunion A").size());
        assertTrue(apiService.get_Meeting_List_Filter("21/1/1980","réunion A").contains(list.get(0)));

        assertEquals(1, apiService.get_Meeting_List_Filter("27/6/2100", "réunion B").size());
        assertTrue(apiService.get_Meeting_List_Filter("15/12/2080","réunion A").contains(list.get(2)));

        assertEquals(1, apiService.get_Meeting_List_Filter("15/12/2080", "réunion A").size());
        assertTrue(apiService.get_Meeting_List_Filter("3/11/2045","réunion F").contains(list.get(3)));

        assertEquals(1, apiService.get_Meeting_List_Filter("3/11/2045", "réunion F").size());
        assertTrue(apiService.get_Meeting_List_Filter("18/7/1989","réunion C").contains(list.get(4)));

        assertEquals(1, apiService.get_Meeting_List_Filter("18/7/1989", "réunion C").size());
        assertTrue(apiService.get_Meeting_List_Filter("11/4/1923","réunion A").contains(list.get(5)));

        assertEquals(1, apiService.get_Meeting_List_Filter("11/4/1923", "réunion A").size());
        assertTrue(apiService.get_Meeting_List_Filter("27/6/2100","réunion B").contains(list.get(1)));

        assertEquals(1, apiService.get_Meeting_List_Filter("27/6/2100", "réunion H").size());
        assertTrue(apiService.get_Meeting_List_Filter("27/6/2100","réunion H").contains(list.get(6)));
// _____ CALANDAR == TRUE   LOCATION(ROOM) == FALSE
        assertEquals(2, apiService.get_Meeting_List_Filter("27/6/2100", "SELECT ROOM").size());
        assertTrue(apiService.get_Meeting_List_Filter("27/6/2100","SELECT ROOM").contains(list.get(1)));
        assertTrue(apiService.get_Meeting_List_Filter("27/6/2100","SELECT ROOM").contains(list.get(6)));
// _____ CALANDAR == FALSE   LOCATION(ROOM) == TRUE
        assertEquals(3, apiService.get_Meeting_List_Filter("", "réunion A").size());
        assertTrue(apiService.get_Meeting_List_Filter("","réunion A").contains(list.get(0)));
        assertTrue(apiService.get_Meeting_List_Filter("","réunion A").contains(list.get(2)));
        assertTrue(apiService.get_Meeting_List_Filter("","réunion A").contains(list.get(5)));

        assertEquals(1, apiService.get_Meeting_List_Filter("", "réunion B").size());
        assertTrue(apiService.get_Meeting_List_Filter("","réunion B").contains(list.get(1)));

        assertEquals(1, apiService.get_Meeting_List_Filter("", "réunion F").size());
        assertTrue(apiService.get_Meeting_List_Filter("","réunion F").contains(list.get(3)));

        assertEquals(1, apiService.get_Meeting_List_Filter("", "réunion C").size());
        assertTrue(apiService.get_Meeting_List_Filter("","réunion C").contains(list.get(4)));

        assertEquals(1, apiService.get_Meeting_List_Filter("", "réunion H").size());
        assertTrue(apiService.get_Meeting_List_Filter("",  "réunion H").contains(list.get(6)));
    }

}