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
import java.io.File;
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
 * @class MainLeftPanel
 * @brief "멍청한 뷰" - 오직 컴포넌트를 소유하고 Getter만 제공합니다.
 */
public class MainLeftPanel extends JPanel {

    // 1. 뷰가 내부적으로 소유한 컴포넌트
    private JList<Building> buildingList;
    private JList<Facility> facilityList;
    private JButton buildingButton;
    private JButton facilityButton;
    private JPanel buttonPanel;
    private JPanel contentPanel;
    private JScrollPane currentScrollPane;

    // (JList에 데이터를 채우기 위한 모델)
    private DefaultListModel<Building> buildingModel = new DefaultListModel<>();
    private DefaultListModel<Facility> facilityModel = new DefaultListModel<>();

    // 이미지 아이콘
    private ImageIcon buildingIcon;
    private ImageIcon facilityIcon;
    
    // 현재 선택된 뷰 타입 (true: 건물, false: 시설)
    private boolean isBuildingView = true;

    public MainLeftPanel() {
        super(new BorderLayout());
        
        // 1. 리소스 로드
        loadIcons();
        
        // 2. 컴포넌트 초기화
        initializeComponents();
        
        // 3. 레이아웃 설정
        setupLayout();
        
        // 4. 초기 상태 설정
        updateButtonStates(true);
        
        // 뷰는 리스너를 등록하지 않습니다!
    }
    
    /**
     * 모든 컴포넌트를 생성하고 기본 설정을 적용합니다.
     */
    private void initializeComponents() {
        // 리스트 컴포넌트 생성
        buildingList = new JList<>(buildingModel);
        facilityList = new JList<>(facilityModel);
        
        // 커스텀 렌더러 설정
        buildingList.setCellRenderer(new ButtonListCellRenderer<>(true));
        facilityList.setCellRenderer(new ButtonListCellRenderer<>(false));
        
        // 카테고리 버튼 생성
        buildingButton = createCategoryButton(true);
        facilityButton = createCategoryButton(false);
        
        // 버튼 패널 생성 및 버튼 추가
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(buildingButton);
        buttonPanel.add(facilityButton);
        
        // 콘텐츠 패널 생성
        contentPanel = new JPanel(new BorderLayout());
        currentScrollPane = new JScrollPane(buildingList);
        contentPanel.add(currentScrollPane, BorderLayout.CENTER);
    }
    
