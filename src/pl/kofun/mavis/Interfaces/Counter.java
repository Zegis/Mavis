package pl.kofun.mavis.Interfaces;

import pl.kofun.mavis.counters.PeriodToCount;

public interface Counter {

	int count();
	void setPeriodToCount(PeriodToCount newPeriod);

}