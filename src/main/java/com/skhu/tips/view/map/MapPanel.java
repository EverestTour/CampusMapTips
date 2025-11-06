package com.skhu.tips.view.map;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * ***************************
 *  파일명: MapPanel
 *  작성자: 김준
 *  작성일:
 *  내용:
 * ***************************
 */

public class MapPanel extends JPanel {
	private ImageIcon icon = new ImageIcon("src/image/testImage.jpg");	//배경
	private Image img = icon.getImage();
	
	
	private int width = 500, height = 500;	//맵 가로, 세로 길이. 기본값 500x500, 언제든 수정가능
	private int x = 0, y = 0;		//이미지의 첫 좌표, 수정가능
	private int current_x, current_y;	//마우스 클릭 시의 커서 좌표
	
	
	private static class OverlayIcon{
		final Image image;
		int x, y, w, h;
		double dx, dy, dw, dh;
		
		OverlayIcon(String path, int x, int y, int w, int h){
			this.image = new ImageIcon(path).getImage();
			this.x = x; 
			this.y = y;
			this.w = w;
			this.h = h;
			this.dx = x;
			this.dy = y;
			this.dw = w;
			this.dh = h;
		}
	}
	
	private final List<OverlayIcon> overlays = new ArrayList<>(); 
	
	public void addOverlay(String path, int x, int y, int w, int h) {
		overlays.add(new OverlayIcon(path, x, y, w, h));
		repaint();
	}
	
	public void moveOverlay(int x, int y) {
		for (OverlayIcon o : overlays) {
	        o.x += x;
	        o.y += y;
	    }
	}
	
	public void resizeOverlay(double size) {
		for (OverlayIcon o : overlays) {
	        o.dw *= size;
	        o.dh *= size;
	        o.w = (int)o.dw;
	        o.h = (int)o.dh;
	    }
	}
	
	public void setImg(String imagePath) {
		this.icon = new ImageIcon(imagePath);
		this.img = icon.getImage();
		repaint();
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCurrentXY(int x, int y) {
		this.current_x = x;
		this.current_y = y;
	}
	
	public void setImgSize(int width, int height) {
		this.width = width;
		this.height = height;
		repaint();
	}
	
	public int getImgX() {	return this.x; }
	public int getImgY() { return this.y; }
	public int getCurrent_X() { return this.current_x; }
	public int getCurrent_Y() { return this.current_y; }
	public int getImgW() { return this.width; }
	public int getImgH() { return this.height; }
	
	public void paintComponent(Graphics g) {	//지도 그리기
		super.paintComponent(g);
		g.drawImage(img, x, y, width, height, this);	//지도 배경 그리기

		for (OverlayIcon o : overlays) {
	        g.drawImage(o.image, o.x, o.y, o.w, o.h, this);
	    }
	}
	
	
	
	
}
