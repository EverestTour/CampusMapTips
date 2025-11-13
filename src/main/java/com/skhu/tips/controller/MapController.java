package com.skhu.tips.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.skhu.tips.view.map.MapPanel;

/*
 * ***************************
 *  파일명: MapController.java
 *  작성자: 김준
 *  작성일:
 *  내용:
 * ***************************
 */

public class MapController implements MapControllerInterface, MouseMotionListener, MouseListener, MouseWheelListener{
	
	private final MapPanel panel;
	
	
	public MapController(MapPanel panel) {
		this.panel = panel;
	}
	
	public void mousePressed(MouseEvent e) {
		panel.setCurrentXY(e.getX(), e.getY());
	}
	public void mouseDragged(MouseEvent e) {

        int dx = e.getX() - panel.getCurrent_X();
        int dy = e.getY() - panel.getCurrent_Y();


        panel.setXY(panel.getImgX() + dx, panel.getImgY() + dy);


        panel.setCurrentXY(e.getX(), e.getY());
        
        panel.moveOverlay(dx, dy);

        panel.repaint();

	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
	
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		int n = e.getWheelRotation();
		int size = 10; //사이즈 배율, 10만큼 커지거나 작아짐
		
		 int w = panel.getImgW();
	     int h = panel.getImgH();
	     double ww = w;
	     
	     System.out.println(ww);
	     
		if(n < 0) { // 확대
			panel.setLocation(size);
			w += size; h += size;

			//panel.resizeOverlay(w/ww);
        }
        else {      // 축소
        	panel.setLocation(-size);
        	w = Math.max(10, w - size);
            h = Math.max(10, h - size);
            //panel.resizeOverlay(w/ww);
            
        }
		if(w <= 1000 && panel.change_flag == 0) {
			panel.change_flag = 1;
			panel.smallOverlayImg();
		} else if(w > 1000 && panel.change_flag == 1) {
			panel.change_flag = 0;
			panel.bigOverlayImg();
		}
		System.out.print(w + " ");
		panel.printOverlay();
		
		panel.setImgSize(w, h);
		
	}
}
