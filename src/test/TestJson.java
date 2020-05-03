package test;
import StarSky.*;
import StarSky.myutil.*;
import com.alibaba.fastjson.*;

public class TestJson {
	public static void main(String[] args) {
		Task task[]=new Task[2];
		task[0]=new Task("task1",new MyDate());
		task[1]=new Task("task2",new MyDate(2020,12,21));
		String jsonOutput= JSON.toJSONString(task);
		System.out.println(jsonOutput);
		java.util.List<Task> task2= JSON.parseArray(jsonOutput,Task.class);
		String jsonOutput2= JSON.toJSONString(task2);
		System.out.println(jsonOutput2);
	}
}