    /**
     * 레이아웃을 설정하고 컴포넌트를 배치합니다.
     */
    private void setupLayout() {
        add(buttonPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * 카테고리 버튼을 생성합니다.
     */
    private JButton createCategoryButton(boolean isBuilding) {
        JButton button = new JButton();
        
        // 아이콘 크기 조정 (버튼용)
        ImageIcon buttonIcon = resizeIconForButton(isBuilding ? buildingIcon : facilityIcon, 32);
        button.setIcon(buttonIcon);
        
        // 색상 설정
        if (isBuilding) {
            button.setBackground(new Color(173, 216, 230)); // 연한 파랑
        } else {
            button.setBackground(new Color(255, 182, 193)); // 연한 빨강
        }
        
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setPreferredSize(new Dimension(150, 60));
        
        return button;
    }
    
    /**
     * 아이콘을 버튼용 크기로 리사이즈합니다.
     */
    private ImageIcon resizeIconForButton(ImageIcon originalIcon, int size) {
        if (originalIcon == null || originalIcon.getIconWidth() == 0) {
            return new ImageIcon();
        }
        Image img = originalIcon.getImage();
        Image resizedImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    
    /**
     * 버튼 상태를 업데이트하고 뷰를 전환합니다.
     */
    private void updateButtonStates(boolean showBuilding) {
        isBuildingView = showBuilding;
        
        if (showBuilding) {
            // 건물 버튼 활성화, 시설 버튼 비활성화
            buildingButton.setBackground(new Color(173, 216, 230).darker());
            facilityButton.setBackground(new Color(255, 182, 193));
            
            // 건물 리스트 표시
            contentPanel.remove(currentScrollPane);
            currentScrollPane = new JScrollPane(buildingList);
            contentPanel.add(currentScrollPane, BorderLayout.CENTER);
        } else {
            // 시설 버튼 활성화, 건물 버튼 비활성화
            facilityButton.setBackground(new Color(255, 182, 193).darker());
            buildingButton.setBackground(new Color(173, 216, 230));
            
            // 시설 리스트 표시
            contentPanel.remove(currentScrollPane);
            currentScrollPane = new JScrollPane(facilityList);
            contentPanel.add(currentScrollPane, BorderLayout.CENTER);
        }
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    /**
     * 이미지 아이콘을 로드합니다.
     */
    private void loadIcons() {
        buildingIcon = loadImageIcon("images/LeftPanel/Building_Icon.png", 40);
        facilityIcon = loadImageIcon("images/LeftPanel/Facility_Icon.png", 40);
    }
    
    /**
     * 이미지 파일을 로드하여 ImageIcon으로 변환합니다.
     * 클래스패스와 파일 시스템 경로를 모두 시도합니다.
     * 
     * @param resourcePath 클래스패스 기준 리소스 경로 (예: "images/LeftPanel/icon.png")
     * @param size 리사이즈할 크기 (정사각형)
     * @return 로드된 ImageIcon, 실패 시 빈 ImageIcon
     */
    private ImageIcon loadImageIcon(String resourcePath, int size) {
        try {
            Image image = loadImage(resourcePath);
            
            if (image != null) {
                image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
                return new ImageIcon(image);
            }
        } catch (Exception e) {
            System.err.println("이미지 로드 실패 [" + resourcePath + "]: " + e.getMessage());
        }
        
        return new ImageIcon();
    }
    
    /**
     * 이미지 파일을 로드합니다. 클래스패스를 먼저 시도하고, 실패하면 파일 시스템 경로를 시도합니다.
     * 
     * @param resourcePath 클래스패스 기준 리소스 경로
     * @return 로드된 Image, 실패 시 null
     */
    private Image loadImage(String resourcePath) throws IOException {
        // 1. 클래스패스에서 리소스 로드 시도
        java.net.URL url = getClass().getClassLoader().getResource(resourcePath);
        if (url != null) {
            return ImageIO.read(url);
        }
        
        // 2. 파일 시스템에서 로드 시도
        String[] paths = {
            "src/resources/" + resourcePath,
            "CampusMapTips/src/resources/" + resourcePath,
            "../src/resources/" + resourcePath
        };
        
        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                return ImageIO.read(file);
            }
        }
        
        return null;
    }

    // --- 4. 컨트롤러가 접근할 수 있도록 Getter를 제공 ---

    public JList<Building> getBuildingList() {
        return buildingList;
    }

    public JList<Facility> getFacilityList() {
        return facilityList;
    }

    public JButton getBuildingButton() {
        return buildingButton;
    }

    public JButton getFacilityButton() {
        return facilityButton;
    }
    
    /**
     * 뷰를 건물 또는 시설로 전환합니다.
     */
    public void switchToBuildingView() {
        updateButtonStates(true);
    }
    
    /**
     * 뷰를 시설로 전환합니다.
     */
    public void switchToFacilityView() {
        updateButtonStates(false);
    }

    // --- 5. 뷰가 데이터를 표시하기 위한 메소드 ---
    public void setBuildingListData(List<Building> data) {
        buildingModel.clear();
        data.forEach(buildingModel::addElement);
    }

    public void setFacilityListData(List<Facility> data) {
        facilityModel.clear();
        data.forEach(facilityModel::addElement);
    }

    /**
     * JList의 각 셀을 커스텀 레이아웃으로 렌더링하는 커스텀 렌더러
     * 좌측: 동그란 원에 순번, 우측: 명칭과 설명 텍스트
     * @param <T> Building 또는 Facility 타입
     */
    private static class ButtonListCellRenderer<T> implements ListCellRenderer<Object> {
        private final boolean isBuilding; // 건물인지 시설인지 구분
        private final Color lightBlue = new Color(173, 216, 230); // 연한 파랑
        private final Color lightRed = new Color(255, 182, 193); // 연한 빨강
        
        // 패널 컴포넌트들
        private final JPanel panel;
        private final JLabel numberLabel;
        private final JLabel nameLabel;
        private final JLabel descriptionLabel;
        
        public ButtonListCellRenderer(boolean isBuilding) {
            this.isBuilding = isBuilding;
            
            // 패널 생성
            panel = new JPanel(new BorderLayout(10, 0));
            panel.setOpaque(true);
            panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));
            
            // 순번 원형 라벨
            numberLabel = new JLabel();
            numberLabel.setPreferredSize(new Dimension(40, 40));
            numberLabel.setHorizontalAlignment(JLabel.CENTER);
            numberLabel.setVerticalAlignment(JLabel.CENTER);
            
            // 텍스트 패널 (명칭 + 설명)
            JPanel textPanel = new JPanel(new BorderLayout(0, 4));
            textPanel.setOpaque(false);
            
            // 명칭 라벨 (크고 bold)
            nameLabel = new JLabel();
            nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            nameLabel.setOpaque(false);
            
            // 설명 라벨 (작은 텍스트)
            descriptionLabel = new JLabel();
            descriptionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
            descriptionLabel.setForeground(Color.GRAY);
            descriptionLabel.setOpaque(false);
            
            textPanel.add(nameLabel, BorderLayout.NORTH);
            textPanel.add(descriptionLabel, BorderLayout.CENTER);
            
            // 레이아웃 설정
            panel.add(numberLabel, BorderLayout.WEST);
            panel.add(textPanel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            
            // 데이터 추출
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
            
            // 텍스트 설정
            nameLabel.setText(name);
            descriptionLabel.setText(description);
            
            // 설명이 너무 길면 자르기
            if (description.length() > 30) {
                descriptionLabel.setText(description.substring(0, 27) + "...");
            }
            
            // 원형 아이콘 색상 설정 (카테고리 색상 사용)
            Color bgColor = isBuilding ? lightBlue : lightRed;
            Color circleColor = isSelected ? bgColor.darker() : bgColor;
            
            // 패널 배경색은 흰색으로 설정
            panel.setBackground(Color.WHITE);
            
            // 원형 아이콘 생성 및 설정
            String numberText = String.valueOf(index + 1);
            ImageIcon circleIcon = createCircleIcon(circleColor, numberText, 40);
            numberLabel.setIcon(circleIcon);
            
            // 선택 시 테두리 추가
            if (isSelected) {
                panel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(bgColor.darker().darker(), 2),
                    javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8)
                ));
            } else {
                panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));
            }
            
            // 크기 설정
            panel.setPreferredSize(new Dimension(list.getWidth() - 20, 70));
            
            return panel;
        }
        
        /**
         * 원형 배경에 숫자가 들어간 아이콘을 생성합니다.
         */
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