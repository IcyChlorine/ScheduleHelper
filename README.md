# ScheduleHelper
 一个基于java的任务/日程管理器，仍在编写中。
 
2020-5-3
	beta版完成！
	功能：
		添加任务，可选是否带有ddl
		完成/删除进行中任务
		浏览已完成任务
		将任务存储在json文件中，下次使用时从文件中读取，可以继续你的任务进度。
	
	轮子：
		json读写使用了阿里巴巴的fastjson
		使用了自己写的日期类MyDate以方便程序的编写。
	
2020-5-6
	第一次UI更新！
	写了class FairButton extends JButton,（Fair是美丽、好看的意思）
	重写了其中的paintComponent方法，使得按钮可以不拘泥于swing提供的样式，而更好看一些
		其中用到了Graphics、Graphics2D、Graphics2D.drawString等方法
	
	轮子：
		HSVtoRGB算法：参考了https://blog.csdn.net/zhuxb523/article/details/51017139
		重写按钮绘图方法：参考了https://blog.csdn.net/gaowen_han/article/details/8164722
			见工程中test.MyButton类