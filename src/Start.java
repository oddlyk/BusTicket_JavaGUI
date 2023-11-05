import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.*;

import java.util.*;


public class Start extends JFrame {
	private Container c=getContentPane();
	private String[] customer_S=new String[9];//고객이름[0], 비밀번호[1], 출발지[2], 도착지[3], 시간[4],좌석1[5],좌석2[6],좌석3[7],좌석4[8]
	private int[] customer_I=new int[6]; //성인[0],청소년[1],노인[2],어린이[3], 총인원[4], 총비용[5];
	private boolean[] seatT=new boolean[25];
	
	ArrayList<Customer> cst=new ArrayList<Customer>(10);

	private Font font=new Font("맑은 고딕",Font.PLAIN,20);
	private Font fonts=new Font("맑은 고딕",Font.PLAIN,15);
	private Font fontss=new Font("맑은 고딕",Font.PLAIN,8);
	private LineBorder bb= new LineBorder(Color.BLACK, 1, false);
	
	public Start() {
		super("버스 표 당일 예매"); //==setTitle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,500);
		setVisible(true);
		c.setLayout(null);
		new page1();
		
		for(int i=0;i<seatT.length;i++) {
			seatT[i]=false;
		}
	}
	
	//시작 페이지 (예매, 예매확인 선택)
	class page1 {
		private JPanel page1 = new JPanel();
		private ImageIcon icon = new ImageIcon("image/000.jpg"); //이미지
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
			
			
			JLabel title=new JLabel("버스 표 예매"); 
			
			JButton start=new JButton("예매");
			JButton cheak=new JButton("예매 확인");
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

	//이름, 비밀번호 입력 페이지
	class final1_1 {
		
		private JPanel final1_1=new JPanel(); //패널 생성
		
		private JLabel exp=new JLabel("새로운 회원 정보를 입력해 주세요.");
		private JLabel name=new JLabel("이름: ");
		private JTextPane name_tp = new JTextPane();
		private JLabel passw=new JLabel("비밀 번호: ");
		private JTextPane passw_tp = new JTextPane();
		
		private String[] cus=new String[2];
		
		private JButton next=new JButton("예매 시작");
		
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
			name_tp.setToolTipText("예매자 이름을 입력해 주세요.");
			final1_1.add(name_tp);
			
			passw.setFont(fonts);
			passw.setHorizontalAlignment(JLabel.RIGHT);
			passw.setSize(100,30);
			passw.setLocation(50,200);
			final1_1.add(passw);
			
			passw_tp.setFont(fonts);
			passw_tp.setSize(80,30);
			passw_tp.setLocation(150,200);
			passw_tp.setToolTipText("사용하실 비밀번호를 입력해 주세요.");
			passw_tp.setBorder(bb);
			final1_1.add(passw_tp);
			
