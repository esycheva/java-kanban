package ru.yandex.practicum;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Timetable {
    
    private Map<DayOfWeek, Map<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();
    private CounterComparator counterComparator = new CounterComparator();
    
    private Comparator<TimeOfDay> comparator = new Comparator<>() {
    	@Override
    	public int compare(TimeOfDay t1, TimeOfDay t2) {
    		if(t1.getHours() == t2.getHours() && t1.getMinutes() == t2.getMinutes()) {
    			return 0;
    		}
    		
    		if(t1.getHours() > t2.getHours()) {
    			return 1;
    		}
    		
    		if(t1.getHours() == t2.getHours() && t1.getMinutes() > t2.getMinutes()) {
    			return 1;
    		}
    		return -1;
    	}
    };
    
    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
    	Map<TimeOfDay, ArrayList<TrainingSession>> day;
    	
    	if(timetable.containsKey(trainingSession.getDayOfWeek())) {
    		day = timetable.get(trainingSession.getDayOfWeek());
    	} else {
    		day = new TreeMap<>(comparator);
    	}
    	    	
    	ArrayList<TrainingSession> sessionsInTime;
    	
    	
    	if (day.containsKey(trainingSession.getTimeOfDay())) {
    		sessionsInTime = day.get(trainingSession.getTimeOfDay());
    	} else {
    		sessionsInTime = new ArrayList<>();
    	}
    	sessionsInTime.add(trainingSession);
    	System.out.println(sessionsInTime);
    	
    	day.put(trainingSession.getTimeOfDay(), sessionsInTime);
    	timetable.put(trainingSession.getDayOfWeek(), day);
    }

    public Map<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
    	return timetable.get(dayOfWeek);
    }
    
    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
    	ArrayList<TrainingSession> result;
    	
    	if(timetable.containsKey(dayOfWeek)) {
        	Map<TimeOfDay, ArrayList<TrainingSession>> day = timetable.get(dayOfWeek);
        	
        	if(day.containsKey(timeOfDay)) {
        		result = day.get(timeOfDay);
        	} else {
        		result = new ArrayList<TrainingSession>();
        	}
        } else {
        	result = new ArrayList<TrainingSession>();
        }
    	return result;
    }
    
    public ArrayList<CounterOfTrainings> getCountByCoaches() {
    	ArrayList<CounterOfTrainings> counters = new ArrayList<>();
    	
    	for (Map.Entry<DayOfWeek, Map<TimeOfDay, ArrayList<TrainingSession>>> entry : timetable.entrySet()) {
    		Map<TimeOfDay, ArrayList<TrainingSession>> day = entry.getValue();
    		
    		for(Map.Entry<TimeOfDay, ArrayList<TrainingSession>> e : day.entrySet()) {
    			ArrayList<TrainingSession> sessions = e.getValue();
    			
    			for(TrainingSession session : sessions) {
    				Coach coach = session.getCoach();
    				
    				CounterOfTrainings matchCounter = null;
    				
    				for(CounterOfTrainings c : counters) {
    					if(c.getFio().equals(coach.getFio())) {
    						c.incrementCounter();
    						matchCounter = c;
    					}
    				}
    				
    				if (matchCounter == null) {
    					CounterOfTrainings counter = new CounterOfTrainings(coach.getFio());
    					counter.incrementCounter();
    					counters.add(counter);
    				}    				
    			}
    		}
     	}
    	counters.sort(counterComparator);
    	return counters;	
    }
}

