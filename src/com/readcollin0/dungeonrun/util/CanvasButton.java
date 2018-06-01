package com.readcollin0.dungeonrun.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.event.button.CanvasButtonClickEvent;
import com.readcollin0.dungeonrun.event.button.CanvasClickEvent;
import com.readcollin0.dungeonrun.event.view.CanvasUpdateTopEvent;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class CanvasButton {
	
	protected boolean shown;
	protected int x, y, width, height;
	protected String buttonText;
	protected Font font;
	protected Runnable clickHandler;
	
	// Defaults
	protected int borderSize = 3;
	protected Color borderColor = Color.BLUE, insideColor = Color.BLACK, textColor = Color.WHITE;
	
	
	public CanvasButton(int x, int y, int width, int height, String text, boolean shown, Font font, Runnable handler) {
		DungeonRun.EVENT_BUS.subscribeObject(this);
		
		this.shown = shown;
		this.buttonText = text;
		this.font = font;
		this.clickHandler = handler;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setBorderColor(Color color) {
		this.borderColor = color;
	}
	
	public void setTextColor(Color color) {
		this.textColor = color;
	}
	
	public void setInsideColor(Color color) {
		this.insideColor = color;
	}
	
	public void setVisible(boolean state) {
		shown = state;
	}
	
	@SubscribeEvent
	public void canvasClick(CanvasClickEvent e) {
		if (e instanceof CanvasButtonClickEvent) return;
		if (e.getX() > x && e.getY() > y && x + width > e.getX() && y + height > e.getY()) {
			clickHandler.run();
			DungeonRun.EVENT_BUS.fire(new CanvasButtonClickEvent(e.getX(), e.getY(), this));
		}
	}
	
	@SubscribeEvent
	public void onCanvasDrawTop(CanvasUpdateTopEvent e) {
		if (shown) {
			drawButton(e);
		}
	}
	
	public void drawButton(CanvasUpdateTopEvent e) {
		Graphics g = e.getCanvas().getGraphics();
		g.setColor(borderColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(insideColor);
		g.fillRect(x + borderSize, y + borderSize, width - 2*borderSize, height - 2*borderSize);
		
		drawCenteredText(e.getCanvas(), textColor, font, buttonText, x + width/2, y + height/2);
	}
	
	
	public static void drawCenteredText(Canvas c, Color color, Font font, String text, int x, int y) {
		Graphics g = c.getGraphics();
		g.setColor(color);
		g.setFont(font);
		
		int textWidth = g.getFontMetrics().stringWidth(text);
		int textHeight = getFontHeight(g, font);// g.getFontMetrics().getHeight();
		
//		g.drawRect(x - textWidth/2, y - textHeight, textWidth, textHeight);

		g.drawString(text, x - textWidth/2, y + textHeight/2);
	}
	
	public static int getFontHeight(Graphics g, Font myfont) {
		FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
		GlyphVector gv = myfont.createGlyphVector(frc, "5");
		Rectangle2D bounds = gv.getGlyphMetrics(0).getBounds2D();
		int height = (int) bounds.getHeight();
		return height;
	}
	
}
