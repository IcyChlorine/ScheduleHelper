package StarSky;

import StarSky.myutil.MyDate;
import com.alibaba.fastjson.*;
import com.alibaba.fastjson.annotation.JSONField;

public class Task {
	@JSONField(name="content")
	String content;
	
	@JSONField(name="ddlEnabled")
	boolean ddlEnabled;
	
	@JSONField(name="ddl")
	MyDate ddl;
	
	@JSONField(name="done")
	boolean done;
	
	public Task(String content){
		this.content=content;
		this.ddlEnabled=false;
		this.done=false;
	}
	public Task(String content,MyDate ddl){
		this.content=content;
		this.ddlEnabled=true;
		this.ddl=ddl;
		this.done=false;
	}
	
	public String getContent() {
		return content;
	}
	public MyDate getDDL() {
		return ddl;
	}
	public boolean getDDLEnabled() {
		return ddlEnabled;
	}
	public boolean isDDLEnabled() {
		return ddlEnabled;
	}
	public boolean isDDLPassed() {
		//细节：短路与运算符，避免ddl为初始化而造成bug
		return ddlEnabled && ddl.compareTo(new MyDate())>0;
	}
	public void setDone(boolean doneFlag) {
		done=doneFlag;
	}
	public String toString() {
		return content;
	}
}
