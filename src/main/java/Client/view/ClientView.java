/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client.view;

import Server.model.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ACER
 */
public class ClientView extends javax.swing.JFrame {
    
    private int serverPort,clientPort;

    private String serverHost;
    private DatagramSocket mySocket;
    private DatagramSocket mySocketAsset,mySocketRoom;
    
    ArrayList<Asset> assetList;
    ArrayList<Room> roomList;
    ArrayList<RoomAsset> roomAssetList;
    
    ArrayList<AssetDTO> assetDTOList;
    ArrayList<RoomDTO> roomDTOList;
    ArrayList<RoomAssetDTO> roomAssetDTOList;
    
    DefaultTableModel assetDefaultTableModel ;
    DefaultTableModel roomDefaultTableModel ;
    DefaultTableModel roomAssetDefaultTableModel ;
    
           

    /**
     * Creates new form ClientView
     */
    public ClientView() {
        initComponents();
        serverPort = 1234;

        serverHost = "localhost";
        clientPort = 8888;
        openConnection();
        
        System.out.println("1");
        
        assetList = new ArrayList<>();
        roomList = new ArrayList<>();
        roomAssetList = new ArrayList<>();
        
        assetDTOList = new ArrayList<>();
        roomDTOList = new ArrayList<>();
        roomAssetDTOList = new ArrayList<>();
        
        assetDefaultTableModel = (DefaultTableModel) tblAsset.getModel();
        roomDefaultTableModel = (DefaultTableModel) tblRoom.getModel();
        roomAssetDefaultTableModel = (DefaultTableModel) tblRoomAsset.getModel();

        System.out.println("1");
        fetch();
        
    }
    
    public void openConnection(){
        try {
            mySocket = new DatagramSocket(clientPort);
            
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public void closeConnection(){
        try {
            if(mySocket != null){
                mySocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendAssetDTO(AssetDTO a){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(a);
            byte[] data = baos.toByteArray();
            DatagramPacket sendPkg = new DatagramPacket(data, data.length,InetAddress.getByName(serverHost),serverPort);
            mySocket.send(sendPkg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void sendAssetDTO(AssetDTO a){
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            oos.writeObject(a);
//            byte[] data = baos.toByteArray();
//            DatagramPacket sendPkg = new DatagramPacket(data, data.length,InetAddress.getByName(serverHost),serverPortAsset);
//            mySocket.send(sendPkg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public void sendRoomDTO(RoomDTO r){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(r);
            byte[] data = baos.toByteArray();
            DatagramPacket sendPkg = new DatagramPacket(data, data.length,InetAddress.getByName(serverHost),serverPort);
            mySocket.send(sendPkg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void sendRoomAssetDTO(RoomAssetDTO ra) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(ra);
            byte[] data = baos.toByteArray();
            DatagramPacket sendPkg = new DatagramPacket(data, data.length, InetAddress.getByName(serverHost), serverPort);
            mySocket.send(sendPkg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void send(Object o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            byte[] data = baos.toByteArray();
            DatagramPacket sendPkg = new DatagramPacket(data, data.length, InetAddress.getByName(serverHost), serverPort);
            mySocket.send(sendPkg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void sendRoomDTO(RoomDTO r){
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            oos.writeObject(r);
//            byte[] data = baos.toByteArray();
//            DatagramPacket sendPkg = new DatagramPacket(data, data.length,InetAddress.getByName(serverHost),serverPortRoom);
//            mySocket.send(sendPkg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public ArrayList<Asset> receiveListAsset(){
        assetList = new ArrayList<>();
        try {
            byte[] data = new byte[1024];
            DatagramPacket receivePkg = new DatagramPacket(data,data.length);
            mySocket.receive(receivePkg);
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePkg.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            assetList = (ArrayList<Asset>) ois.readObject();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assetList;
    }
    
    public ArrayList<Room> receiveListRoom(){
        roomList = new ArrayList<>();
        try {
            byte[] data = new byte[1024];
            DatagramPacket receivePkg = new DatagramPacket(data,data.length);
            mySocket.receive(receivePkg);
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePkg.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            roomList = (ArrayList<Room>) ois.readObject();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }
    
    private ArrayList<RoomAsset> receiveListRoomAsset() {
        roomAssetList = new ArrayList<>();
        try {
            byte[] data = new byte[1024];
            DatagramPacket receivePkg = new DatagramPacket(data, data.length);
            mySocket.receive(receivePkg);
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePkg.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            roomAssetList = (ArrayList<RoomAsset>) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomAssetList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtSearchRoom = new javax.swing.JTextField();
        btnSearchRoom = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRoom = new javax.swing.JTable();
        btnAddRoom = new javax.swing.JButton();
        btnUpdateRoom = new javax.swing.JButton();
        btnDeleteRoom = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtRoomName = new javax.swing.JTextField();
        txtRoomDescription = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnLoadFullRoom = new javax.swing.JButton();
        ComboBoxRoomVariables = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnDeleteAsset = new javax.swing.JButton();
        btnUpdateAsset = new javax.swing.JButton();
        btnAddAsset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAsset = new javax.swing.JTable();
        btnSearchAsset = new javax.swing.JButton();
        txtSearchAsset = new javax.swing.JTextField();
        txtAssetName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCurrentLocation = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAssetPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ComboBoxAssetType = new javax.swing.JComboBox<>();
        ComboBoxAssetVariables = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnLoadFullAsset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        ComboBoxRoomName = new javax.swing.JComboBox<>();
        ComboBoxAssetName = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnAddRoomAsset = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRoomAsset = new javax.swing.JTable();
        txtSearchRoomId = new javax.swing.JTextField();
        btnSearchRoomByID = new javax.swing.JButton();
        txtSearchAssetId = new javax.swing.JTextField();
        btnSearchAssetById = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSearchRoom.setText("Tìm Kiếm");
        btnSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchRoomActionPerformed(evt);
            }
        });

        tblRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phòng", "Tên phòng", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRoomMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRoom);

        btnAddRoom.setText("Thêm");
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomActionPerformed(evt);
            }
        });

        btnUpdateRoom.setText("Sửa");
        btnUpdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRoomActionPerformed(evt);
            }
        });

        btnDeleteRoom.setText("Xóa");
        btnDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên phòng");

        txtRoomName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoomNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Mô tả");

        btnLoadFullRoom.setText("Update");
        btnLoadFullRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadFullRoomActionPerformed(evt);
            }
        });

        ComboBoxRoomVariables.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên Phòng" }));

        jLabel8.setText("Tim kiem theo :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(ComboBoxRoomVariables, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchRoom)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAddRoom)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnUpdateRoom))
                                    .addComponent(txtRoomDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addComponent(btnDeleteRoom))
                            .addComponent(btnLoadFullRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchRoom))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxRoomVariables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddRoom)
                            .addComponent(btnUpdateRoom)
                            .addComponent(btnDeleteRoom))
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadFullRoom))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phong ", jPanel1);

