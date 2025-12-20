package ru.yandex.practicum;

import java.util.Objects;

public class CounterOfTrainings {
	private String fio;
	int counter;

	public CounterOfTrainings(String fio) {
		this.fio = fio;
		this.counter = 0;
	}

	public String getFio() {
		return this.fio;
	}

	public int getCounter() {
		return this.counter;
	}

	public void incrementCounter() {
		this.counter++;
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
