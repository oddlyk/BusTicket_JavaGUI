import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.*;

import java.util.*;


public class Start extends JFrame {
	private Container c=getContentPane();
	private String[] customer_S=new String[9];//���̸�[0], ��й�ȣ[1], �����[2], ������[3], �ð�[4],�¼�1[5],�¼�2[6],�¼�3[7],�¼�4[8]
	private int[] customer_I=new int[6]; //����[0],û�ҳ�[1],����[2],���[3], ���ο�[4], �Ѻ��[5];
	private boolean[] seatT=new boolean[25];
	
	ArrayList<Customer> cst=new ArrayList<Customer>(10);

	private Font font=new Font("���� ���",Font.PLAIN,20);
	private Font fonts=new Font("���� ���",Font.PLAIN,15);
	private Font fontss=new Font("���� ���",Font.PLAIN,8);
	private LineBorder bb= new LineBorder(Color.BLACK, 1, false);
	
	public Start() {
		super("���� ǥ ���� ����"); //==setTitle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,500);
		setVisible(true);
		c.setLayout(null);
		new page1();
		
		for(int i=0;i<seatT.length;i++) {
			seatT[i]=false;
		}
	}
	
	//���� ������ (����, ����Ȯ�� ����)
	class page1 {
		private JPanel page1 = new JPanel();
		private ImageIcon icon = new ImageIcon("image/000.jpg"); //�̹���
		private JLabel img=new JLabel();
		
