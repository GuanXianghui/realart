package com.realart.utils.qrcode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.realart.interfaces.QrCodeInterface;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;
import org.apache.commons.lang.StringUtils;

public class TwoDimensionCode {
	
	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * @param content �洢����
	 * @param imgPath ͼƬ·��
	 */
	public void encoderQRCode(String content, String imgPath) {
		this.encoderQRCode(content, imgPath, "png", 7);
	}
	
	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * @param content �洢����
	 * @param output �����
	 */
	public void encoderQRCode(String content, OutputStream output) {
		this.encoderQRCode(content, output, "png", 7);
	}

	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * @param content �洢����
	 * @param imgPath ͼƬ·��
	 * @param imgType ͼƬ����
	 */
	public void encoderQRCode(String content, String imgPath, String imgType) {
		this.encoderQRCode(content, imgPath, imgType, 7);
	}
	
	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * @param content �洢����
	 * @param output �����
	 * @param imgType ͼƬ����
	 */
	public void encoderQRCode(String content, OutputStream output, String imgType) {
		this.encoderQRCode(content, output, imgType, 7);
	}

	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * @param content �洢����
	 * @param imgPath ͼƬ·��
	 * @param imgType ͼƬ����
	 * @param size ��ά��ߴ�
	 */
	public void encoderQRCode(String content, String imgPath, String imgType, int size) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
			
