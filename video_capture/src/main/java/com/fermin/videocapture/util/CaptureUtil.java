package com.fermin.videocapture.util;

import com.fermin.videocapture.bo.CaptureRequest;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaptureUtil {

    public static final String[] VIDEO_FILE_EXTENSION = {"mp4", "ts", "mov"};

    /**
     * 获取本地视频封面
     *
     * @param videoPath 视频path
     * @param request   封面保存请求参数
     */
    public static boolean getCover(String videoPath, CaptureRequest request) throws Exception {
        List<File> videos = getAllVideos(FileUtils.getFile(videoPath));
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        videos.forEach(v -> {
            try {
                request.setFilePath(v.getAbsolutePath());
                String filePath = v.getAbsolutePath();
                request.setFileName(filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf(".")));
                threadPool.submit(new Capture(FFmpegFrameGrabber.createDefault(new File(videoPath)), request));
            } catch (FrameGrabber.Exception e) {

            }
        });

        threadPool.shutdown();
        while (!threadPool.isTerminated()) {

        }

        return true;
    }

    private static List<File> getAllVideos(File file) {
        List<File> videos = new ArrayList();
        if (file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) {
                        return true;
                    }

                    String fileName = pathname.getName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0 && i < fileName.length() - 1) {
                        String desiredExtension = fileName.substring(i + 1).toLowerCase(Locale.ENGLISH);
                        for (String extension : VIDEO_FILE_EXTENSION) {
                            if (desiredExtension.equals(extension)) {
                                return true;
                            }
                        }
                    }

                    return false;
                }
            });

            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    videos.add(files[i]);
                } else {
                    videos.addAll(getAllVideos(files[i]));
                }
            }
        } else {
            videos.add(file);
        }
        return videos;
    }

    /**
     * 获取网络视频封面
     *
     * @param videoPath 网络视频path
     * @param savePath  封面保存path
     */
    public static void getCoverByNetwork(String videoPath, String savePath) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath);

    }

    static class Capture implements Runnable {
        private FFmpegFrameGrabber fFmpegFrameGrabber;
        private CaptureRequest captureRequest;

        public Capture(FFmpegFrameGrabber aDefault, CaptureRequest request) {
            this.fFmpegFrameGrabber = aDefault;
            this.captureRequest = request;
        }

        @Override
        public void run() {
            try {
                fFmpegFrameGrabber.start();

                int length = fFmpegFrameGrabber.getLengthInFrames();

                //截图的点
                Set<Integer> capturePoints = new HashSet<>();

                if (captureRequest.isTimeRandom()) {
                    //截图的数量
                    int count = Integer.parseInt(captureRequest.getCount());
                    if (count > 1) {
                        int gap = fFmpegFrameGrabber.getLengthInFrames() / count;
                        for (int i = 0; i < count; i++) {
                            int point = i * gap + new Random().nextInt(gap);
                            if (point > length) {
                                point = length;
                            }
                            capturePoints.add(point);
                        }
                    } else {
                        capturePoints.add(new Random().nextInt(length));
                    }

                } else {
                    // 此视频时长（s/秒）：
                    long duration = fFmpegFrameGrabber.getLengthInTime() / (1000 * 1000);
                }

                // 视频的视频
                List<Frame> frames = new ArrayList<>(capturePoints.size());
                int i = 0;
                while (i++ < length && capturePoints.size() > 0) {
                    Frame frame = fFmpegFrameGrabber.grabImage();
                    if (capturePoints.contains(i)) {
                        frames.add(frame);
                        capturePoints.remove(i);
                    }
                }

                writeImage(frames);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fFmpegFrameGrabber.stop();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void writeImage(List<Frame> frames) {

            try {
                Java2DFrameConverter converter = new Java2DFrameConverter();

                for (Frame frame : frames) {
                    BufferedImage bufferedImage = converter.getBufferedImage(frame);

                    if (!captureRequest.isOriginalSize()) {
                        int width = Integer.parseInt(captureRequest.getWidth()), height = Integer.parseInt(captureRequest.getHeight());
                        BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                        thumbnailImage.getGraphics().drawImage(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
                        bufferedImage = thumbnailImage;
                    }

                    File file = new File(captureRequest.getSavePath() + File.separator + captureRequest.getFileName() + File.separator + System.currentTimeMillis() + ".png");
                    ImageIO.write(bufferedImage, "png", file);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
