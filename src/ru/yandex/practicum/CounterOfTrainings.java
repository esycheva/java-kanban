package ru.yandex.practicum;

import java.util.Objects;

public class CounterOfTrainings {
	private String fio;
	private int counter;

	public CounterOfTrainings(String fio) {
		this.fio = fio;
		this.counter = 0;
	}

	public String getFio() {
		return fio;
	}

	public int getCounter() {
		return counter;
	}

	public void incrementCounter() {
		counter++;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CounterOfTrainings counterOfTraining = (CounterOfTrainings) o;
		return Objects.equals(fio, counterOfTraining.fio);
	}

	@Override
	public int hashCode() {
		int hash = 17;
		if (fio != null) {
			hash = fio.hashCode();
		}
		return hash;
	}
}
