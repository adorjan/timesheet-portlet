package com.liferay.timesheet.util;

import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeCalculatorUtil {

	public static long summerizeDayTime(List<TaskSession> taskSessions)
		throws Exception {

		long time = 0;

		for(TaskSession taskSession: taskSessions) {
			time += taskSession.getDuration();
		}

		return time;
	}

	public static long summerizeWeekTime(Date dateOfWeek, long userId)
		throws Exception {

		long time = 0;

		List<Date> daysOfWeek = getDaysOfWeek(dateOfWeek);

		for(Date date: daysOfWeek) {
			List<TaskSession> taskSessions =
				TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
					date, userId);
			
			time += summerizeDayTime(taskSessions);
		}

		return time;
	}

	public static long summerizeMonthTime(Date dateOfMonth, long userId)
		throws Exception {

		long time = 0;

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(dateOfMonth);

		int first = calendar.getMinimum(Calendar.DAY_OF_MONTH);
		int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, first);

		for (int i = first; i < last; i++) {
			List<TaskSession> taskSessions =
				TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
					calendar.getTime(), userId);

			time += summerizeDayTime(taskSessions);

			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return time;
	}

	public static String getStringFromTime(long time) {
		StringBuffer buffer = new StringBuffer();

		long hours = time / (60 * 60 * 1000);
		long minutes = (time % (60 * 60 * 1000)) / (60 * 1000);

		if (hours < 10) {
			buffer.append("0");
		}

		buffer.append(hours);
		buffer.append(":");

		if (minutes < 0) {
			buffer.append("0");
		}

		buffer.append(minutes);

		return buffer.toString();
	}

	private static List<Date> getDaysOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();

		int firstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);

		List<Date> daysOfWeek = new ArrayList<Date>();

		for (int i = 0; i < 7; i++) {
			daysOfWeek.add(calendar.getTime());

			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return daysOfWeek;
	}

}