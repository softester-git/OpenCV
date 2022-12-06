import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;

public class OpenCV extends JFrame {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Version: " + Core.VERSION);}
    public static void main(String[] args){
        JFrame window = new JFrame("Window:");
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Mat img = Imgcodecs.imread("src" + File.separator + "butterfly.png");
        Mat imgEmpty =  new Mat(img.size(), img.type());
        Mat kernel = new Mat (20,20, CvType.CV_8UC1, new Scalar(1.0));

        Imgproc.Canny(img, imgEmpty, 2,2);

        Imgcodecs.imwrite("src" + File.separator + "butterfly_canny.png", img);

        /*
        MatOfByte bufImg = new MatOfByte();
        Imgcodecs.imencode(".png", imgEmpty, bufImg);
        ImageIcon icImg = new ImageIcon(buf.toArray());
        screen.setIcon(icImg);
        window.getContentPane().add(screen);
        window.pack();
        */


        // Video

        VideoCapture cap = new VideoCapture(1);
        VideoWriter writer = new VideoWriter("src" + File.separator + "webcam.mpeg", VideoWriter.fourcc('m', 'j', 'p', 'g'), 30, new Size(640, 480));

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();
        ImageIcon ic;

        while (cap.grab()) {
            cap.read(frame);
            writer.write(frame);

            Imgcodecs.imencode(".png", frame, buf);

            ic = new ImageIcon(buf.toArray());
            screen.setIcon(ic);
            window.setContentPane(screen);
            window.pack();
        }
        cap.release();
        writer.release();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
}

