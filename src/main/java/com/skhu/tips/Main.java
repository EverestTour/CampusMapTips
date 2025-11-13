package com.skhu.tips;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.skhu.tips.controller.MapController;

/*
 * ***************************
 *  파일명: Main
 *  작성자: 조민성
 *  작성일:
 *  내용:
 * ***************************
 */

import com.skhu.tips.controller.PanelController;
import com.skhu.tips.view.map.MapPanel;	//*******************
import com.skhu.tips.view.panel.MainLeftPanel;





public class Main{
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Campus Map Tips");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			


			MainLeftPanel leftPanel = new MainLeftPanel();
			JPanel detailContainer = new JPanel();
			detailContainer.setPreferredSize(new Dimension(320, 0));
			JPanel mapPlaceholder = new JPanel(new BorderLayout());
			
			MapPanel panel = new MapPanel();	//*******************
			mapPlaceholder.add(panel, BorderLayout.CENTER);		//수정*******************
			MapController controller = new MapController(panel);//*******************
	        panel.addMouseListener(controller);                 //*******************
	        panel.addMouseMotionListener(controller);           //*******************
	        panel.addMouseWheelListener(controller); 			//*******************
	        
	        panel.addOverlay("src/image/red.png", 250, 200, 40, 40);	//*******************
	        panel.addOverlay("src/image/blue.png", 200, 150, 40, 40);		//*******************
	        panel.addOverlay("src/image/green.png", 250, 100, 40, 40);	//*******************
	        
	        
			JPanel topPanel = new JPanel();
			JLabel titleLabel = new JLabel("캠퍼스 안내 프로그램");
			titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 18));
			topPanel.add(titleLabel);

			PanelController panelController = new PanelController(leftPanel, detailContainer);

			frame.add(leftPanel, BorderLayout.WEST);
			frame.add(mapPlaceholder, BorderLayout.CENTER);
			frame.add(detailContainer, BorderLayout.EAST);
            frame.add(topPanel, BorderLayout.NORTH);

			frame.setSize(1200, 800);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
