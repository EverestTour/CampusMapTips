package com.skhu.tips.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import com.skhu.tips.model.entity.Building;
import com.skhu.tips.model.entity.Facility;

/**
 * 왼쪽 메인 패널 (건물/시설 리스트 표시)
 */
public class MainLeftPanel extends JPanel {

    // --- 필드 (컴포넌트들) ---
    private JList<Building> buildingList;
    private JList<Facility> facilityList;
    private JButton buildingButton;
    private JButton facilityButton;
    private JPanel buttonPanel;
    private JPanel contentPanel;
    
    // (JList에 데이터를 채우기 위한 모델)
    private DefaultListModel<Building> buildingModel = new DefaultListModel<>();
    private DefaultListModel<Facility> facilityModel = new DefaultListModel<>();

    private JScrollPane buildingScrollPane;
    private JScrollPane facilityScrollPane;
    
    private ImageIcon buildingIcon;
    private ImageIcon facilityIcon;
    
    private final Color buildingColor = new Color(173, 216, 230); // 연한 파랑
    private final Color facilityColor = new Color(255, 182, 193); // 연한 빨강

    public MainLeftPanel() {
        super(new BorderLayout());
        loadIcons(); // 아이콘 불러오기
        initializeComponents(); // 컴포넌트 만들기
        setupLayout(); // 화면에 배치하기
    }
    
    // 컴포넌트 생성 및 설정
    private void initializeComponents() {
        buildingList = new JList<>(buildingModel);
        facilityList = new JList<>(facilityModel);
        
        // 리스트 모양 설정
        buildingList.setCellRenderer(new ButtonListCellRenderer<>(true));
        facilityList.setCellRenderer(new ButtonListCellRenderer<>(false));
        
        buildingScrollPane = new JScrollPane(buildingList);
        facilityScrollPane = new JScrollPane(facilityList);
        
        buildingButton = createCategoryButton(true);
        facilityButton = createCategoryButton(false);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(buildingButton);
        buttonPanel.add(facilityButton);
        
        // contentPanel은 기본으로 건물 리스트를 보여줌
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(buildingScrollPane, BorderLayout.CENTER);
    }
    
