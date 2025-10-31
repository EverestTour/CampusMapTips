package com.skhu.tips.view.panel;

/*
 * ***************************
 *  파일명: AppInfoPanel
 *  작성자: 조민성
 *  작성일:
 *  내용:
 * ***************************
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class AppInfoPanel extends JPanel {
	public AppInfoPanel() {
		setLayout(new BorderLayout());
		add(new JLabel("캠퍼스 지도 팁 앱"), BorderLayout.CENTER);
	}
}
