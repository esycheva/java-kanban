package ru.yandex.practicum;

import java.util.Comparator;

public class CounterComparator implements Comparator<CounterOfTrainings>{
	@Override
	public int compare(CounterOfTrainings c1, CounterOfTrainings c2) {
		if (c1.getCounter() == c2.getCounter()) {
			return 0;
		}

		if (c1.getCounter() < c2.getCounter()) {
			return 1;
		}

		return -1;
	}
}