    // 화면 레이아웃 설정
    private void setupLayout() {
        add(buttonPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    // 카테고리 버튼 만들기
    private JButton createCategoryButton(boolean isBuilding) {
        JButton button = new JButton();
        
        // 아이콘 크기 조절
        ImageIcon buttonIcon = resizeIconForButton(isBuilding ? buildingIcon : facilityIcon, 32);
        button.setIcon(buttonIcon);
        
        button.setBackground(isBuilding ? buildingColor : facilityColor);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setPreferredSize(new Dimension(150, 60));
        
        return button;
    }
    
    // 아이콘 크기 조절 (버튼용)
    private ImageIcon resizeIconForButton(ImageIcon originalIcon, int size) {
        if (originalIcon == null) {
            return new ImageIcon(); 
        }
        
        Image img = originalIcon.getImage();
        
        // 이미지가 null일 경우 프로그램이 멈추지 않게 함
        if (img == null) {
            return new ImageIcon(); 
        }
        
        Image resizedImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    
    // 컨트롤러가 호출: 건물 리스트 보여주기
    public void showBuildingList() {
        contentPanel.remove(facilityScrollPane);
        contentPanel.add(buildingScrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    // 컨트롤러가 호출: 시설 리스트 보여주기
    public void showFacilityList() {
        contentPanel.remove(buildingScrollPane);
        contentPanel.add(facilityScrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    // 컨트롤러가 호출: 버튼 색상 변경
    public void updateButtonColors(boolean isBuildingActive) {
        if (isBuildingActive) {
            buildingButton.setBackground(buildingColor.darker());
            facilityButton.setBackground(facilityColor);
        } else {
            buildingButton.setBackground(buildingColor);
            facilityButton.setBackground(facilityColor.darker());
        }
    }

    // 아이콘 이미지 불러오기
    private void loadIcons() {
        // 두 경로 중 하나에서 이미지를 찾음
        buildingIcon = loadImageIcon("images/LeftPanel/Building_Icon.png", "resources/images/LeftPanel/Building_Icon.png", 40);
        facilityIcon = loadImageIcon("images/LeftPanel/Facility_Icon.png", "resources/images/LeftPanel/Facility_Icon.png", 40);
    }
    
    // 이미지 파일을 읽어오는 헬퍼
    private ImageIcon loadImageIcon(String primaryPath, String fallbackPath, int size) {
        Image image = null;
        try {
            // 첫 번째 경로에서 이미지 찾기
            java.net.URL url = getClass().getClassLoader().getResource(primaryPath);
            
            if (url == null) {
                // 첫 번째 경로 실패 시, 두 번째 경로에서 찾기
                url = getClass().getClassLoader().getResource(fallbackPath);
            }

            if (url != null) {
                // 이미지 읽어오기
                image = ImageIO.read(url);
                image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
                return new ImageIcon(image);
            } else {
                System.err.println("이미지 리소스 찾기 실패: " + primaryPath + " 및 " + fallbackPath);
            }
        } catch (Exception e) {
            System.err.println("이미지 로드 실패 [" + primaryPath + "]: " + e.getMessage());
        }
        return new ImageIcon(); // 실패 시 빈 아이콘 반환
    }
    
    // --- 컨트롤러가 사용할 Getter ---
    public JList<Building> getBuildingList() { return buildingList; }
    public JList<Facility> getFacilityList() { return facilityList; }
    public JButton getBuildingButton() { return buildingButton; }
    public JButton getFacilityButton() { return facilityButton; }

    // --- 컨트롤러가 데이터 채울 때 사용 ---
    public void setBuildingListData(List<Building> data) {
        buildingModel.clear();
        data.forEach(buildingModel::addElement);
    }

    public void setFacilityListData(List<Facility> data) {
        facilityModel.clear();
        data.forEach(facilityModel::addElement);
    }

    /**
     * JList를 예쁘게 꾸미기 위한 내부 클래스
     * (리스트의 각 항목을 커스텀 모양으로 그려줌)
     */
    private static class ButtonListCellRenderer<T> implements ListCellRenderer<Object> {
        
        private final boolean isBuilding;
        private final Color lightBlue = new Color(173, 216, 230);
        private final Color lightRed = new Color(255, 182, 193);
        
        // 리스트 항목으로 사용될 패널과 그 안의 라벨들
        private final JPanel panel;
        private final JLabel numberLabel;
        private final JLabel nameLabel;
        private final JLabel descriptionLabel;
        
        public ButtonListCellRenderer(boolean isBuilding) {
            this.isBuilding = isBuilding;
            
            // --- 리스트 항목 레이아웃 설정 ---
            panel = new JPanel(new BorderLayout(10, 0));
            panel.setOpaque(true);
            panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));
            
            // (왼쪽) 순번이 들어갈 라벨
            numberLabel = new JLabel();
            numberLabel.setPreferredSize(new Dimension(40, 40));
            numberLabel.setHorizontalAlignment(JLabel.CENTER);
            numberLabel.setVerticalAlignment(JLabel.CENTER);
            
            // (오른쪽) 텍스트 (이름 + 설명)
            JPanel textPanel = new JPanel(new BorderLayout(0, 4));
            textPanel.setOpaque(false);
            
            nameLabel = new JLabel();
            nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            nameLabel.setOpaque(false);
            
            descriptionLabel = new JLabel();
            descriptionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
            descriptionLabel.setForeground(Color.GRAY);
            descriptionLabel.setOpaque(false);
            
            textPanel.add(nameLabel, BorderLayout.NORTH);
            textPanel.add(descriptionLabel, BorderLayout.CENTER);
            
            panel.add(numberLabel, BorderLayout.WEST);
            panel.add(textPanel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            
            // 리스트에 표시할 이름과 설명을 가져옴
            String name = "";
            String description = "";
            
            if (value instanceof Building) {
                Building building = (Building) value;
                name = building.getName();
                description = building.getDescription() != null ? building.getDescription() : "";
            } else if (value instanceof Facility) {
                Facility facility = (Facility) value;
                name = facility.getName();
                description = facility.getDescription() != null ? facility.getDescription() : "";
            }
            
            nameLabel.setText(name);
            descriptionLabel.setText(description);
            
            // 설명이 너무 길면 "..."로 자르기
            if (description.length() > 30) {
                descriptionLabel.setText(description.substring(0, 27) + "...");
            }
            
            // 배경색 설정
            Color bgColor = isBuilding ? lightBlue : lightRed;
            Color circleColor = isSelected ? bgColor.darker() : bgColor;
            panel.setBackground(Color.WHITE);
            
            // 왼쪽 원형 아이콘 그리기
            String numberText = String.valueOf(index + 1);
            ImageIcon circleIcon = createCircleIcon(circleColor, numberText, 40);
            numberLabel.setIcon(circleIcon);
            
            // 선택되었을 때 테두리 표시
            if (isSelected) {
                panel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(bgColor.darker().darker(), 2),
                        javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)
                ));
            } else {
                panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));
            }
            
            panel.setPreferredSize(new Dimension(list.getWidth() - 20, 70));
            
            return panel;
        }
        
        // 원형 배경에 숫자가 들어간 아이콘을 그려주는 헬퍼
        private ImageIcon createCircleIcon(Color color, String text, int size) {
            BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // 원 그리기
            g2.setColor(color);
            g2.fillOval(2, 2, size - 4, size - 4);
            
            // 텍스트 그리기
            if (text != null && !text.isEmpty()) {
                g2.setColor(Color.WHITE);
                Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g2.setFont(font);
                
                // 텍스트 중앙 정렬
                int textX = (size - g2.getFontMetrics().stringWidth(text)) / 2;
                int textY = (size + g2.getFontMetrics().getAscent()) / 2 - 2;
                g2.drawString(text, textX, textY);
            }
            
            g2.dispose();
            return new ImageIcon(image);
        }
    }
}