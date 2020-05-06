package test;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.border.Border;

public class MyButton extends JButton {
	private String text;
	private String state = "normal";
	// private String state = "focused";
	// private String state = "pressed";
	// private String state = "released";
	
	Shape shape;

	// 无参构造继承时自动调用，而有参构造继承时则需手动重写
	MyButton(String text) {
		// super("<html><font size=5>" + text + "</font></html>");
		super(text);
		this.text = text;

		// 下 面的代码块若是放到下面的paintComponent()方法里则Swing界面初始化时，
		// 布局管理器还是采用的是系统默认的PreferredSize。因为构造函数要优先于
		// paintComponent()方法执行。
		Dimension preferredSize = getPreferredSize();
		Dimension preferredSizeNew = new Dimension(preferredSize.width,
				preferredSize.width);
		setPreferredSize(preferredSizeNew);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;

		if (state.equals("normal")) {
			// draw background pattern
			Point2D center = new Point2D.Float(width / 2, height / 2);
			float radius = height / 2;
			float[] dist = { 0.0f, 1.0f };
			Color[] colors = { new Color(0, 0, 0, 255),
					new Color(255, 255, 255, 0) };
			RadialGradientPaint paint = new RadialGradientPaint(center,
					radius, dist, colors);
			g2.setPaint(paint);
			shape = new Ellipse2D.Double(width / 2 - height / 2, 0, height,
					height);
			g2.fill(shape);
			// draw string text
			g2.setColor(Color.RED);
			Font defaultFont = getFont();
			g2.setFont(defaultFont);
			Rectangle2D rect = defaultFont.getStringBounds(text,
					g2.getFontRenderContext());
			LineMetrics lineMetrics = defaultFont.getLineMetrics(text,
					g2.getFontRenderContext());
			g2.drawString(
					text,
					(float) (width / 2 - rect.getWidth() / 2),
					(float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics
							.getDescent()) / 2 - lineMetrics.getDescent())));

		} else if (state.equals("focused")) {
			// draw background pattern
			Point2D center = new Point2D.Float(width / 2, height / 2);
			float radius = height / 2;
			float[] dist = { 0.2f, 1.0f };
			Color[] colors = { new Color(0, 0, 0, 255),
					new Color(255, 255, 255, 0) };
			RadialGradientPaint paint = new RadialGradientPaint(center,
					radius, dist, colors);
			g2.setPaint(paint);
			g2.fill(new Ellipse2D.Double(width / 2 - height / 2, 0, height,
					height));
			// draw string text
			g2.setColor(Color.RED);
			Font defaultFont = getFont();
			g2.setFont(defaultFont);
			Rectangle2D rect = defaultFont.getStringBounds(text,
					g2.getFontRenderContext());
			LineMetrics lineMetrics = defaultFont.getLineMetrics(text,
					g2.getFontRenderContext());
			g2.drawString(
					text,
					(float) (width / 2 - rect.getWidth() / 2),
					(float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics
							.getDescent()) / 2 - lineMetrics.getDescent())));
		} else if (state.equals("pressed")) {
			// draw background pattern
			int offsetCenter = 1;
			Point2D center = new Point2D.Float(width / 2 + offsetCenter,
					height / 2 + offsetCenter);
			float radius = height / 2;
			float[] dist = { 0.2f, 1.0f };
			Color[] colors = { new Color(0, 0, 0, 255),
					new Color(255, 255, 255, 0) };
			RadialGradientPaint paint = new RadialGradientPaint(center,
					radius, dist, colors);
			g2.setPaint(paint);
			g2.fill(new Ellipse2D.Double(width / 2 - height / 2
					+ offsetCenter, offsetCenter, height, height));
			// draw string text
			g2.setColor(Color.RED);
			Font defaultFont = getFont();
			g2.setFont(defaultFont);
			Rectangle2D rect = defaultFont.getStringBounds(text,
					g2.getFontRenderContext());
			LineMetrics lineMetrics = defaultFont.getLineMetrics(text,
					g2.getFontRenderContext());
			g2.drawString(
					text,
					(float) (width / 2 - rect.getWidth() / 2)
							+ offsetCenter,
					(float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics
							.getDescent()) / 2 - lineMetrics.getDescent()))
							+ offsetCenter);
		} else if (state.equals("released")) {
			// draw background pattern
			Point2D center = new Point2D.Float(width / 2, height / 2);
			float radius = height / 2;
			float[] dist = { 0.2f, 1.0f };
			Color[] colors = { new Color(0, 0, 0, 255),
					new Color(255, 255, 255, 0) };
			RadialGradientPaint paint = new RadialGradientPaint(center,
					radius, dist, colors);
			g2.setPaint(paint);
			g2.fill(new Ellipse2D.Double(width / 2 - height / 2, 0, height,
					height));
			// draw string text
			g2.setColor(Color.RED);
			Font defaultFont = getFont();
			g2.setFont(defaultFont);
			Rectangle2D rect = defaultFont.getStringBounds(text,
					g2.getFontRenderContext());
			LineMetrics lineMetrics = defaultFont.getLineMetrics(text,
					g2.getFontRenderContext());
			g2.drawString(
					text,
					(float) (width / 2 - rect.getWidth() / 2),
					(float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics
							.getDescent()) / 2 - lineMetrics.getDescent())));
		}

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("光标移入组件");
				state = "focused";
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("光标移出组件");
				state = "normal";
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print("鼠标按键被按下，");
				state = "pressed";
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print("鼠标按键被释放，");
				state = "released";
				repaint();
			}

		});

	}

	// Gives the UI delegate an opportunity to define the precise shape of
	// this component for the sake of mouse processing.
	@Override
	public boolean contains(int x, int y) {
		if (shape.contains(x, y)) {
			return true;
		} else {
			return false;
		}
	}
}