        btnDeleteAsset.setText("Xóa");
        btnDeleteAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAssetActionPerformed(evt);
            }
        });

        btnUpdateAsset.setText("Sửa");
        btnUpdateAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateAssetActionPerformed(evt);
            }
        });

        btnAddAsset.setText("Thêm");
        btnAddAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAssetActionPerformed(evt);
            }
        });

        tblAsset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tài sản", "Tên tài sản", "Loại tài sản", "Vị trí hiện tại", "Giá trị(Nghìn Đồng)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAsset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAssetMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAsset);

        btnSearchAsset.setText("Tìm Kiếm");
        btnSearchAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAssetActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên tài sản");

        jLabel4.setText("Loại tài sản");

        jLabel5.setText("Vị trí hiện tại");

        jLabel6.setText("Giá trị");

        ComboBoxAssetType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bình Dân", "Cao Cấp" }));

        ComboBoxAssetVariables.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã tài sản", "Tên tài sản", "Loại tài sản", "Vị trí hiện tại", "Giá trị", "Mã phòng", "Tên phòng" }));

        jLabel7.setText("Tim kiem theo :");

        btnLoadFullAsset.setText("Update");
        btnLoadFullAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadFullAssetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearchAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ComboBoxAssetVariables, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearchAsset)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLoadFullAsset)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddAsset)
                                .addGap(40, 40, 40)
                                .addComponent(btnUpdateAsset)
                                .addGap(38, 38, 38)
                                .addComponent(btnDeleteAsset)))
                        .addGap(0, 680, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(txtAssetName, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(txtCurrentLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(txtAssetPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(ComboBoxAssetType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchAsset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchAsset)
                    .addComponent(ComboBoxAssetVariables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAssetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(ComboBoxAssetType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurrentLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAssetPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddAsset)
                    .addComponent(btnUpdateAsset)
                    .addComponent(btnDeleteAsset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoadFullAsset)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tai san", jPanel2);

        jLabel9.setText("Phòng");

        jLabel10.setText("Tài sản");

        btnAddRoomAsset.setText("Thêm tài sản vào phòng");
        btnAddRoomAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomAssetActionPerformed(evt);
            }
        });

        tblRoomAsset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên phòng", "Tên tài sản", "Loại tài sản", "Giá tài sản(Nghìn đồng)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblRoomAsset);

        btnSearchRoomByID.setText("Tìm kiếm phòng theo id");
        btnSearchRoomByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchRoomByIDActionPerformed(evt);
            }
        });

        btnSearchAssetById.setText("Tìm kiếm tài sản theo id");
        btnSearchAssetById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAssetByIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(ComboBoxRoomName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxAssetName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddRoomAsset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchRoomId)
                    .addComponent(btnSearchRoomByID, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(txtSearchAssetId)
                    .addComponent(btnSearchAssetById, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboBoxRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchRoomByID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxAssetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchAssetId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnSearchAssetById)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddRoomAsset))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quan ly Phong-Tai San", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void alert(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg);
    }
    private void btnSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchRoomActionPerformed
        // TODO add your handling code here:
        int i = ComboBoxRoomVariables.getSelectedIndex();
        switch(i){
            case 0:
                if(!txtSearchRoom.getText().isEmpty()){
                    try {
                        RoomDTO r = new RoomDTO();
                        
                        String id = txtSearchRoom.getText();
                        
                        r.setStatus(5);
                        r.setId(Integer.parseInt(id));
                        
                        txtRoomName.setText("");
                        txtRoomDescription.setText("");
                        txtSearchRoom.setText("");
                        
                        roomList.clear();

                        roomDefaultTableModel.setRowCount(0);
                        
                        sendRoomDTO(r);
                        
                        roomList = receiveListRoom();
                        
                        if(roomList.isEmpty()){
                            alert("khong tim duoc phong can tim kiem");
                            fetch();
                        }
                        
                        else{
                            for (Room room : roomList) {
                            Object[] row = new Object[4];
                            row[0] = room.getId();
                            row[1] = room.getName();
                            row[2] = room.getDescription();

                            roomDefaultTableModel.addRow(row);

                        }
                        }
                        
                        
                    } catch (Exception e) {
                        alert("Vui long nhap lai id");
                        
                    }
                }
                else {
                    alert("please fill in all the details");
                }
                break;
            case 1:
                if(!txtSearchRoom.getText().isEmpty()){
                    try {
                        RoomDTO r = new RoomDTO();

                        String ten = txtSearchRoom.getText();

                        r.setStatus(6);
                        r.setName(ten);

                        txtRoomName.setText("");
                        txtRoomDescription.setText("");

                        roomList.clear();

                        roomDefaultTableModel.setRowCount(0);

                        sendRoomDTO(r);

                        roomList = receiveListRoom();
                        
                        if(roomList.isEmpty()){
                            alert("khong tim duoc phong can tim kiem");
                            fetch();
                        }
                        else{

                        for (Room room : roomList) {
                            Object[] row = new Object[4];
                            row[0] = room.getId();
                            row[1] = room.getName();
                            row[2] = room.getDescription();

                            roomDefaultTableModel.addRow(row);

                        }
                        }
                    } catch (Exception e) {
                    }
                
                }
                else {
                    alert("please fill in all the details");
                }
                break;
        }
        txtSearchRoom.setText("");
    }//GEN-LAST:event_btnSearchRoomActionPerformed

    private void tblRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRoomMouseClicked
        // TODO add your handling code here:
        int ii = tblRoom.getSelectedRow();
        TableModel model = tblRoom.getModel();
        txtRoomName.setText(model.getValueAt(ii, 1).toString());
        txtRoomDescription.setText(model.getValueAt(ii, 2).toString());

    }//GEN-LAST:event_tblRoomMouseClicked

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomActionPerformed
        // TODO add your handling code here:
        if(!txtRoomName.getText().isEmpty() && !txtRoomDescription.getText().isEmpty()){
            
            RoomDTO rDTO = new RoomDTO();

            String tenPhong = txtRoomName.getText().trim();
            String moTaPhong = txtRoomDescription.getText().trim();
            
            
            rDTO.setStatus(2);
            rDTO.setName(tenPhong);
            rDTO.setDescription(moTaPhong);
            
            txtRoomName.setText("");
            txtRoomDescription.setText("");
            
           sendRoomDTO(rDTO);
           
            System.out.println("Send room thanh cong");
            fetch();
        } else {
            alert("please fill in all the details");
        }
        
        //fetch();
       
    }//GEN-LAST:event_btnAddRoomActionPerformed

    private void btnUpdateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRoomActionPerformed
        // TODO add your handling code here:
        int ii = tblRoom.getSelectedRow();
        TableModel modell = tblRoom.getModel();
        int idd = Integer.parseInt(modell.getValueAt(ii, 0).toString());
        
        if (!txtRoomName.getText().isEmpty() && !txtRoomDescription.getText().isEmpty()) {
            try {
                RoomDTO r = new RoomDTO();
                
                String tenPhong = txtRoomName.getText().trim();
                String moTaPhong = txtRoomDescription.getText().trim();
                
                
                r.setStatus(3);
                r.setId(idd);
                r.setName(tenPhong);
                r.setDescription(moTaPhong);
                
                txtRoomName.setText("");
                txtRoomDescription.setText("");
                
                sendRoomDTO(r);
                
                fetch();
                
            } catch (Exception e) {
            }
        }
        else {
            alert("please fill in all the details");
        }

    }//GEN-LAST:event_btnUpdateRoomActionPerformed

    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomActionPerformed
        // TODO add your handling code here:
        int ii = tblRoom.getSelectedRow();
        TableModel modell = tblRoom.getModel();
        int idd = Integer.parseInt(modell.getValueAt(ii, 0).toString());
        
        try {
            RoomDTO r = new RoomDTO();
            
            
            r.setStatus(4);
            r.setId(idd);
            

            txtRoomName.setText("");
            txtRoomDescription.setText("");
            
            sendRoomDTO(r);

            fetch();

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnDeleteRoomActionPerformed

    private void txtRoomNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoomNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRoomNameActionPerformed

    private void btnLoadFullRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFullRoomActionPerformed
        // TODO add your handling code here:
        fetch();
    }//GEN-LAST:event_btnLoadFullRoomActionPerformed

    private void btnDeleteAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAssetActionPerformed
        // TODO add your handling code here:
        int i = tblAsset.getSelectedRow();
        TableModel model = tblAsset.getModel();
        int id  = Integer.parseInt(model.getValueAt(i, 0).toString());
        
        try{
                AssetDTO a = new AssetDTO();
               
                
                a.setStatus(4);
                a.setId(id);
                
                txtAssetName.setText("");
                txtCurrentLocation.setText("");
                txtAssetPrice.setText("");
                
                sendAssetDTO(a);
                
                fetch();
                
                
            }catch(Exception e){
                
            }

    }//GEN-LAST:event_btnDeleteAssetActionPerformed

    private void btnUpdateAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAssetActionPerformed
        // TODO add your handling code here:
        int i = tblAsset.getSelectedRow();
        TableModel model = tblAsset.getModel();
        int id  = Integer.parseInt(model.getValueAt(i, 0).toString());
        
        if(!txtAssetName.getText().isEmpty() && !txtCurrentLocation.getText().isEmpty() && !txtAssetPrice.getText().isEmpty()){
            try {
                AssetDTO a = new AssetDTO();

                String tenTaiSan = txtAssetName.getText().trim();
                String loaiTaiSan = ComboBoxAssetType.getItemAt(ComboBoxAssetType.getSelectedIndex());
                String viTriHienTai = txtCurrentLocation.getText().trim();
                int price = Integer.parseInt(txtAssetPrice.getText());

                a.setStatus(3);
                
                a.setId(id);
                a.setName(tenTaiSan);
                a.setType(loaiTaiSan);
                a.setCurrentLocation(viTriHienTai);
                a.setPrice(price);

                sendAssetDTO(a);

                txtAssetName.setText("");
                ComboBoxAssetType.setSelectedIndex(0);
                txtCurrentLocation.setText("");
                txtAssetPrice.setText("");

                fetch();

            } catch (Exception e) {
                e.printStackTrace();
                alert("vui long nhap so nguyen");

            }
        }
        else {
            alert("please fill in all the details");
        }

    }//GEN-LAST:event_btnUpdateAssetActionPerformed

    private void btnAddAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAssetActionPerformed
        // TODO add your handling code here:

        if(!txtAssetName.getText().isEmpty() && !txtCurrentLocation.getText().isEmpty() && !txtAssetPrice.getText().isEmpty()){
            try {
                AssetDTO aDTO = new AssetDTO();
                
                String tenTaiSan = txtAssetName.getText().trim();
                String loaiTaiSan = ComboBoxAssetType.getItemAt(ComboBoxAssetType.getSelectedIndex());
                String viTriHienTai = txtCurrentLocation.getText().trim();
                int price = 0;
                
                try {
                    price = Integer.parseInt(txtAssetPrice.getText());
                    
                
                aDTO.setStatus(2);
                aDTO.setName(tenTaiSan);
                aDTO.setType(loaiTaiSan);
                aDTO.setCurrentLocation(viTriHienTai);
                aDTO.setPrice(price);
                
                txtAssetName.setText("");
                ComboBoxAssetType.setSelectedIndex(0);
                txtCurrentLocation.setText("");
                txtAssetPrice.setText("");
                
                
                
                sendAssetDTO(aDTO);
                
                System.out.println("Add thanh cong");
                
                fetch();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    alert("vui long nhap so nguyen");
                    
                }
                

                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            alert("please fill in all the details");
        }
        

    }//GEN-LAST:event_btnAddAssetActionPerformed

    private void tblAssetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAssetMouseClicked
        // TODO add your handling code here:
        int i = tblAsset.getSelectedRow();
        TableModel model = tblAsset.getModel();
        txtAssetName.setText(model.getValueAt(i, 1).toString());
        txtCurrentLocation.setText(model.getValueAt(i, 3).toString());
        txtAssetPrice.setText(model.getValueAt(i, 4).toString());
        
        int n;
        String loai =  model.getValueAt(i, 2).toString();
        
        if(loai.equalsIgnoreCase("Bình Dân")){
            n=0;
        }
        else n=1;
        ComboBoxAssetType.setSelectedIndex(n);

    }//GEN-LAST:event_tblAssetMouseClicked

    private void btnSearchAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAssetActionPerformed
        // TODO add your handling code here:
        int i = ComboBoxAssetVariables.getSelectedIndex();
        switch(i){
            case 0 :  //id
                if (!txtSearchAsset.getText().isEmpty()) {
                    
                        AssetDTO a = new AssetDTO();
                        
                        String id = txtSearchAsset.getText();
                        
                    try{
                        a.setStatus(5);
                        a.setId(Integer.parseInt(id));
                        
                        txtAssetName.setText("");
                        ComboBoxAssetType.setSelectedIndex(0);
                        txtCurrentLocation.setText("");
                        txtAssetPrice.setText("");
                        
                        assetList.clear();
                        
                        assetDefaultTableModel.setRowCount(0);
                        
                        sendAssetDTO(a);
                        
                        assetList = receiveListAsset();
                        
                        if(assetList.isEmpty()){
                            alert("Khong co tai san can tim kiem");
                            fetch();
                        }
                        
                        else{
                        
                        for (Asset asset : assetList) {
                            Object[] row = new Object[6];
                            row[0] = asset.getId();
                            row[1] = asset.getName();
                            row[2] = asset.getType();
                            row[3] = asset.getCurrentLocation();
                            row[4] = asset.getPrice();

                            assetDefaultTableModel.addRow(row);

                        }
                        
                        }
                        
                        
                        txtSearchAsset.setText("");
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        alert("vui long nhap so nguyen");
                    }
                }
                else {
                    alert("please fill in all the details");
                }
                break;
            case 1 : //search by name
                if (!txtSearchAsset.getText().isEmpty()) {
                    try {
                        AssetDTO a = new AssetDTO();

                        String ten = txtSearchAsset.getText();

                        a.setStatus(6);
                        a.setName(ten);

                        txtAssetName.setText("");
                        ComboBoxAssetType.setSelectedIndex(0);
                        txtCurrentLocation.setText("");
                        txtAssetPrice.setText("");

                        assetList.clear();

                        assetDefaultTableModel.setRowCount(0);

                        sendAssetDTO(a);

                        assetList = receiveListAsset();
                        
                        if(assetList.isEmpty()){
                            alert("Khong co tai san can tim kiem");
                            fetch();
                        }
                        
                        else{

                        for (Asset asset : assetList) {
                            Object[] row = new Object[6];
                            row[0] = asset.getId();
                            row[1] = asset.getName();
                            row[2] = asset.getType();
                            row[3] = asset.getCurrentLocation();
                            row[4] = asset.getPrice();

                            assetDefaultTableModel.addRow(row);

                        }
                        }

                        txtSearchAsset.setText("");

                    } catch (Exception e) {
                    }
                } else {
                    alert("please fill in all the details");
                }
                break;
            case 2 : //search by type
                if (!txtSearchAsset.getText().isEmpty()) {
                    try {
                        AssetDTO a = new AssetDTO();

                        String loai = txtSearchAsset.getText();

                        a.setStatus(7);
                        a.setType(loai);

                        txtAssetName.setText("");
                        ComboBoxAssetType.setSelectedIndex(0);
                        txtCurrentLocation.setText("");
                        txtAssetPrice.setText("");

                        assetList.clear();

                        assetDefaultTableModel.setRowCount(0);

                        sendAssetDTO(a);

                        assetList = receiveListAsset();
                        
                        if(assetList.isEmpty()){
                            alert("Khong co tai san can tim kiem");
                            fetch();
                        }
                        
                        else{

                        for (Asset asset : assetList) {
                            Object[] row = new Object[6];
                            row[0] = asset.getId();
                            row[1] = asset.getName();
                            row[2] = asset.getType();
                            row[3] = asset.getCurrentLocation();
                            row[4] = asset.getPrice();

                            assetDefaultTableModel.addRow(row);

                        }
                        }

                        txtSearchAsset.setText("");

                    } catch (Exception e) {
                    }
                } else {
                    alert("please fill in all the details");
                }
                break;
            case 3 : //search by currentLocation
                if (!txtSearchAsset.getText().isEmpty()) {
                    try {
                        AssetDTO a = new AssetDTO();

                        String currentLocation = txtSearchAsset.getText();

                        a.setStatus(8);
                        a.setCurrentLocation(currentLocation);

                        txtAssetName.setText("");
                        ComboBoxAssetType.setSelectedIndex(0);
                        txtCurrentLocation.setText("");
                        txtAssetPrice.setText("");

                        assetList.clear();

                        assetDefaultTableModel.setRowCount(0);

                        sendAssetDTO(a);

                        assetList = receiveListAsset();
                        
                        if(assetList.isEmpty()){
                            alert("Khong co tai san can tim kiem");
                            fetch();
                        }
                        
                        else{

                        for (Asset asset : assetList) {
                            Object[] row = new Object[6];
                            row[0] = asset.getId();
                            row[1] = asset.getName();
                            row[2] = asset.getType();
                            row[3] = asset.getCurrentLocation();
                            row[4] = asset.getPrice();

                            assetDefaultTableModel.addRow(row);

                        }
                        }

                        txtSearchAsset.setText("");

                    } catch (Exception e) {
                        
                    }
                } else {
                    alert("please fill in all the details");
                }
                break;
            case 4 : //search by price
                if (!txtSearchAsset.getText().isEmpty()) {
                    try {
                        AssetDTO a = new AssetDTO();

                        String price = txtSearchAsset.getText();

                        a.setStatus(9);
                        a.setPrice(Integer.parseInt(price));

                        txtAssetName.setText("");
                        ComboBoxAssetType.setSelectedIndex(0);
                        txtCurrentLocation.setText("");
                        txtAssetPrice.setText("");

                        assetList.clear();

                        assetDefaultTableModel.setRowCount(0);

                        sendAssetDTO(a);

                        assetList = receiveListAsset();
                        
                        if(assetList.isEmpty()){
                            alert("Khong co tai san can tim kiem");
                            fetch();
                        }
                        
                        else{

                        for (Asset asset : assetList) {
                            Object[] row = new Object[6];
                            row[0] = asset.getId();
                            row[1] = asset.getName();
                            row[2] = asset.getType();
                            row[3] = asset.getCurrentLocation();
                            row[4] = asset.getPrice();

                            assetDefaultTableModel.addRow(row);

                        }
                        }

                        txtSearchAsset.setText("");

                    } catch (Exception e) {
                        e.printStackTrace();
                        alert("vui long nhap so nguyen");
                    }
                } else {
                    alert("please fill in all the details");
                }
                break;
            case 5: //ma Phong
                if (!txtSearchAsset.getText().isEmpty()) {
                   
                    RoomAssetDTO ra = new RoomAssetDTO();
                    
                    
                    try{
                    int maPhong = Integer.parseInt(txtSearchAsset.getText());
                    
                    
                    ra.setStatus(3);
                    ra.setIdRoom(maPhong);
                    
                    
                    
                    
                    roomAssetList.clear();
                    
                    sendRoomAssetDTO(ra);
                    
                    assetDefaultTableModel.setRowCount(0);
                    
                    roomAssetList = receiveListRoomAsset();
                    
                    if(roomAssetList.isEmpty()){
                        alert("khong tim duoc danh sach");
                    }
                    
                    
                    else{
                    
                    

                    for (RoomAsset roomAsset : roomAssetList) {
                        
                        int idAsset = roomAsset.getIdAsset();
                        
                        AssetDTO a = new AssetDTO();
                        
                        a.setId(idAsset);
                        a.setStatus(5);
                        
                        assetList.clear();
                        
                        sendAssetDTO(a);
                        
                        assetList = receiveListAsset();
                        
                        Asset aa = assetList.get(0);
                        
                        
                        Object[] row = new Object[6];
                            
                        row[0] = aa.getId();
                        row[1] = aa.getName();
                        row[2] = aa.getType();
                        row[3] = aa.getCurrentLocation();
                        row[4] = aa.getPrice();

                        assetDefaultTableModel.addRow(row);

                        }
                    }

                        txtSearchAsset.setText("");
                    
                    } catch (Exception e) {
                        e.printStackTrace();
                        alert("vui long nhap so nguyen");
                    }
                    
                }
                else {
                    alert("please fill in all the details");
                }
                break;
            case 6:// ten Phong
                if (!txtSearchAsset.getText().isEmpty()) {
                    
                    RoomAssetDTO ra = new RoomAssetDTO();
                    
                    String tenPhong = txtSearchAsset.getText();
                    
                    ra.setStatus(4);
                    ra.setNameRoom(tenPhong);
                    
                    roomAssetList.clear();

                    sendRoomAssetDTO(ra);

                    assetDefaultTableModel.setRowCount(0);

                    roomAssetList = receiveListRoomAsset();
                    
                    if(roomAssetList.isEmpty()){
                        alert("khong tim duoc danh sach");
                    }
                    
                    
                    else{
                    
                    for (RoomAsset roomAsset : roomAssetList) {

                        int idAsset = roomAsset.getIdAsset();

                        AssetDTO a = new AssetDTO();

                        a.setId(idAsset);
                        a.setStatus(5);

                        assetList.clear();

                        sendAssetDTO(a);

                        assetList = receiveListAsset();

                        Asset aa = assetList.get(0);

                        Object[] row = new Object[6];

                        row[0] = aa.getId();
                        row[1] = aa.getName();
                        row[2] = aa.getType();
                        row[3] = aa.getCurrentLocation();
                        row[4] = aa.getPrice();

                        assetDefaultTableModel.addRow(row);

                    }
                    }
                    
                    txtSearchAsset.setText("");

                } else {
                    alert("please fill in all the details");
                }
                break;
        }
        

    }//GEN-LAST:event_btnSearchAssetActionPerformed

    private void btnLoadFullAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFullAssetActionPerformed
        // TODO add your handling code here:
        fetch();
    }//GEN-LAST:event_btnLoadFullAssetActionPerformed

    private void btnAddRoomAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomAssetActionPerformed
        // TODO add your handling code here:
        AssetDTO aDTO;
        RoomDTO rDTO;
        RoomAssetDTO raDTO;
        
        
        assetList.clear();
        roomList.clear();
        
        int room = ComboBoxRoomName.getSelectedIndex();
        int asset = ComboBoxAssetName.getSelectedIndex();
        
        aDTO = new AssetDTO();
        
        aDTO.setStatus(1);
        
        rDTO = new RoomDTO();
        
        rDTO.setStatus(1);
        
        sendAssetDTO(aDTO);
        sendRoomDTO(rDTO);
        
        assetList = receiveListAsset();
        roomList = receiveListRoom();
        
        Asset a = assetList.get(asset);
        Room r = roomList.get(room);
        
        raDTO = new RoomAssetDTO();
        
        raDTO.setStatus(2);
        raDTO.setIdRoom(r.getId());
        raDTO.setNameRoom(r.getName());
        raDTO.setIdAsset(a.getId());
        raDTO.setNameAsset(a.getName());
        raDTO.setTypeAsset(a.getType());
        raDTO.setPriceAsset(a.getPrice());
        
        sendRoomAssetDTO(raDTO);
        
        fetch();
        
    }//GEN-LAST:event_btnAddRoomAssetActionPerformed

    private void btnSearchRoomByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchRoomByIDActionPerformed
        // TODO add your handling code here:
        if (!txtSearchRoomId.getText().isEmpty()) {
            try {
                
                int roomId = Integer.parseInt(txtSearchRoomId.getText());
                
                RoomDTO r = new RoomDTO();
                
                r.setStatus(1);

                roomList.clear();

                sendRoomDTO(r);

                roomList = receiveListRoom();
                
                int i=0;
                
                for (Room room : roomList) {
                    if(room.getId() == roomId){
                        ComboBoxRoomName.setSelectedIndex(i);
                        break;
                    }
                    else i++;

                }
                
                txtSearchRoomId.setText("");
            } catch (Exception e) {
            }
        }
        else {
                    alert("please fill in all the details");
                }
    }//GEN-LAST:event_btnSearchRoomByIDActionPerformed

    private void btnSearchAssetByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAssetByIdActionPerformed
        // TODO add your handling code here:
        if (!txtSearchAssetId.getText().isEmpty()) {
            try {
                int assetId = Integer.parseInt(txtSearchAssetId.getText());
                
                AssetDTO aDTO = new AssetDTO();

                assetList.clear();
                
                aDTO = new AssetDTO();
                
                aDTO.setStatus(1);
                
                sendAssetDTO(aDTO);
                
                assetList = receiveListAsset();
                int i=0;
                for(Asset asset : assetList){
                    if(asset.getId() == assetId){
                        ComboBoxAssetName.setSelectedIndex(i);
                        break;
                    }
                    else i++;
                }
                
                txtSearchAssetId.setText("");
            } catch (Exception e) {
            }
        }
        else {
                    alert("please fill in all the details");
                }
    }//GEN-LAST:event_btnSearchAssetByIdActionPerformed

    
    private void fetch() {
        assetList.clear();
        roomList.clear();
        roomAssetList.clear();
        
        assetDefaultTableModel.setRowCount(0);
        roomDefaultTableModel.setRowCount(0);
        roomAssetDefaultTableModel.setRowCount(0);
        
        AssetDTO a = new AssetDTO();
        
        a.setStatus(1);
        System.out.println("1");
        
        RoomDTO r = new RoomDTO();
        
        r.setStatus(1);
        System.out.println("2");
        
        RoomAssetDTO ra = new RoomAssetDTO();
        ra.setStatus(1);
        System.out.println("3");
        
        sendAssetDTO(a);
        sendRoomDTO(r);
        sendRoomAssetDTO(ra);
        System.out.println("4");
        
        //roomAssetList = receiveListRoomAsset();
        assetList = receiveListAsset();
        roomList = receiveListRoom();
        roomAssetList = receiveListRoomAsset();
        System.out.println("5");
        
        
        for(Asset asset : assetList){
            Object[] row = new Object[6];
            row[0] = asset.getId();
            row[1] = asset.getName();
            row[2] = asset.getType();
            row[3] = asset.getCurrentLocation();
            row[4] = asset.getPrice();
            
            assetDefaultTableModel.addRow(row);
            
        }
        
        ComboBoxAssetName.removeAllItems();
        ComboBoxRoomName.removeAllItems();
        
        for(Asset asset : assetList){
            ComboBoxAssetName.addItem(asset.getName() +" "+ asset.getType() + "");
        }
        
        for(Room room : roomList){
            ComboBoxRoomName.addItem(room.getName() + "");
        }
        
        for(Room room : roomList){
            Object[] row = new Object[4];
            row[0] = room.getId();
            row[1] = room.getName();
            row[2] = room.getDescription();
            
            
            roomDefaultTableModel.addRow(row);
            
        }
        
        for(RoomAsset roomAsset : roomAssetList){
            Object[] row = new Object[5];
            row[0] = roomAsset.getId();
            row[1] = roomAsset.getNameRoom();
            row[2] = roomAsset.getNameAsset();
            row[3] = roomAsset.getTypeAsset();
            row[4] = roomAsset.getPriceAsset();
            
            roomAssetDefaultTableModel.addRow(row);
        }
        
        
        
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxAssetName;
    private javax.swing.JComboBox<String> ComboBoxAssetType;
    private javax.swing.JComboBox<String> ComboBoxAssetVariables;
    private javax.swing.JComboBox<String> ComboBoxRoomName;
    private javax.swing.JComboBox<String> ComboBoxRoomVariables;
    private javax.swing.JButton btnAddAsset;
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnAddRoomAsset;
    private javax.swing.JButton btnDeleteAsset;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnLoadFullAsset;
    private javax.swing.JButton btnLoadFullRoom;
    private javax.swing.JButton btnSearchAsset;
    private javax.swing.JButton btnSearchAssetById;
    private javax.swing.JButton btnSearchRoom;
    private javax.swing.JButton btnSearchRoomByID;
    private javax.swing.JButton btnUpdateAsset;
    private javax.swing.JButton btnUpdateRoom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblAsset;
    private javax.swing.JTable tblRoom;
    private javax.swing.JTable tblRoomAsset;
    private javax.swing.JTextField txtAssetName;
    private javax.swing.JTextField txtAssetPrice;
    private javax.swing.JTextField txtCurrentLocation;
    private javax.swing.JTextField txtRoomDescription;
    private javax.swing.JTextField txtRoomName;
    private javax.swing.JTextField txtSearchAsset;
    private javax.swing.JTextField txtSearchAssetId;
    private javax.swing.JTextField txtSearchRoom;
    private javax.swing.JTextField txtSearchRoomId;
    // End of variables declaration//GEN-END:variables

    

    
}
