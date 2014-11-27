package com.gabilheri.githubviewer.utils;

import com.gabilheri.githubviewer.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/26/14.
 */
public class FileUtils {


    public static int getFileDrawable(FileType fileType) {

        switch (fileType) {
            case JAVA:
                return R.drawable.ic_java;
            case C:
                return R.drawable.ic_c;
            case RUBY:
                return R.drawable.ic_ruby;
            case TEXT:
                return R.drawable.ic_txt;
            case PYTHON:
                return R.drawable.ic_python;
            case CPP:
                return R.drawable.ic_cpp;
            case JS:
                return R.drawable.ic_js;
            case CSS:
                return R.drawable.ic_css;
            case DIR:
                return R.drawable.ic_folder;
            default:
                return R.drawable.ic_file_default;
        }
    }

    public static FileType getFileType(String fileName) {
        switch (getFileExtension(fileName)) {
            case "css":
                return FileType.CSS;
            case "java":
                return FileType.JAVA;
            case "c":
                return FileType.C;
            case "cpp":
                return FileType.CPP;
            case "html":
                return FileType.HTML;
            case "xml":
                return FileType.XML;
            case "text":
                return FileType.TEXT;
            case "rb":
                return FileType.RUBY;
            case "js":
                return FileType.JS;
            case "py":
                return FileType.PYTHON;
            case "pdf":
                return FileType.PDF;
            case "png":
                return FileType.PNG;
            case "jpeg":
            case "jpg":
                return FileType.JPEG;
            default:
                return FileType.DEFAULT;
        }


    }

    public static String getFileExtension(String fileName) {

        String[] ext =  fileName.split("\\.(?=[^\\.]+$)");

        return (ext.length > 1 ? ext[1] : ext[0]).toLowerCase();


    }
}
