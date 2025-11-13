package com.skhu.tips.view.map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	// ⭐ 새로 그릴 사각형의 고정된 크기 (N: 높이, M: 너비)
	private static final int RECT_N = 40; 
	private static final int RECT_M = 40; 
	// 탐색 그리드의 해상도 (픽셀 단위로 1:1 매칭)
	private static final int GRID_RESOLUTION = 1;
	
	private ImageIcon icon = new ImageIcon("src/image/testImage.jpg");	//배경
	private Image img = icon.getImage();
	private int newWidth, newHeight;	//현재 배경 크기
	private int[][] unAvailRange = new int[100][4];
	private int[] availRange = new int[4];
	private int ar = 0;
	private int DPW = 40, DPH = 80;	//상세페이지 w, h값
	
	private int width = 500, height = 500;	//맵 가로, 세로 길이. 기본값 500x500, 언제든 수정가능
	private int mapX = 0, mapY = 0;		//이미지의 첫 좌표, 수정가능
	private int current_x, current_y;	//마우스 클릭 시의 커서 좌표
	
	public int change_flag = 1;
	
	private static class OverlayIcon{
		Image image;
		int x, y, w, h;
		double dx, dy, dw, dh;
		String path;
		int quad;
		
		OverlayIcon(String path, int x, int y, int w, int h){
			this.image = new ImageIcon(path).getImage();
			this.path = path;
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
	
	private final List<OverlayIcon> detailPageOverlays = new ArrayList<>(); 
	
	public void addDetailPageOverlay(String path, int x, int y, int w, int h) {
		overlays.add(new OverlayIcon(path, x, y, w, h));
		repaint();
	}
	
	public void claerDetailPageOverlay() {
		this.detailPageOverlays.clear();
	}
	
	private final List<OverlayIcon> overlays = new ArrayList<>(); 
	
	public void addOverlay(String path, int x, int y, int w, int h) {
		overlays.add(new OverlayIcon(path, x, y, w, h));
		//System.out.println("객체 범위 " + this.availRange[ar-1][0] + "," + this.availRange[ar-1][1] + "그리고" + this.availRange[ar-1][2] + "," + this.availRange[ar-1][3] );
		repaint();
	}

	
	public void bigOverlayImg() {
		for (OverlayIcon o : overlays) {
			o.path = o.path.substring(0, o.path.length() - 4);
			o.path += "1" + ".png";
			o.image = new ImageIcon(o.path).getImage();
	    }
	}
	
	public void smallOverlayImg() {
		for (OverlayIcon o : overlays) {
		      o.path = o.path.substring(0, o.path.length() - 5);
		      o.path += ".png";
		      o.image = new ImageIcon(o.path).getImage();
		}
	}
	
	public void printOverlay() {
		for (OverlayIcon o : overlays) {
			System.out.println(o.path);
		}
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
	
	public void setLocation(int size) {
		for (OverlayIcon o : overlays) {
			o.dx = o.dx / this.width * (this.width + size);
			o.dy = o.dy / this.height * (this.height + size);
			o.x = (int)o.dx + this.mapX;
			o.y = (int)o.dy + this.mapY;
	    }
		//System.out.println("맵좌표 : " + this.mapX + "," + this.mapY);
	}
	

	public void setImg(String imagePath) {
		this.icon = new ImageIcon(imagePath);
		this.img = icon.getImage();
		repaint();
	}
	
	public void setXY(int x, int y) {
		this.mapX = x;
		this.mapY = y;
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
	
	public int getImgX() {	return this.mapX; }
	public int getImgY() { return this.mapY; }
	public int getCurrent_X() { return this.current_x; }
	public int getCurrent_Y() { return this.current_y; }
	public int getImgW() { return this.width; }
	public int getImgH() { return this.height; }
	
	public void paintComponent(Graphics g) {	//지도 그리기
		super.paintComponent(g);
		g.drawImage(img, mapX, mapY, width, height, this);	//지도 배경 그리기

		for (OverlayIcon o : overlays) {
	        g.drawImage(o.image, o.x, o.y, o.w, o.h, this);
	    }
	}
	
	
	public MapPanel() {	//사이즈 재조절
		this.addComponentListener(new ComponentAdapter() {
			
	        public void componentResized(ComponentEvent e) {
	            newWidth = MapPanel.this.getWidth();
	            newHeight = MapPanel.this.getHeight();
	            


	            if(newWidth > newHeight) {
	            	MapPanel.this.width = newWidth;
	            	MapPanel.this.height = newWidth;
	            } 
	            else {
	            	MapPanel.this.width = newHeight;
	            	MapPanel.this.height = newHeight;
	            }

	            
	            MapPanel.this.repaint();
	        }
	    });
		
	}
	
	public void unAvailabilityRange(int x1, int y1, int x2, int y2) {
		this.unAvailRange[ar][0] = x1;
		this.unAvailRange[ar][1] = y1;
		this.unAvailRange[ar][2] = x2;
		this.unAvailRange[ar++][3] = y2;
	}
	
	
	public void availRange() {
		if(mapX < 0) {
			this.availRange[0] = 0;
		}
		else {
			this.availRange[0] = mapX;
		}
		
		if(mapX + width > newWidth) {
			this.availRange[2] = newWidth;
		}
		else {
			this.availRange[2] = mapX + width;
		}
			
		if(mapY < 0) {
			this.availRange[1] = 0;
		}
		else {
			this.availRange[1] = mapY;
		}
		
		if(mapY + height > newHeight) {
			this.availRange[3] = newHeight;
		}
		else {
			this.availRange[3] = mapY + height;
		}
		
		System.out.println("mapX : " + mapX + " mapY : " + mapY + " width : " + width + " height : " + height);
		System.out.println(this.availRange[0] + "," + this.availRange[1] + "그리고" + this.availRange[2] + "," + this.availRange[3] );
	}
	
	

	
}
