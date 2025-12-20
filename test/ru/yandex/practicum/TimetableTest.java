package ru.yandex.practicum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.ArrayList;

import ru.yandex.practicum.Age;
import ru.yandex.practicum.Coach;
import ru.yandex.practicum.DayOfWeek;
import ru.yandex.practicum.Group;
import ru.yandex.practicum.TimeOfDay;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        Map<TimeOfDay, ArrayList<TrainingSession>> monday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, monday.values().size());
        
        // Проверить, что за вторник не вернулось занятий
        Map<TimeOfDay, ArrayList<TrainingSession>> tuesday = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        assertEquals(null, tuesday);
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        Map<TimeOfDay, ArrayList<TrainingSession>> monday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, monday.values().size());
        
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        Map<TimeOfDay, ArrayList<TrainingSession>> thursday = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(2, thursday.values().size());
        
        int count = 0;
        
        for(Map.Entry<TimeOfDay, ArrayList<TrainingSession>> entry : thursday.entrySet()) {        	
        	if (count == 0) {
        		assertEquals(13, entry.getKey().getHours());
        	}
        	
        	if (count == 1) {
        		assertEquals(20, entry.getKey().getHours());
        	}
        	count++;
        }
        
        // Проверить, что за вторник не вернулось занятий
        Map<TimeOfDay, ArrayList<TrainingSession>> tuesday = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        assertEquals(null, tuesday);
        System.out.println(timetable.getCountByCoaches().size());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        ArrayList<TrainingSession> sessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        assertEquals(1, sessions.size());
        
        TrainingSession session = sessions.get(0);
        assertEquals(13, session.getTimeOfDay().getHours());
        
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        ArrayList<TrainingSession> sessionsB = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        assertEquals(0, sessionsB.size());
    }
    
    @Test
    public void testGetCountByCoaches() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));
        
        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);
        
        
        Coach coachA = new Coach("Иванова", "Светлана", "Александровна");

        Group groupAdultA = new Group("Гимнастика", Age.ADULT, 90);
        TrainingSession fridaySession = new TrainingSession(groupAdultA, coachA,
                DayOfWeek.FRIDAY, new TimeOfDay(13, 0));
        TrainingSession mondaySession = new TrainingSession(groupAdultA, coachA,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        
        timetable.addNewTrainingSession(fridaySession);
        timetable.addNewTrainingSession(mondaySession);
        
        Coach coachB = new Coach("Петросян", "Иван", "Иванович");

        Group groupAdultB = new Group("Борьба", Age.ADULT, 90);
        TrainingSession saturdaySession = new TrainingSession(groupAdultB, coachB,
                DayOfWeek.SATURDAY, new TimeOfDay(17, 0));
        
        timetable.addNewTrainingSession(saturdaySession);
            	
        assertEquals(3, timetable.getCountByCoaches().size());
        assertEquals("Васильев Николай Сергеевич", timetable.getCountByCoaches().get(0).getFio());
        assertEquals("Петросян Иван Иванович", timetable.getCountByCoaches().get(2).getFio());
    }

}