		void clear() {
			for(int i=0;i<customer_S.length;i++) {
				customer_S[i]=null;
			}
			for(int i=0;i<customer_I.length;i++) {
				customer_I[i]=0;
			}
		}
		public page1() {
			c.add(page1);
			page1.setSize(300, 500);
			page1.setVisible(true);
			page1.setLayout(null);
			page1.setBackground(Color.WHITE);
			
			
			JLabel title=new JLabel("���� ǥ ����"); 
			
			JButton start=new JButton("����");
			JButton cheak=new JButton("���� Ȯ��");
			img.setIcon(icon);
			
			title.setSize(100,40);
			title.setLocation(100, 0);
			page1.add(title);
			
			start.setSize(100,40);
			start.setLocation(20, 400);
			page1.add(start);
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();
					c.remove(page1);
					new final1_1();
				}
			});
			
			cheak.setSize(100,40);
			cheak.setLocation(160, 400);
			page1.add(cheak);
			cheak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.remove(page1);
					new final1_2();
				}
			});
			
			img.setSize(250,300);
			img.setLocation(15, 50);
			page1.add(img);
			
		}
		
	}

	//�̸�, ��й�ȣ �Է� ������
	class final1_1 {
		
		private JPanel final1_1=new JPanel(); //�г� ����
		
		private JLabel exp=new JLabel("���ο� ȸ�� ������ �Է��� �ּ���.");
		private JLabel name=new JLabel("�̸�: ");
		private JTextPane name_tp = new JTextPane();
		private JLabel passw=new JLabel("��� ��ȣ: ");
		private JTextPane passw_tp = new JTextPane();
		
		private String[] cus=new String[2];
		
		private JButton next=new JButton("���� ����");
		
		public final1_1() {
			c.add(final1_1);
			final1_1.setLayout(null);
			final1_1.setSize(300,500);
			final1_1.setVisible(true);
			final1_1.setBackground(Color.WHITE);
			
			ToolTipManager m = ToolTipManager.sharedInstance();
			m.setEnabled(true);
			m.setInitialDelay(0);
			
			
			
			exp.setFont(fonts);
			exp.setSize(280,30);
			exp.setLocation(20,100);
			final1_1.add(exp);
			
			name.setFont(fonts);
			name.setHorizontalAlignment(JLabel.RIGHT);
			name.setSize(100,30);
			name.setLocation(50,150);
			final1_1.add(name);
			
			name_tp.setFont(fonts);
			name_tp.setSize(80,30);
			name_tp.setLocation(150,150);
			name_tp.setBorder(bb);
			name_tp.setToolTipText("������ �̸��� �Է��� �ּ���.");
			final1_1.add(name_tp);
			
			passw.setFont(fonts);
			passw.setHorizontalAlignment(JLabel.RIGHT);
			passw.setSize(100,30);
			passw.setLocation(50,200);
			final1_1.add(passw);
			
			passw_tp.setFont(fonts);
			passw_tp.setSize(80,30);
			passw_tp.setLocation(150,200);
			passw_tp.setToolTipText("����Ͻ� ��й�ȣ�� �Է��� �ּ���.");
			passw_tp.setBorder(bb);
			final1_1.add(passw_tp);
			
			//���� ȭ������
			next.setFont(font);
			next.setSize(130,30);
			next.setLocation(70,300);
			final1_1.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					cus[0]=name_tp.getText();
					cus[1]=passw_tp.getText();
					
					String nameC=null;
					String passC=null;
					int custi=0;
					for(int i=0;i<cst.size();i++) {
						custi=0;
						nameC=cst.get(i).name; //i��° ���� �̸� ����
						passC=cst.get(i).passward;
						if(cus[0].equals(nameC)) {
							if(cus[1].equals(passC)) {
								custi=1;
								break;
							}
						}
					}
					if(custi==1) {
						JOptionPane.showMessageDialog(null,"���������� �����մϴ�. �ٸ� ��й�ȣ�� ����� �ּ���.", null, JOptionPane.WARNING_MESSAGE);
						name_tp.setText("");
						passw_tp.setText("");
					}
					else if(cus[0].equals("")||cus[1].equals("")) {
						JOptionPane.showMessageDialog(null,"������ �̸��� ����Ͻ� ��й�ȣ�� �Է��� �ּ���.", null, JOptionPane.WARNING_MESSAGE);
						name_tp.setText("");
						passw_tp.setText("");
					}
						
					
					else {
						customer_S[0]=cus[0]; //�� �̸� ����
						customer_S[1]=cus[1]; //�� ��й�ȣ ����
						c.remove(final1_1);
						new final2();
					}
						
						
				}
			});

		}
	}	
	
	//�����, ������, �ð� ����
	class final2 {
		private JPanel final2=new JPanel(); //�г� ����
		private JLabel departure_la=new JLabel("�����"); 
		private JLabel arrival_la=new JLabel("������"); 
		
		private String[] start= {"��õ","����","�λ�"}; //����� ����
		private JComboBox<String> departure_cb = new JComboBox<String>(start);
		private String[] end= {"��õ","����","�λ�"}; //������ ����
		private JComboBox<String> arrival_cb = new JComboBox<String>(end);
		
		private JLabel time_exp=new JLabel("�ð��� ������ �ּ���."); 
		
		private JLabel ch_exp=new JLabel("������ �ð�"); 
		
		private String[] timetable= {"06:00","10:00","14:00","18:00"}; //�ð�ǥ ����
		private JList<String> timetable_li=new JList<String>(timetable); 
		
		private JLabel eventText=new JLabel("00:00"); 
		private String event_s=new String("00:00");
		
		private JButton cheak=new JButton("Ȯ��");
		private JButton next=new JButton("����");
		
		
		public final2(){
			c.add(final2);
			final2.setLayout(null);
			final2.setSize(300,500);
			final2.setVisible(true);
			final2.setBackground(Color.WHITE);
			
			//���� �߰�
			ToolTipManager m = ToolTipManager.sharedInstance();
			m.setEnabled(true);
			m.setInitialDelay(0);
			m.setDismissDelay(1000);
			
			
			//����� ����
			departure_la.setFont(font);
			departure_la.setSize(100,30);
			departure_la.setLocation(30, 10);
			final2.add(departure_la);
			
			//������ ����
			arrival_la.setFont(font);
			arrival_la.setSize(100,30);
			arrival_la.setLocation(30, 50);
			final2.add(arrival_la);
			
			//����� ����
			departure_cb.setFont(font);
			departure_cb.setSize(100,30);
			departure_cb.setLocation(150, 10);
			departure_cb.setToolTipText("������� ������ �ּ���.");
			final2.add(departure_cb);
			
			//������ ����
			arrival_cb.setFont(font);
			arrival_cb.setSelectedIndex(1); //���� ȭ�鿡�� ������ ������
			arrival_cb.setSize(100,30);
			arrival_cb.setLocation(150,50);
			arrival_cb.setToolTipText("�������� ������ �ּ���.");
			final2.add(arrival_cb);
			
			//Ȯ�� ��ư
			cheak.setFont(font);
			cheak.setSize(80,30);
			cheak.setLocation(100,120);
			final2.add(cheak);
			
			cheak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int A=departure_cb.getSelectedIndex();
					int B=arrival_cb.getSelectedIndex();
					if(A==B) {
						JOptionPane.showMessageDialog(null,"������� �������� �ٸ��� ������ �ּ���.", null, JOptionPane.WARNING_MESSAGE);
						timetable_li.setVisible(false);
						time_exp.setVisible(false);
						eventText.setText("00:00");
					}
						
					else if(A!=B){
						customer_S[2]=start[A];
						customer_S[3]=start[B];
						timetable_li.setVisible(true);
						//������� �������� �´� ���ð�ǥ ����, ������ �ð��� �ʱ�ȭ
						time_exp.setVisible(true);
						eventText.setText("00:00");
					}
				}
			});
			
			//�ð�ǥ �ȳ���
			time_exp.setFont(fonts);
			time_exp.setSize(200,30);
			time_exp.setLocation(70,160);
			time_exp.setVisible(false);
			final2.add(time_exp);
			
			//�ð�ǥ
			
			timetable_li.addListSelectionListener(new MyListSelectionListener());
			timetable_li.setCellRenderer(new DefaultListCellRenderer(){
			       public int getHorizontalAlignment() {
			    	   return CENTER;
			       }
			});
			timetable_li.setFont(font);
			timetable_li.setSize(150,120);
			timetable_li.setLocation(70,200);
			timetable_li.setVisible(false);
			timetable_li.setBorder(bb);
			final2.add(timetable_li);
			
			ch_exp.setFont(fonts);
			ch_exp.setSize(110,30);
			ch_exp.setLocation(50,380);
			final2.add(ch_exp);
			
			//������ �ð� ���̱�
			eventText.setFont(font);
			eventText.setHorizontalAlignment(JLabel.CENTER);
			eventText.setSize(110,30);
			eventText.setLocation(30,400);
			final2.add(eventText);
			
			//���� ȭ������
			next.setFont(font);
			next.setSize(80,30);
			next.setLocation(170,400);
			final2.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String aa=eventText.getText();
					if(aa.equals(event_s))
						JOptionPane.showMessageDialog(null,"�ð��� ������ �ּ���.", null, JOptionPane.WARNING_MESSAGE);
					else {
						customer_S[4]=aa;
						c.remove(final2);
						new final3();
					}
						
				}
			});
		}
		
		//List���� ������ �ð��� Label�� ����
		class MyListSelectionListener implements ListSelectionListener{
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<String> jl=(JList<String>)e.getSource();
				int index=jl.getSelectedIndex();
				eventText.setText(timetable[index]);
			}		
		}
	}

	//�ο�����, ��� Ȯ��
	//�ο� ����
	class final3 {
		private JPanel final3=new JPanel(); //�г� ����
		private Font font=new Font("���� ���",Font.PLAIN,20);
		private Font fonts=new Font("���� ���",Font.PLAIN,15);
		
		private JLabel human=new JLabel("�ο� ����"); //����
		private JTextPane infor = new JTextPane(); //����
		
		private JLabel exp=new JLabel("�ִ� 4�α��� ������ �ּ���."); 
		
		private String[] typ={"����","û�ҳ�","����","���"};
		private JLabel[] type=new JLabel[4]; //����
		private JButton[] miB=new JButton[4]; //���� ��ư
		private JButton[] adB=new JButton[4]; //���ϱ� ��ư
		private JLabel[] Hmany=new JLabel[4]; //�ο� ǥ��
		private String[] Hcount_S=new String[4]; //�ο� ǥ�� (string)
		private int[] Hcount= {0,0,0,0}; //�ο�ǥ�ÿ� ���
		private int[] pay= {4,3,2,1}; //���
		
		private String[] Ctex={"�� �ο�", "��� �հ�"}; //����
		private JLabel[] Cla=new JLabel[2]; //����
		private JLabel[] CC=new JLabel[2]; //���ο� ��, ��� �հ�
		private int[] CCC= {0,0}; //���ο� ��, ��� �հ�
		
		private JButton next=new JButton("����");
		
		public final3() {
			c.add(final3);
			final3.setLayout(null);
			final3.setSize(300,500);
			final3.setVisible(true);
			
			human.setFont(font);
			human.setSize(100,30);
			human.setLocation(100, 10);
			final3.add(human);
			
			infor.setEditable(false);
			infor.setText("��� �ȳ�\n����: 4����\nû�ҳ�: 3����\n����: 2����\n���: 1����");
			
			//tpName�� styleDocument�� ������ ��� ���� ����
			infor.setFont(fonts);
			StyledDocument doc = infor.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
			infor.setSize(200,110);
			infor.setLocation(40, 45);
			final3.add(infor);
			
			exp.setFont(fonts);
			exp.setSize(200,30);
			exp.setLocation(40, 160);
			final3.add(exp);
			
			for(int i=0;i<type.length;i++) {
				type[i]=new JLabel(typ[i]); 
				type[i].setFont(fonts);
				type[i].setHorizontalAlignment(JLabel.RIGHT);
				type[i].setSize(50, 30);
				type[i].setLocation(20, 190+i*40);
				final3.add(type[i]);
			}
			
			for(int i=0;i<type.length;i++) {
				miB[i]=new JButton("��"); 
				miB[i].setFont(fonts);
				miB[i].setSize(50, 30);
				miB[i].setLocation(90, 190+i*40);
				final3.add(miB[i]);
				
				
				Hcount_S[i]=String.valueOf(Hcount[i]);
				Hmany[i]=new JLabel(Hcount_S[i]);
				Hmany[i].setFont(fonts);
				Hmany[i].setHorizontalAlignment(JLabel.CENTER);
				Hmany[i].setOpaque(true);
				Hmany[i].setBackground(Color.WHITE);
				Hmany[i].setSize(50, 30);
				Hmany[i].setLocation(145, 190+i*40);
				final3.add(Hmany[i]);
				
				adB[i]=new JButton("��"); 
				adB[i].setFont(fonts);
				adB[i].setSize(50, 30);
				adB[i].setLocation(200, 190+i*40);
				final3.add(adB[i]);
			}	
			
			
			for(int i=0;i<Cla.length;i++) {
				Cla[i]=new JLabel(Ctex[i]);
				Cla[i].setFont(fonts);
				Cla[i].setHorizontalAlignment(JLabel.RIGHT);
				Cla[i].setSize(70, 30);
				Cla[i].setLocation(30, 370+i*30);
				final3.add(Cla[i]);
			}
			
			for(int i=0;i<CC.length;i++) {
				CC[i]=new JLabel();
				CC[i].setFont(fonts);
				CC[i].setHorizontalAlignment(JLabel.CENTER);
				CC[i].setSize(40, 30);
				CC[i].setLocation(105, 370+i*30);
				final3.add(CC[i]);
			}
			
			bt();
			
			for(int j=0;j<CCC.length;j++) {
				CCC[0]+=Hcount[j];
				
			}
			CC[0].setText(String.valueOf(CCC[0])+"��");
			CC[1].setText(String.valueOf(CCC[1])+"����");
			CC[1].setSize(70, 30);
			
			
			next.setFont(font);
			
			next.setSize(80,30);
			next.setLocation(170,400);
			final3.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(CCC[0]==0)
						JOptionPane.showMessageDialog(null,"�ο��� ������ �ּ���.", null, JOptionPane.WARNING_MESSAGE);
					else {
						for(int i=0;i<customer_I.length-2;i++) {
							customer_I[i]=Hcount[i];
						}
						customer_I[4]=CCC[0];
						customer_I[5]=CCC[1];
						c.remove(final3);
						new final4();
					}
						
				}
			});
			
			
			//����
			miB[0].addActionListener(new ActionListener() { //���� ����
				public void actionPerformed(ActionEvent e) {
					if(Hcount[0]>0&&CCC[0]>0) { 
						Hcount[0]=Hcount[0]-1;
						Hcount_S[0]=String.valueOf(Hcount[0]);
						Hmany[0].setText(Hcount_S[0]);
						
						CCC[0]=CCC[0]-1;
						CC[0].setText(String.valueOf(CCC[0])+"��");
						CCC[1]=CCC[1]-pay[0];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});
			miB[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Hcount[1]>0&&CCC[0]>0) {
						Hcount[1]=Hcount[1]-1;
						Hcount_S[1]=String.valueOf(Hcount[1]);
						Hmany[1].setText(Hcount_S[1]);
						
						CCC[0]=CCC[0]-1;
						CC[0].setText(String.valueOf(CCC[0])+"��");	
						CCC[1]=CCC[1]-pay[1];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});
			miB[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Hcount[2]>0&&CCC[0]>0) {
						Hcount[2]=Hcount[2]-1;
						Hcount_S[2]=String.valueOf(Hcount[2]);
						Hmany[2].setText(Hcount_S[2]);
						
						CCC[0]=CCC[0]-1;
						CC[0].setText(String.valueOf(CCC[0])+"��");
						CCC[1]=CCC[1]-pay[2];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});
			miB[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Hcount[3]>0&&CCC[0]>0) {
						Hcount[3]=Hcount[3]-1;
						Hcount_S[3]=String.valueOf(Hcount[3]);
						Hmany[3].setText(Hcount_S[3]);
						
						CCC[0]=CCC[0]-1;
						CC[0].setText(String.valueOf(CCC[0])+"��");	
						CCC[1]=CCC[1]-pay[3];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});

			//���ϱ�
			adB[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(CCC[0]<4) {
						Hcount[0]=1+Hcount[0];
						Hcount_S[0]=String.valueOf(Hcount[0]);
						Hmany[0].setText(Hcount_S[0]);
					
						CCC[0]=CCC[0]+1;
						CC[0].setText(String.valueOf(CCC[0])+"��");
						CCC[1]=CCC[1]+pay[0];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});
			adB[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(CCC[0]<4) {
						Hcount[1]=1+Hcount[1];
						Hcount_S[1]=String.valueOf(Hcount[1]);
						Hmany[1].setText(Hcount_S[1]);
					
						CCC[0]=CCC[0]+1;
						CC[0].setText(String.valueOf(CCC[0])+"��");
						CCC[1]=CCC[1]+pay[1];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});
			adB[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					if(CCC[0]<4) {
						Hcount[2]=1+Hcount[2];
						Hcount_S[2]=String.valueOf(Hcount[2]);
						Hmany[2].setText(Hcount_S[2]);
								
						CCC[0]=CCC[0]+1;
						CC[0].setText(String.valueOf(CCC[0])+"��");
						CCC[1]=CCC[1]+pay[2];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});
			adB[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(CCC[0]<4) {
						Hcount[3]=1+Hcount[3];
						Hcount_S[3]=String.valueOf(Hcount[3]);
						Hmany[3].setText(Hcount_S[3]);
								
						CCC[0]=CCC[0]+1;
						CC[0].setText(String.valueOf(CCC[0])+"��");
						CCC[1]=CCC[1]+pay[3];
						CC[1].setText(String.valueOf(CCC[1])+"����");
						bt();
					}
					
				}
			});		
			
			
		}
		
		private void bt() {
			if(CCC[0]==0) {
				for(int i=0;i<miB.length;i++) {
					miB[i].setEnabled(false);
				}
			}
			else
				for(int i=0;i<miB.length;i++) {
					miB[i].setEnabled(true);
				}
			//4�� �ʰ��� �߰� ��ư ��Ȱ��ȭ
			if(CCC[0]==4) {
				for(int i=0;i<adB.length;i++) {
					adB[i].setEnabled(false);
				}
			}
			else
				for(int i=0;i<adB.length;i++) {
					adB[i].setEnabled(true);
				}
		}
	}

	//�¼� ����
	//�¼� ����
	class final4 {
		private JPanel final4=new JPanel(); //�г� ����
		
		private JLabel exp= new JLabel("�¼��� ������ �ּ���.");
		private JLabel Lef= new JLabel("���� �� �¼� ��: ");
		private JLabel Lefseat=new JLabel();
		
		private JLabel exp_seat=new JLabel("������ �¼�");
		
		private String[] seat_ch_s= {"","","",""}; //new String[4]
		private JLabel[] seat_ch=new JLabel[4]; //������ �¼� ����
		private int ccc=0;
		private JTextPane Chseat = new JTextPane();
		
		
		private JLabel driver= new JLabel("������");
		private JLabel door= new JLabel("��");
		private JLabel way= new JLabel("���");
		
		private String[] seatN= {"A-1","A-2","A-3","A-4","B-1","B-2","B-3","B-4","C-1","C-2","C-3","C-4",
								"D-1","D-2","D-3","D-4","E-1","E-2","E-3","E-4","F-1","F-2","F-3","F-4","F-5"};
		private JPanel seatP=new JPanel();
		
		private JButton[] seatB=new JButton[25];
		private JButton reset=new JButton("�ٽ� ����");
		private JButton next=new JButton("����");
		
		private void sea() {
			for(int i=0;i<seat_ch.length;i++) {
				seat_ch[i]=new JLabel(seat_ch_s[i]);
				seat_ch[i].setFont(fonts);
				seat_ch[i].setSize(40, 20);
				seat_ch[i].setLocation(80, 24+i*22);
				Chseat.add(seat_ch[i]);
			}
		}
		private void cou() {
			Lefseat.setFont(fonts);
			Lefseat.setSize(50,30);
			Lefseat.setLocation(185,135);
		}
		public final4() {
			c.add(final4);
			final4.setLayout(null);
			final4.setSize(300,500);
			final4.setVisible(true);
			
			exp.setFont(fonts);
			exp.setSize(200,20);
			exp.setLocation(60,2);
			final4.add(exp);
			
			
			Chseat.setEditable(false);
			Chseat.setLayout(null);
			Chseat.setFont(fonts);
			Chseat.setSize(200,110);
			Chseat.setLocation(40, 25);
			final4.add(Chseat);
			
			exp_seat.setFont(fonts);
			exp_seat.setSize(100, 20);
			exp_seat.setLocation(60, 2);
			Chseat.add(exp_seat);
			
			for(int i=0;i<seat_ch.length;i++) {
				sea();
				Chseat.add(seat_ch[i]);
			}
			
			
			Lef.setFont(fonts);
			Lef.setSize(130,30);
			Lef.setLocation(50,135);
			final4.add(Lef);
			
			cou();
			ccc=customer_I[4];
			Lefseat.setText(ccc+"��");
			final4.add(Lefseat);
			
			
			
			//�¼� �г� (��ũ�ѷ� �����, ������ �Ա� ��� �߰�, �׿� ���� ��ġ ����, ���� ũ�� ����
			seatP.setSize(262, 227);
			seatP.setLayout(null);
			seatP.setLocation(10, 166);
			seatP.setBackground(Color.WHITE);
			final4.add(seatP);
			
			driver.setFont(fonts);
			driver.setHorizontalAlignment(JLabel.CENTER);
			driver.setBorder(bb);
			driver.setSize(50, 30);
			driver.setLocation(2, 2);
			seatP.add(driver);
			
			door.setFont(fonts);
			door.setHorizontalAlignment(JLabel.CENTER);
			door.setBorder(bb);
			door.setSize(50, 30);
			door.setLocation(210, 2);
			seatP.add(door);
			
			way.setFont(fonts);
			way.setHorizontalAlignment(JLabel.CENTER);
			way.setBorder(bb);
			way.setSize(50, 158);
			way.setLocation(106, 35);
			seatP.add(way);
			
			for(int i=0;i<seatN.length;i++) {
				seatB[i]=new JButton(seatN[i]);
				seatB[i].setFont(fontss);
				seatB[i].setSize(50,30);
				
				seatB[i].setBackground(Color.GRAY);
				seatB[i].setForeground(Color.WHITE);
				if(seatT[i]==true) {
					seatB[i].setEnabled(false);
					seatB[i].setBackground(Color.LIGHT_GRAY);
				}
				seatB[i].addActionListener(new Btn());
				
				if(i<=1) {
					seatB[i].setLocation(2+i*52,35);
				}
				else if(i>1&&i<=3){
					seatB[i].setLocation(54+i*52,35);
				}
				else if(i>3&&i<=5){
					seatB[i].setLocation(2+(i-4)*52,67);
				}
				else if(i>5&&i<=7){
					seatB[i].setLocation(54+(i-4)*52,67);
				}
				else if(i>7&&i<=9){
					seatB[i].setLocation(2+(i-8)*52,99);
				}
				else if(i>9&&i<=11){
					seatB[i].setLocation(54+(i-8)*52,99);
				}
				else if(i>11&&i<=13){
					seatB[i].setLocation(2+(i-12)*52,131);
				}
				else if(i>13&&i<=15){
					seatB[i].setLocation(54+(i-12)*52,131);
				}
				else if(i>15&&i<=17){
					seatB[i].setLocation(2+(i-16)*52,163);
				}
				else if(i>17&&i<=19){
					seatB[i].setLocation(54+(i-16)*52,163);
				}
				else if(i>19)
					seatB[i].setLocation(2+(i-20)*52,195);
				seatP.add(seatB[i]);
			}
			
			
			
			
			reset.setFont(font);
			
			reset.setSize(125,30);
			reset.setLocation(20,400);
			final4.add(reset);
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//ccc�� ���� ������ ������
					ccc=customer_I[4];
					Lefseat.setText(ccc+"��");
					for(int i=0;i<seat_ch.length;i++) {
						seat_ch_s[i]="";
						seat_ch[i].setText(seat_ch_s[i]);
					}
					
					for(int i=0;i<seatT.length;i++) {
						if(seatT[i]==false) {
							seatB[i].setBackground(Color.GRAY);
							seatB[i].setEnabled(true);
						}
						
					}
					
					//seatP ���� ������� ������
				}
			});
			
			next.setFont(font);
			
			next.setSize(80,30);
			next.setLocation(170,400);
			final4.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(ccc!=0)
						JOptionPane.showMessageDialog(null,"�ο��� ��ŭ �¼��� ������ �ּ���.", null, JOptionPane.WARNING_MESSAGE);
					else {
						for(int i=0;i<seat_ch_s.length;i++) {
							customer_S[i+5]=seat_ch_s[i];
						}
						for(int i=0;i<seatB.length;i++) {
							if(seatB[i].getBackground()==Color.BLUE) {
								seatB[i].setEnabled(false);
								seatT[i]=true;
							}
						}
						c.remove(final4);
						new final5();
					}
						
				}
			});
		}
		
		class Btn implements ActionListener{
			public void actionPerformed(ActionEvent e) { //��ư�� ������ ��,
				//�¼� Ŭ���� ��� �� ����
				
				JButton b=(JButton)e.getSource(); 
				Color b_now=b.getBackground();
				String aa=b.getText();
				if(b_now==Color.GRAY) {
					//���õ� ��ư�� �� �����ͼ� ��ܿ� �ֱ�
					
					if(ccc>0) {
						ccc=ccc-1;
						Lefseat.setText(ccc+"��");
						if(ccc<=0) {
							for(int i=0;i<seatB.length;i++)
								seatB[i].setEnabled(false);
						}
						b.setBackground(Color.BLUE);
						for(int i=0;i<seat_ch.length;i++) {
							if(seat_ch_s[i].equals("")) {
								seat_ch_s[i]=aa;
								seat_ch[i].setText(seat_ch_s[i]);
								
								
								break;
							}
						}
					}
					
					
				}
				else if(b_now==Color.BLUE){
					ccc=ccc+1;
						Lefseat.setText(ccc+"��");
					//���� ���� �� �����
					for(int i=0;i<seat_ch.length;i++) {
						
						b.setBackground(Color.GRAY);
						if(seat_ch_s[i].equals(aa)) {
							seat_ch_s[i]="";
							seat_ch[i].setText(seat_ch_s[i]);
							
							break;
						}
					}
				}
					
				
			}
			
		}
	}

	//�ȳ��� Ȯ��
	//�ȳ��� Ȯ��
	class final5 {
		private JPanel final5=new JPanel(); //�г� ����
		
		private JTextPane Notice = new JTextPane(); 
		private JScrollPane jsp = new JScrollPane(Notice); 
		private JPanel Npanel = new JPanel(new BorderLayout()); 
		
		private JLabel cheak=new JLabel("���� ���� ������ Ȯ���Ͽ����ϴ�.");
		private JTextPane cheak_tf = new JTextPane();
		private boolean ck=false;
		
		private JButton ch=new JButton("Ȯ��");
		private JButton rese=new JButton("����");
		
		public final5() {
			c.add(final5);
			final5.setLayout(null);
			final5.setSize(300,500);
			final5.setVisible(true);
			
			ToolTipManager m = ToolTipManager.sharedInstance();
			m.setEnabled(true);
			m.setInitialDelay(0);
			
			
			Notice.setEditable(false);
			Notice.setFont(fonts);
			Notice.setText("�̰��� �ȳ����Դϴ�. ���Ű� �����Ѵٸ�, ���� ��Ұ� �Ұ��� �Ͽ���, ���� ���� ��Ź�帳�ϴ�. \n �ε����� ������ ���Ÿ� ����Ͻð� ������ ���, �翪�� ����ڿ��� ���� ��Ź�帳�ϴ�. \n���Ű� �Ϸ�Ǹ� ���� ȭ�鿡 �������� �����Ͻ� �������� �����Ͽ���, Ȯ�� ��Ź�帳�ϴ�. \n�ȳ����� ��� Ȯ���ϼ̴ٸ� �ϴ��� ĭ�� ������ ���� �����ֽʽÿ�.\n�����մϴ�. \n\nDB������ ���߽��ϴ�... �׷��� â�� ������ ��� �� ������ �¼� ������ ���ư��ϴ�. �׷��� Console�� ���� â�� ������ ������ ��ϵ� ��� ���� ������ Ȯ���� �� �ֽ��ϴ�. ������ �����*������*�ð����� 64���� �¼� ������ ��� ������� �����߽��ϴ�...");
			Notice.setLocation(0, 0);
			Notice.setBackground(Color.WHITE);
			
			Npanel.setLocation(20, 30);
		 	Npanel.setSize(240, 250);
		 	Npanel.add(jsp); 
			final5.add(Npanel);
			
			
			
			
			cheak.setHorizontalAlignment(JLabel.CENTER);
			cheak.setSize(200,30);
			cheak.setLocation(40,310);
			final5.add(cheak);
			
			cheak_tf.setBackground(Color.WHITE);
			cheak_tf.setSize(200, 30);
			cheak_tf.setLocation(10, 340);
			cheak_tf.setToolTipText("�� �ؽ�Ʈ�� �Ȱ��� �����ּ���.");
			final5.add(cheak_tf);
			
			ch.setSize(60, 30);
			ch.setLocation(220, 340);
			final5.add(ch);
			
			ch.addActionListener(new ActionListener() { //�͸��� �̺�Ʈ �����ʷ� (implements ActionListener���� class �������)
				public void actionPerformed(ActionEvent e) {
					String A = cheak.getText();
					String B = cheak_tf.getText();
					if(A.equals(B)) {
						cheak_tf.setEnabled(false);
						ch.setEnabled(false);
						ck=true;
					}
					else {
						cheak_tf.setText("");
						JOptionPane.showMessageDialog(null,"�� �ؽ�Ʈ�� �Ȱ��� �����ּ���.", null, JOptionPane.WARNING_MESSAGE);
					}
						
					
				}
			});
			
			rese.setFont(fonts);
			rese.setSize(80, 30);
			rese.setLocation(100, 400);
			final5.add(rese);
			rese.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(!ck)
						JOptionPane.showMessageDialog(null,"�ȳ����� Ȯ���� �ּ���.", null, JOptionPane.WARNING_MESSAGE);
					else {
						c.remove(final5);
						new final6();
					}
						
				}
			});
			
		}
	}
	
	//��ü Ȯ�� ������
	class final6 {
		private JPanel final6=new JPanel(); //�г� ����
		
		private String[] loc={" �����:","������:"};
		private JLabel[] loc_la=new JLabel[2];
		
		private JLabel[] loc_name=new JLabel[2]; //������ ����
		
		private String[] St1={"��߽ð�: ","�� �ο�: "};
		private JLabel[] St1_la=new JLabel[2];
		
		private JLabel[] St2_la=new JLabel[2]; //�ð�, �ο� ����
		
		private String[] human= {"����:","û�ҳ�:","����:","���:"};
		private JLabel[] human_la=new JLabel[4];
		private JLabel[] human_ca=new JLabel[4]; //�� �� ����
		
		private JLabel exp_seat=new JLabel("������ �¼�");
		private JLabel[] seat_ch=new JLabel[4]; //������ �¼� ����
		
		private JTextPane Chseat = new JTextPane();
		
		private JLabel pay=new JLabel("����հ�: ");
		private JLabel pay_ca=new JLabel(); //�հ��� ����
		
		private JButton rese=new JButton("ó������");
		
		private void sea() {
			for(int i=0;i<seat_ch.length;i++) {
				seat_ch[i]=new JLabel(customer_S[i+5]);
				seat_ch[i].setFont(fonts);
				seat_ch[i].setSize(40, 20);
				seat_ch[i].setLocation(80, 35+i*30);
				Chseat.add(seat_ch[i]);
			}
		}
		
		public final6() {
			c.add(final6);
			final6.setLayout(null);
			final6.setSize(300,500);
			final6.setVisible(true);
			
			for(int i=0;i<loc_la.length;i++) {
				loc_la[i]=new JLabel(loc[i]); 
				loc_la[i].setFont(fonts);
				loc_la[i].setSize(70, 30);
				loc_la[i].setLocation(20+i*120, 30);
				final6.add(loc_la[i]);
				
				loc_name[i]=new JLabel(); //������ ��
				loc_name[i].setFont(fonts);
				loc_name[i].setSize(100, 30);
				loc_name[i].setLocation(75+i*120, 30);
				final6.add(loc_name[i]);
			}
			loc_name[0].setText(customer_S[2]);
			loc_name[1].setText(customer_S[3]);
			
			for(int i=0;i<St1_la.length;i++) {
				St1_la[i]=new JLabel(St1[i]); 
				St1_la[i].setFont(fonts);
				St1_la[i].setSize(70, 30);
				St1_la[i].setLocation(20+i*120, 70);
				final6.add(St1_la[i]);
				
				St2_la[i]=new JLabel(); //�ð�, �ο� ��
				St2_la[i].setFont(fonts);
				St2_la[i].setSize(100, 30);
				St2_la[i].setLocation(90+i*110, 70);
				final6.add(St2_la[i]);
			}
			St2_la[0].setText(customer_S[4]); //�ð�
			St2_la[1].setText(String.valueOf(customer_I[4])+"��"); //�ο�
			
			for(int i=0;i<human_la.length;i++) {
				human_la[i]=new JLabel(human[i]); 
				human_la[i].setHorizontalAlignment(JLabel.RIGHT);
				human_la[i].setSize(40, 15);
				human_la[i].setLocation(5+i*65, 110);
				final6.add(human_la[i]);
				
				human_ca[i]=new JLabel(c+"��");  //�� �ο� ��
				human_ca[i].setSize(20, 15);
				human_ca[i].setLocation(50+i*65, 110);
				final6.add(human_ca[i]);
			}
			for(int i=0;i<human_ca.length;i++) {
				human_ca[i].setText(customer_I[i]+"��");
			}
			
			
			Chseat.setEditable(false);
			Chseat.setLayout(null);
			Chseat.setFont(fonts);
			Chseat.setSize(200,150);
			Chseat.setLocation(40, 150);
			final6.add(Chseat);
			
			exp_seat.setFont(fonts);
			exp_seat.setSize(100, 20);
			exp_seat.setLocation(60, 2);
			Chseat.add(exp_seat);
			
			for(int i=0;i<seat_ch.length;i++) {
				sea();
			}
			
			pay.setFont(fonts);
			pay.setSize(70, 30);
			pay.setLocation(50, 320);
			final6.add(pay);
			
			pay_ca.setFont(fonts);
			pay_ca.setSize(100, 30);
			pay_ca.setLocation(150, 320);
			pay_ca.setText(customer_I[5]+"����"); //����հ� ��
			final6.add(pay_ca);
			
			
			rese.setFont(fonts);
			rese.setSize(100, 30);
			rese.setLocation(90, 400);
			final6.add(rese);
			rese.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.remove(final6);
					
					Customer tem=new Customer(); //�̸�,��й�ȣ�� ����
					cst.add(tem);
					new page1();
				}
			});
		}
	}
	
	//���� Ȯ�ο� ȸ�� Ȯ��
	class final1_2 {
		private JPanel final1_2=new JPanel(); //�г� ����
		
		private JLabel exp=new JLabel("������ ������ �Է��� �ּ���.");
		private JLabel name=new JLabel("�̸�: ");
		private JTextPane name_tp = new JTextPane();
		private JLabel passw=new JLabel("��� ��ȣ: ");
		private JTextPane passw_tp = new JTextPane();
		
		private String[] cus=new String[2];
		private int custi=0;
		
		private JButton next=new JButton("�α���");
		
		public final1_2() {
			
			c.add(final1_2);
			final1_2.setLayout(null);
			final1_2.setSize(300,500);
			final1_2.setVisible(true);
			ToolTipManager m = ToolTipManager.sharedInstance();
			m.setEnabled(true);
			m.setInitialDelay(0);
			
			
			
			exp.setFont(fonts);
			exp.setSize(280,30);
			exp.setLocation(30,100);
			final1_2.add(exp);
			
			name.setFont(fonts);
			name.setHorizontalAlignment(JLabel.RIGHT);
			name.setSize(100,30);
			name.setLocation(50,150);
			final1_2.add(name);
			
			name_tp.setFont(fonts);
			name_tp.setSize(80,30);
			name_tp.setLocation(150,150);
			name_tp.setToolTipText("������ �̸��� �Է��� �ּ���.");
			final1_2.add(name_tp);
			
			passw.setFont(fonts);
			passw.setHorizontalAlignment(JLabel.RIGHT);
			passw.setSize(100,30);
			passw.setLocation(50,200);
			final1_2.add(passw);
			
			passw_tp.setFont(fonts);
			passw_tp.setSize(80,30);
			passw_tp.setLocation(150,200);
			passw_tp.setToolTipText("����Ͻ� ��й�ȣ�� �Է��� �ּ���.");
			final1_2.add(passw_tp);
			
			//���� ȭ������
			next.setFont(font);
			next.setSize(130,30);
			next.setLocation(70,300);
			final1_2.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					cus[0]=name_tp.getText();
					cus[1]=passw_tp.getText();
					if(cus[0].equals("")||cus[1].equals("")) {
						name_tp.setText("");
						passw_tp.setText("");
						JOptionPane.showMessageDialog(null,"�̸��� ��ȭ��ȣ�� �Է��� �ּ���.", null, JOptionPane.WARNING_MESSAGE);
					}
						
					else {
						String nameC=null;
						String passC=null;
						for(int i=0;i<cst.size();i++) {
							nameC=cst.get(i).name; //i��° ���� �̸� ����
							passC=cst.get(i).passward; //i��° ���� ��й�ȣ ����
							if(cus[0].equals(nameC)&&cus[1].equals(passC)) {
								custi=i;
								break;
							}
						}
						
						
						if(cus[0].equals(nameC)&&cus[1].equals(passC)) {
							c.remove(final1_2);
							new final1_2_2(custi);
						}
						else {
							name_tp.setText("");
							passw_tp.setText("");
							JOptionPane.showMessageDialog(null,"�ش��ϴ� �̸��� ���ų�, ��й�ȣ�� Ʋ�Ƚ��ϴ�.", null, JOptionPane.WARNING_MESSAGE);
						}
							
							
						
					}
						
				}
			});
		}
	}
	
	//���� Ȯ�ο� Ȯ�� ������
	class final1_2_2 {
		private JPanel final1_2_2=new JPanel(); //�г� ����
		
		private String[] loc={" �����:","������:"};
		private JLabel[] loc_la=new JLabel[2];
		
		private JLabel[] loc_name=new JLabel[2]; //������ ����
		
		private String[] St1={"��߽ð�: ","�� �ο�: "};
		private JLabel[] St1_la=new JLabel[2];
		
		private JLabel[] St2_la=new JLabel[2]; //�ð�, �ο� ����
		
		private String[] human= {"����:","û�ҳ�:","����:","���:"};
		private JLabel[] human_la=new JLabel[4];
		private JLabel[] human_ca=new JLabel[4]; //�� �� ����
		
		private JLabel exp_seat=new JLabel("������ �¼�");
		private JLabel[] seat_ch=new JLabel[4]; //������ �¼� ����
		
		private JTextPane Chseat = new JTextPane();
		
		private JLabel pay=new JLabel("����հ�: ");
		private JLabel pay_ca=new JLabel(); //�հ��� ����
		
		private JButton rese=new JButton("ó������");
		
		private void sea() {
			for(int i=0;i<seat_ch.length;i++) {
				seat_ch[i]=new JLabel();
				seat_ch[i].setFont(fonts);
				seat_ch[i].setSize(40, 20);
				seat_ch[i].setLocation(80, 35+i*30);
				Chseat.add(seat_ch[i]);
			}
		}
		
		public final1_2_2(int K) {
			c.add(final1_2_2);
			final1_2_2.setLayout(null);
			final1_2_2.setSize(300,500);
			final1_2_2.setVisible(true);
			
			for(int i=0;i<loc_la.length;i++) {
				loc_la[i]=new JLabel(loc[i]); 
				loc_la[i].setFont(fonts);
				loc_la[i].setSize(70, 30);
				loc_la[i].setLocation(20+i*120, 30);
				final1_2_2.add(loc_la[i]);
				
				loc_name[i]=new JLabel(); //������ ��
				loc_name[i].setFont(fonts);
				loc_name[i].setSize(100, 30);
				loc_name[i].setLocation(75+i*120, 30);
				final1_2_2.add(loc_name[i]);
			}
			
			loc_name[0].setText(cst.get(K).from);
			loc_name[1].setText(cst.get(K).land);
			
			for(int i=0;i<St1_la.length;i++) {
				St1_la[i]=new JLabel(St1[i]); 
				St1_la[i].setFont(fonts);
				St1_la[i].setSize(70, 30);
				St1_la[i].setLocation(20+i*120, 70);
				final1_2_2.add(St1_la[i]);
				
				St2_la[i]=new JLabel(); //�ð�, �ο� ��
				St2_la[i].setFont(fonts);
				St2_la[i].setSize(100, 30);
				St2_la[i].setLocation(90+i*110, 70);
				final1_2_2.add(St2_la[i]);
			}
			St2_la[0].setText(cst.get(K).time); //�ð�
			St2_la[1].setText(cst.get(K).sum+"��"); //�ο�
			
			for(int i=0;i<human_la.length;i++) {
				human_la[i]=new JLabel(human[i]); 
				human_la[i].setHorizontalAlignment(JLabel.RIGHT);
				human_la[i].setSize(40, 15);
				human_la[i].setLocation(5+i*65, 110);
				final1_2_2.add(human_la[i]);
				
				human_ca[i]=new JLabel(c+"��");  //�� �ο� ��
				human_ca[i].setSize(20, 15);
				human_ca[i].setLocation(50+i*65, 110);
				final1_2_2.add(human_ca[i]);
			}
			human_ca[0].setText(cst.get(K).adult+"��");
			human_ca[1].setText(cst.get(K).teenage+"��");
			human_ca[2].setText(cst.get(K).grand+"��");
			human_ca[3].setText(cst.get(K).child+"��");
			
			
			Chseat.setEditable(false);
			Chseat.setLayout(null);
			Chseat.setFont(fonts);
			Chseat.setSize(200,150);
			Chseat.setLocation(40, 150);
			final1_2_2.add(Chseat);
			
			exp_seat.setFont(fonts);
			exp_seat.setSize(100, 20);
			exp_seat.setLocation(60, 2);
			Chseat.add(exp_seat);
			
			for(int i=0;i<seat_ch.length;i++) {
				sea();
			}
			seat_ch[0].setText(cst.get(K).seat1);
			seat_ch[1].setText(cst.get(K).seat2);
			seat_ch[2].setText(cst.get(K).seat3);
			seat_ch[3].setText(cst.get(K).seat4);
			
			pay.setFont(fonts);
			pay.setSize(70, 30);
			pay.setLocation(50, 320);
			final1_2_2.add(pay);
			
			pay_ca.setFont(fonts);
			pay_ca.setSize(100, 30);
			pay_ca.setLocation(150, 320);
			pay_ca.setText(cst.get(K).pay+"����"); //����հ� ��
			final1_2_2.add(pay_ca);
			
			
			rese.setFont(fonts);
			rese.setSize(100, 30);
			rese.setLocation(90, 400);
			final1_2_2.add(rese);
			rese.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.remove(final1_2_2);
					
					new page1();
				}
			});
		}
	}
	
	//�� ����
	class Customer {
		String name;
		String passward;
		String from; //����� ����
		String land; //������ ����
		String time; //��� �ð�
		
		int adult; //���� �ο� �� (���� [] ���·�?
		int teenage; //û�ҳ�
		int grand; //����
		int child; //����
		
		String seat1;
		String seat2;
		String seat3;
		String seat4;
		
		int sum; //�� �ο�
		int pay; //�� ���
		
		public Customer() {
			name=customer_S[0];
			passward=customer_S[1];
			from=customer_S[2];
			land=customer_S[3];
			time=customer_S[4];
			
			adult=customer_I[0];
			teenage=customer_I[1];
			grand=customer_I[2];
			child=customer_I[3];
			
			seat1=customer_S[5];
			seat2=customer_S[6];
			seat3=customer_S[7];
			seat4=customer_S[8];
			
			sum=customer_I[4];
			pay=customer_I[5];
			
			
			System.out.println("�̸�: "+name);
			System.out.println("��й�ȣ: "+passward);
			System.out.println("�����: "+from);
			System.out.println("������: "+land);
			System.out.println("��� �ð�: "+time);
			System.out.println("����: "+adult+"��");
			System.out.println("û�ҳ�: "+teenage+"��");
			System.out.println("����: "+grand+"��");
			System.out.println("���: "+child+"��");
			
			
			System.out.println("���� �¼�1: "+seat1);
			if(seat2!="") {
				System.out.println("���� �¼�2: "+seat2);
			}
			if(seat2!="") {
				System.out.println("���� �¼�3: "+seat3);
			}
			if(seat2!="") {
				System.out.println("���� �¼�4: "+seat4);
			}
			System.out.println("�� �ο�: "+sum+"��");
			System.out.println("�� ���: "+pay+"����");
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		new Start();
	}
}