			//다음 화면으로
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
						nameC=cst.get(i).name; //i번째 고객의 이름 저장
						passC=cst.get(i).passward;
						if(cus[0].equals(nameC)) {
							if(cus[1].equals(passC)) {
								custi=1;
								break;
							}
						}
					}
					if(custi==1) {
						JOptionPane.showMessageDialog(null,"동명이인이 존재합니다. 다른 비밀번호를 사용해 주세요.", null, JOptionPane.WARNING_MESSAGE);
						name_tp.setText("");
						passw_tp.setText("");
					}
					else if(cus[0].equals("")||cus[1].equals("")) {
						JOptionPane.showMessageDialog(null,"예매자 이름과 사용하실 비밀번호를 입력해 주세요.", null, JOptionPane.WARNING_MESSAGE);
						name_tp.setText("");
						passw_tp.setText("");
					}
						
					
					else {
						customer_S[0]=cus[0]; //고객 이름 저장
						customer_S[1]=cus[1]; //고객 비밀번호 저장
						c.remove(final1_1);
						new final2();
					}
						
						
				}
			});

		}
	}	
	
	//출발지, 도착지, 시간 선택
	class final2 {
		private JPanel final2=new JPanel(); //패널 생성
		private JLabel departure_la=new JLabel("출발지"); 
		private JLabel arrival_la=new JLabel("도착지"); 
		
		private String[] start= {"인천","서울","부산"}; //출발지 선택
		private JComboBox<String> departure_cb = new JComboBox<String>(start);
		private String[] end= {"인천","서울","부산"}; //도착지 선택
		private JComboBox<String> arrival_cb = new JComboBox<String>(end);
		
		private JLabel time_exp=new JLabel("시간을 선택해 주세요."); 
		
		private JLabel ch_exp=new JLabel("선택한 시간"); 
		
		private String[] timetable= {"06:00","10:00","14:00","18:00"}; //시간표 선택
		private JList<String> timetable_li=new JList<String>(timetable); 
		
		private JLabel eventText=new JLabel("00:00"); 
		private String event_s=new String("00:00");
		
		private JButton cheak=new JButton("확인");
		private JButton next=new JButton("다음");
		
		
		public final2(){
			c.add(final2);
			final2.setLayout(null);
			final2.setSize(300,500);
			final2.setVisible(true);
			final2.setBackground(Color.WHITE);
			
			//툴팁 추가
			ToolTipManager m = ToolTipManager.sharedInstance();
			m.setEnabled(true);
			m.setInitialDelay(0);
			m.setDismissDelay(1000);
			
			
			//출발지 설명
			departure_la.setFont(font);
			departure_la.setSize(100,30);
			departure_la.setLocation(30, 10);
			final2.add(departure_la);
			
			//도착지 설명
			arrival_la.setFont(font);
			arrival_la.setSize(100,30);
			arrival_la.setLocation(30, 50);
			final2.add(arrival_la);
			
			//출발지 선택
			departure_cb.setFont(font);
			departure_cb.setSize(100,30);
			departure_cb.setLocation(150, 10);
			departure_cb.setToolTipText("출발지를 선택해 주세요.");
			final2.add(departure_cb);
			
			//도착지 선택
			arrival_cb.setFont(font);
			arrival_cb.setSelectedIndex(1); //시작 화면에서 보여줄 도착지
			arrival_cb.setSize(100,30);
			arrival_cb.setLocation(150,50);
			arrival_cb.setToolTipText("도착지를 선택해 주세요.");
			final2.add(arrival_cb);
			
			//확인 버튼
			cheak.setFont(font);
			cheak.setSize(80,30);
			cheak.setLocation(100,120);
			final2.add(cheak);
			
			cheak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int A=departure_cb.getSelectedIndex();
					int B=arrival_cb.getSelectedIndex();
					if(A==B) {
						JOptionPane.showMessageDialog(null,"출발지와 도착지를 다르게 선택해 주세요.", null, JOptionPane.WARNING_MESSAGE);
						timetable_li.setVisible(false);
						time_exp.setVisible(false);
						eventText.setText("00:00");
					}
						
					else if(A!=B){
						customer_S[2]=start[A];
						customer_S[3]=start[B];
						timetable_li.setVisible(true);
						//출발지와 목적지에 맞는 새시간표 띄우고, 선택한 시간도 초기화
						time_exp.setVisible(true);
						eventText.setText("00:00");
					}
				}
			});
			
			//시간표 안내문
			time_exp.setFont(fonts);
			time_exp.setSize(200,30);
			time_exp.setLocation(70,160);
			time_exp.setVisible(false);
			final2.add(time_exp);
			
			//시간표
			
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
			
			//선택한 시간 보이기
			eventText.setFont(font);
			eventText.setHorizontalAlignment(JLabel.CENTER);
			eventText.setSize(110,30);
			eventText.setLocation(30,400);
			final2.add(eventText);
			
			//다음 화면으로
			next.setFont(font);
			next.setSize(80,30);
			next.setLocation(170,400);
			final2.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String aa=eventText.getText();
					if(aa.equals(event_s))
						JOptionPane.showMessageDialog(null,"시간을 선택해 주세요.", null, JOptionPane.WARNING_MESSAGE);
					else {
						customer_S[4]=aa;
						c.remove(final2);
						new final3();
					}
						
				}
			});
		}
		
		//List에서 선택한 시간을 Label로 보냄
		class MyListSelectionListener implements ListSelectionListener{
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<String> jl=(JList<String>)e.getSource();
				int index=jl.getSelectedIndex();
				eventText.setText(timetable[index]);
			}		
		}
	}

	//인원선택, 비용 확인
	//인원 선택
	class final3 {
		private JPanel final3=new JPanel(); //패널 생성
		private Font font=new Font("맑은 고딕",Font.PLAIN,20);
		private Font fonts=new Font("맑은 고딕",Font.PLAIN,15);
		
		private JLabel human=new JLabel("인원 선택"); //설명문
		private JTextPane infor = new JTextPane(); //설명문
		
		private JLabel exp=new JLabel("최대 4인까지 선택해 주세요."); 
		
		private String[] typ={"성인","청소년","노인","어린이"};
		private JLabel[] type=new JLabel[4]; //설명문
		private JButton[] miB=new JButton[4]; //빼기 버튼
		private JButton[] adB=new JButton[4]; //더하기 버튼
		private JLabel[] Hmany=new JLabel[4]; //인원 표시
		private String[] Hcount_S=new String[4]; //인원 표시 (string)
		private int[] Hcount= {0,0,0,0}; //인원표시용 계산
		private int[] pay= {4,3,2,1}; //비용
		
		private String[] Ctex={"총 인원", "요금 합계"}; //설명문
		private JLabel[] Cla=new JLabel[2]; //설명문
		private JLabel[] CC=new JLabel[2]; //총인원 수, 요금 합계
		private int[] CCC= {0,0}; //총인원 수, 요금 합계
		
		private JButton next=new JButton("다음");
		
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
			infor.setText("요금 안내\n성인: 4만원\n청소년: 3만원\n노인: 2만원\n어린이: 1만원");
			
			//tpName의 styleDocument를 가져와 가운데 정렬 설정
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
				miB[i]=new JButton("◀"); 
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
				
				adB[i]=new JButton("▶"); 
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
			CC[0].setText(String.valueOf(CCC[0])+"명");
			CC[1].setText(String.valueOf(CCC[1])+"만원");
			CC[1].setSize(70, 30);
			
			
			next.setFont(font);
			
			next.setSize(80,30);
			next.setLocation(170,400);
			final3.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(CCC[0]==0)
						JOptionPane.showMessageDialog(null,"인원을 선택해 주세요.", null, JOptionPane.WARNING_MESSAGE);
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
			
			
			//빼기
			miB[0].addActionListener(new ActionListener() { //성인 빼기
				public void actionPerformed(ActionEvent e) {
					if(Hcount[0]>0&&CCC[0]>0) { 
						Hcount[0]=Hcount[0]-1;
						Hcount_S[0]=String.valueOf(Hcount[0]);
						Hmany[0].setText(Hcount_S[0]);
						
						CCC[0]=CCC[0]-1;
						CC[0].setText(String.valueOf(CCC[0])+"명");
						CCC[1]=CCC[1]-pay[0];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
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
						CC[0].setText(String.valueOf(CCC[0])+"명");	
						CCC[1]=CCC[1]-pay[1];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
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
						CC[0].setText(String.valueOf(CCC[0])+"명");
						CCC[1]=CCC[1]-pay[2];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
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
						CC[0].setText(String.valueOf(CCC[0])+"명");	
						CCC[1]=CCC[1]-pay[3];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
						bt();
					}
					
				}
			});

			//더하기
			adB[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(CCC[0]<4) {
						Hcount[0]=1+Hcount[0];
						Hcount_S[0]=String.valueOf(Hcount[0]);
						Hmany[0].setText(Hcount_S[0]);
					
						CCC[0]=CCC[0]+1;
						CC[0].setText(String.valueOf(CCC[0])+"명");
						CCC[1]=CCC[1]+pay[0];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
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
						CC[0].setText(String.valueOf(CCC[0])+"명");
						CCC[1]=CCC[1]+pay[1];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
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
						CC[0].setText(String.valueOf(CCC[0])+"명");
						CCC[1]=CCC[1]+pay[2];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
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
						CC[0].setText(String.valueOf(CCC[0])+"명");
						CCC[1]=CCC[1]+pay[3];
						CC[1].setText(String.valueOf(CCC[1])+"만원");
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
			//4명 초과시 추가 버튼 비활성화
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

	//좌석 선택
	//좌석 선택
	class final4 {
		private JPanel final4=new JPanel(); //패널 생성
		
		private JLabel exp= new JLabel("좌석을 선택해 주세요.");
		private JLabel Lef= new JLabel("선택 할 좌석 수: ");
		private JLabel Lefseat=new JLabel();
		
		private JLabel exp_seat=new JLabel("선택한 좌석");
		
		private String[] seat_ch_s= {"","","",""}; //new String[4]
		private JLabel[] seat_ch=new JLabel[4]; //선택한 좌석 저장
		private int ccc=0;
		private JTextPane Chseat = new JTextPane();
		
		
		private JLabel driver= new JLabel("운전석");
		private JLabel door= new JLabel("문");
		private JLabel way= new JLabel("통로");
		
		private String[] seatN= {"A-1","A-2","A-3","A-4","B-1","B-2","B-3","B-4","C-1","C-2","C-3","C-4",
								"D-1","D-2","D-3","D-4","E-1","E-2","E-3","E-4","F-1","F-2","F-3","F-4","F-5"};
		private JPanel seatP=new JPanel();
		
		private JButton[] seatB=new JButton[25];
		private JButton reset=new JButton("다시 선택");
		private JButton next=new JButton("다음");
		
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
			Lefseat.setText(ccc+"개");
			final4.add(Lefseat);
			
			
			
			//좌석 패널 (스크롤로 만들기, 운전석 입구 통로 추가, 그에 맞춰 배치 변경, 글자 크기 변경
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
					
					//ccc를 원래 값으로 돌리기
					ccc=customer_I[4];
					Lefseat.setText(ccc+"개");
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
					
					//seatP 상태 원래대로 돌리기
				}
			});
			
			next.setFont(font);
			
			next.setSize(80,30);
			next.setLocation(170,400);
			final4.add(next);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(ccc!=0)
						JOptionPane.showMessageDialog(null,"인원수 만큼 좌석을 선택해 주세요.", null, JOptionPane.WARNING_MESSAGE);
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
			public void actionPerformed(ActionEvent e) { //버튼을 눌렀을 때,
				//좌석 클릭시 배경 색 변경
				
				JButton b=(JButton)e.getSource(); 
				Color b_now=b.getBackground();
				String aa=b.getText();
				if(b_now==Color.GRAY) {
					//선택된 버튼의 색 가져와서 상단에 넣기
					
					if(ccc>0) {
						ccc=ccc-1;
						Lefseat.setText(ccc+"명");
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
						Lefseat.setText(ccc+"명");
					//위에 넣은 것 지우기
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

	//안내문 확인
	//안내문 확인
	class final5 {
		private JPanel final5=new JPanel(); //패널 생성
		
		private JTextPane Notice = new JTextPane(); 
		private JScrollPane jsp = new JScrollPane(Notice); 
		private JPanel Npanel = new JPanel(new BorderLayout()); 
		
		private JLabel cheak=new JLabel("위의 주의 사항을 확인하였습니다.");
		private JTextPane cheak_tf = new JTextPane();
		private boolean ck=false;
		
		private JButton ch=new JButton("확인");
		private JButton rese=new JButton("예매");
		
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
			Notice.setText("이것은 안내문입니다. 예매가 성공한다면, 예매 취소가 불가능 하오니, 이점 양해 부탁드립니다. \n 부득이한 사유로 예매를 취소하시고 싶으신 경우, 당역의 담당자에게 문의 부탁드립니다. \n예매가 완료되면 다음 화면에 이전까지 선택하신 내역들이 등장하오니, 확인 부탁드립니다. \n안내문을 모두 확인하셨다면 하단의 칸에 문구를 따라 적어주십시오.\n감사합니다. \n\nDB구현을 못했습니다... 그래서 창을 닫으면 모든 고객 정보와 좌석 정보가 날아갑니다. 그러나 Console을 통해 창이 닫히기 전까지 등록된 모든 고객의 정보를 확인할 수 있습니다. 버스는 출발지*도착지*시간으로 64개의 좌석 정보를 모두 만드려다 실패했습니다...");
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
			cheak_tf.setToolTipText("위 텍스트와 똑같이 적어주세요.");
			final5.add(cheak_tf);
			
			ch.setSize(60, 30);
			ch.setLocation(220, 340);
			final5.add(ch);
			
			ch.addActionListener(new ActionListener() { //익명의 이벤트 리스너로 (implements ActionListener없이 class 선언없이)
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
						JOptionPane.showMessageDialog(null,"위 텍스트와 똑같이 적어주세요.", null, JOptionPane.WARNING_MESSAGE);
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
						JOptionPane.showMessageDialog(null,"안내문을 확인해 주세요.", null, JOptionPane.WARNING_MESSAGE);
					else {
						c.remove(final5);
						new final6();
					}
						
				}
			});
			
		}
	}
	
	//전체 확인 페이지
	class final6 {
		private JPanel final6=new JPanel(); //패널 생성
		
		private String[] loc={" 출발지:","도착지:"};
		private JLabel[] loc_la=new JLabel[2];
		
		private JLabel[] loc_name=new JLabel[2]; //지역명 저장
		
		private String[] St1={"출발시간: ","총 인원: "};
		private JLabel[] St1_la=new JLabel[2];
		
		private JLabel[] St2_la=new JLabel[2]; //시간, 인원 저장
		
		private String[] human= {"성인:","청소년:","노인:","어린이:"};
		private JLabel[] human_la=new JLabel[4];
		private JLabel[] human_ca=new JLabel[4]; //각 값 저장
		
		private JLabel exp_seat=new JLabel("선택한 좌석");
		private JLabel[] seat_ch=new JLabel[4]; //선택한 좌석 저장
		
		private JTextPane Chseat = new JTextPane();
		
		private JLabel pay=new JLabel("요금합계: ");
		private JLabel pay_ca=new JLabel(); //합계요금 저장
		
		private JButton rese=new JButton("처음으로");
		
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
				
				loc_name[i]=new JLabel(); //지역명 들어감
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
				
				St2_la[i]=new JLabel(); //시간, 인원 들어감
				St2_la[i].setFont(fonts);
				St2_la[i].setSize(100, 30);
				St2_la[i].setLocation(90+i*110, 70);
				final6.add(St2_la[i]);
			}
			St2_la[0].setText(customer_S[4]); //시간
			St2_la[1].setText(String.valueOf(customer_I[4])+"명"); //인원
			
			for(int i=0;i<human_la.length;i++) {
				human_la[i]=new JLabel(human[i]); 
				human_la[i].setHorizontalAlignment(JLabel.RIGHT);
				human_la[i].setSize(40, 15);
				human_la[i].setLocation(5+i*65, 110);
				final6.add(human_la[i]);
				
				human_ca[i]=new JLabel(c+"명");  //각 인원 들어감
				human_ca[i].setSize(20, 15);
				human_ca[i].setLocation(50+i*65, 110);
				final6.add(human_ca[i]);
			}
			for(int i=0;i<human_ca.length;i++) {
				human_ca[i].setText(customer_I[i]+"명");
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
			pay_ca.setText(customer_I[5]+"만원"); //비용합계 들어감
			final6.add(pay_ca);
			
			
			rese.setFont(fonts);
			rese.setSize(100, 30);
			rese.setLocation(90, 400);
			final6.add(rese);
			rese.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.remove(final6);
					
					Customer tem=new Customer(); //이름,비밀번호를 저장
					cst.add(tem);
					new page1();
				}
			});
		}
	}
	
	//예매 확인용 회원 확인
	class final1_2 {
		private JPanel final1_2=new JPanel(); //패널 생성
		
		private JLabel exp=new JLabel("예매자 정보를 입력해 주세요.");
		private JLabel name=new JLabel("이름: ");
		private JTextPane name_tp = new JTextPane();
		private JLabel passw=new JLabel("비밀 번호: ");
		private JTextPane passw_tp = new JTextPane();
		
		private String[] cus=new String[2];
		private int custi=0;
		
		private JButton next=new JButton("로그인");
		
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
			name_tp.setToolTipText("예매자 이름을 입력해 주세요.");
			final1_2.add(name_tp);
			
			passw.setFont(fonts);
			passw.setHorizontalAlignment(JLabel.RIGHT);
			passw.setSize(100,30);
			passw.setLocation(50,200);
			final1_2.add(passw);
			
			passw_tp.setFont(fonts);
			passw_tp.setSize(80,30);
			passw_tp.setLocation(150,200);
			passw_tp.setToolTipText("사용하실 비밀번호를 입력해 주세요.");
			final1_2.add(passw_tp);
			
			//다음 화면으로
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
						JOptionPane.showMessageDialog(null,"이름과 전화번호를 입력해 주세요.", null, JOptionPane.WARNING_MESSAGE);
					}
						
					else {
						String nameC=null;
						String passC=null;
						for(int i=0;i<cst.size();i++) {
							nameC=cst.get(i).name; //i번째 고객의 이름 저장
							passC=cst.get(i).passward; //i번째 고객의 비밀번호 저장
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
							JOptionPane.showMessageDialog(null,"해당하는 이름이 없거나, 비밀번호가 틀렸습니다.", null, JOptionPane.WARNING_MESSAGE);
						}
							
							
						
					}
						
				}
			});
		}
	}
	
	//예매 확인용 확인 페이지
	class final1_2_2 {
		private JPanel final1_2_2=new JPanel(); //패널 생성
		
		private String[] loc={" 출발지:","도착지:"};
		private JLabel[] loc_la=new JLabel[2];
		
		private JLabel[] loc_name=new JLabel[2]; //지역명 저장
		
		private String[] St1={"출발시간: ","총 인원: "};
		private JLabel[] St1_la=new JLabel[2];
		
		private JLabel[] St2_la=new JLabel[2]; //시간, 인원 저장
		
		private String[] human= {"성인:","청소년:","노인:","어린이:"};
		private JLabel[] human_la=new JLabel[4];
		private JLabel[] human_ca=new JLabel[4]; //각 값 저장
		
		private JLabel exp_seat=new JLabel("선택한 좌석");
		private JLabel[] seat_ch=new JLabel[4]; //선택한 좌석 저장
		
		private JTextPane Chseat = new JTextPane();
		
		private JLabel pay=new JLabel("요금합계: ");
		private JLabel pay_ca=new JLabel(); //합계요금 저장
		
		private JButton rese=new JButton("처음으로");
		
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
				
				loc_name[i]=new JLabel(); //지역명 들어감
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
				
				St2_la[i]=new JLabel(); //시간, 인원 들어감
				St2_la[i].setFont(fonts);
				St2_la[i].setSize(100, 30);
				St2_la[i].setLocation(90+i*110, 70);
				final1_2_2.add(St2_la[i]);
			}
			St2_la[0].setText(cst.get(K).time); //시간
			St2_la[1].setText(cst.get(K).sum+"명"); //인원
			
			for(int i=0;i<human_la.length;i++) {
				human_la[i]=new JLabel(human[i]); 
				human_la[i].setHorizontalAlignment(JLabel.RIGHT);
				human_la[i].setSize(40, 15);
				human_la[i].setLocation(5+i*65, 110);
				final1_2_2.add(human_la[i]);
				
				human_ca[i]=new JLabel(c+"명");  //각 인원 들어감
				human_ca[i].setSize(20, 15);
				human_ca[i].setLocation(50+i*65, 110);
				final1_2_2.add(human_ca[i]);
			}
			human_ca[0].setText(cst.get(K).adult+"명");
			human_ca[1].setText(cst.get(K).teenage+"명");
			human_ca[2].setText(cst.get(K).grand+"명");
			human_ca[3].setText(cst.get(K).child+"명");
			
			
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
			pay_ca.setText(cst.get(K).pay+"만원"); //비용합계 들어감
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
	
	//고객 저장
	class Customer {
		String name;
		String passward;
		String from; //출발지 저장
		String land; //도착지 저장
		String time; //출발 시간
		
		int adult; //성인 인원 수 (추후 [] 형태로?
		int teenage; //청소년
		int grand; //노인
		int child; //아이
		
		String seat1;
		String seat2;
		String seat3;
		String seat4;
		
		int sum; //총 인원
		int pay; //총 비용
		
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
			
			
			System.out.println("이름: "+name);
			System.out.println("비밀번호: "+passward);
			System.out.println("출발지: "+from);
			System.out.println("도착지: "+land);
			System.out.println("출발 시간: "+time);
			System.out.println("성인: "+adult+"명");
			System.out.println("청소년: "+teenage+"명");
			System.out.println("노인: "+grand+"명");
			System.out.println("어린이: "+child+"명");
			
			
			System.out.println("선택 좌석1: "+seat1);
			if(seat2!="") {
				System.out.println("선택 좌석2: "+seat2);
			}
			if(seat2!="") {
				System.out.println("선택 좌석3: "+seat3);
			}
			if(seat2!="") {
				System.out.println("선택 좌석4: "+seat4);
			}
			System.out.println("총 인원: "+sum+"명");
			System.out.println("총 비용: "+pay+"만원");
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		new Start();
	}
}
