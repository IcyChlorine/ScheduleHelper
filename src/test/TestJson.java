package test;
import StarSky.*;
import StarSky.myutil.*;
import com.alibaba.fastjson.*;

public class TestJson {
	public static void main(String[] args) {
		Task task=new Task("some content",new MyDate());
		String jsonOutput= JSON.toJSONString(task);
		System.out.println(jsonOutput);
	}
}
