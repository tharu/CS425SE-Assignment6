package edu.mum.cs.cs425.demos.studentrecordsmgmtapp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.cs.cs425.demos.studentrecordsmgmtapp.model.Student;

public class MyStudentRecordsMgmtApp {

	public static void main(String[] args) 
	{
		List<Student> lstStudents= new ArrayList<Student>();
		lstStudents.add(new Student(110001,"Dave",new Date("11/18/1951")));
		lstStudents.add(new Student(110002,"Anna",new Date("12/07/1990")));
		lstStudents.add(new Student(110003,"Erica",new Date("01/31/1974")));
		lstStudents.add(new Student(110004,"Carlos",new Date("08/22/2009")));
		lstStudents.add(new Student(110005,"Bob",new Date("03/05/1990")));
		System.out.println("Printing all the students...");
		printListOfStudents(lstStudents);
		System.out.println("Printing platinum alumni students...");
		printListOfStudents(new MyStudentRecordsMgmtApp().getListOfPlatinumAlumniStudents(lstStudents));
		
		int[] arr= {5,7,35};
		printHelloWorld(arr);
		int[] nums= {5,68,11,9};
		System.out.println(findSecondBiggest(nums));
		
	}
	
	private static void printListOfStudents(List<Student> lstStudents)
	{
		Collections.sort(lstStudents);
		int i=1;
		for(Student student : lstStudents)
		{
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String submissionDate = df.format(student.getDateOfAdmission()).toString();
			System.out.println("s"+i+":studentId:"+student.getStudentId()+", name:"+student.getName()+", dateOfSubmission:"+submissionDate);
			i++;
		}
	}
	
	List<Student> getListOfPlatinumAlumniStudents(List<Student> lstStudents)	
	{
		List<Student> lstPlatinumStudents=lstStudents.stream().filter(s->getYearsBetweenDates(s.getDateOfAdmission(),new Date())>=30).collect(Collectors.toList());
		Comparator<Student> byDateOfAdmission=Comparator.comparing(Student::getDateOfAdmission);
		lstPlatinumStudents.sort(byDateOfAdmission);
		return lstPlatinumStudents;
		
	}
	
	public int getYearsBetweenDates(Date first, Date second) {
	    Calendar firstCal = GregorianCalendar.getInstance();
	    Calendar secondCal = GregorianCalendar.getInstance();

	    firstCal.setTime(first);
	    secondCal.setTime(second);

	    secondCal.add(Calendar.DAY_OF_YEAR, 1 - firstCal.get(Calendar.DAY_OF_YEAR));

	    return secondCal.get(Calendar.YEAR) - firstCal.get(Calendar.YEAR);
	}
	
	static void printHelloWorld(int[] nums)
	{
		for(int num:nums)
		{
			if(num%5==0 && num%7==0)
			{
				System.out.println("HelloWorld");
			}
			else if(num%5==0)
			{
				System.out.println("Hello");
			}
			else if(num%7==0)
			{
				System.out.println("World");
			}
		}
	}
	
	static int findSecondBiggest(int[] nums)
	{
		int firstMax,secondMax;
		firstMax=secondMax=Integer.MIN_VALUE;
		for(int i=0;i<nums.length;i++)
		{
			if(nums[i]>firstMax)
			{
				secondMax=firstMax;
				firstMax=nums[i];
			}
			
			if(nums[i]>secondMax && nums[i]!=firstMax)
			{
				secondMax=nums[i];
			}
		}
		return secondMax;
	}

}
