package com.fermin.videocapture.util;

import com.fermin.videocapture.bo.CaptureRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaptureUtil {

    public static final String[] VIDEO_FILE_EXTENSION = {"mp4", "ts", "mov", "avi", "flv", "mkv", "rmvb"};

    static ExecutorService threadPool = Executors.newFixedThreadPool(100);

    /**
     * 获取本地视频封面
     *
     * @param videoPath 视频path
     * @param request   封面保存请求参数
     */
    public static boolean getCover(String videoPath, CaptureRequest request) throws Exception {
        List<File> videos = getAllVideos(FileUtils.getFile(videoPath));

        System.out.println(">>>>>>>>>>>>>>list files>>>>>>>>>>>>>>");
        videos.stream().forEach(e -> {
            System.out.println(e.getAbsolutePath());
        });

        CountDownLatch countDownLatch = new CountDownLatch(videos.size());

        videos.forEach(v -> {
            request.setFilePath(v.getAbsolutePath());
            String filePath = v.getAbsolutePath();
            request.setFileName(filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf(".")));
            threadPool.submit(new Capture(request, countDownLatch));
        });

        countDownLatch.await();
        System.out.println(">>>>>>>>>>>>>>task finished>>>>>>>>>>>>>>");

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
        private CountDownLatch countDownLatch;

        public Capture(CaptureRequest request, CountDownLatch countDownLatch) {
            try {
                this.fFmpegFrameGrabber = FFmpegFrameGrabber.createDefault(new File(request.getFilePath()));
                this.countDownLatch = countDownLatch;
                this.captureRequest = request;
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println(">>>>>>>>>>>>>>" + captureRequest.getFilePath() + ">>>>>>>>>>>>>> start..." + Thread.currentThread().getName());

            try {
                fFmpegFrameGrabber.start();

                int length = fFmpegFrameGrabber.getLengthInFrames();


                // 视频截取
                List<Frame> frames = new ArrayList();

                if (captureRequest.isTimeRandom()) {
                    //截图的数量
                    int count = Integer.parseInt(captureRequest.getCount());
                    //截图的点
                    List<Integer> capturePoints = new ArrayList<>(count);

                    int gap = fFmpegFrameGrabber.getLengthInFrames() / count;
                    for (int i = 0; i < count; i++) {
                        int point = i * gap + new Random().nextInt(gap);
                        if (point > length) {
                            point = length;
                        }
                        capturePoints.add(point);
                    }

                    System.out.println(">>>>>>>>>>>>>>" + captureRequest.getFilePath() + ">>>>>>>>>>>>>> capture frames..." + Thread.currentThread().getName());
                    capturePoints.stream().forEach(e -> System.out.print(e + " "));
                    System.out.println("");

                    for (int i = 0; i < capturePoints.size(); i++) {
                        fFmpegFrameGrabber.setVideoFrameNumber(capturePoints.get(i));
                        /*
                        Each call to grab stores the new image in the memory address for the previously returned frame.
                        grabber.grab() == grabber.grab()
                        This means that if you need to cache images returned from grab you should Frame.clone() the returned frame as the next call to grab will overwrite your existing image's memory.
                        Why?
                        Using this method instead of allocating a new buffer every time a frame is grabbed improves performance by reducing the frequency of garbage collections. Almost no additional heap space is typically allocated per frame.
                        */
                        frames.add(fFmpegFrameGrabber.grabImage().clone());

                    }

                } else {
                    // 此视频时长（s/秒）：
                    long duration = fFmpegFrameGrabber.getLengthInTime();
                    String time = captureRequest.getCaptureTime();
                    String[] timemarks = StringUtils.split(time, ":");
                    long timestamp = (Long.parseLong(timemarks[0]) * 3600 + Long.parseLong(timemarks[1]) * 60 + Long.parseLong(timemarks[2])) * 1000000l;

                    System.out.println(">>>>>>>>>>>>>>" + captureRequest.getFilePath() + ">>>>>>>>>>>>>> capture time..." + Thread.currentThread().getName());
                    System.out.println(">>>>>>>>>>>>>>" + time + ">>>>>>>>>>>>>> timestamp>>>>>" + timestamp + ">>>>" + Thread.currentThread().getName());

                    if (duration < timestamp) {
                        System.out.println(">>>>>>>>>>>>>>" + captureRequest.getFilePath() + "截取视频时间超过视频时长(S)" + duration / (1000 * 1000l));
                        return;
                    }
                    fFmpegFrameGrabber.setTimestamp(timestamp);
                    frames.add(fFmpegFrameGrabber.grabImage().clone());
                }

                writeImage(frames);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.countDownLatch.countDown();
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

                for (int i = 0; i < frames.size(); i++) {
                    Frame frame = frames.get(i);
                    BufferedImage bufferedImage = converter.getBufferedImage(frame);

                    if (!captureRequest.isOriginalSize()) {
                        int width = Integer.parseInt(captureRequest.getWidth()), height = Integer.parseInt(captureRequest.getHeight());
                        BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                        thumbnailImage.getGraphics().drawImage(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
                        bufferedImage = thumbnailImage;
                    }

                    String savePath = captureRequest.getSavePath();
                    if (captureRequest.isCreateFolderByName()) {
                        File file1 = new File(savePath + File.separator + captureRequest.getFileName());
                        if (!file1.exists()) {
                            FileUtils.forceMkdir(file1);
                        }
                        savePath = file1.getAbsolutePath();
                    }

                    File file = new File(savePath + File.separator + captureRequest.getFileName() + "_" + i + ".png");
                    ImageIO.write(bufferedImage, "png", file);

                    System.out.println(">>>>>>>>>>>>>>" + file.getAbsolutePath() + ">>>>>>>>>>>>>> capture finished..." + Thread.currentThread().getName());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
