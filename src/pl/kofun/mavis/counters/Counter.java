package pl.kofun.mavis.counters;

public interface Counter {

	int count();
	void setPeriodToCount(PeriodToCount newPeriod);

}