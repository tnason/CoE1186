package TLTTC;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class SchedulerViewModel
{
	private Container myContainer;
	private OperatorScheduleTableModel mySchedule;
	private DefaultTableModel myTimetable;
	private JFrame myFrame;
	private JTable myScheduleGrid;
	private JTable myTimetableGrid;
	private JPanel myPanel;
	private JButton myScheduleButton;
	private JButton myTimetableButton;

	private Scheduler scheduler;

	public static void main(String[] args)
	{
	}

	public SchedulerViewModel(Scheduler scheduler)
	{
		initUI();

		this.scheduler = scheduler;
		scheduler.addSchedulerListener(new MySchedulerListener());
	}

	public void initUI()
	{
		myFrame = new JFrame("Marcus Hayes - Scheduler");
		myContainer = myFrame.getContentPane();

		mySchedule = new OperatorScheduleTableModel();
		myTimetable = new TimetableTableModel();

		myScheduleGrid = new JTable(mySchedule);
		myScheduleGrid.setRowSorter(new TableRowSorter<TableModel>(mySchedule));
		myScheduleGrid.setPreferredScrollableViewportSize(new Dimension(200, myScheduleGrid.getRowHeight() * 5));
		myScheduleGrid.setFillsViewportHeight(true);

		myTimetableGrid = new JTable(myTimetable);
		myTimetableGrid.setRowSorter(new TableRowSorter<TableModel>(myTimetable));
		myTimetableGrid.setPreferredScrollableViewportSize(new Dimension(200, myTimetableGrid.getRowHeight() * 5));
		myTimetableGrid.setFillsViewportHeight(true);

		myScheduleButton = new JButton("Update Operator Schedule");
		myScheduleButton.setHorizontalTextPosition(AbstractButton.CENTER);
		myScheduleButton.setVerticalTextPosition(AbstractButton.CENTER);
		myScheduleButton.addActionListener(new MyButtonListener());

		myTimetableButton = new JButton("Update Timetable");
		myTimetableButton.setHorizontalTextPosition(AbstractButton.CENTER);
		myTimetableButton.setVerticalTextPosition(AbstractButton.CENTER);
		myTimetableButton.addActionListener(new MyButtonListener());

		myPanel = new JPanel();

		myPanel.add(myScheduleButton);
		myPanel.add(myTimetableButton);

		myContainer.add(new JScrollPane(myScheduleGrid), BorderLayout.NORTH);
		myContainer.add(new JScrollPane(myTimetableGrid), BorderLayout.CENTER);
		myContainer.add(myPanel, BorderLayout.SOUTH);

		myFrame.validate();
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initScheduler()
	{
	}

	private class MySchedulerListener implements SchedulerListener
	{
		public void timetableChanged(SchedulerEvent e)
		{
			mySchedule.update(scheduler.getTimetable());
			/*
			Iterator<TimesObject> i;
			TimesObject t;

			i = scheduler.getTimetable().getIterator();

			while(i.hasNext())
			{
				t = i.next();
				myTimetable.addRow(new Object[]{t.stationName, t.trainNumber, new Time(t.time), t.status.toString()});
			}
			*/
		}

		public void operatorScheduleChanged(SchedulerEvent e)
		{
			mySchedule.update(scheduler.getOperatorSchedule());
/*
			Iterator<Operator> i;
			Operator s;

			i = myScheduler.getOperatorSchedule().getIterator();

			while(i.hasNext())
			{
				s = i.next();
				mySchedule.addRow(new Object[]{s.firstName, s.lastName, s.trainNumber, new Time(s.shiftStart), new Time(s.breakStart), new Time(s.breakEnd), new Time(s.shiftEnd), s.status.toString()});
			}
*/
		}
	}

	private class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == myTimetableButton)
			{
				scheduler.updateTimetable();
			}
			else if(e.getSource() == myScheduleButton)
			{
				scheduler.updateOperatorSchedule();
			}
		}
	}
}
