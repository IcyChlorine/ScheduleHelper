package StarSky;

import StarSky.myutil.MyDate;
import com.alibaba.fastjson.*;
import com.alibaba.fastjson.annotation.JSONField;

public class Task {
	String content;
	boolean ddlEnabled;
	MyDate ddl;
	boolean done;
	
	public Task() {
		content="(null)";
		ddlEnabled=false;
	}
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
	@JSONField(name="content")
	public void setContent(String content) {
		this.content=content;
	}
	@JSONField(name="content")
	public String getContent() {
		return content;
	}
	@JSONField(name="ddl")
	public void setDDL(MyDate ddl) {
		this.ddl=ddl;
	}
	@JSONField(name="ddl")
	public MyDate getDDL() {
		return ddl;
	}
	@JSONField(name="ddlEnabled")
	public void setDDLEnabled(boolean enabled) {
		ddlEnabled=enabled;
	}
	@JSONField(name="ddlEnabled")
	public boolean isDDLEnabled() {
		return ddlEnabled;
	}
	
	@JSONField(serialize=false)
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