			File imgFile = new File(imgPath);
			// ���ɶ�ά��QRCodeͼƬ
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * @param content �洢����
	 * @param output �����
	 * @param imgType ͼƬ����
	 * @param size ��ά��ߴ�
	 */
	public void encoderQRCode(String content, OutputStream output, String imgType, int size) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
			// ���ɶ�ά��QRCodeͼƬ
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ɶ�ά��(QRCode)ͼƬ�Ĺ�������
	 * @param content �洢����
	 * @param imgType ͼƬ����
	 * @param size ��ά��ߴ�
	 * @return
	 */
	private BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// �������ö�ά��ߴ磬ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��
			qrcodeHandler.setQrcodeVersion(size);
			// ������ݵ��ֽ����飬���ñ����ʽ
			byte[] contentBytes = content.getBytes("utf-8");
			// ͼƬ�ߴ�
			int imgSize = (67 + 12 * (size - 1))*10;
			bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
            //�������������ܱ��Բ����
            gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			// ���ñ�����ɫ
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// �趨ͼ����ɫ> BLACK
			gs.setColor(Color.BLACK);
			// ����ƫ�����������ÿ��ܵ��½�������
			int pixoff = 20;
			// �������> ��ά��
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                //�����ɫ��Բ��
//                for (int i = 0; i < codeOut.length; i++) {
//                    for (int j = 0; j < codeOut.length; j++) {
//                        if (codeOut[j][i]) {
//                            String[] colors = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
//                            String colorStr = "";
//                            for(int k=0;k<6;k++){
//                                colorStr += colors[(int)(Math.random() * 10)];
//                            }
//                            Color color = new Color(Integer.parseInt(colorStr,16));
//                            gs.setColor(color);
//                            gs.setColor(Color.BLACK);
//                            gs.drawRect(j * 30 + pixoff, i * 30 + pixoff, 30, 30);
//                            gs.setColor(color);
//                            gs.fillOval(j * 30 + pixoff, i * 30 + pixoff, 30, 30);
//                            gs.setColor(Color.BLACK);
//                            gs.drawLine(j * 30 + pixoff, i * 30 + pixoff, j * 30 + pixoff + 30, i * 30 + pixoff + 30);
//                            gs.drawLine(j * 30 + pixoff + 30, i * 30 + pixoff, j * 30 + pixoff, i * 30 + pixoff + 30);

//                            int x0 = j * 30 + pixoff;
//                            int y0 = i * 30 + pixoff;
//                            int w = 30;
//                            int[] xs = {x0+w/2,x0,x0+w,x0,x0+w};
//                            int[] ys = {y0,y0+w,y0+w/2,y0+w/2,y0+w};
//                            gs.fillPolygon(xs, ys, 5);
//                            gs.fillOval(x0+w/4,y0+w/4+2,w/2,w/2);
//                            gs.drawRect(x0,y0,w,w);
//                        }
//                    }
//                }
                //��Բ��
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillOval(j * 30 + pixoff, i * 30 + pixoff, 30, 30);
						}
					}
				}
                //����Բ�� ��ֱ������
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if(i <= codeOut.length-2){
                            if (codeOut[j][i] && codeOut[j][i+1]) {
                                gs.fillRect(j * 30 + pixoff, i * 30 + pixoff + 15, 30, 30);
                            }
                        }
                        if(j <= codeOut.length-2){
                            if (codeOut[j][i] && codeOut[j+1][i]) {
                                gs.fillRect(j * 30 + pixoff + 15, i * 30 + pixoff, 30, 30);
                            }
                        }
                    }
                }
                //����Բ�� ����ֱ������ ������Բ��
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        //���ǿհ�����
                        if(codeOut[j][i]){
                            continue;
                        }
                        if(j>=1 && i>=1){
                            //���Ͻ�
                            if(codeOut[j-1][i-1] && codeOut[j-1][i] && codeOut[j][i-1]){
                                gs.setColor(Color.BLACK);
                                gs.fillRect(j * 30 + pixoff, i * 30 + pixoff, 30/2, 30/2);
                                gs.setColor(Color.WHITE);
                                gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 90, 180);
                                gs.setColor(Color.BLACK);
                            }
                        }
                        if(j>=1 && i<=codeOut.length-2){
                            //���Ͻ�
                            if(codeOut[j-1][i+1] && codeOut[j-1][i] && codeOut[j][i+1]){
                                gs.setColor(Color.BLACK);
                                gs.fillRect(j * 30 + pixoff, i * 30 + pixoff + 30/2, 30/2, 30/2);
                                gs.setColor(Color.WHITE);
                                gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 180, 270);
                                gs.setColor(Color.BLACK);
                            }
                        }
                        if(j<=codeOut.length-2 && i>=1){
                            //���½�
                            if(codeOut[j+1][i-1] && codeOut[j+1][i] && codeOut[j][i-1]){
                                gs.setColor(Color.BLACK);
                                gs.fillRect(j * 30 + pixoff + 30/2, i * 30 + pixoff, 30/2, 30/2);
                                gs.setColor(Color.WHITE);
                                gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 180, 270);
                                gs.setColor(Color.BLACK);
                            }
                        }
                        if(j<=codeOut.length-2 && i<=codeOut.length-2){
                            //���½�
                            if(codeOut[j+1][i+1] && codeOut[j+1][i] && codeOut[j][i+1]){
                                gs.setColor(Color.BLACK);
                                gs.fillRect(j * 30 + pixoff + 30/2, i * 30 + pixoff + 30/2, 30/2, 30/2);
                                gs.setColor(Color.WHITE);
                                gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 270, 360);
                                gs.setColor(Color.BLACK);
                            }
                        }
                    }
                }
			} else {
				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
			}

            gs.setColor(Color.GREEN);
            gs.fillRoundRect((int)(imgSize*1.0/140*54), (int)(imgSize*1.0/140*54), (int)(imgSize*1.0/140*32), (int)(imgSize*1.0/140*32), 45, 45);

            Image img = ImageIO.read(new File("C:\\Users\\sky\\Desktop\\1.png"));//ʵ����һ��Image����
            gs.drawImage(img, (int)(imgSize*1.0/140*55), (int)(imgSize*1.0/140*55), (int)(imgSize*1.0/140*30), (int)(imgSize*1.0/140*30), null);

			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}
	
	/**
	 * ������ά�루QRCode��
	 * @param imgPath ͼƬ·��
	 * @return
	 */
	public String decoderQRCode(String imgPath) {
		// QRCode ��ά��ͼƬ���ļ�
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8"); 
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}
	
	/**
	 * ������ά�루QRCode��
	 * @param input ������
	 * @return
	 */
	public String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8"); 
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

    /**
     * ���ɶ�ά��(QRCode)ͼƬ�Ķ��Ʒ���
     * @param content �洢����
     * @param antiError ��ά���Ŵ��� ��ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
     * @param size ��ά��ߴ� ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��
     * @param bgColor ������ɫ
     * @param frontColor ǰ����ɫ
     * @param type ��̬ 1 Һ̬ 2 ֱ�� 3 Բ��
     * @param qrLogoFile logoͼƬ null�򲻴�logo
     * @param logoBorderType logo��Ե 1 �ޱ߿� 2 ֱ�� 3 Բ��
     * @param logoBorderColor logo��Ե��ɫ
     * @param imgRoute ����ͼƬ·��
     * @return
     */
    public void customQrCode(String content, char antiError, int size, Color bgColor, Color frontColor,
                             String type, File qrLogoFile, String logoBorderType, Color logoBorderColor,
                             String imgRoute) throws Exception {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            // ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
            qrcodeHandler.setQrcodeErrorCorrect(antiError);
            qrcodeHandler.setQrcodeEncodeMode('B');
            // �������ö�ά��ߴ磬ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��
            qrcodeHandler.setQrcodeVersion(size);
            // ������ݵ��ֽ����飬���ñ����ʽ
            byte[] contentBytes = content.getBytes("utf-8");
            // ͼƬ�ߴ�
            int imgSize = (67 + 12 * (size - 1))*10;
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
            // ���ñ�����ɫ
            gs.setBackground(bgColor);
            gs.clearRect(0, 0, imgSize, imgSize);

            // �趨ͼ����ɫ> BLACK
            gs.setColor(frontColor);
            // ����ƫ�����������ÿ��ܵ��½�������
            int pixoff = 20;
            // �������> ��ά��
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                if(StringUtils.equals("1", type)){//1 Һ̬
                    //��Բ��
                    for (int i = 0; i < codeOut.length; i++) {
                        for (int j = 0; j < codeOut.length; j++) {
                            if (codeOut[j][i]) {
                                gs.fillOval(j * 30 + pixoff, i * 30 + pixoff, 30, 30);
                            }
                        }
                    }
                    //����Բ�� ��ֱ������
                    for (int i = 0; i < codeOut.length; i++) {
                        for (int j = 0; j < codeOut.length; j++) {
                            if(i <= codeOut.length-2){
                                if (codeOut[j][i] && codeOut[j][i+1]) {
                                    gs.fillRect(j * 30 + pixoff, i * 30 + pixoff + 15, 30, 30);
                                }
                            }
                            if(j <= codeOut.length-2){
                                if (codeOut[j][i] && codeOut[j+1][i]) {
                                    gs.fillRect(j * 30 + pixoff + 15, i * 30 + pixoff, 30, 30);
                                }
                            }
                        }
                    }
                    //����Բ�� ����ֱ������ ������Բ��
                    for (int i = 0; i < codeOut.length; i++) {
                        for (int j = 0; j < codeOut.length; j++) {
                            //���ǿհ�����
                            if(codeOut[j][i]){
                                continue;
                            }
                            if(j>=1 && i>=1){
                                //���Ͻ�
                                if(codeOut[j-1][i-1] && codeOut[j-1][i] && codeOut[j][i-1]){
                                    gs.setColor(frontColor);
                                    gs.fillRect(j * 30 + pixoff, i * 30 + pixoff, 30/2, 30/2);
                                    gs.setColor(bgColor);
                                    gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 90, 180);
                                    gs.setColor(frontColor);
                                }
                            }
                            if(j>=1 && i<=codeOut.length-2){
                                //���Ͻ�
                                if(codeOut[j-1][i+1] && codeOut[j-1][i] && codeOut[j][i+1]){
                                    gs.setColor(frontColor);
                                    gs.fillRect(j * 30 + pixoff, i * 30 + pixoff + 30/2, 30/2, 30/2);
                                    gs.setColor(bgColor);
                                    gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 180, 270);
                                    gs.setColor(frontColor);
                                }
                            }
                            if(j<=codeOut.length-2 && i>=1){
                                //���½�
                                if(codeOut[j+1][i-1] && codeOut[j+1][i] && codeOut[j][i-1]){
                                    gs.setColor(frontColor);
                                    gs.fillRect(j * 30 + pixoff + 30/2, i * 30 + pixoff, 30/2, 30/2);
                                    gs.setColor(bgColor);
                                    gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 180, 270);
                                    gs.setColor(frontColor);
                                }
                            }
                            if(j<=codeOut.length-2 && i<=codeOut.length-2){
                                //���½�
                                if(codeOut[j+1][i+1] && codeOut[j+1][i] && codeOut[j][i+1]){
                                    gs.setColor(frontColor);
                                    gs.fillRect(j * 30 + pixoff + 30/2, i * 30 + pixoff + 30/2, 30/2, 30/2);
                                    gs.setColor(bgColor);
                                    gs.fillArc(j * 30 + pixoff, i * 30 + pixoff, 30, 30, 270, 360);
                                    gs.setColor(frontColor);
                                }
                            }
                        }
                    }
                } else if(StringUtils.equals("2", type)){//2 ֱ��
                    //��ֱ��
                    for (int i = 0; i < codeOut.length; i++) {
                        for (int j = 0; j < codeOut.length; j++) {
                            if (codeOut[j][i]) {
                                gs.fillRect(j * 30 + pixoff, i * 30 + pixoff, 30, 30);
                            }
                        }
                    }
                } else if(StringUtils.equals("3", type)){//3 Բ��
                    //��Բ��
                    for (int i = 0; i < codeOut.length; i++) {
                        for (int j = 0; j < codeOut.length; j++) {
                            if (codeOut[j][i]) {
                                gs.fillOval(j * 30 + pixoff, i * 30 + pixoff, 30, 30);
                            }
                        }
                    }
                }
            } else {
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }

            //logo�ǿ�
            if(null != qrLogoFile){
                /**
                 * logo��Ե 1 �ޱ߿� 2 ֱ�� 3 Բ��
                 */
                if(StringUtils.equals("1", logoBorderType)){//1 �ޱ߿�
                    //do nothing
                } else if(StringUtils.equals("2", logoBorderType)){//2 ֱ��
                    gs.setColor(logoBorderColor);
                    gs.fillRoundRect((int)(imgSize*1.0/140*54), (int)(imgSize*1.0/140*54), (int)(imgSize*1.0/140*32), (int)(imgSize*1.0/140*32), 0, 0);
                } else if(StringUtils.equals("3", logoBorderType)){//3 Բ��
                    gs.setColor(logoBorderColor);
                    gs.fillRoundRect((int)(imgSize*1.0/140*54), (int)(imgSize*1.0/140*54), (int)(imgSize*1.0/140*32), (int)(imgSize*1.0/140*32), 45, 45);
                }

                /**
                 * logoͼƬ null�򲻴�logo
                 */
                Image img = ImageIO.read(qrLogoFile);//ʵ����һ��Image����
                gs.drawImage(img, (int)(imgSize*1.0/140*55), (int)(imgSize*1.0/140*55), (int)(imgSize*1.0/140*30), (int)(imgSize*1.0/140*30), null);
            }

            gs.dispose();
            bufImg.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File imgFile = new File(imgRoute);
        // ���ɶ�ά��QRCodeͼƬ
        ImageIO.write(bufImg, "png", imgFile);
    }

    /**
     * main����
     * @param args
     */
	public static void main(String[] args) {
		String imgPath = "C:\\Users\\sky\\Desktop\\1.jpg";
		String content = QrCodeInterface.QR_CODE_URL_PREFIX + "12asdfbed";
        content = "http://192.168.1.182:200/";
        content = "http://www.suncare-sys.com:11000/";
        //���ɶ�ά��
		TwoDimensionCode handler = new TwoDimensionCode();
		handler.encoderQRCode(content, imgPath, "png", 3);
		System.out.println("========encoder success");

		//������ά��
		String decoderContent = handler.decoderQRCode(imgPath);
		System.out.println("����������£�");
		System.out.println(decoderContent);
		System.out.println("========decoder success!!!");
	}
}