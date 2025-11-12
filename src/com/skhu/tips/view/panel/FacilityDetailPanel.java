package com.skhu.tips.view.panel;

/*
 * ***************************
 *  파일명: AppInfoPanel
 *  작성자: 조민성
 *  작성일:
 *  내용:
 * ***************************
 */

import com.skhu.tips.model.dto.Facility;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class FacilityDetailPanel extends JPanel {
	public FacilityDetailPanel(Facility facility, Runnable onClose) {
		setLayout(new BorderLayout());
		JButton closeButton = new JButton("닫기");
		closeButton.addActionListener(e -> {
			if (onClose != null) onClose.run();
		});
		add(closeButton, BorderLayout.NORTH);

		JPanel content = new JPanel(new GridLayout(0, 1, 4, 4));
		content.setBorder(new EmptyBorder(12, 12, 12, 12));
		content.add(new JLabel("주요 시설 상세"));
		content.add(new JLabel("선택된 시설: " + (facility != null ? facility.toString() : "(알 수 없음)")));
		add(content, BorderLayout.CENTER);
	}
}
